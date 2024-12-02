package com.resohr.wedding.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.resohr.wedding.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {

	List<Booking> findByEvent_Client_ClientId(Long clientId);
}
