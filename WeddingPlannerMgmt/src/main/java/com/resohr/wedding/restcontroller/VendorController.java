package com.resohr.wedding.restcontroller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.resohr.wedding.dto.VenderDTO;
import com.resohr.wedding.entity.Vendor;
import com.resohr.wedding.service.IVendorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/vendors")
public class VendorController {

	@Autowired
	private IVendorService vendorService;

	@PostMapping("/")
	public Vendor registerVendor(@Valid @RequestBody VenderDTO venderDTO) {
		return vendorService.registerVendor(venderDTO);
	}

	@PutMapping("/{id}/updateAvailability")
	public Vendor updateVendorAvailability(@PathVariable Long id, @RequestParam boolean available) {
		return vendorService.updateVendorAvailability(id, available);
	}

	@GetMapping("/available")
	public List<Vendor> getAvailableVendors(@RequestParam String serviceType) {
		return vendorService.getAvailableVendor(serviceType);
	}
	
	 @GetMapping("/lookup")
	    public List<Vendor> getAvailableVendorsByServiceTypeAndDate(
	            @RequestParam String serviceType,
	            @RequestParam String date) {
	        LocalDate localDate = LocalDate.parse(date);
	        return vendorService.getAvailableVendorsByServiceTypeAndDate(serviceType, localDate);
	    }
}
