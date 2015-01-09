package co.uk.mcb.betslip

import groovy.transform.Canonical
import groovy.transform.Immutable

/**
 * TODO all fields should be mandatory
 *
 */
@Immutable
class Selection {
	String id
	BigDecimal odds
	BigDecimal stake
	
	@Override
	String toString() {
		"$id odds=$odds stake=$stake"
	}
}
