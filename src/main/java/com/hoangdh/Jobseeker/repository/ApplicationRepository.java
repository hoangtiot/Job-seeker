package com.hoangdh.Jobseeker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hoangdh.Jobseeker.model.Application;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer>{

	Application findByApplicantId(String id);

	

}
