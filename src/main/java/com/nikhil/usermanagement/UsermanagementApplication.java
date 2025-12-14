package com.nikhil.usermanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UsermanagementApplication {

	public static void main(String[] args) {
		System.err.println("Server Started");
		SpringApplication.run(UsermanagementApplication.class, args);
	}

}
