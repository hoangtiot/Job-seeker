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

import com.hoangdh.model.JobCategory;
import com.hoangdh.repository.JobCategoryRepository;

@RestController
@RequestMapping("/api/jobCategory")
public class JobCategoryController {

	@Autowired 
	private JobCategoryRepository jobCategoryRepository;
	
	@GetMapping("/jobCategories")
	public List<JobCategory> getAlljobCategories(){
		return jobCategoryRepository.findAll();
	}
	
	@GetMapping("/jobCategory")
	public ResponseEntity<JobCategory> getJobCategoryById(@PathVariable(value = "id") String jobCategoryId) throws Exception{
		JobCategory jobCategory = jobCategoryRepository.findById(jobCategoryId)
		          .orElseThrow(() -> new Exception("JobCategory not found for this id :: " + jobCategoryId));
		        return ResponseEntity.ok().body(jobCategory);
	}
	
	@RequestMapping(value = "/jobCategory/add", method = RequestMethod.POST)
	public ResponseEntity<JobCategory> addJobCategory(@RequestBody JobCategory jobCategory){
		JobCategory jobCategoryCheck = jobCategoryRepository.getOne(jobCategory.getId());
		if(jobCategoryCheck == null) {
			return ResponseEntity.notFound().build();
		}
		jobCategoryRepository.save(jobCategory);
		return ResponseEntity.ok().body(jobCategory);
	}
	
	@RequestMapping(value = "/jobCategory/update/{id}", method = RequestMethod.PUT)
	public ResponseEntity<JobCategory> updateJobCategory(@PathVariable(value = "id") String id, @RequestBody JobCategory JobCategory){
		JobCategory result = jobCategoryRepository.getOne(id);
		if(result == null) {
			return ResponseEntity.notFound().build();
		}
		result = JobCategory;
		jobCategoryRepository.save(result);
		return ResponseEntity.ok().body(result);
	}
	
	@RequestMapping(value = "/jobCategory/delete/{id}",method = RequestMethod.DELETE)
	public ResponseEntity<JobCategory> deleteJobCategory(@PathVariable(value = "id") String id){
		JobCategory result = jobCategoryRepository.getOne(id);
		if(result == null) {
			return ResponseEntity.notFound().build();
		}
		jobCategoryRepository.delete(result);
		
		return ResponseEntity.ok().body(result);
	}
}
