package com.resohr.wedding.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.resohr.wedding.entity.Event;
import com.resohr.wedding.entity.Vendor;
import com.resohr.wedding.exception.VendorUnavailableException;
import com.resohr.wedding.repository.BookingRepository;
import com.resohr.wedding.repository.EventRepository;
import com.resohr.wedding.repository.VendorRepository;
import com.resohr.wedding.service.BookingService;

import jakarta.persistence.EntityNotFoundException;

public class BookingServiceTest {

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private EventRepository eventRepository;

    @Mock
    private VendorRepository vendorRepository;

    @InjectMocks
    private BookingService bookingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void bookVendor_ShouldThrowException_WhenEventNotFound() {
        Long eventId = 1L;
        Long vendorId = 1L;

        when(eventRepository.findById(eventId)).thenReturn(Optional.empty());

        EntityNotFoundException thrown = assertThrows(
                EntityNotFoundException.class,
                () -> bookingService.bookVendor(eventId, vendorId)
        );

        assertEquals("Event with ID 1 not found", thrown.getMessage());
        verify(eventRepository, times(1)).findById(eventId);
    }

    @Test
    void bookVendor_ShouldThrowException_WhenVendorUnavailable() {
        Long eventId = 1L;
        Long vendorId = 2L;

        Event event = new Event();
        event.setId(eventId);
        Vendor vendor = new Vendor();
        vendor.setId(vendorId);
        vendor.setAvailable(false);

        when(eventRepository.findById(eventId)).thenReturn(Optional.of(event));
        when(vendorRepository.findById(vendorId)).thenReturn(Optional.of(vendor));

        VendorUnavailableException thrown = assertThrows(
                VendorUnavailableException.class,
                () -> bookingService.bookVendor(eventId, vendorId)
        );

        assertEquals("Vendor with ID 2 is not available for booking", thrown.getMessage());
        verify(eventRepository, times(1)).findById(eventId);
        verify(vendorRepository, times(1)).findById(vendorId);
    }
}

