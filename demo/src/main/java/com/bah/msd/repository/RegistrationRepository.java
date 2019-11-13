package com.bah.msd.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;


import com.bah.msd.model.Registration;

public interface RegistrationRepository extends CrudRepository<Registration, Long>{
	
}
