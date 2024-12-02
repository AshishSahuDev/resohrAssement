package com.resohr.wedding.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ReportControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getMonthlySummaryReport_ShouldReturnValidReport() throws Exception {
        mockMvc.perform(get("/report/monthlySummary"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalClientsRegistered").isNumber())
                .andExpect(jsonPath("$.totalEventsOrganized").isNumber())
                .andExpect(jsonPath("$.totalUpcomingEvents").isNumber())
                .andExpect(jsonPath("$.totalCompletedEvents").isNumber())
                .andExpect(jsonPath("$.totalRevenue").isNumber())
                .andExpect(jsonPath("$.topVendors").isArray());
    }
}
