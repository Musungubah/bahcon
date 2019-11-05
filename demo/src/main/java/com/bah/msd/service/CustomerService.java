package com.bah.msd.service;

import java.util.Collection;

import com.bah.msd.model.Customer;

public interface CustomerService {
	public void createCustomer(Customer customer);
	public Collection<Customer> getAllCustomers();
	public Customer getACustomer(String name);
	
}
