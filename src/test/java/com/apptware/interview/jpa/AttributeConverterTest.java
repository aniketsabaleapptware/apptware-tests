package com.apptware.interview.jpa;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.ObjectContent;
import org.springframework.core.io.DefaultResourceLoader;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.org.apache.commons.io.IOUtils;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@Slf4j
@Testcontainers
@SpringBootTest
@AutoConfigureJsonTesters
public class AttributeConverterTest {

  @Autowired
  private AdultRepository adultRepository;

  @Autowired
  private AdultServiceImpl service;


  @Container
  public static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:latest")
      .withDatabaseName("test")
      .withUsername("User")
      .withPassword("password");


  @Autowired
  private JacksonTester<AdultEntity> jacksonTester;

  @Test
  void addData() throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    InputStream is = new DefaultResourceLoader().getResource("data.json").getInputStream();
    String jsonContent = IOUtils.toString(is, StandardCharsets.UTF_8);
    System.out.println("jsoncontent - "+jsonContent);

    ObjectContent<AdultEntity> dataContent =
        jacksonTester.parse(jsonContent);

    AdultEntity saved = service.create(dataContent.getObject());
    System.out.println("saved ");
    AdultEntity storedEntity = adultRepository.findAll().getFirst();
    assertThat(saved.getId()).isEqualTo(storedEntity.getId());
    assertThat(saved.getFirstName()).isEqualTo(storedEntity.getFirstName());
    assertThat(saved.getLastName()).isEqualTo(storedEntity.getLastName());
    assertThat(saved.getAge()).isEqualTo(storedEntity.getAge());

  }
}
