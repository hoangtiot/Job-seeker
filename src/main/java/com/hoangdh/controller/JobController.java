package com.hoangdh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hoangdh.model.Job;
import com.hoangdh.repository.JobSeekerRepository;

@RestController
@RequestMapping("/api/job")
public class JobController {

	@Autowired 
	private JobSeekerRepository jobSeekerRepository;
	
	@GetMapping("/jobs")
	public List<Job> getAllJobs(){
		return jobSeekerRepository.findAll();
	}
	
	@GetMapping("/job")
	public ResponseEntity<Job> getJobById(@PathVariable(value = "id") String jobId) throws Exception{
		Job job = jobSeekerRepository.findById(jobId)
		          .orElseThrow(() -> new Exception("Job not found for this id :: " + jobId));
		        return ResponseEntity.ok().body(job);
	}
	
}
