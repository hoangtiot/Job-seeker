package com.hoangdh.Jobseeker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hoangdh.Jobseeker.model.Job;

@Repository
public interface JobRepository extends JpaRepository<Job, Integer>{

	List<Job> findByJobTitleContains(String search);

	List<Job> findByJobCategoryId(String id);

	
}
