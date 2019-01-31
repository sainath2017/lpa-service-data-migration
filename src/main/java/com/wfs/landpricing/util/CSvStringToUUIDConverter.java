package com.wfs.landpricing.util;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author vsrivastava
 * @since 11/27/18
 **/
public class CSvStringToUUIDConverter extends AbstractBeanField<UUID> {

  private static final Logger log = LoggerFactory.getLogger(CSvStringToUUIDConverter.class);
  @Override
  protected Object convert(String value) throws CsvDataTypeMismatchException, CsvConstraintViolationException {


    UUID id = null;
    if(value !=null && !value.isEmpty() ){
      id = UUID.fromString(value);
    }
    return id;
  }
}
