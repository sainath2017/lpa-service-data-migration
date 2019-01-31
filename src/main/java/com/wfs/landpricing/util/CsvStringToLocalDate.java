package com.wfs.landpricing.util;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CsvStringToLocalDate extends AbstractBeanField<LocalDate> {

  private static final Logger log = LoggerFactory.getLogger(CsvStringToLocalDate.class);

  private static DateTimeFormatter formatter = new DateTimeFormatterBuilder()
      .appendPattern("MM/dd/yyyy")
      .toFormatter()
      .withZone(ZoneId.of("UTC"));


  @Override
  protected Object convert(String value) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
    LocalDate date = null;
    try{
      date = formatter.parse(value, LocalDate::from);
    }catch (Exception e){
      log.error("error parsing effective date for {}", value);
      log.error("error parsing effective date ", e);
    }
    return date;
  }
}
