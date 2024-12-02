package com.resohr.wedding.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.resohr.wedding.dto.VenderDTO;
import com.resohr.wedding.entity.Vendor;
import com.resohr.wedding.repository.VendorRepository;
import com.resohr.wedding.service.VendorService;

public class VendorServiceTest {

    @Mock
    private VendorRepository vendorRepository;

    @InjectMocks
    private VendorService vendorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerVendor_ShouldReturnRegisteredVendor() {
        VenderDTO vendorDTO = new VenderDTO();
        vendorDTO.setName("ABC Catering");
        vendorDTO.setServiceType("Catering");
        vendorDTO.setServicePrice(1500.0);
        vendorDTO.setContactNumber(1234567890.0);

        Vendor vendor = new Vendor();
        vendor.setId(1L);
        vendor.setName(vendorDTO.getName());
        vendor.setServiceType(vendorDTO.getServiceType());
        vendor.setServicePrice(vendorDTO.getServicePrice());
        vendor.setContactNumber(vendorDTO.getContactNumber());

        when(vendorRepository.save(any(Vendor.class))).thenReturn(vendor);

        Vendor result = vendorService.registerVendor(vendorDTO);

        assertNotNull(result);
        assertEquals("ABC Catering", result.getName());
        verify(vendorRepository, times(1)).save(any(Vendor.class));
    }

    @Test
    void updateVendorAvailability_ShouldUpdateAvailability_WhenVendorExists() {
        Long vendorId = 1L;
        Vendor vendor = new Vendor();
        vendor.setId(vendorId);
        vendor.setAvailable(false);

        when(vendorRepository.findById(vendorId)).thenReturn(Optional.of(vendor));
        when(vendorRepository.save(vendor)).thenReturn(vendor);

        Vendor result = vendorService.updateVendorAvailability(vendorId, true);

        assertNotNull(result);
        assertTrue(result.getAvailable());
        verify(vendorRepository, times(1)).save(vendor);
    }
}
