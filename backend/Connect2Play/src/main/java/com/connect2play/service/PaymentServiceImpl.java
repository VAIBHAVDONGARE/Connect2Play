package com.connect2play.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.connect2play.dto.PaymentDTO;
import com.connect2play.entities.Booking;
import com.connect2play.entities.Payment;
import com.connect2play.entities.PaymentStatus;
import com.connect2play.entities.User;
import com.connect2play.exception.ResourceNotFoundException;
import com.connect2play.repository.IBookingRepository;
import com.connect2play.repository.IPaymentRepository;
import com.connect2play.repository.IUserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class PaymentServiceImpl implements IPaymentService {

	private IPaymentRepository paymentRepository;
	private IUserRepository userRepository;
	private IBookingRepository bookingRepository;

	@Transactional
	@Override
	public Payment createPayment(PaymentDTO paymentDTO) {
		log.info("Creating new payment for booking ID: {}", paymentDTO.getBookings().getBookingId());

		User user = userRepository.findById(paymentDTO.getUserId())
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));

		Booking booking = bookingRepository.findById(paymentDTO.getBookings().getBookingId())
				.orElseThrow(() -> new ResourceNotFoundException("Booking not found"));

		Payment payment = new Payment();
		payment.setUser(user);
		payment.setBooking(booking);
		payment.setAmount(paymentDTO.getAmount());
		payment.setPaymentMethod(paymentDTO.getPaymentMethod());
		payment.setTransactionId(paymentDTO.getTransactionId());
		payment.setStatus(PaymentStatus.PENDING); // Default status

		Payment savedPayment = paymentRepository.save(payment);
		log.info("Payment created successfully with Transaction ID: {}", savedPayment.getTransactionId());

		return savedPayment;
	}

	@Override
	public Payment getPaymentByTransactionId(String transactionId) {
		return paymentRepository.findByTransactionId(transactionId)
				.orElseThrow(() -> new ResourceNotFoundException("Payment not found"));
	}

	@Override
	public List<Payment> getPaymentsByUserId(Long userId) {
		return paymentRepository.findByUserId(userId);
	}

	@Override
	public List<Payment> getPaymentsByBookingId(Long bookingId) {
		return paymentRepository.findByBookingId(bookingId).map(List::of).orElseThrow(()->new ResourceNotFoundException("No Payments Found With This Booking Id"));
	}

	@Transactional
	@Override
	public void updatePaymentStatus(String transactionId, String status) {
		Payment payment = getPaymentByTransactionId(transactionId);
		payment.setStatus(PaymentStatus.valueOf(status.toUpperCase()));
		paymentRepository.save(payment);
		log.info("Updated payment status for Transaction ID: {} to {}", transactionId, status);
	}
}
