package com.hoangdh.Jobseeker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hoangdh.Jobseeker.model.Industry;

@Repository
public interface IndustryRepository extends JpaRepository<Industry, Integer>{

}
