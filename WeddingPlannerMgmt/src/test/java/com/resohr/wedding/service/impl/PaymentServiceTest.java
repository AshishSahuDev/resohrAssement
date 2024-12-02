package com.resohr.wedding.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

import com.resohr.wedding.dto.PaymentDTO;
import com.resohr.wedding.entity.Client;
import com.resohr.wedding.entity.Payment;
import com.resohr.wedding.repository.ClientRepository;
import com.resohr.wedding.repository.PaymentRepository;
import com.resohr.wedding.service.PaymentService;
import com.resohr.wedding.utilty.PaymentStatus;

public class PaymentServiceTest {

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private PaymentService paymentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void recordPayment_ShouldThrowException_WhenClientNotFound() {
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setClientID(1L);
        paymentDTO.setAmount(2000.0);

        when(clientRepository.findById(paymentDTO.getClientID())).thenReturn(Optional.empty());

        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> paymentService.recordPayment(paymentDTO)
        );

        assertEquals("Client not found", thrown.getMessage());
    }

    @Test
    void recordPayment_ShouldRecordPayment_WhenDataIsValid() {
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setClientID(1L);
        paymentDTO.setAmount(2000.0);

        Client client = new Client();
        client.setClientId(1L);

        Payment payment = new Payment();
        payment.setId(1L);
        payment.setClient(client);
        payment.setAmount(paymentDTO.getAmount());
        payment.setStatus(PaymentStatus.COMPLETED);

        when(clientRepository.findById(paymentDTO.getClientID())).thenReturn(Optional.of(client));
        when(paymentRepository.save(any(Payment.class))).thenReturn(payment);

        Payment result = paymentService.recordPayment(paymentDTO);

        assertNotNull(result);
        assertEquals(2000.0, result.getAmount());
        verify(paymentRepository, times(1)).save(any(Payment.class));
    }
}
