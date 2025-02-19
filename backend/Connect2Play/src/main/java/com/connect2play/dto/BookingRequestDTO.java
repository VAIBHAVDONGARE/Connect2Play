package com.connect2play.dto;

import java.time.LocalDateTime;

import com.connect2play.entities.BookingStatus;
import com.connect2play.entities.PaymentMethod;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingRequestDTO extends BaseDTO{

    @NotNull(message = "Turf ID is required")
    private Long turfId;       // The turf being booked

    @NotNull(message = "User ID is required")
    private Long userId;       // The user making the booking

    private LocalDateTime bookingDate; // Date when the booking request was made

    @NotNull(message = "Time slot ID is required")
    private Long timeSlotId;   // The time slot for the booking

    private Double totalAmount; // Total amount for the booking

    private BookingStatus status = BookingStatus.PENDING;      // Status of the booking (PENDING, CONFIRMED)
    
    private PaymentMethod paymentMethod;
}
