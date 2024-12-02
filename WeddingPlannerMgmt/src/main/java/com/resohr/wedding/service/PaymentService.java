package com.resohr.wedding.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resohr.wedding.dto.PaymentDTO;
import com.resohr.wedding.entity.Client;
import com.resohr.wedding.entity.Payment;
import com.resohr.wedding.exception.ClientNotFoundException;
import com.resohr.wedding.repository.ClientRepository;
import com.resohr.wedding.repository.PaymentRepository;
import com.resohr.wedding.utilty.PaymentStatus;

@Service("paymentService1")
public class PaymentService implements IPaymentService {

	@Autowired
    private PaymentRepository paymentRepo;

    @Autowired
    private ClientRepository clientRepo;
	
	@Override
	public Payment recordPayment(PaymentDTO paymentDTO) {
		Client client = clientRepo.findById(paymentDTO.getClientID()).orElseThrow(()-> new ClientNotFoundException("Client Not Found"));
        Payment payment = new Payment();
        payment.setClient(client);
        payment.setAmount(paymentDTO.getAmount());
        payment.setPaymentDate(LocalDate.now());
        payment.setStatus(PaymentStatus.COMPLETED); 
        return paymentRepo.save(payment);
	}

	@Override
	public List<Payment> getPaymentsByStatus(PaymentStatus status) {
		return paymentRepo.findByStatus(status);
	}

}
