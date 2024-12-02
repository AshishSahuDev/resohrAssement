package com.resohr.wedding.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.resohr.wedding.entity.Booking;
import com.resohr.wedding.service.IBookingService;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private IBookingService bookingService;

    @PostMapping
    public Booking bookVendor(@RequestParam Long eventId, @RequestParam Long vendorId) {
        return bookingService.bookVendor(eventId, vendorId);
    }

    @DeleteMapping("/{id}/cancel")
    public Boolean cancelBooking(@PathVariable Long id) {
        return bookingService.cancelBooking(id);
    }
}
