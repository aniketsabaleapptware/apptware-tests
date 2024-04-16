package com.apptware.interview.jpa;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdultServiceImpl implements AdultService{

  @Autowired
  private AdultRepository adultRepository;

  public AdultEntity create(AdultEntity student) {
    return adultRepository.save(student);
  }
}
