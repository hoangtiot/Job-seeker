package com.hoangdh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hoangdh.model.Industry;

@Repository
public interface IndustryRepository extends JpaRepository<Industry, Integer>{

}
