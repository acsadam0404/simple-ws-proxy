package co.uk.mcb.betslip

import groovy.transform.Immutable

/**
 * number should be mandatory, shake is not
 */
@Immutable
class BettingOption {
	BigDecimal stake
	int number
}
