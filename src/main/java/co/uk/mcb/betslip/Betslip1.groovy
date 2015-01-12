package co.uk.mcb.betslip

import java.util.List;

class Betslip1 extends AbstractBetslip{
	Betslip1(List<Selection> selections, List<BettingOption> options) {
		super(selections, options)

		assert selections.size() == 1
	}

	/**
	 * Singles	1at
	 */
	@Override
	BigDecimal calculate() {
		selections[0].odds * selections[0].stake
	}
}
