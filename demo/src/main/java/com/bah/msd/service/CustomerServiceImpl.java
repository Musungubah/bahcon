package com.bah.msd.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import com.bah.msd.dao.CustomersDAO;
import com.bah.msd.model.Customer;

public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomersDAO customerDAO;
	
	public void createCustomer(Customer customer) {
		customerDAO.createCustomer(customer);
	}
	public Collection<Customer> getAllCustomers(){
		return customerDAO.getAllCustomers();
	}
	public Customer getACustomer(String name) {
		return customerDAO.getACustomer(name);
		
	}

}
