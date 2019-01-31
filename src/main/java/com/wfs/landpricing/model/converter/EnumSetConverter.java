package com.wfs.landpricing.model.converter;

import java.util.Set;

import javax.persistence.AttributeConverter;
import javax.persistence.Convert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.wfs.landpricing.model.enums.PnrMethod;
import com.wfs.landpricing.util.JsonUtil;



@Convert
@Component
public class EnumSetConverter implements AttributeConverter<Set<PnrMethod>, String> {

  private static JsonUtil json;

  @Autowired
  public void init(JsonUtil json) {
    this.json = json;
  }

  @Override
  public String convertToDatabaseColumn(Set<PnrMethod> data) {
    if(data != null && !data.isEmpty()){
      return json.toJsonString(data);
    }else{
      return null;
    }
  }

  @Override
  public Set<PnrMethod> convertToEntityAttribute(String s) {
    if(s != null && !s.isEmpty()){
      return json.toObject(s, new TypeReference<Set<PnrMethod>>() {});
    }else{
      return null;
    }
  }
}
