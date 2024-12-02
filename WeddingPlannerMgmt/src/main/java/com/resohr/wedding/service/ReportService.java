package com.resohr.wedding.service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resohr.wedding.dto.MonthlySummaryReportDTO;
import com.resohr.wedding.entity.Client;
import com.resohr.wedding.entity.Event;
import com.resohr.wedding.entity.Payment;
import com.resohr.wedding.entity.Vendor;
import com.resohr.wedding.repository.ClientRepository;
import com.resohr.wedding.repository.EventRepository;
import com.resohr.wedding.repository.PaymentRepository;
import com.resohr.wedding.repository.VendorRepository;
import com.resohr.wedding.utilty.EventStatus;

@Service("reportService")
public class ReportService implements IReportService {

    @Autowired
    private ClientRepository clientRepo;

    @Autowired
    private EventRepository eventRepo;

    @Autowired
    private PaymentRepository paymentRepo;

    @Autowired
    private VendorRepository vendorRepo;

    @Override
    public MonthlySummaryReportDTO generateMonthlyReport() {
        LocalDate startDate = YearMonth.now().minusMonths(1).atDay(1);
        LocalDate endDate = YearMonth.now().minusMonths(1).atEndOfMonth();

        List<Client> clients = clientRepo.findAll().stream()
                .filter(client -> isDateInPreviousMonth(client.getRegistrationDate(), startDate, endDate))
                .collect(Collectors.toList());
        int totalClientsRegistered = clients.size();

        List<Event> events = eventRepo.findAll().stream()
                .filter(event -> isDateInPreviousMonth(event.getEventDate(), startDate, endDate))
                .collect(Collectors.toList());
        int totalEventsOrganized = events.size();
        int totalUpcomingEvents = (int) events.stream().filter(event -> event.getStatus() == EventStatus.UPCOMING).count();
        int totalCompletedEvents = (int) events.stream().filter(event -> event.getStatus() == EventStatus.COMPELETED).count();

        Map<Vendor, Long> vendorBookings = vendorRepo.findAll().stream()
                .collect(Collectors.groupingBy(vendor -> vendor, Collectors.counting()));

        List<String> topVendors = vendorBookings.entrySet().stream()
                .sorted((entry1, entry2) -> Long.compare(entry2.getValue(), entry1.getValue()))
                .limit(3)
                .map(entry -> entry.getKey().getName())
                .collect(Collectors.toList());

        double totalRevenue = paymentRepo.findAll().stream()
                .filter(payment -> isDateInPreviousMonth(payment.getPaymentDate(), startDate, endDate))
                .mapToDouble(Payment::getAmount)
                .sum();

        MonthlySummaryReportDTO reportDTO = new MonthlySummaryReportDTO();
        reportDTO.setTotalClientsRegistered(totalClientsRegistered);
        reportDTO.setTotalEventsOrganized(totalEventsOrganized);
        reportDTO.setTotalUpcomingEvents(totalUpcomingEvents);
        reportDTO.setTotalCompletedEvents(totalCompletedEvents);
        reportDTO.setTopVendors(topVendors);
        reportDTO.setTotalRevenue(totalRevenue);

        return reportDTO;
    }

    private boolean isDateInPreviousMonth(LocalDate date, LocalDate startDate, LocalDate endDate) {
        return date != null && !date.isBefore(startDate) && !date.isAfter(endDate);
    }
}
