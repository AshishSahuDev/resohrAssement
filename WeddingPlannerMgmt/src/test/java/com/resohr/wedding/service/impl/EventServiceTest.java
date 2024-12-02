package com.resohr.wedding.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.resohr.wedding.dto.EventDTO;
import com.resohr.wedding.entity.Client;
import com.resohr.wedding.entity.Event;
import com.resohr.wedding.exception.InvalidRequestException;
import com.resohr.wedding.repository.ClientRepository;
import com.resohr.wedding.repository.EventRepository;
import com.resohr.wedding.service.EventService;

public class EventServiceTest {

    @Mock
    private EventRepository eventRepository;

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private EventService eventService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addEvent_ShouldThrowException_WhenEventDateIsInThePast() {
        EventDTO eventDTO = new EventDTO();
        eventDTO.setEventName("Wedding Ceremony");
        eventDTO.setEventDate(LocalDate.now().minusDays(1)); // Past date
        eventDTO.setClientId(1L);

        InvalidRequestException thrown = assertThrows(
                InvalidRequestException.class,
                () -> eventService.addEvent(eventDTO)
        );

        assertEquals("Event date must be in the future.", thrown.getMessage());
    }

    @Test
    void addEvent_ShouldAddEvent_WhenDataIsValid() {
        // Arrange
        EventDTO eventDTO = new EventDTO();
        eventDTO.setEventName("Wedding Ceremony");
        eventDTO.setEventDate(LocalDate.now().plusDays(10));
        eventDTO.setClientId(1L);

        Client client = new Client();
        client.setClientId(1L);

        Event event = new Event();
        event.setId(1L);
        event.setEventName(eventDTO.getEventName());
        event.setEventDate(eventDTO.getEventDate());
        event.setClient(client);

        when(clientRepository.findById(eventDTO.getClientId())).thenReturn(Optional.of(client));
        when(eventRepository.save(any(Event.class))).thenReturn(event);

        Event result = eventService.addEvent(eventDTO);

        assertNotNull(result);
        assertEquals("Wedding Ceremony", result.getEventName());
        verify(eventRepository, times(1)).save(any(Event.class));
    }
}
