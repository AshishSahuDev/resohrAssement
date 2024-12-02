package com.resohr.wedding.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.resohr.wedding.dto.MonthlySummaryReportDTO;
import com.resohr.wedding.service.IReportService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/report")
@RequiredArgsConstructor
public class ReportController {

    @Autowired
    private final IReportService reportService;

    @GetMapping("/monthlySummary")
    public MonthlySummaryReportDTO getMonthlySummaryReport() {
        return reportService.generateMonthlyReport();
    }
}
