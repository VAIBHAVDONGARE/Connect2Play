package com.connect2play.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.connect2play.entities.Payment;
import com.connect2play.entities.PaymentStatus;

public interface IPaymentRepository extends JpaRepository<Payment, Long> {
	Optional<Payment> findByBookingId(Long bookingId);

	Optional<Payment> findByTransactionId(String transactionId);
	
	//List<Payment> findByTurfId(Long turfId);
	
    List<Payment> findByStatus(PaymentStatus status);
    
    List<Payment> findByUserId(Long userId);

}
