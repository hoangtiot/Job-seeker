package com.hoangdh.Jobseeker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hoangdh.Jobseeker.model.Job;
import com.hoangdh.Jobseeker.repository.JobRepository;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

	@Autowired 
	private JobRepository jobRepository;
	
	@GetMapping("/")
	public String getPage() {
		return "Welcome";
	}
	
	@GetMapping("/jobs")
	public List<Job> getAllJobs(){
		return jobRepository.findAll();
	}
	
	@GetMapping("/job/{id}")
	public ResponseEntity<Job> getJobById(@PathVariable(value = "id") int jobId) throws Exception{
		Job job = jobRepository.findById(jobId)
		          .orElseThrow(() -> new Exception("Job not found for this id :: " + jobId));
		        return ResponseEntity.ok().body(job);
	}
	
	@PostMapping("/job/add")
	public ResponseEntity<Job> addJob(@RequestBody Job newJob){
		Job jobCheck = jobRepository.getOne(newJob.getId());
		if(jobCheck == null) {
			return ResponseEntity.notFound().build();
		}
		jobRepository.save(newJob);
		return ResponseEntity.ok().body(newJob);
	}
	
	@RequestMapping(value = "/job/update/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Job> updateJob(@PathVariable(value = "id") int id, @RequestBody Job job){
		Job result = jobRepository.getOne(id);
		if(result == null) {
			return ResponseEntity.notFound().build();
		}
		result = job;
		jobRepository.save(result);
		return ResponseEntity.ok().body(result);
	}
	
	@RequestMapping(value = "/job/delete/{id}",method = RequestMethod.DELETE)
	public ResponseEntity<Job> deleteJob(@PathVariable(value = "id") int id){
		Job result = jobRepository.getOne(id);
		if(result == null) {
			return ResponseEntity.notFound().build();
		}
		jobRepository.delete(result);
		
		return ResponseEntity.ok().body(result);
	}
	
}