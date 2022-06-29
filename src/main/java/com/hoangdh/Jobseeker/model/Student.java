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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "Student")
public class Student implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private String id;
	
	@Column(name = "Name")
	private String name;
	
	@Column(name = "Gender")
	private String gender;

	@Column(name = "DoB")
	private String DOB;
	
	@ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE
			,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name="SemID")
	private Semester semester;
		
	@JsonIgnoreProperties(ignoreUnknown = true)
	@ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE
			,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name="MajorID")
	private Major major;

	@Column(name ="Status")
	private int status;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDOB() {
		return DOB;
	}

	public void setDOB(String dOB) {
		DOB = dOB;
	}

	public Semester getSemester() {
		return semester;
	}

	public void setSemester(Semester semester) {
		this.semester = semester;
	}

	public Major getMajor() {
		return major;
	}

	public void setMajor(Major major) {
		this.major = major;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
		
}
