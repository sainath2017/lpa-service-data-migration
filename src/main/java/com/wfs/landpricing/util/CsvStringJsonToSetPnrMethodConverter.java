package com.wfs.landpricing.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.wfs.landpricing.model.enums.PnrMethod;

/**
 * @author vsrivastava
 * @since 11/19/18
 **/
public class CsvStringJsonToSetPnrMethodConverter extends AbstractBeanField<Set<PnrMethod>> {

	private static Logger log = LoggerFactory.getLogger(CsvStringJsonToSetPnrMethodConverter.class);

	private static JsonUtil jsonUtil = new JsonUtil();

	@Override
	protected Object convert(String value) throws CsvDataTypeMismatchException, CsvConstraintViolationException {

		List<PnrMethod> list = null;

		if (value != null && !StringUtils.isAllBlank(value) && !StringUtils.isAllEmpty(value)) {
			list = new ArrayList<>();
			try {

				String purgedValue = StringUtils.replaceEach(value, new String[] { "{", "}", "'" },
						new String[] { "", "", "" });

				String[] split = purgedValue.split(",");

				for (String ar : split) {
					list.add(PnrMethod.valueOf(ar.toUpperCase().trim()));
				}

			} catch (Exception e) {
				log.error("error converting json for value {}", value);
				log.error("exception", e);
			}
		}
		return list;
	}

	

}
