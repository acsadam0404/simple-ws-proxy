package co.uk.mcb.betslip

import org.junit.Test;
import co.uk.mcb.betslip.Selection as S
import co.uk.mcb.betslip.BettingOption as BO

class Betslip2Test {

	@Test(expected = AssertionError.class)
	void test() {
		new Betslip2(null, null).calculate()
		
	}
	
	@Test(expected = AssertionError.class)
	void test2() {
		new Betslip2([new S(odds: 4.50, stake : 2)], [new BettingOption()])
	}

	
	@Test
	void calculate() {
		assert 81.93 == new Betslip2([new S(odds: 4.40, stake : 2), new S(odds: 3.55, stake : 3)], [new BettingOption(4, "Doubles")]).calculate()
	}
}
