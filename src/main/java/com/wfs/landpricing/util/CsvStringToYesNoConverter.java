package com.wfs.landpricing.util;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.wfs.landpricing.model.enums.YesNo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author vsrivastava
 * @since 11/19/18
 **/
public class CsvStringToYesNoConverter extends AbstractBeanField<YesNo> {

  private static Logger log = LoggerFactory.getLogger(CsvStringToYesNoConverter.class);
  @Override
  protected Object convert(String value) throws CsvDataTypeMismatchException, CsvConstraintViolationException {

    YesNo yesNo =YesNo.N;
    try{
      if("Y".equalsIgnoreCase(value) || "Yes".equalsIgnoreCase(value))
        yesNo =YesNo.Y;

    }catch (Exception e){
      log.error("error converting boolean for value {}", value);
      log.error("exception",e );
    }
    return yesNo;
  }

}
