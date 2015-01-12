package co.uk.mcb.betslip


class Betslip3 extends AbstractBetslip{
	Betslip3(List<Selection> selections, List<BettingOption> options) {
		super(selections, options)

		assert selections.size() == 3
	}

	/**
	 * singles	3at		 
	 * Doubles	3at		 
	 * Trebles	1at		 
	 * Trixie	4at		 
	 * Patent
	 */
	@Override
	BigDecimal calculate() {
		BigDecimal sum = 0
		sum += calcSingle()
		//trebles
		sum += calcAccumulator()
		sum += calcDouble()
		sum += calcTrixie()
		sum += calcPatent()

		return sum

	}

	/**
	 * TODO ez nem túlbonyolított? nézd a patentet
	 * A Trixie consists of four bets on three selections in different events i.e. 3 doubles and 1 treble. Two or more selections must be successful to have a return. A €1/£1 trixie costs €4/£4.
	 * type: trixie
	 * returns: (AB + AC + BC + ABC) x stake
	 */
	BigDecimal calcTrixie() {
		BigDecimal sum = 0
		BettingOption bo = findBo('Trixie')
		if (bo) {
			pairs(selections).each {
				def (a, b) = it
				sum += (a.odds * b.odds)
			}

			BigDecimal product = 1
			selections.each { product *= it.odds }
			sum += product


			sum *= bo.stake
		}
		return sum
	}

	/**
	 * sel: A, B, C
	 type: patent
	 returns: (A + B + C + AB + AC + BC + ABC) x stake
	 azaz: 3 single + trixie
	 * @return
	 */
	BigDecimal calcPatent() {
		BettingOption bo = findBo("Patent")
		if (!bo) {
			return 0
		}
		def a = selections[0].odds
		def b = selections[1].odds
		def c = selections[2].odds
		return (a + b + c + a*b + a*c+ b*c+a*b*c) * bo.stake
	}
}

