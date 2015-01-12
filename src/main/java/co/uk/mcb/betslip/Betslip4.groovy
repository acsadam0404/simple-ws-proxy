package co.uk.mcb.betslip

import java.util.List;

class Betslip4 extends AbstractBetslip{
	Betslip4(List<Selection> selections, List<BettingOption> options) {
		super(selections, options)

		assert selections.size() == 4
	}

	/**
	 * Singles	4at		 
	 Doubles	6at		 
	 Trebles	4at		 
	 Accumulator	1at		 
	 Yankee	11at		 
	 Lucky 15	15at
	 */
	@Override
	BigDecimal calculate() {
		return null;
	}
}
