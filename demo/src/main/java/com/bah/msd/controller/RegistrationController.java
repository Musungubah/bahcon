package com.bah.msd.controller;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import com.bah.msd.model.Registration;
import com.bah.msd.repository.RegistrationRepository;

@RestController
@RequestMapping("/registrations")
public class RegistrationController {
	
	@Autowired
	RegistrationRepository repo;
	
	@GetMapping
	public Iterable<Registration> getAll(){
		return repo.findAll();
	}
	
	@GetMapping("/{id}")
	public Registration getById(@PathVariable("id") Long Id) {
		return repo.findById(Id).get();
	}
	
	
	@GetMapping("/name/{RegistrationName}")
	public Registration getCustomerByName(@PathVariable("RegistrationName") String RegistrationName){
		return repo.findByName(RegistrationName);
	}
	
	@DeleteMapping(path="/{name}")
	public void deleteAEvent(@PathVariable String name) {
		repo.deleteByName(name);
	}
	
	@PostMapping
	public ResponseEntity<?> addEvent(@RequestBody Registration newRegistration,
			UriComponentsBuilder uri){
		if( newRegistration.getName() == null 
				|| newRegistration.getDate() == null) {
			return ResponseEntity.badRequest().build();
		}
		newRegistration=repo.save(newRegistration);
		URI location=ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{RegistrationName}").buildAndExpand(newRegistration.getId()).toUri();
		
		ResponseEntity<?> response=ResponseEntity.created(location).build();
		return response;
	}
	
	@PutMapping("/{RegistrationName}")
	public ResponseEntity<?> putEvent(@RequestBody Registration newRegistration, 
			@PathVariable("RegistrationName") String RegistrationName){
		if(newRegistration.getId() == 0|| newRegistration.getName() == null 
				|| newRegistration.getDate()== null) {
			return ResponseEntity.badRequest().build();
		}
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd"); 
		newRegistration.setDate(dtf.format(LocalDateTime.now()).toString());
		newRegistration = repo.save(newRegistration);
		return ResponseEntity.ok().build();
	}
	
}
