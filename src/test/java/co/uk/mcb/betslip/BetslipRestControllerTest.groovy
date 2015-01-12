package co.uk.mcb.betslip

import org.junit.Test

import co.uk.mcb.betslip.Selection as S
import co.uk.mcb.betslip.BettingOption as BO

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
		
		assert 30 == brc.calcSingle([new S(odds: 10, stake: 3)])
		assert 48.6030 == brc.calcSingle([
			new S(id: "", odds: 4.10, stake: 2.43),
			new S(id: "", odds: 2, stake: 1.32),
			new S(id: "", odds: 7.20, stake: 5)
		])
	}
	
	@Test
	void pairs() {
		assert brc.pairs(['A', 'B', 'C']) == [['A', 'B'], ['A', 'C'], ['B', 'C']]
		assert brc.pairs(['A', 'B', 'C', 'D']) == [['A', 'B'], ['A', 'C'], ['A', 'D'], ['B', 'C'], ['B', 'D'], ['C', 'D']]
	}


	@Test
	void calcAccumulator() {
		assert 0 == brc.calcAccumulator(null, null)
		assert 0 == brc.calcAccumulator([], null)
		
		/* no stake on bettingoption */
		assert 0 == brc.calcAccumulator([
			new S(odds: 2.00),
			new S(odds: 5.00)
		], new BO(number: 2))
		
		/* two Ss but not a double */
		assert 0 == brc.calcAccumulator([
			new S(odds: 2.00),
			new S(odds: 5.00)
		], new BO(number: 1, stake: 1))
		
		/* singles shouldnt be calculated */
		assert 0 == brc.calcAccumulator([
			new S(odds: 2.00)
		], new BO(number: 2, stake: 1))
		
		
		assert 10 == brc.calcAccumulator([
			new S(odds: 2.00),
			new S(odds: 5.00)
		], new BO(number: 2, stake: 1))

		assert 30 == brc.calcAccumulator([
			new S(odds: 2.00),
			new S(odds: 5.00)
		], new BO(number: 2, stake: 3))

		
		assert 437.47 == brc.calcAccumulator([
			new S(odds: 2.06),
			new S(odds: 3.70),
			new S(odds: 3.15),
			new S(odds: 1.47),
			new S(odds: 1.85),
			new S(odds: 3.35)
		], new BO(stake: 2, number : 6)).round(2)
		
	}
	
	@Test
	void calcSpecial() {
		assert 0 == brc.calcSpecial([
			new S(odds: 2.00),
			new S(odds: 5.00)
		], new BO(number: 2, stake: 3))
	}
	
	
	@Test
	void calcDouble() {
		assert 51.8060 == brc.calcDouble([
			new S(odds: 2.08),
			new S(odds: 3.70),
			new S(odds: 3.15)
		], new BO(stake: 2, number : 2))
		
		assert 183.97 == brc.calcDouble([
			new S(odds: 2.08),
			new S(odds: 3.70),
			new S(odds: 7.40),
			new S(odds: 3.15)
		], new BO(stake: 2, number : 2))
	}
	
	
	@Test
	void calcTrixie() {
		assert 105.35 == brc.calcTrixie([
			new S(odds: 2.04),
			new S(odds: 3.75),
			new S(odds: 3.35)
		], new BO(stake: 2, number : 3)).round()
		
	}
	
	
}
