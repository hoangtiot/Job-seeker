package com.hoangdh.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Job implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	
	private String jobTitle;
	
	private String jobSummary;
	
	private Date datePublished;
	
	private String salary;
	
	private String responsibility;
	
	private String workLocation;
	
	private String qualifications;
	
}
