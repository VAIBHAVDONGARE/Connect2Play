package com.connect2play.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO for checking turf availability.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TurfAvailabilityDTO {

    private Long turfId;            // Turf ID
    private LocalDateTime startTime; // Start time for the booking request
    private LocalDateTime endTime;   // End time for the booking request
    private Boolean isAvailable;    // Whether the turf is available at the given time
}
