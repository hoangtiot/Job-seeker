package com.hoangdh.Jobseeker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hoangdh.Jobseeker.model.Semester;

@Repository
public interface SemesterRepository extends JpaRepository<Semester, String>{

}
