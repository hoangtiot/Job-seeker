package com.hoangdh.Jobseeker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hoangdh.Jobseeker.model.JobCategory;

@Repository
public interface JobCategoryRepository extends JpaRepository<JobCategory, Integer>{

}
