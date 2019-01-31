package com.wfs.landpricing.model.converter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.wfs.landpricing.util.JsonUtil;
import java.util.List;
import javax.persistence.AttributeConverter;
import javax.persistence.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

/**
 * @author vsrivastava
 * @since 10/25/18
 **/


@Convert
@Component
@Configurable
public class StringListConverter implements AttributeConverter<List<String>, String> {

  private static JsonUtil json;

  @Autowired
  public void init(JsonUtil json) {
    this.json = json;
  }

  @Override
  public String convertToDatabaseColumn(List<String> data) {
    if (null == data) {
      return null;
    }
    return json.toJsonString(data);
  }

  @Override
  public List<String> convertToEntityAttribute(String s) {
    if (null == s) {
      return null;
    }
    return json.toObject(s, new TypeReference<List<String>>() {});
  }
}
