package com.apptware.interview.serialization;

import java.util.Objects;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

@Data
@NoArgsConstructor
public class Adult {
  private String firstName;
  private String lastName;
  private Integer age;

  public Adult(String firstName, String lastName, Integer age) {
    if (StringUtils.isBlank(firstName) || StringUtils.isBlank(lastName)) {
      throw new IllegalArgumentException("Firstname or Lastname CANNOT be blank.");
    }

    if (Objects.isNull(age) || age < 18) {
      throw new IllegalArgumentException("Inappropriate Age for an Adult.");
    }

    this.firstName = firstName;
    this.lastName = lastName;
    this.age = age;
  }


}
