package com.bah.msd.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.bah.msd.model.Customer;

public interface CustomersRepository extends CrudRepository<Customer, Long>{
	public Customer findByName(String name);
	@Transactional
	public void deleteByName(String name);
	
	
	

}
