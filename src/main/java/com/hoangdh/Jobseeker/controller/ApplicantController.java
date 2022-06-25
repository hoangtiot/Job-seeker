package com.hoangdh.Jobseeker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hoangdh.Jobseeker.model.Applicant;
import com.hoangdh.Jobseeker.model.Job;
import com.hoangdh.Jobseeker.repository.ApplicantRepository;

@RestController
@RequestMapping("/api/applicant")
public class ApplicantController {
	
	@Autowired
	private ApplicantRepository applicantRepository;
	
	@GetMapping("/")
	public ResponseEntity<List<Applicant>> getAllApplicants(){
		return ResponseEntity.ok().body(applicantRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Applicant> getApplicantById(@PathVariable(value = "id") int applicantId) throws Exception{
		Applicant applicant = applicantRepository.findById(applicantId)
		          .orElseThrow(() -> new Exception("Applicant not found for this id :: " + applicantId));
		        return ResponseEntity.ok().body(applicant);
	}
	
	@PostMapping(value = "/add")
	public ResponseEntity<Applicant> addApplicant(@RequestBody Applicant applicant){
		Applicant applicantCheck = applicantRepository.getOne(applicant.getId());
		if(applicantCheck == null) {
			return ResponseEntity.notFound().build();
		}
		applicantRepository.save(applicant);
		return ResponseEntity.ok().body(applicant);
	}
	
	@PutMapping(value = "/update/{id}")
	public ResponseEntity<Applicant> updateApplicant(@PathVariable(value = "id") int id, @RequestBody Applicant applicant){
		Applicant result = applicantRepository.getOne(id);
		if(result == null) {
			return ResponseEntity.notFound().build();
		}
		result = applicant;
		applicantRepository.save(result);
		return ResponseEntity.ok().body(result);
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<Applicant> deleteApplicant(@PathVariable(value = "id") int id){
		Applicant result = applicantRepository.getOne(id);
		if(result == null) {
			return ResponseEntity.notFound().build();
		}
		applicantRepository.delete(result);
		return ResponseEntity.ok().body(result);
	}
	
}
