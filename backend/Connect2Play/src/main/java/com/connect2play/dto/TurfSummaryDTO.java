package com.connect2play.dto;

import java.util.List;

import com.connect2play.entities.Sports;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Lightweight DTO for listing turfs with basic information. This DTO is used
 * for turf search/listing with essential details.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TurfSummaryDTO extends BaseDTO {

	private String name; // Turf name

	private String streetAddress;
	
	private String city;
	
	private String state;
	
	private String country;
	
	private Sports sportType; // Turf location (e.g., "New York, NY, USA")

	private Double pricePerHour; // Price per hour for renting the turf

	private List<String> imageUrl; // URL of the primary image for the turf (e.g., a thumbnail)
	
    private Boolean isAvailable;    // Turf availability status

}
