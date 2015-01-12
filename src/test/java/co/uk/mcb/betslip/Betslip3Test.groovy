package co.uk.mcb.betslip

import org.junit.Test;
import co.uk.mcb.betslip.Selection as S
import co.uk.mcb.betslip.BettingOption as BO

import co.uk.mcb.math.BigDecimalRounding

class Betslip3Test {

	Betslip3Test() {
		BigDecimal.mixin BigDecimalRounding
	}

	@Test(expected = AssertionError.class)
	void test() {
		new Betslip3(null, null).calculate()
	}

	@Test(expected = AssertionError.class)
	void test2() {
		new Betslip3([new S(odds: 4.50, stake : 2)], [new BettingOption()])
	}


	@Test
	void calculate() {
		assert 35.00 == new Betslip3([
			new S(odds: 3.30, stake : 3),
			new S(odds: 3.55, stake : 2),
			new S(odds: 4.50, stake : 4)
		], []).calculate()

		assert 254.58 == new Betslip3([
			new S(odds: 3.30, stake : 1),
			new S(odds: 3.55, stake : 1),
			new S(odds: 4.50, stake : 1)
		], [new BO(stake:2, name:'Doubles'), new BO(stake:3, name:'Trebles')]).calculate().round()

		assert 1449.16 == new Betslip3([
			new S(odds: 3.55, stake : 2),
			new S(odds: 3.30, stake : 2),
			new S(odds: 4.40, stake : 2)
		], [
			new BO(stake:2, name:'Singles'),
			new BO(stake:3, name:'Doubles'),
			new BO(stake:4, name:'Trebles'),
			new BO(stake:5, name:'Trixie'),
			new BO(stake:6, name:'Patent')
		]).calculate().round()
	}

	@Test
	void calcTrixie() {
		assert 186.80 == new Betslip3([new S(odds: 3.30), new S(odds: 3.55), new S(odds: 4.40)], [new BO(stake:2, name:'Trixie')]).calcTrixie().round()
	}


	@Test
	void calcPatent() {
		assert 209.30 == new Betslip3([new S(odds: 3.55), new S(odds: 3.30), new S(odds: 4.40)], [new BO(stake:2, name:'Patent')]).calcPatent().round()
	}
}
