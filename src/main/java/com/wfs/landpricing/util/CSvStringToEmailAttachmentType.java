package com.wfs.landpricing.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.wfs.landpricing.model.enums.EmailAttachmentType;


public class CSvStringToEmailAttachmentType extends AbstractBeanField {

  private static final Logger log = LoggerFactory.getLogger(CSvStringToEmailAttachmentType.class);

  @Override
  protected Object convert(String value) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
	  EmailAttachmentType type = null;
    try{
      if("CSV".equalsIgnoreCase(value)){
        type = EmailAttachmentType.CSV;
      }
      else if("PDF".equalsIgnoreCase(value)){
        type = EmailAttachmentType.PDF;
      }
    }catch (Exception e){
      log.error("error parsing customer type for {}", value);
      log.error("error parsing customer type ", e);
    }
    return type;
  }
}
