package com.hoangdh.Jobseeker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hoangdh.Jobseeker.model.Applicant;

public interface ApplicantRepository extends JpaRepository<Applicant, Integer> {
	
}
