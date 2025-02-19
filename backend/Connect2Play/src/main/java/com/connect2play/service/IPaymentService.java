package com.connect2play.service;

import java.util.List;

import com.connect2play.dto.PaymentDTO;
import com.connect2play.entities.Payment;

public interface IPaymentService {
    
    Payment createPayment(PaymentDTO paymentDTO);

    Payment getPaymentByTransactionId(String transactionId);

    List<Payment> getPaymentsByUserId(Long userId);

    List<Payment> getPaymentsByBookingId(Long bookingId);

    void updatePaymentStatus(String transactionId, String status);
}
