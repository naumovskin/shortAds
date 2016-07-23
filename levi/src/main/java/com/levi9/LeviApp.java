package com.levi9;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;

@SpringBootApplication
public class LeviApp
	extends SpringBootServletInitializer {

	@Autowired
	TestData td;
	
	
	public static void main(String[] args) {
		 SpringApplication.run(LeviApp.class, args);
	}

}