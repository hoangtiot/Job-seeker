package com.hoangdh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hoangdh.model.Applicant;
import com.hoangdh.model.Job;
import com.hoangdh.repository.ApplicantRepository;

@RestController
@RequestMapping("/api/applicant")
public class ApplicantController {
	
	@Autowired
	private ApplicantRepository applicantRepository;
	
	@GetMapping("/applicants")
	public List<Applicant> getAllApplicants(){
		return applicantRepository.findAll();
	}
	
	@GetMapping("/applicant")
	public ResponseEntity<Applicant> getApplicantById(@PathVariable(value = "id") String applicantId) throws Exception{
		Applicant applicant = applicantRepository.findById(applicantId)
		          .orElseThrow(() -> new Exception("Applicant not found for this id :: " + applicantId));
		        return ResponseEntity.ok().body(applicant);
	}
	
	@RequestMapping(value = "/applicant/addApplicant", method = RequestMethod.POST)
	public ResponseEntity<Applicant> addApplicant(@RequestBody Applicant applicant){
		Applicant result = applicantRepository.save(applicant);
		return ResponseEntity.ok().body(result);
	}
}
