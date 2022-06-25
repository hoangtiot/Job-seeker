package com.hoangdh.Jobseeker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hoangdh.Jobseeker.model.Company;

public interface CompanyRepository extends JpaRepository<Company,Integer> {

	List<Company> findByIndustryId(int industyId);

}
