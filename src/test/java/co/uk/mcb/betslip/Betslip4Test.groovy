package co.uk.mcb.betslip


import org.junit.Test;
import co.uk.mcb.betslip.Selection as S
import co.uk.mcb.betslip.BettingOption as BO

import co.uk.mcb.math.BigDecimalRounding

class Betslip4Test {

	Betslip4Test() {
		BigDecimal.mixin BigDecimalRounding
	}

	@Test(expected = AssertionError.class)
	void test() {
		new Betslip4(null, null).calculate()
	}

	@Test(expected = AssertionError.class)
	void test2() {
		new Betslip4([new S(odds: 4.50, stake : 2)], [new BettingOption()])
	}


	@Test
	void calculate() {
		assert 35.00 == new Betslip3([
			new S(odds: 3.30, stake : 3),
			new S(odds: 3.55, stake : 2),
			new S(odds: 4.50, stake : 4)
		], []).calculate()

	}
}
