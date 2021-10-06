package com.javatech.spring.cloud.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@RestController
	class RestTemplateController{

		@GetMapping
		public ResponseEntity<?> get(){
			return ResponseEntity.ok("Welcome to 1st App Engine with Spring boot!!!");
		}

		@GetMapping("/test")
		public ResponseEntity<?> getTest(){
			return ResponseEntity.ok().body("Success");
		}
	}
}
