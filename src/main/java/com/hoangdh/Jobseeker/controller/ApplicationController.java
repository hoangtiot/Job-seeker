package com.hoangdh.Jobseeker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.firebase.auth.ExportedUserRecord;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.ListUsersPage;
import com.google.firebase.auth.UserRecord;
import com.hoangdh.Jobseeker.model.Application;
import com.hoangdh.Jobseeker.repository.ApplicationRepository;

@CrossOrigin
@RestController
@RequestMapping("/api/application")
public class ApplicationController {
	
	@Autowired
	private ApplicationRepository applicationRepository;
	
	@GetMapping("/")
	public ResponseEntity<List<Application>> getAllApplications() throws FirebaseAuthException{
		List<Application> result = (List<Application>) applicationRepository.findAll();
		
//		UserRecord userRecord = FirebaseAuth.getInstance().getUserByEmail("hoangdhse140659@fpt.edu.vn");
//		System.out.println("Successfully fetched user data: " + userRecord.getEmail());

		// Start listing users from the beginning, 1000 at a time.
		ListUsersPage page = FirebaseAuth.getInstance().listUsers(null);
		while (page != null) {
		  for (ExportedUserRecord user : page.getValues()) {
		    System.out.println("User: " + user.getUid());
		  }
		  page = page.getNextPage();
		}

		// Iterate through all users. This will still retrieve users in batches,
		// buffering no more than 1000 users in memory at a time.
		page = FirebaseAuth.getInstance().listUsers(null);
		for (ExportedUserRecord user : page.iterateAll()) {
		  System.out.println("User: " + user.getEmail());
		}

		
		return ResponseEntity.ok()
				.header("X-Total-Count", String.valueOf(result.size()))
				.body(result);
	}
	
//	@GetMapping("/{id}")
//	public ResponseEntity<Application> getApplicationById(@PathVariable(value = "id") int ApplicationId) throws Exception{
//		Application Application = applicationRepository.findById(ApplicationId)
//		          .orElseThrow(() -> new Exception("Application not found for this id :: " + ApplicationId));
//		        return ResponseEntity.ok().body(Application);
//	}
//	
//	@PostMapping(value = "/add")
//	public ResponseEntity<Application> addApplication(@RequestBody Application application){
//		Application ApplicationCheck = ApplicationRepository.getOne(application.getId());
//		if(ApplicationCheck == null) {
//			return ResponseEntity.notFound().build();
//		}
//		ApplicationRepository.save(Application);
//		return ResponseEntity.ok().body(Application);
//	}
	
//	@PutMapping(value = "/update/{id}")
//	public ResponseEntity<Application> updateApplication(@PathVariable(value = "id") int id, @RequestBody Application Application){
//		Application result = applicationRepository.getOne(id);
//		if(result == null) {
//			return ResponseEntity.notFound().build();
//		}
//		result = Application;
//		applicationRepository.save(result);
//		return ResponseEntity.ok().body(result);
//	}
//	
//	@DeleteMapping(value = "/delete/{id}")
//	public ResponseEntity<Application> deleteApplication(@PathVariable(value = "id") int id){
//		Application result = applicationRepository.getOne(id);
//		if(result == null) {
//			return ResponseEntity.notFound().build();
//		}
//		applicationRepository.delete(result);
//		return ResponseEntity.ok().body(result);
//	}
	
}
