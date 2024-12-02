package com.resohr.wedding.entity;

import java.time.LocalDate;
import java.util.List;

import com.resohr.wedding.utilty.EventStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String eventName;
	
	private LocalDate eventDate;
		
	@ManyToOne
	@JoinColumn(name = "client_id",nullable = false)
	private Client client;
	
	@OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
	private List<Booking> booking;
	
	@Enumerated(EnumType.STRING)
	private EventStatus status;
}

