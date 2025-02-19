package com.connect2play.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO for Turf booking history, which tracks bookings for a particular turf
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TurfBookingHistoryDTO {
    
    private Long bookingId;
    
    private Long userId; // ID of the user who booked the turf
    
    private LocalDateTime bookingDate;
    
    private LocalDateTime startTime;
    
    private LocalDateTime endTime;
    
    private double amount; // Amount paid for the booking
    
    private String status; // Booking status (e.g., confirmed, completed, canceled)
}
