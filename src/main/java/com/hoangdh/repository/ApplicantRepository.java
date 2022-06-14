package com.hoangdh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hoangdh.model.Applicant;

public interface ApplicantRepository extends JpaRepository<Applicant, Integer> {
	
}
