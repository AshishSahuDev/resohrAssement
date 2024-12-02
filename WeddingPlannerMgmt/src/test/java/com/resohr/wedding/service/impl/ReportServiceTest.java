package com.resohr.wedding.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.resohr.wedding.dto.MonthlySummaryReportDTO;
import com.resohr.wedding.entity.Client;
import com.resohr.wedding.entity.Event;
import com.resohr.wedding.entity.Payment;
import com.resohr.wedding.entity.Vendor;
import com.resohr.wedding.repository.ClientRepository;
import com.resohr.wedding.repository.EventRepository;
import com.resohr.wedding.repository.PaymentRepository;
import com.resohr.wedding.repository.VendorRepository;
import com.resohr.wedding.service.ReportService;
import com.resohr.wedding.utilty.EventStatus;

public class ReportServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private EventRepository eventRepository;

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private VendorRepository vendorRepository;

    @InjectMocks
    private ReportService reportService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void generateMonthlyReport_ShouldReturnValidReport() {
        LocalDate lastMonthStart = LocalDate.now().minusMonths(1).withDayOfMonth(1);
        LocalDate lastMonthEnd = LocalDate.now().minusMonths(1).withDayOfMonth(lastMonthStart.lengthOfMonth());

        Client client1 = new Client();
        client1.setClientId(1L);
        client1.setRegistrationDate(lastMonthStart.plusDays(5));

        Client client2 = new Client();
        client2.setClientId(2L);
        client2.setRegistrationDate(lastMonthStart.plusDays(10));

        when(clientRepository.findAll()).thenReturn(Arrays.asList(client1, client2));

        Event event1 = new Event();
        event1.setId(1L);
        event1.setEventDate(lastMonthStart.plusDays(15));
        event1.setStatus(EventStatus.UPCOMING);

        Event event2 = new Event();
        event2.setId(2L);
        event2.setEventDate(lastMonthEnd.minusDays(3));
        event2.setStatus(EventStatus.COMPELETED);

        when(eventRepository.findAll()).thenReturn(Arrays.asList(event1, event2));

        Payment payment1 = new Payment();
        payment1.setId(1L);
        payment1.setPaymentDate(lastMonthStart.plusDays(20));
        payment1.setAmount(1000.0);

        Payment payment2 = new Payment();
        payment2.setId(2L);
        payment2.setPaymentDate(lastMonthEnd.minusDays(2));
        payment2.setAmount(2000.0);

        when(paymentRepository.findAll()).thenReturn(Arrays.asList(payment1, payment2));

        Vendor vendor1 = new Vendor();
        vendor1.setId(1L);
        vendor1.setName("Vendor A");

        Vendor vendor2 = new Vendor();
        vendor2.setId(2L);
        vendor2.setName("Vendor B");

        when(vendorRepository.findAll()).thenReturn(Arrays.asList(vendor1, vendor2));

        MonthlySummaryReportDTO report = reportService.generateMonthlyReport();

        assertNotNull(report);
        assertEquals(2, report.getTotalClientsRegistered());
        assertEquals(2, report.getTotalEventsOrganized());
        assertEquals(1, report.getTotalUpcomingEvents());
        assertEquals(1, report.getTotalCompletedEvents());
        assertEquals(3000.0, report.getTotalRevenue(), 0.001);
    }
}
