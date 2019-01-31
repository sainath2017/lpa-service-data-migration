package com.wfs.landpricing.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.wfs.landpricing.model.enums.TaxType;


public class CSvStringToTaxSummaryType extends AbstractBeanField {

  private static final Logger log = LoggerFactory.getLogger(CSvStringToTaxSummaryType.class);

  @Override
  protected Object convert(String value) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
	  TaxType type = null;
    try{
      if (StringUtils.containsIgnoreCase(value, "summary")){
        type = TaxType.TAX_SUMMARY;
      }
      else if (StringUtils.containsIgnoreCase(value, "breakout")){
          type = TaxType.TAX_DETAILS_BREAKOUT;
        }
    }catch (Exception e){
      log.error("error parsing customer type for {}", value);
      log.error("error parsing customer type ", e);
    }
    return type;
  }
}
