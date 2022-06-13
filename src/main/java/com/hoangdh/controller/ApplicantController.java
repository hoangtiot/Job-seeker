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
	
	@GetMapping("/applicant/{id}")
	public ResponseEntity<Applicant> getApplicantById(@PathVariable(value = "id") String applicantId) throws Exception{
		Applicant applicant = applicantRepository.findById(applicantId)
		          .orElseThrow(() -> new Exception("Applicant not found for this id :: " + applicantId));
		        return ResponseEntity.ok().body(applicant);
	}
	
	@RequestMapping(value = "/applicant/add", method = RequestMethod.POST)
	public ResponseEntity<Applicant> addApplicant(@RequestBody Applicant applicant){
		Applicant applicantCheck = applicantRepository.getOne(applicant.getId());
		if(applicantCheck == null) {
			return ResponseEntity.notFound().build();
		}
		applicantRepository.save(applicant);
		return ResponseEntity.ok().body(applicant);
	}
	
	@RequestMapping(value = "/applicant/update/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Applicant> updateApplicant(@PathVariable(value = "id") String id, @RequestBody Applicant applicant){
		Applicant result = applicantRepository.getOne(id);
		if(result == null) {
			return ResponseEntity.notFound().build();
		}
		result = applicant;
		applicantRepository.save(result);
		return ResponseEntity.ok().body(result);
	}
	
	@RequestMapping(value = "/applicant/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Applicant> deleteApplicant(@PathVariable(value = "id") String id){
		Applicant result = applicantRepository.getOne(id);
		if(result == null) {
			return ResponseEntity.notFound().build();
		}
		applicantRepository.delete(result);
		return ResponseEntity.ok().body(result);
	}
	
	@GetMapping("/login")
	public ResponseEntity<Applicant> login(@RequestBody Applicant loginData) throws Exception{
		Applicant applicant = applicantRepository.getOne(loginData.getId());
		if (!applicant.getPassword().equalsIgnoreCase(loginData.getPassword())) {
			return ResponseEntity.notFound().build();
		}         
		return ResponseEntity.ok().body(applicant);
	}
}
