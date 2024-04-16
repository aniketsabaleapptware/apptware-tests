package com.apptware.interview.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdultRepository extends JpaRepository<AdultEntity, String> {
}
