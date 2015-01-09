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

	/**
	 * sel: A
	 * odds: 10.00
	 * stake: 3.00
	 * potential return: 30.00
	 * (odds * stake)
	 * (A * stake) 
	 */
	BigDecimal calcSingle(List<Selection> selections) {
		BigDecimal sum = 0
		selections?.each {
			sum += (it.odds * it.stake)
		}
		sum
	}

	/**
	 * sel: A, B
	 * odds: 2.00, 5.00
	 * potential return: 10.00
	 * (A * B * stake)
	 * 
	 * harom (negy...nyolc) selectionnel ugyanez
	 * sel: A, B, C
	 * odds: 2.00, 5.00, 3.00
	 * stake: 1.00
	 * returns: (A x B x C x stake)
	 * ezt 'treble'-nek hivjak
	 * 
	 * van meg: four-fold, five-fold, six-fold, seven-fold, eight-fold - ez mind ugyanaz
	 * egy gyujto nev az 'accumulator' bet
	 * tehat ha az osszes selection-t beszamitod, akkor accumulator betet csinalsz (tehat a fenti ket pelda is az)
	 */
	BigDecimal calcAccumulator(List<Selection> selections, BettingOption bo) {
		if (!selections || selections.size == 1 || !bo || !bo.stake || selections.size() != bo.number) {
			return 0
		}
		BigDecimal prod = bo.stake
		selections?.each { prod *= it.odds }
		prod
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

	/**
	 * sel: A, B, C
	 * type: 'doubles'
	 * returns: (A x B x stake) + (A x C x stake) + (B x C x stake) + stake
	 * ami: ( (A x B) + (A x C) + (B x C) + 1 ) x stake
	 * 
	 * sel: A, B, C, D
	 * type: doubles
	 * returns: (AB + AC + AD + BC + BD + CD + 1) x stake
	 */
	BigDecimal calcDouble(List<Selection> selections, BettingOption bo) {
		BigDecimal sum = 0
		selections?.each {
			
		}
		sum
	}

	BigDecimal calcTrixie(List<Selection> selections, BettingOption bo) {
		0
	}
}
