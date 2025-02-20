package com.connect2play.dto;

import java.time.LocalDateTime;

import com.connect2play.entities.BookingStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class BookingResponseDTO {
	private Long bookingId;
	private TurfResponseDTO turf; // Lightweight DTO for Turf
	private UserSummaryDTO user; // Lightweight DTO for User
	private TimeSlotDTO timeSlot; // Lightweight DTO for TimeSlot
	private LocalDateTime bookingStartTime;
	private LocalDateTime bookingEndTime;
	private Double totalAmount;
	private BookingStatus status;
}