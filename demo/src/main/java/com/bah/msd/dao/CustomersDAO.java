package com.bah.msd.dao;

import java.util.Collection;

import com.bah.msd.model.Customer;

public interface CustomersDAO {
	public void createCustomer(Customer customer);
	public Collection<Customer> getAllCustomers();
	public Customer getACustomer(String name);

}
