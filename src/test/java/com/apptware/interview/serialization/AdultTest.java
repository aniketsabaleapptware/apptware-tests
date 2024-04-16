package com.apptware.interview.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class AdultTest {

  @Test
  @SneakyThrows
  void testConstructorValidation() {
    Assertions.assertThatThrownBy(() -> new Adult("", "", 18))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("Firstname or Lastname CANNOT be blank.");
    Assertions.assertThatThrownBy(() -> new Adult("Firstname", "Lastname", 17))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("Firstname or Lastname CANNOT be blank.");

    String json1 =
        """
    {
      "firstName": "",
      "lastName": "",
      "age": 18
    }
    """;

    String json2 =
        """
    {
      "firstName": "Firstname",
      "lastName": "Lastname",
      "age": 17
    }
    """;

    ObjectMapper objectMapper = new ObjectMapper();
    Assertions.assertThatThrownBy(
            () -> {
              Adult adult = objectMapper.readValue(json1, Adult.class);
              System.out.println(adult);
            })
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("Firstname or Lastname CANNOT be blank.");
    Assertions.assertThatThrownBy(
            () -> {
              Adult adult = objectMapper.readValue(json2, Adult.class);
              System.out.println(adult);
            })
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("Inappropriate Age for an Adult.");
  }
}
