package co.uk.mcb.math

class BigDecimalRounding {
	BigDecimal round(int n) {
		return setScale(n, BigDecimal.ROUND_HALF_UP);
	}
	
	BigDecimal round() {
		round(2)
	}
}
