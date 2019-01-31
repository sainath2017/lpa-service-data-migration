package com.wfs.landpricing.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.wfs.landpricing.model.enums.YesNo;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author vsrivastava
 * @since 11/19/18
 **/
public class CsvStringJsonToSetConverter extends AbstractBeanField<Set<String>> {

  private static Logger log = LoggerFactory.getLogger(CsvStringJsonToSetConverter.class);

  private static JsonUtil jsonUtil = new JsonUtil();

  @Override
  protected Object convert(String value) throws CsvDataTypeMismatchException, CsvConstraintViolationException {

    List<String> set = null;

    if (value != null && !StringUtils.isAllBlank(value) && !StringUtils.isAllEmpty(value)){
      set = new ArrayList<>();
      try {
        String valWithoutBrackets = value.substring(1, value.length() - 1);
        String withoutQoutes = valWithoutBrackets.replaceAll("'", "");

        String[] split = withoutQoutes.split(",");
        for (String ar : split) {
          set.add(ar);
        }

      } catch (Exception e) {
        log.error("error converting json for value {}", value);
        log.error("exception", e);
      }
    }
    return set;
  }

  private String removeBrackets(String value){

    String valWithoutBrackets = value.substring(1,value.length()-1);
    return valWithoutBrackets;
  }



}
