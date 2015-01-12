package co.uk.mcb.betslip

import java.math.BigDecimal;
import java.util.List;


abstract class AbstractBetslip {
	protected List<Selection> selections;
	protected List<BettingOption> options;
	protected Map accumulators = [
		'Singles':1,
		'Doubles':2,
		'Trebles':3
	]


	AbstractBetslip(List<Selection> selections, List<BettingOption> options) {
		assert selections
		this.options = options
		this.selections = selections
	}

	protected BettingOption findBo(String name) {
		options.find {it.name == name}
	}
	
	abstract BigDecimal calculate()

	/**
	 * assert pairs(['A', 'B', 'C']) == [['A', 'B'], ['A', 'C'], ['B', 'C']]
	 * assert pairs(['A', 'B', 'C', 'D']) == [['A', 'B'], ['A', 'C'], ['A', 'D'], ['B', 'C'], ['B', 'D'], ['C', 'D']]
	 */
	def pairs(def elements) {
		return elements.tail().collect { [elements.head(), it]} + (elements.size() > 1 ? pairs(elements.tail()) : [])
	}

	/**
	 * A Single is one bet on one selection. Your selection must be successful to have a return.
	 *
	 * sel: A
	 * odds: 10.00
	 * stake: 3.00
	 * potential return: 30.00
	 * (odds * stake)
	 * (A * stake)
	 */
	BigDecimal calcSingle() {
		BigDecimal sum = 0
		selections?.each {
			sum += (it.odds * it.stake)
		}
		sum
	}

	/**
	 * sel: A, B
	 * odds: 2.00, 5.00
	 * potential return: 10.00
	 * (A * B * stake)
	 *
	 * harom (negy...nyolc) selectionnel ugyanez
	 * sel: A, B, C
	 * odds: 2.00, 5.00, 3.00
	 * stake: 1.00
	 * returns: (A x B x C x stake)
	 * ezt 'treble'-nek hivjak
	 *
	 * van meg: four-fold, five-fold, six-fold, seven-fold, eight-fold - ez mind ugyanaz
	 * egy gyujto nev az 'accumulator' bet
	 * tehat ha az osszes selection-t beszamitod, akkor accumulator betet csinalsz (tehat a fenti ket pelda is az)
	 */
	BigDecimal calcAccumulator() {
		if (!selections || selections.size == 1 || !options) {
			return 0
		}
		BettingOption bo = options.find() { accumulators[it.name] == selections.size }
		if (!bo) {
			return 0
		}
		BigDecimal prod = bo.stake
		selections?.each { prod *= it.odds }
		prod
	}


	/**
	 * A Double is one bet on two selections in different events. Both selections must be successful to have a return.
	 *
	 * sel: A, B, C
	 * type: 'doubles'
	 * returns: (A x B x stake) + (A x C x stake) + (B x C x stake)
	 * ami: ( (A x B) + (A x C) + (B x C)) x stake
	 *
	 * sel: A, B, C, D
	 * type: doubles
	 * returns: (AB + AC + AD + BC + BD + CD) x stake
	 */
	BigDecimal calcDouble() {
		BettingOption doubleOption = options.find() { it.name == 'Doubles' }
		if (!doubleOption) {
			return 0
		}

		BigDecimal sum = 0
		pairs(selections).each {
			def (a, b) = it
			sum += (a.odds * b.odds)
		}
		sum * doubleOption.stake
	}
}
