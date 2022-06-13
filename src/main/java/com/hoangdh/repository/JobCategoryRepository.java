package com.hoangdh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hoangdh.model.JobCategory;

@Repository
public interface JobCategoryRepository extends JpaRepository<JobCategory, String>{

}
