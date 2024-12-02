package com.resohr.wedding.service;

import java.util.List;

import com.resohr.wedding.dto.EventDTO;
import com.resohr.wedding.entity.Event;
import com.resohr.wedding.utilty.EventStatus;

public interface IEventService {

	public Event addEvent(EventDTO eventDTO);
	public Event getEventById(Long id);
	public List<Event> getAllEvents();
	public List<Event> getEventsByStatus(EventStatus eventStatus);
}
