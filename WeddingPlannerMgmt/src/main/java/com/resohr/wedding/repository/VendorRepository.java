package com.resohr.wedding.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.resohr.wedding.entity.Vendor;

public interface VendorRepository extends JpaRepository<Vendor, Long> {
	
	public List<Vendor> findByServiceTypeAndAvailable(String serviceType, boolean availabe);

	@Query("SELECT v FROM Vendor v WHERE v.serviceType = :serviceType AND v.available = true AND "
	         + "NOT EXISTS (SELECT b FROM Booking b WHERE b.vendor = v AND b.event.eventDate = :eventDate)")
	    List<Vendor> findAvailableVendorsByServiceTypeAndDate(@Param("serviceType") String serviceType, @Param("eventDate") LocalDate eventDate);
}
