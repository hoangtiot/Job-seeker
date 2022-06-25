package com.hoangdh.Jobseeker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hoangdh.Jobseeker.model.Job;
import com.hoangdh.Jobseeker.repository.JobRepository;

@CrossOrigin
@RestController
@RequestMapping("/api/job")
public class JobController {

	@Autowired 
	private JobRepository jobRepository;
	
	@GetMapping("/")
	public List<Job> getAllJobs(){
		return jobRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Job> getJobById(@PathVariable(value = "id") int jobId) throws Exception{
		Job job = jobRepository.findById(jobId)
		          .orElseThrow(() -> new Exception("Job not found for this id :: " + jobId));
		        return ResponseEntity.ok().body(job);
	}
	
	@GetMapping("/search/{search}")
	public List<Job> findByTitle(@PathVariable(value = "search") String search) throws Exception{
		List<Job> result = (List<Job>) jobRepository.findByJobTitleContains(search);
		return result;
	}
	
//	@GetMapping("/searchByCategory/{categoryId}")
//	public List<Job> findByCategory(@PathVariable(value = "categoryId") String id) throws Exception{
//		List<Job> result = (List<Job>) jobRepository.findByCategoryId(id);
//		return result;
//	}
	
	@PostMapping(value = "/add")
	public ResponseEntity<Job> addJob(@RequestBody Job job){
		Job jobCheck = jobRepository.getOne(job.getId());
		if(jobCheck == null) {
			return ResponseEntity.notFound().build();
		}
		jobRepository.save(job);
		return ResponseEntity.ok().body(job);
	}
	
	@PutMapping(value = "/update/{id}")
	public ResponseEntity<Job> updateJob(@PathVariable(value = "id") int id, @RequestBody Job job){
		Job result = jobRepository.getOne(id);
		if(result == null) {
			return ResponseEntity.notFound().build();
		}
		result = job;
		jobRepository.save(result);
		return ResponseEntity.ok().body(result);
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<Job> deleteJob(@PathVariable(value = "id") int id){
		Job result = jobRepository.getOne(id);
		if(result == null) {
			return ResponseEntity.notFound().build();
		}
		jobRepository.delete(result);
		
		return ResponseEntity.ok().body(result);
	}
	
}