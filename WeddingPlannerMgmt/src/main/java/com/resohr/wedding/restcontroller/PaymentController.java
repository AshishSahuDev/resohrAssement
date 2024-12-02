package com.resohr.wedding.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.resohr.wedding.dto.PaymentDTO;
import com.resohr.wedding.entity.Payment;
import com.resohr.wedding.service.IPaymentService;
import com.resohr.wedding.utilty.PaymentStatus;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private IPaymentService paymentService;

    @PostMapping
    public Payment recordPayment(@Valid @RequestBody PaymentDTO paymentDTO) {
        return paymentService.recordPayment(paymentDTO);
    }

    @GetMapping
    public List<Payment> getPaymentsByStatus(@RequestParam PaymentStatus status) {
        return paymentService.getPaymentsByStatus(status);
    }
}
