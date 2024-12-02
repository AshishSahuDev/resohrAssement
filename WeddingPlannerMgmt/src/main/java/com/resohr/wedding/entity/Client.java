package com.resohr.wedding.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long clientId;
	
	private String name;
	
	private String phoneNumber;
	
	private String address;
	
	private Double budget;
	
	private LocalDate registrationDate;
	
	@OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
	private List<Event> event;
}
