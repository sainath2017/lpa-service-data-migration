package com.wfs.landpricing.util;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * Intended to be used to convert json string to json object.
 *
 * Create a Bean and autowire ObjectMapper.
 *
 * <code>
 *
 * @Bean public Json json(ObjectMapper mapper) { return new Json(mapper); }
 * </code>
 *
 * This is a thread safe class. Initialize one instance of Json object and keep reusing it.
 */
public class JsonUtil {

  private ObjectMapper mapper;

  public JsonUtil(ObjectMapper mapper) {
    this.mapper = mapper;
  }

  public JsonUtil() {
    this.mapper = new ObjectMapper();
  }


  /**
   * Convert String to Object of type T.
   *
   * @param jsonString to convert to object of type T
   * @param clazz of object
   * @return Object of type T
   * @throws IllegalArgumentException if an IOException occurs during conversion
   */
  public <T> T toObject(String jsonString, Class<T> clazz) {
    try {
      return mapper.readValue(jsonString, clazz);
    } catch (IOException e) {
      throw new IllegalArgumentException("Error converting Json to " + clazz.getName() + ", json string = " + jsonString, e);
    }
  }

  /**
   * Convert String to Object of type T.
   *
   * @param jsonString to convert to object of type T
   * @param typeRef of TypeReference (to handle generic types)
   * @return Object of type T
   * @throws IllegalArgumentException if an IOException occurs during conversion
   */
  public <T> T toObject(String jsonString, TypeReference<T> typeRef) {
    try {
      return mapper.readValue(jsonString, typeRef);
    } catch (IOException e) {
      throw new IllegalArgumentException("Error converting Json to " + typeRef.getClass()
          .getName() + ", json string = " + jsonString, e);
    }
  }

  /**
   * Convert InputStream to Object of type T.
   *
   * @param is to convert to object of type T
   * @param clazz of the object
   * @return Object of type T
   * @throws IllegalArgumentException if an IOException occurs during conversion
   */
  public <T> T toObject(InputStream is, Class<T> clazz) {
    try {
      return mapper.readValue(is, clazz);
    } catch (IOException e) {
      throw new IllegalArgumentException("Error converting Json to " + clazz.getName(), e);
    }
  }

  /**
   * Convert String to Object of type T.
   *
   * @param is to convert to object of type T
   * @param typeRef of TypeReference (to handle generic types)
   * @return Object of type T
   * @throws IllegalArgumentException if an IOException occurs during conversion
   */
  public <T> T toObject(InputStream is, TypeReference<T> typeRef) {
    try {
      return mapper.readValue(is, typeRef);
    } catch (IOException e) {
      throw new IllegalArgumentException("Error converting Json to " + typeRef.getClass()
          .getName() + ", json string = " + is, e);
    }
  }

  /**
   * Converts String to JsonNode.
   *
   * @return JsonNode
   * @throws IllegalArgumentException if an IOException occurs during conversion
   */
  public JsonNode toJsonNode(String jsonStr) {
    try {
      return mapper.readTree(jsonStr);

    } catch (IOException e) {
      throw new IllegalArgumentException("Error converting " + jsonStr + " to JsonNode", e);
    }
  }

  /**
   * Convert Object of type T to a Map (LinkedHashMap).
   *
   * @return Map of String, Object.
   */
  public <T> Map<String, Object> toMap(T t) {
    return mapper.convertValue(t, new TypeReference<Map<String, Object>>() {});
  }

  /**
   * Convert Object of type T to Json String.
   *
   * @param object of type T
   * @return json string
   * @throws IllegalArgumentException if an IOException occurs during conversion
   */
  public <T> String toPrettyJsonString(T object) {
    try {
      return mapper.writerWithDefaultPrettyPrinter()
          .writeValueAsString(object);
    } catch (IOException e) {
      throw new IllegalArgumentException("Error converting " + object.getClass()
          .getName() + " to Json", e);
    }
  }

  /**
   * Converts object of type T to JSON String.
   *
   * @param <T> object of type T
   * @return JSON String
   * @throws IllegalArgumentException if an IOException occurs during conversion
   */
  public <T> String toJsonString(T object) {
    try {
      return mapper.writeValueAsString(object);
    } catch (IOException e) {
      throw new IllegalArgumentException("Error converting " + object.getClass()
          .getName() + " to Json", e);
    }
  }

}
