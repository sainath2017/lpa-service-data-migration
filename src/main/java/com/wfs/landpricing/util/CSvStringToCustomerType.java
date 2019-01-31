package com.wfs.landpricing.util;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.wfs.landpricing.model.enums.CustomerType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author vsrivastava
 * @since 11/29/18
 **/
public class CSvStringToCustomerType extends AbstractBeanField {

  private static final Logger log = LoggerFactory.getLogger(CSvStringToCustomerType.class);

  @Override
  protected Object convert(String value) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
    CustomerType type = CustomerType.CUSTOMER;
    try{
      if("P".equalsIgnoreCase(value)){
        type = CustomerType.PROSPECT;
      }
    }catch (Exception e){
      log.error("error parsing customer type for {}", value);
      log.error("error parsing customer type ", e);
    }
    return type;
  }
}
