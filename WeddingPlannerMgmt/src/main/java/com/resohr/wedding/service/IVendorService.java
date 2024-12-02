package com.resohr.wedding.service;

import java.time.LocalDate;
import java.util.List;

import com.resohr.wedding.dto.VenderDTO;
import com.resohr.wedding.entity.Vendor;

public interface IVendorService {
	
	public Vendor registerVendor(VenderDTO vendorDTO);
	public Vendor updateVendorAvailability(Long venderId, boolean available);
	public List<Vendor> getAvailableVendor(String serviceType);
	List<Vendor> getAvailableVendorsByServiceTypeAndDate(String serviceType, LocalDate date);
}
