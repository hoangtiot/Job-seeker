package com.hoangdh.Jobseeker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hoangdh.Jobseeker.model.Major;
import com.hoangdh.Jobseeker.repository.MajorRepository;

@CrossOrigin
@RestController
@RequestMapping("/api/major")
public class MajorController {
	
	@Autowired
	private MajorRepository majorRepository;
	
	@GetMapping("/")
	public ResponseEntity<List<Major>> getAllMajors(){
		List<Major> result = (List<Major>) majorRepository.findAll();
		return ResponseEntity.ok()
				.header("X-Total-Count", String.valueOf(result.size()))
				.body(result);
	}
	

	
}
