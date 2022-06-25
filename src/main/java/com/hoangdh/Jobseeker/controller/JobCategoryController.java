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

import com.hoangdh.Jobseeker.model.JobCategory;
import com.hoangdh.Jobseeker.repository.JobCategoryRepository;

@RestController
@RequestMapping("/api/jobCategory")
public class JobCategoryController {

	@Autowired 
	private JobCategoryRepository jobCategoryRepository;
	
	@GetMapping("/")
	public ResponseEntity<List<JobCategory>> getAlljobCategories(){
		return ResponseEntity.ok().body(jobCategoryRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<JobCategory> getJobCategoryById(@PathVariable(value = "id") int jobCategoryId) throws Exception{
		JobCategory jobCategory = jobCategoryRepository.findById(jobCategoryId)
		          .orElseThrow(() -> new Exception("JobCategory not found for this id :: " + jobCategoryId));
		        return ResponseEntity.ok().body(jobCategory);
	}
	
	@PostMapping(value = "/add")
	public ResponseEntity<JobCategory> addJobCategory(@RequestBody JobCategory jobCategory){
		JobCategory jobCategoryCheck = jobCategoryRepository.getOne(jobCategory.getId());
		if(jobCategoryCheck == null) {
			return ResponseEntity.notFound().build();
		}
		jobCategoryRepository.save(jobCategory);
		return ResponseEntity.ok().body(jobCategory);
	}
	
	@PutMapping(value = "/update/{id}")
	public ResponseEntity<JobCategory> updateJobCategory(@PathVariable(value = "id") int id, @RequestBody JobCategory JobCategory){
		JobCategory result = jobCategoryRepository.getOne(id);
		if(result == null) {
			return ResponseEntity.notFound().build();
		}
		result = JobCategory;
		jobCategoryRepository.save(result);
		return ResponseEntity.ok().body(result);
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<JobCategory> deleteJobCategory(@PathVariable(value = "id") int id){
		JobCategory result = jobCategoryRepository.getOne(id);
		if(result == null) {
			return ResponseEntity.notFound().build();
		}
		jobCategoryRepository.delete(result);
		
		return ResponseEntity.ok().body(result);
	}
}
