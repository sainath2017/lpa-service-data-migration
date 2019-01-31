package com.wfs.landpricing.util;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CsvStringToLocalDateTimeConverter extends AbstractBeanField<LocalDateTime> {

  private static final Logger log = LoggerFactory.getLogger(CsvStringToLocalDateTimeConverter.class);

  private static DateTimeFormatter formatter = new DateTimeFormatterBuilder()
      .appendPattern("yyyy-MM-dd HH:mm:ssZ")
      .toFormatter()
      .withZone(ZoneId.of("UTC"));


  @Override
  protected Object convert(String value) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
    LocalDateTime date = null;

    if(value !=null && !value.isEmpty()){
      try{
        date = formatter.parse(value, LocalDateTime::from);
      }catch (Exception e){
        log.error("error parsing effective date for {}", value);
        log.error("error parsing effective date ", e);
      }
    }
    return date;
  }


}
