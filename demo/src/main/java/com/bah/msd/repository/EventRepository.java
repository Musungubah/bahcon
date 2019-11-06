package com.bah.msd.repository;

import org.springframework.data.repository.CrudRepository;


import com.bah.msd.model.Event;

public interface EventRepository extends CrudRepository<Event, Long>{
	public Event findByName(String name);
}
