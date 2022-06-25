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

import com.hoangdh.Jobseeker.model.Semester;
import com.hoangdh.Jobseeker.repository.SemesterRepository;

@RestController
@RequestMapping("/api/semester")
public class SemesterController {

	@Autowired 
	private SemesterRepository semesterRepository;
	
	@GetMapping("/")
	public List<Semester> getAllSemesters(){
		return semesterRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Semester> getSemesterById(@PathVariable(value = "id") String semesterId) throws Exception{
		Semester semester = semesterRepository.findById(semesterId)
		          .orElseThrow(() -> new Exception("Semester not found for this id :: " + semesterId));
		        return ResponseEntity.ok().body(semester);
	}
	
	@PostMapping(value = "/add")
	public ResponseEntity<Semester> addSemester(@RequestBody Semester semester){
		Semester semesterCheck = semesterRepository.getOne(semester.getId());
		if(semesterCheck == null) {
			return ResponseEntity.notFound().build();
		}
		semesterRepository.save(semester);
		return ResponseEntity.ok().body(semester);
	}
	
	@PutMapping(value = "/update/{id}")
	public ResponseEntity<Semester> updateSemester(@PathVariable(value = "id") String id, @RequestBody Semester Semester){
		Semester result = semesterRepository.getOne(id);
		if(result == null) {
			return ResponseEntity.notFound().build();
		}
		result = Semester;
		semesterRepository.save(result);
		return ResponseEntity.ok().body(result);
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<Semester> deleteSemester(@PathVariable(value = "id") String id){
		Semester result = semesterRepository.getOne(id);
		if(result == null) {
			return ResponseEntity.notFound().build();
		}
		semesterRepository.delete(result);
		
		return ResponseEntity.ok().body(result);
	}
}
