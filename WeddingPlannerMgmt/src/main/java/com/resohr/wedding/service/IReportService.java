package com.resohr.wedding.service;

import com.resohr.wedding.dto.MonthlySummaryReportDTO;

public interface IReportService {
    MonthlySummaryReportDTO generateMonthlyReport();
}
