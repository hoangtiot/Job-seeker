package com.hoangdh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hoangdh.model.Company;

public interface CompanyRepository extends JpaRepository<Company,Integer> {

}
