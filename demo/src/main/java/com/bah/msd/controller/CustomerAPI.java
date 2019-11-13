package com.bah.msd.controller;

import java.net.URI;
import java.util.Optional;

import org.hibernate.annotations.UpdateTimestamp;
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

import com.bah.msd.model.Customer;
import com.bah.msd.repository.CustomersRepository;

@RestController
@RequestMapping("/customers")
public class CustomerAPI {
	static final String JSON = "application/json";
	
	@Autowired
	CustomersRepository repo;
	
	@GetMapping
	public Iterable<Customer> getAll(){
		return repo.findAll();
	}
	
	@GetMapping(path="/name/{name}", produces=JSON)
	public Customer getACustomerByName(@PathVariable String name) {
		return repo.findByName(name);
	}
	
	@PostMapping(path="/login")
	public boolean doLogin(@RequestBody Customer customer) {
		String testPass = customer.getPassword();
		String repoPass = repo.findByName(customer.getName()).getPassword();
		
		if(testPass.equals(repoPass))
			return true;
		else
			return false;
	}
	
	@GetMapping(path="/{id}", produces=JSON)
	public Customer getACustomer(@PathVariable Long Id) {
		return repo.findByName(repo.findById(Id).get().getName());
	}
	
	@DeleteMapping(path="/{id}")
	public void deleteACustomer(@PathVariable Long id) {
		repo.deleteByName(repo.findById(id).get().getName());
	}
	
	@PostMapping
	public ResponseEntity<?> addCustomer(@RequestBody Customer newCustomer,
			UriComponentsBuilder uri){
		if( newCustomer.getName() == null 
				|| newCustomer.getEmail() == null) {
			return ResponseEntity.badRequest().build();
		}
		
		newCustomer=repo.save(newCustomer);
		URI location=ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(newCustomer.getId()).toUri();
		
		ResponseEntity<?> response=ResponseEntity.created(location).build();
		return response;
	}
	
	@PutMapping("/{name}")
	public ResponseEntity<?> putCustomer(@RequestBody Customer newCustomer, 
			@PathVariable("name") String name){
		if( newCustomer.getName() == null 
				|| newCustomer.getEmail() == null) {
			return ResponseEntity.badRequest().build();
		}
		
		newCustomer = repo.save(newCustomer);
		return ResponseEntity.ok().build();
	}
	
	
	
	
	

}
