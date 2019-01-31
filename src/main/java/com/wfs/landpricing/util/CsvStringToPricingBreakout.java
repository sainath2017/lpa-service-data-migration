package com.wfs.landpricing.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.wfs.landpricing.model.enums.PricingBreakout;

public class CsvStringToPricingBreakout extends AbstractBeanField {

	private static final Logger log = LoggerFactory.getLogger(CsvStringToPricingBreakout.class);

	@Override
	protected Object convert(String value) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
		PricingBreakout type = PricingBreakout.DELIVERED_PRICE_ONLY;
		try {

			if (StringUtils.containsIgnoreCase(value, "Freight") && StringUtils.containsIgnoreCase(value, "Tax")) {
				type = PricingBreakout.FUEL_PRICE_FREIGHT_TAX;
			}			
			else if (StringUtils.containsIgnoreCase(value, "Freight")) {
				type = PricingBreakout.FUEL_PRICE_FREIGHT;
			}
			else if (StringUtils.containsIgnoreCase(value, "Tax")) {
				type = PricingBreakout.FUEL_PRICE_FREIGHT;
			}
			

		} catch (Exception e) {
			log.error("error parsing customer type for {}", value);
			log.error("error parsing customer type ", e);
		}
		return type;
	}
}
