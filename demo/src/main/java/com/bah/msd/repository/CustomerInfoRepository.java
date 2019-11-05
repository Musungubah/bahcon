package com.bah.msd.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.bah.msd.model.Customer;

public class CustomerInfoRepository {
	
	
	List<Customer> customerInfoRepository = new ArrayList<>();
	
	public List<Customer> getCustomerInfoRepository() {
		return customerInfoRepository;
	}

	public void setCustomerInfoRepository(List<Customer> customerInfoRepository) {
		this.customerInfoRepository = customerInfoRepository;
	}

	public CustomerInfoRepository() {
		 this.initializeSeedData();
	
	}
	
	//some seed data in memory
	public void initializeSeedData() {
		Customer one = new Customer("John", "ABC123","John@bah.com");
		Customer two = new Customer("Frank", "ABC123", "Frank@bah.com");
		this.customerInfoRepository.add(one);
		this.customerInfoRepository.add(two);
	}
	
	
	public void addCustomerInfo(Customer customer) {
		this.customerInfoRepository.add(customer);
		System.out.println("Added " + customer + " to inMemory Repository");
	}
	
	public Customer findByName(String name) {
		Optional<Customer> customerOptional = this.customerInfoRepository.stream().
				filter(item -> item.getName().equals(name))
				.findAny();
		return customerOptional.get();
	}
	
	public Collection<Customer> findAll(){
		return Collections.unmodifiableCollection(this.customerInfoRepository);
	}
	
	
	

}
