package com.resohr.wedding.dto;

import java.util.List;

import lombok.Data;

@Data
public class MonthlySummaryReportDTO {
    private int totalClientsRegistered;
    private int totalEventsOrganized;
    private int totalUpcomingEvents;
    private int totalCompletedEvents;
    private List<String> topVendors;
    private double totalRevenue;
}
