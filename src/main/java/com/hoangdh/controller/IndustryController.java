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

import com.hoangdh.model.Industry;
import com.hoangdh.repository.IndustryRepository;

@RestController
@RequestMapping("/api/industry")
public class IndustryController {
	
	@Autowired 
	private IndustryRepository industryRepository;
	
	@GetMapping("/industries")
	public List<Industry> getAllIndustries(){
		return industryRepository.findAll();
	}
	
	@GetMapping("/industry/{id}")
	public ResponseEntity<Industry> getIndustryById(@PathVariable(value = "id") String industryId) throws Exception{
		Industry industry = industryRepository.findById(industryId)
		          .orElseThrow(() -> new Exception("Industry not found for this id :: " + industryId));
		        return ResponseEntity.ok().body(industry);
	}
	
	@RequestMapping(value = "/industry/add", method = RequestMethod.POST)
	public ResponseEntity<Industry> addIndustry(@RequestBody Industry industry){
		Industry industryCheck = industryRepository.getOne(industry.getId());
		if(industryCheck == null) {
			return ResponseEntity.notFound().build();
		}
		industryRepository.save(industry);
		return ResponseEntity.ok().body(industry);
	}
	
	@RequestMapping(value = "/industry/update/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Industry> updateIndustry(@PathVariable(value = "id") String id, @RequestBody Industry Industry){
		Industry result = industryRepository.getOne(id);
		if(result == null) {
			return ResponseEntity.notFound().build();
		}
		result = Industry;
		industryRepository.save(result);
		return ResponseEntity.ok().body(result);
	}
	
	@RequestMapping(value = "/industry/delete/{id}",method = RequestMethod.DELETE)
	public ResponseEntity<Industry> deleteIndustry(@PathVariable(value = "id") String id){
		Industry result = industryRepository.getOne(id);
		if(result == null) {
			return ResponseEntity.notFound().build();
		}
		industryRepository.delete(result);
		
		return ResponseEntity.ok().body(result);
	}
}
