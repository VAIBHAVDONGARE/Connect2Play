package com.connect2play.dto;

import java.time.LocalDateTime;

import com.connect2play.entities.BookingStatus;
import com.connect2play.entities.Sports;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO for booking a turf.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TurfBookingDTO extends BaseDTO {

	private Long bookingId;
    private UserSummaryDTO user;           // ID 
    private TurfSummaryDTO turf; 
    private Sports sportType; 
    private LocalDateTime bookingDate; // Date and time for the booking
    private TimeSlotDTO timeSlot;     // Duration of booking (in hours)
    private BookingStatus status; 
    private Double totalAmount; 
    private PaymentDTO payment;// Status of the booking (confirmed, pending, cancelled, etc.)
}
