package com.connect2play.dto;

import java.time.LocalTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TurfResponseDTO {
    private String name;

    private String streetAddress;

    private String city;
    
    private String state;

    private String pincode;

    private String country;

    private Double latitude;

    private Double longitude;

    private LocalTime openingTime;

    private LocalTime closingTime;

    private Double pricePerHour;

    private String facilities;

    private Double width;

    private Double height;

    private Double length;
    
    private String ownerFullName;  // owner's name
    private List<String> turfImages;
    private int totalBookings;// image URLs 
    private int totalEarnings;
    private Boolean isAvailable;     // Availability status

//    private Long id;
//    private String name;
//    private String location;

}
