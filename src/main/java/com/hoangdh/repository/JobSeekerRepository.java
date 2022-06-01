package com.hoangdh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hoangdh.model.Job;

@Repository
public interface JobSeekerRepository extends JpaRepository<Job, String>{
	

}
