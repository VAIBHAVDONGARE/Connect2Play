package com.connect2play.entities;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "bookings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "booking_id")
	private Long id;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "turf_id", nullable = false)
	private Turf turf;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "time_slot_id", nullable = false)
	private TimeSlot timeSlot; // TimeSlot for the booking

	@Column(name = "total_amount")
	private Double totalAmount;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private BookingStatus status = BookingStatus.PENDING;

	@NotNull(message = "Payment is required")
	@OneToOne(mappedBy = "booking", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Payment payment;

	// Helper method to check if this booking overlaps with given time range
	public boolean overlaps(LocalDateTime otherStart, LocalDateTime otherEnd) {
		// Validate inputs
		if (otherStart == null || otherEnd == null) {
			throw new IllegalArgumentException("Start and end times cannot be null");
		}
		if (otherStart.isAfter(otherEnd)) {
			throw new IllegalArgumentException("Start time cannot be after end time");
		}

		// Check for overlap using TimeSlot's start and end times
		return (otherStart.isBefore(timeSlot.getEndTime()) && otherStart.isAfter(timeSlot.getStartTime()))
				|| (otherEnd.isAfter(timeSlot.getStartTime()) && otherEnd.isBefore(timeSlot.getEndTime()))
				|| (otherStart.isBefore(timeSlot.getStartTime()) && otherEnd.isAfter(timeSlot.getEndTime()))
				|| otherStart.equals(timeSlot.getStartTime()) || otherEnd.equals(timeSlot.getEndTime());
	}

	// Validation method
	@PrePersist
	@PreUpdate
	private void validateTimeRange() {
		// Ensure that the start time is before the end time in the TimeSlot
		if (timeSlot != null && timeSlot.getStartTime() != null && timeSlot.getEndTime() != null
				&& timeSlot.getStartTime().isAfter(timeSlot.getEndTime())) {
			throw new IllegalStateException("Booking start time must be before end time in the selected time slot");
		}
	}

	@Version
	@Column(name = "version")
	private Long version;

	@CreationTimestamp
	@Column(nullable = false, updatable = false)
	private LocalDateTime createdAt;

	@UpdateTimestamp
	@Column(nullable = false)
	private LocalDateTime updatedAt;

}
