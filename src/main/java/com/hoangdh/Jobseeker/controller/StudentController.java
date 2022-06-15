package com.hoangdh.Jobseeker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hoangdh.Jobseeker.model.Student;
import com.hoangdh.Jobseeker.repository.StudentRepository;

@RestController
@RequestMapping("/api/student")
public class StudentController{

	@Autowired
	private StudentRepository studentRepository;
	
	@GetMapping("/students")
	public List<Student> getAllstudents(){
		return studentRepository.findAll();
	}
	
	@GetMapping("/student/{id}")
	public ResponseEntity<Student> getstudentById(@PathVariable(value = "id") String studentId) throws Exception{
		Student student = studentRepository.findById(studentId)
		          .orElseThrow(() -> new Exception("student not found for this id :: " + studentId));
		        return ResponseEntity.ok().body(student);
	}
	
	@RequestMapping(value = "/student/add", method = RequestMethod.POST)
	public ResponseEntity<Student> addstudent(@RequestBody Student student){
		Student studentCheck = studentRepository.getOne(student.getId());
		if(studentCheck == null) {
			return ResponseEntity.notFound().build();
		}
		studentRepository.save(student);
		return ResponseEntity.ok().body(student);
	}
	
	@RequestMapping(value = "/student/update/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Student> updatestudent(@PathVariable(value = "id") String id, @RequestBody Student student){
		Student result = studentRepository.getOne(id);
		if(result == null) {
			return ResponseEntity.notFound().build();
		}
		result = student;
		studentRepository.save(result);
		return ResponseEntity.ok().body(result);
	}
	
	@RequestMapping(value = "/student/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Student> deletestudent(@PathVariable(value = "id") String id){
		Student result = studentRepository.getOne(id);
		if(result == null) {
			return ResponseEntity.notFound().build();
		}
		studentRepository.delete(result);
		return ResponseEntity.ok().body(result);
	}
}
