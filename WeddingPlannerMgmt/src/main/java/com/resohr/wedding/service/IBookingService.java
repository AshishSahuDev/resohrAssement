package com.resohr.wedding.service;

import com.resohr.wedding.entity.Booking;

public interface IBookingService {
	
	public Booking bookVendor(Long eventId, Long vendorId);
	public Boolean cancelBooking(Long bookingId);
	
}
