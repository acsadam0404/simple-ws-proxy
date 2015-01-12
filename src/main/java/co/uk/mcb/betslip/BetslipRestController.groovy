package co.uk.mcb.betslip;

import groovy.transform.PackageScope

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

import co.uk.mcb.math.BigDecimalRounding

@RestController("/betslip")
public class BetslipRestController {
	BetslipRestController() {
		BigDecimal.mixin BigDecimalRounding
	}
	

	/**
	 * bemeno adatok: selections(id, odds, stake), bettingoptions(name, stakes)
	 * kijovo adat mondjuk legyen akkor a potential returns
	 */
	@RequestMapping("/returns")
	@ResponseBody
	BigDecimal calculatePotentialReturns(List<Selection> selections, List<BettingOption> options) {
		if (!selections) {
			return 0
		}
		BigDecimal sum = 0
		sum += calcSingle(selections)
		options?.each {
			sum += calcAccumulator(selections, it)
		}
		options?.each {
			sum += calcSpecial(selections, it)
		}
		sum.round()
	}




	BigDecimal calcSpecial(List<Selection> selections, BettingOption bo) {
		if (!selections || bo.number == selections.size()) {
			return 0
		}

		def calculators = [
			2:calcDouble(selections, bo),
			3:calcTrixie(selections, bo)
		]

		calculators[bo.number]
	}

}
