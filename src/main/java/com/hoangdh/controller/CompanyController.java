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

import com.hoangdh.model.Company;
import com.hoangdh.repository.CompanyRepository;

@RestController
@RequestMapping("/api/company")
public class CompanyController {
	@Autowired 
	private CompanyRepository companyRepository;
	
	
	@GetMapping("/companies")
	public List<Company> getAllCompanies(){
		return companyRepository.findAll();
	}
	
	@GetMapping("/company")
	public ResponseEntity<Company> getCompanyById(@PathVariable(value = "id") int companyId) throws Exception{
		Company Company = companyRepository.findById(companyId)
		          .orElseThrow(() -> new Exception("Company not found for this id :: " + companyId));
		        return ResponseEntity.ok().body(Company);
	}
	
	@RequestMapping(value = "/company/add", method = RequestMethod.POST)
	public ResponseEntity<Company> addCompany(@RequestBody Company company){
		Company CompanyCheck = companyRepository.getOne(company.getId());
		if(CompanyCheck == null) {
			return ResponseEntity.notFound().build();
		}
		companyRepository.save(company);
		return ResponseEntity.ok().body(company);
	}
	
	@RequestMapping(value = "/company/update/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Company> updateCompany(@PathVariable(value = "id") int id, @RequestBody Company company){
		Company result = companyRepository.getOne(id);
		if(result == null) {
			return ResponseEntity.notFound().build();
		}
		result = company;
		companyRepository.save(result);
		return ResponseEntity.ok().body(result);
	}
	
	@RequestMapping(value = "/company/delete/{id}",method = RequestMethod.DELETE)
	public ResponseEntity<Company> deleteCompany(@PathVariable(value = "id") int id){
		Company result = companyRepository.getOne(id);
		if(result == null) {
			return ResponseEntity.notFound().build();
		}
		companyRepository.delete(result);
		
		return ResponseEntity.ok().body(result);
	}
}
