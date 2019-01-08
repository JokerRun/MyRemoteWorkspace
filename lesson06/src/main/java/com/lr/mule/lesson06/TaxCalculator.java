package com.lr.mule.lesson06;

import java.math.BigDecimal;

import org.mule.api.annotations.param.InboundHeaders;
import org.mule.api.annotations.param.Payload;

public class TaxCalculator {
	BigDecimal calculateTaxForState(String state, BigDecimal value) {
		// dummy
		return BigDecimal.ZERO;
	}
	public BigDecimal calculateTax(@InboundHeaders("state") String state,@Payload BigDecimal cartValue) {
		return calculateTaxForState(state, cartValue);
		
	}

}
