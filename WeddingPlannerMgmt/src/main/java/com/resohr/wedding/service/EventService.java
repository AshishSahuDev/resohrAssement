package com.resohr.wedding.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resohr.wedding.dto.EventDTO;
import com.resohr.wedding.entity.Client;
import com.resohr.wedding.entity.Event;
import com.resohr.wedding.exception.ClientNotFoundException;
import com.resohr.wedding.exception.EventNotFoundException;
import com.resohr.wedding.exception.InvalidRequestException;
import com.resohr.wedding.repository.ClientRepository;
import com.resohr.wedding.repository.EventRepository;
import com.resohr.wedding.utilty.EventStatus;

@Service("eventService1")
public class EventService implements IEventService {
	
	@Autowired
	private EventRepository eventRepo;
	
	@Autowired
	private ClientRepository clientRepo;
	
	@Override
	public Event addEvent(EventDTO eventDTO) {

		 if (eventDTO.getEventDate().isBefore(LocalDate.now())) {
	            throw new InvalidRequestException("Event date must be in the future.");
	        }
		
		Client client = clientRepo.findById(eventDTO.getClientId()).orElseThrow(()-> new ClientNotFoundException("Client Not Found"));
       
        Event event = new Event();
        event.setEventName(eventDTO.getEventName());
        event.setEventDate(eventDTO.getEventDate());
        event.setClient(client);
        event.setStatus(EventStatus.UPCOMING);
        client.getEvent().add(event);
        clientRepo.save(client);
        return eventRepo.save(event);
	}

	@Override
	public Event getEventById(Long id) {
		return eventRepo.findById(id).orElseThrow(()-> new EventNotFoundException("Event Not Registered"));
	}

	@Override
	public List<Event> getAllEvents() {
		 return eventRepo.findAll();
	}

	@Override
	public List<Event> getEventsByStatus(EventStatus eventStatus) {
		return eventRepo.findByStatus(eventStatus);
	}

}
