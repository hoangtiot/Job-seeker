package com.hoangdh.Jobseeker;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class JobSeekerApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobSeekerApplication.class, args);
		System.out.println("Hello World");
	}

}
