package com.apptware.interview.jpa;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.springframework.stereotype.Component;

@Component
@Converter(autoApply = true)
public class JsonNodeConverter implements AttributeConverter<JsonNode, String> {

  private final ObjectMapper objectMapper = new ObjectMapper();

  @Override
  public String convertToDatabaseColumn(JsonNode jsonNode) {
    try {
      return jsonNode != null ? objectMapper.writeValueAsString(jsonNode) : null;
    } catch (Exception e) {
      throw new IllegalArgumentException("Error converting JSON to string", e);
    }
  }

  @Override
  public JsonNode convertToEntityAttribute(String jsonStr) {
    try {
      return jsonStr != null ? objectMapper.readTree(jsonStr) : null;
    } catch (Exception e) {
      throw new IllegalArgumentException("Error converting string to JSON", e);
    }
  }
}
