package com.resohr.wedding.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resohr.wedding.dto.VenderDTO;
import com.resohr.wedding.entity.Vendor;
import com.resohr.wedding.exception.VendorUnavailableException;
import com.resohr.wedding.repository.VendorRepository;

@Service("vendorService1")
public class VendorService implements IVendorService {

	@Autowired
	private VendorRepository vendorRepo;

	@Override
	public Vendor registerVendor(VenderDTO vendorDTO) {
		Vendor vendor = new Vendor();
		vendor.setName(vendorDTO.getName());
		vendor.setServiceType(vendorDTO.getServiceType());
		vendor.setContactNumber(vendorDTO.getContactNumber());
		vendor.setServicePrice(vendorDTO.getServicePrice());
		vendor.setAvailable(true);
		return vendorRepo.save(vendor);
	}

	@Override
	public Vendor updateVendorAvailability(Long venderId, boolean available) {
		Vendor vendor = vendorRepo.findById(venderId)
				.orElseThrow(() -> new VendorUnavailableException("Vendor Not Found"));
		vendor.setAvailable(available);
		return vendorRepo.save(vendor);
	}

	@Override
	public List<Vendor> getAvailableVendor(String serviceType) {
		return vendorRepo.findByServiceTypeAndAvailable(serviceType, true);
	}

	@Override
	public List<Vendor> getAvailableVendorsByServiceTypeAndDate(String serviceType, LocalDate date) {
		return vendorRepo.findAvailableVendorsByServiceTypeAndDate(serviceType, date);
	}

}
