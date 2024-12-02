package com.resohr.wedding.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.resohr.wedding.entity.Payment;
import com.resohr.wedding.utilty.PaymentStatus;

public interface PaymentRepository extends JpaRepository<Payment, Long>{
	
	List<Payment> findByStatus(PaymentStatus status);
}
