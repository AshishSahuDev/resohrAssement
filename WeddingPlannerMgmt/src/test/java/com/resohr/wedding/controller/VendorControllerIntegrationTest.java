package com.resohr.wedding.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class VendorControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void registerVendor_ShouldReturnCreatedVendor() throws Exception {
        String vendorJson = "{"
                + "\"name\": \"ABC Catering\","
                + "\"serviceType\": \"Catering\","
                + "\"servicePrice\": 2000.0,"
                + "\"contactNumber\": \"1234567890\""
                + "}";

        mockMvc.perform(post("/vendors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(vendorJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("ABC Catering"));
    }

    @Test
    void updateVendorAvailability_ShouldUpdateAvailability() throws Exception {
        Long vendorId = 1L;

        mockMvc.perform(put("/vendors/{id}/updateAvailability", vendorId)
                        .param("available", "true"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.available").value(true));
    }
}

