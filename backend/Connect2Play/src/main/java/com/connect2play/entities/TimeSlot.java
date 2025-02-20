package com.connect2play.entities;

import java.time.LocalDateTime;
import java.util.List;

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
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "time_slots")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TimeSlot {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "time_slot_id")
	private Long id;

	@CreationTimestamp
	@Column(nullable = false, updatable = false)
	private LocalDateTime createdAt;

	@UpdateTimestamp
	@Column(nullable = false)
	private LocalDateTime updatedAt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "turf_id", nullable = false)
	private Turf turf; // Reference to Turf

	@Column(name = "start_time", nullable = false)
	private LocalDateTime startTime;

	@Column(name = "end_time", nullable = false)
	private LocalDateTime endTime;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false)
	private SlotStatus status = SlotStatus.AVAILABLE;

	@OneToMany(mappedBy = "timeSlot", cascade = CascadeType.ALL) // One TimeSlot can have many Bookings
	private List<Booking> bookings;

	public enum SlotStatus {
		AVAILABLE, BOOKED, RESERVED
	}

	@PrePersist
	@PreUpdate
	private void validateTimeRange() {
		if (startTime.isAfter(endTime)) {
			throw new IllegalStateException("Start time must be before end time");
		}
	}

	// Helper method to check for overlapping time slots
	public boolean overlaps(LocalDateTime otherStart, LocalDateTime otherEnd) {
		if (otherStart == null || otherEnd == null) {
			throw new IllegalArgumentException("Start and end times cannot be null");
		}
		if (otherStart.isAfter(otherEnd)) {
			throw new IllegalArgumentException("Start time cannot be after end time");
		}

		return (otherStart.isBefore(endTime) && otherStart.isAfter(startTime))
				|| (otherEnd.isAfter(startTime) && otherEnd.isBefore(endTime))
				|| (otherStart.isBefore(startTime) && otherEnd.isAfter(endTime)) || otherStart.equals(startTime)
				|| otherEnd.equals(endTime);
	}

	@Version
	@Column(name = "version")
	private Long version;
}
