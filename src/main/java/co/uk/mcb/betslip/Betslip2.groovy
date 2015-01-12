package co.uk.mcb.betslip

import java.math.BigDecimal;
import java.util.List;

class Betslip2 extends AbstractBetslip{
	Betslip2(List<Selection> selections, List<BettingOption> options) {
		super(selections, options)

		assert selections.size() == 2
	}

	/**
	 * Singles	2at		 
	 * Doubles	1at	
	 */
	@Override
	BigDecimal calculate() {
		BigDecimal sum = calcSingle()
		sum += calcAccumulator()
	}
}
