package co.uk.mcb.betslip

import org.junit.Test


class BetslipRestControllerTest {
	BetslipRestController brc  = new BetslipRestController()

	@Test
	void calcPotentialReturns() {
		assert 0 == brc.calculatePotentialReturns(null, null)
		assert 0 == brc.calculatePotentialReturns([], null)
	}
	
	@Test
	void calcSingle() {
		assert 0 == brc.calcSingle()
		assert 0 == brc.calcSingle(null)
		assert 0 == brc.calcSingle([])
		
		assert 30 == brc.calcSingle([new Selection(odds: 10, stake: 3)])
		assert 48.6030 == brc.calcSingle([
			new Selection(id: "", odds: 4.10, stake: 2.43),
			new Selection(id: "", odds: 2, stake: 1.32),
			new Selection(id: "", odds: 7.20, stake: 5)
		])
	}
	
	@Test
	void permutations() {
		def ar = ["A", "B", "C"]
		println "permutations"
		ar.eachPermutation {
			print it
		}
		println "combinations"
		ar.eachCombination {
			print it
		}
		println "permutations()"
		ar.permutations().each {
			print it
		}
		
	}



	@Test
	void calcAccumulator() {
		assert 0 == brc.calcAccumulator(null, null)
		assert 0 == brc.calcAccumulator([], null)
		
		/* no stake on bettingoption */
		assert 0 == brc.calcAccumulator([
			new Selection(odds: 2.00),
			new Selection(odds: 5.00)
		], new BettingOption(number: 2))
		
		/* two selections but not a double */
		assert 0 == brc.calcAccumulator([
			new Selection(odds: 2.00),
			new Selection(odds: 5.00)
		], new BettingOption(number: 1, stake: 1))
		
		/* singles shouldnt be calculated */
		assert 0 == brc.calcAccumulator([
			new Selection(odds: 2.00)
		], new BettingOption(number: 2, stake: 1))
		
		
		assert 10 == brc.calcAccumulator([
			new Selection(odds: 2.00),
			new Selection(odds: 5.00)
		], new BettingOption(number: 2, stake: 1))

		assert 30 == brc.calcAccumulator([
			new Selection(odds: 2.00),
			new Selection(odds: 5.00)
		], new BettingOption(number: 2, stake: 3))

	}
	
	@Test
	void calcSpecial() {
		assert 0 == brc.calcSpecial([
			new Selection(odds: 2.00),
			new Selection(odds: 5.00)
		], new BettingOption(number: 2, stake: 3))
	}
}
