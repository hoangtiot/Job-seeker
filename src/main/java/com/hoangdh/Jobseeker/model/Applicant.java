package com.hoangdh.Jobseeker.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "Applicant")
public class Applicant implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	
	@Column(name = "PhoneNumber")
	private String phoneNumber;
	
	@Column(name = "Address")
	private String address;
		
	@Column(name = "Email")
	private String email;
	
	@Column(name = "Status")
	private int status;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="StudentID")
	private Student student;
	
	@JsonIgnore
	@ManyToMany(fetch=FetchType.LAZY,
			cascade = {CascadeType.DETACH,CascadeType.MERGE
			,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinTable(name="Application",
				joinColumns=@JoinColumn(name="ApplicantID"),
				inverseJoinColumns=@JoinColumn(name="JobID"))
	private List<Job> jobs;
	
	
	public List<Job> getJobs() {
		return jobs;
	}

	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	
	
	
}
