package com.wfs.landpricing.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.wfs.landpricing.model.enums.PnrType;

public class CSvStringToPnrType extends AbstractBeanField {

	private static final Logger log = LoggerFactory.getLogger(CSvStringToPnrType.class);

	@Override
	protected Object convert(String value) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
		PnrType type = PnrType.DELIVERED_PRICE_ONLY;
		try {

			if (StringUtils.containsIgnoreCase(value, "Terminal")) {
				type = PnrType.DELIVERED_PRICE_BY_TERMINAL;
			}
			else if (StringUtils.containsIgnoreCase(value, "Retail")) {
				type = PnrType.RETAIL;
			}
			

		} catch (Exception e) {
			log.error("error parsing customer type for {}", value);
			log.error("error parsing customer type ", e);
		}
		return type;
	}
}
