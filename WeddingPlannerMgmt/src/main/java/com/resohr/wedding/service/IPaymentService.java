package com.resohr.wedding.service;

import java.util.List;

import com.resohr.wedding.dto.PaymentDTO;
import com.resohr.wedding.entity.Payment;
import com.resohr.wedding.utilty.PaymentStatus;

public interface IPaymentService {
	
	public Payment recordPayment(PaymentDTO paymentDTO);
	public List<Payment> getPaymentsByStatus(PaymentStatus status);
}
