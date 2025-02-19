package com.connect2play.dto;

import java.util.List;

import com.connect2play.entities.Sports;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for registering a new Turf.
 * This DTO is used to send the data when creating or registering a new turf.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TurfRegistrationDTO {

    @NotBlank(message = "Turf name is required")
    @Size(min = 3, max = 100, message = "Turf name must be between 3 and 100 characters")
    private String turfName;            // Turf name

    @NotBlank(message = "Street address is required")
    @Size(max = 200, message = "Street address must not exceed 200 characters")
    private String streetAddress;   // Turf street address

    @NotBlank(message = "City is required")
    @Size(min = 2, max = 100, message = "City must be between 2 and 100 characters")
    private String city;            // Turf city

    @NotBlank(message = "State is required")
    @Size(min = 2, max = 100, message = "State must be between 2 and 100 characters")
    private String state;           // Turf state

    @NotBlank(message = "Pincode is required")
    @Size(min = 6, max = 6, message = "Pincode must be 6 digits")
    private String pincode;         // Turf pincode

    @NotBlank(message = "Country is required")
    @Size(min = 2, max = 100, message = "Country must be between 2 and 100 characters")
    private String country;         // Turf country
    
    @NotNull(message = "Sport type is required")
    private Sports sportType;

    @NotNull(message = "Latitude is required")
    @DecimalMin(value = "-90.0", message = "Latitude must be between -90 and 90")
    private Double latitude;        // Turf latitude

    @NotNull(message = "Longitude is required")
    @DecimalMin(value = "-180.0", message = "Longitude must be between -180 and 180")
    private Double longitude;       // Turf longitude

    @NotNull(message = "Opening time is required")
    private String openingTime;     // Turf opening time (in HH:mm format)

    @NotNull(message = "Closing time is required")
    private String closingTime;     // Turf closing time (in HH:mm format)

    @DecimalMin(value = "0.0", message = "Price per hour must be positive")
    private Double pricePerHour;    // Price per hour to rent the turf

    @Size(max = 500, message = "Facilities description must not exceed 500 characters")
    private String facilities;      // Turf facilities description

    @DecimalMin(value = "0.0", message = "Width must be positive")
    private Double width;           // Turf width

    @DecimalMin(value = "0.0", message = "Height must be positive")
    private Double height;          // Turf height

    @DecimalMin(value = "0.0", message = "Length must be positive")
    private Double length;          // Turf length

    private List<TurfImageDTO> images;  // List of images for the turf (optional for registration)
}
