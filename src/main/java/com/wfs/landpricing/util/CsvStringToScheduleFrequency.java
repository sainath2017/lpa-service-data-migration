package com.wfs.landpricing.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.wfs.landpricing.model.enums.ScheduleFrequency;

public class CsvStringToScheduleFrequency extends AbstractBeanField {

	private static final Logger log = LoggerFactory.getLogger(CsvStringToScheduleFrequency.class);

	@Override
	protected Object convert(String value) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
		ScheduleFrequency type = ScheduleFrequency.DAILY;
		try {

			if (StringUtils.containsIgnoreCase(value, "Updat")) {
				type = ScheduleFrequency.WHEN_UPDATED;
			}
			
			else if (StringUtils.containsIgnoreCase(value, "eek")) {
				type = ScheduleFrequency.WEEKLY;
			}
			

		} catch (Exception e) {
			log.error("error parsing customer type for {}", value);
			log.error("error parsing customer type ", e);
		}
		return type;
	}
}
