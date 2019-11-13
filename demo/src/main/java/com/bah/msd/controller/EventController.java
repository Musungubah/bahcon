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

import com.bah.msd.model.Event;
import com.bah.msd.repository.EventRepository;

@RestController
@RequestMapping("/events")
public class EventController {
	
	@Autowired
	EventRepository repo;
	
	@GetMapping
	public Iterable<Event> getAll(){
		System.out.println(repo.findAll());
		return repo.findAll();
	}
	
	
	@GetMapping("/{EventId}")
	public Event getCustomerById(@PathVariable("EventId") Long EventId){
		return repo.findById(EventId).get();
	}
	
	@DeleteMapping(path="/{id}")
	public void deleteAEvent(@PathVariable("id") Long id) {
		repo.deleteById(id);;
	}
	
	@PostMapping
	public ResponseEntity<?> addEvent(@RequestBody Event newEvent,
			UriComponentsBuilder uri){
		if( newEvent.getTitle() == null 
				|| newEvent.getId() == null) {
			return ResponseEntity.badRequest().build();
		}
		
		newEvent=repo.save(newEvent);
		URI location=ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{EventName}").buildAndExpand(newEvent.getId()).toUri();
		
		ResponseEntity<?> response=ResponseEntity.created(location).build();
		return response;
	}
	
	@PutMapping("/{EventName}")
	public ResponseEntity<?> putEvent(@RequestBody Event newEvent, 
			@PathVariable("EventName") String EventName){
		if(newEvent.getId() == 0|| newEvent.getTitle() == null 
				|| newEvent.getdescription()== null) {
			return ResponseEntity.badRequest().build();
		}
		//DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); 
		//newEvent.setDate(dtf.format(LocalDateTime.now()).toString());
		newEvent = repo.save(newEvent);
		return ResponseEntity.ok().build();
	}
	
}
