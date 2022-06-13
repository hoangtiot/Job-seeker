package com.hoangdh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hoangdh.model.Semester;

@Repository
public interface SemesterRepository extends JpaRepository<Semester, String>{

}
