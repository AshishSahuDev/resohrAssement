package com.resohr.wedding.entity;

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
public class Vendor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private String serviceType;
	
	private Boolean available;
	
	private Double contactNumber;
	
	private double servicePrice;
	
	@OneToMany(mappedBy = "vendor", cascade = CascadeType.ALL)
	private List<Booking> booking;
}
