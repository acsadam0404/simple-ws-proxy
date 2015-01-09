package co.uk.mcb.math

import static org.junit.Assert.*

import org.junit.Test

public class BigDecimalRoundingTest {
	static {
		BigDecimal.mixin BigDecimalRounding
	}
	
	@Test
	void test() {
		assert (new BigDecimal("1.27")) == (new BigDecimal("1.2686589").round(2))
		assert (1.2686589).round(2) == 1.27
		assert (1.2334695).round(3) == 1.233
		assert (1.2334695).round() == 1.23
	}
}
