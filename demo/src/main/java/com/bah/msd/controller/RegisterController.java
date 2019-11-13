package com.bah.msd.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.bah.msd.model.Customer;

@RestController("/register")
public class RegisterController {
	
	@PostMapping
	public void registerUser(@RequestBody Customer customer) {
		
		final String uri = "http://localhost:8080/api/customers";
	     
	    RestTemplate restTemplate = new RestTemplate();
	    //Customer result = restTemplate.getForObject(uri, Customer.class);
		restTemplate.postForObject(uri, customer, Customer.class);
	}

}
