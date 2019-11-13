package com.bah.msd.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;


import com.bah.msd.model.Event;

public interface EventRepository extends CrudRepository<Event, Long>{
	//public Event findByName(String name);
	//@Transactional
	//public void deleteByName(String name);
}
