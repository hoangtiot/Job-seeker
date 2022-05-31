package com.hoangdh.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Applicant implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	
	private String phoneNumber;
	
	private String address;
	
	private String major;
	
	private String email;
	
	private String password;
	
	private String semesterId;
}
