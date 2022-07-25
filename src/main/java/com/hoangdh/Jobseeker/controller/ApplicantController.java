package com.hoangdh.Jobseeker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.google.firebase.auth.ExportedUserRecord;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.ListUsersPage;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.auth.UserRecord.CreateRequest;
import com.hoangdh.Jobseeker.model.Applicant;
import com.hoangdh.Jobseeker.model.Job;
import com.hoangdh.Jobseeker.repository.ApplicantRepository;

@CrossOrigin
@RestController
@RequestMapping("/api/applicant")
public class ApplicantController {
	
	@Autowired
	private ApplicantRepository applicantRepository;
	
	@GetMapping("/")
	public ResponseEntity<List<Applicant>> getAllApplicants(){
		List<Applicant> result = (List<Applicant>) applicantRepository.findAll();
//		for (int i = 0; i < result.size(); i ++) {
//			CreateRequest request = new CreateRequest()
//				    .setEmail(result.get(i).getEmail())
//				    .setEmailVerified(false)
//				    .setPassword("secretPassword")
//				    .setPhoneNumber("+84" + result.get(i).getPhoneNumber())
//				    .setDisplayName(result.get(i).getStudent().getName())
//				    .setPhotoUrl("http://www.example.com/12345678/photo.png")
//				    .setDisabled(false);
//
//				try {
//					UserRecord userRecord = FirebaseAuth.getInstance().createUser(request);
//					System.out.println(userRecord.getEmail());
//				} catch (FirebaseAuthException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//
//		}
		return ResponseEntity.ok()
				.header("X-Total-Count", String.valueOf(result.size()))
				.body(result);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Applicant> getApplicantById(@PathVariable(value = "id") int applicantId) throws Exception{
		Applicant applicant = applicantRepository.findById(applicantId)
		          .orElseThrow(() -> new Exception("Applicant not found for this id :: " + applicantId));
		        return ResponseEntity.ok().body(applicant);
	}
	
	@GetMapping("/google/")
	public ResponseEntity<List<Applicant>> getApplicantsByGoogleId(@RequestBody List<String> listEmail) throws Exception{
		List<Applicant> list = (List<Applicant>) applicantRepository.findAll();
		List<Applicant> result = null;
		for (int i = 0; i < listEmail.size(); i++) {
		  for (int j = 0; j < list.size(); j++) {
			  if (list.get(j).getEmail()
					  .equalsIgnoreCase(listEmail.get(i))) {
				  result.add(list.get(i));
			  };
		  }
		}
		return ResponseEntity.ok()
				.header("X-Total-Count", String.valueOf(result.size()))
				.body(result);
	}
	
	@GetMapping("/google/{email}")
	public ResponseEntity<Applicant> getApplicantByGoogleId(@PathVariable(value = "email") String email) throws Exception{
		UserRecord userRecord = FirebaseAuth.getInstance().getUserByEmail(email);

		Applicant applicant = applicantRepository.findByEmail(userRecord.getEmail())
		          .orElseThrow(() -> new Exception("Applicant not found for this email :: " + email));
		        return ResponseEntity.ok().body(applicant);
	}
	
	@PostMapping(value = "/add")
	public ResponseEntity<Applicant> addApplicant(@RequestBody Applicant applicant){
//		Applicant applicantCheck = applicantRepository.getOne(applicant.getId());
//		if(applicantCheck == null) {
			applicantRepository.save(applicant);
//		} else {
//			return ResponseEntity.status(409).body(null);
//		}
		return ResponseEntity.ok().body(applicant);
	}
	
	@PutMapping(value = "/update/{id}")
	public ResponseEntity<Applicant> updateApplicant(@PathVariable(value = "id") int id, @RequestBody Applicant applicant){
		Applicant result = applicantRepository.findById(id).get();
		if(result == null) {
			return ResponseEntity.notFound().build();
		}
		result = applicant;
		applicantRepository.save(result);
		return ResponseEntity.ok().body(result);
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<Applicant> deleteApplicant(@PathVariable(value = "id") int id){
		Applicant result = applicantRepository.findById(id).get();
		if(result == null) {
			return ResponseEntity.notFound().build();
		}
		applicantRepository.delete(result);
		return ResponseEntity.ok().body(result);
	}
	
	@GetMapping(value = "/semester/{semesterId}")
	public ResponseEntity<List<Applicant>> findBySemester(@PathVariable(value  = "semesterId") String id){
		List<Applicant> result = applicantRepository.findBySemesterId(id);
		return ResponseEntity.ok()
				.header("X-Total-Count", String.valueOf(result.size()))
				.body(result);
	}
	
	
}
