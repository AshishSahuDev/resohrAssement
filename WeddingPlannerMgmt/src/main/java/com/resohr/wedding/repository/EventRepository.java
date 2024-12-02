package com.resohr.wedding.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.resohr.wedding.entity.Event;
import com.resohr.wedding.utilty.EventStatus;

public interface EventRepository extends JpaRepository<Event, Long> {
	
	List<Event> findByEventDateBetween(LocalDate startDate, LocalDate endDate);
	List<Event> findByStatus(EventStatus status);
}
