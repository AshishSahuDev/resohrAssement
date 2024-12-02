package com.resohr.wedding.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resohr.wedding.entity.Booking;
import com.resohr.wedding.entity.Client;
import com.resohr.wedding.entity.Event;
import com.resohr.wedding.entity.Vendor;
import com.resohr.wedding.exception.InvalidRequestException;
import com.resohr.wedding.repository.BookingRepository;
import com.resohr.wedding.repository.EventRepository;
import com.resohr.wedding.repository.VendorRepository;

import jakarta.persistence.EntityNotFoundException;

@Service("bookingService1")
public class BookingService implements IBookingService {

	@Autowired
	private BookingRepository bookingRepo;

	@Autowired
	private EventRepository eventRepo;

	@Autowired
	private VendorRepository vendorRepo;

	@Override
	public Booking bookVendor(Long eventId, Long vendorId) {
		Event event = eventRepo.findById(eventId)
				.orElseThrow(() -> new EntityNotFoundException("Event with ID " + eventId + " not found"));

		Vendor vendor = vendorRepo.findById(vendorId)
				.orElseThrow(() -> new EntityNotFoundException("Vendor with ID " + vendorId + " not found"));

		Client client = event.getClient();
		double totalCost = bookingRepo.findByEvent_Client_ClientId(client.getClientId()).stream()
				.mapToDouble(booking -> booking.getVendor().getServicePrice()).sum();

		if (totalCost + vendor.getServicePrice() > client.getBudget()) {
			throw new InvalidRequestException("Booking this vendor would exceed the client's budget.");
		}

		Booking booking = new Booking();
		booking.setEvent(event);
		booking.setVendor(vendor);
		booking.setConfirmed(true);

		event.getBooking().add(booking);
		vendor.getBooking().add(booking);

		eventRepo.save(event);
		vendorRepo.save(vendor);

		return bookingRepo.save(booking);
	}

	@Override
	public Boolean cancelBooking(Long bookingId) {
		Booking booking = bookingRepo.findById(bookingId)
				.orElseThrow(() -> new EntityNotFoundException("Booking with ID " + bookingId + " not found"));

		Event event = booking.getEvent();
		Vendor vendor = booking.getVendor();

		if (event != null) {
			event.getBooking().remove(booking);
			eventRepo.save(event);
		}

		if (vendor != null) {
			vendor.getBooking().remove(booking);
			vendorRepo.save(vendor);
		}

		bookingRepo.delete(booking);
		return true;
	}
}
