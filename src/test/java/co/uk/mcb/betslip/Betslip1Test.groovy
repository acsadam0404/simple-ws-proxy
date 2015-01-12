package co.uk.mcb.betslip;

import static org.junit.Assert.*;

import org.junit.Test;

import co.uk.mcb.betslip.Selection as S
import co.uk.mcb.betslip.BettingOption as BO

class Betslip1Test {

	@Test(expected = AssertionError.class)
	void test() {
		new Betslip1(null, null).calculate()
	}

	@Test
	void calculate() {
		assert 9 == new Betslip1([new S(odds: 4.50, stake : 2)], [new BettingOption()]).calculate()
	}
}
