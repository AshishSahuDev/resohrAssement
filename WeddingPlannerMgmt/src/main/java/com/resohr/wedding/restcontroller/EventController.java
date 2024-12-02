package com.resohr.wedding.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.resohr.wedding.dto.EventDTO;
import com.resohr.wedding.entity.Event;
import com.resohr.wedding.service.IEventService;
import com.resohr.wedding.utilty.EventStatus;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/events")
public class EventController {

	@Autowired
	private IEventService eventService;

	@PostMapping
	public Event addEvent(@Valid @RequestBody EventDTO eventDTO) {
		return eventService.addEvent(eventDTO);
	}

	@GetMapping("/{id}")
	public Event getEventById(@PathVariable Long id) {
		return eventService.getEventById(id);
	}

	@GetMapping
	public List<Event> getAllEvents(@RequestParam(required = false) EventStatus status) {
		if (status != null) {
			return eventService.getEventsByStatus(status);
		}
		return eventService.getAllEvents();
	}
}
