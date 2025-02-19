package com.connect2play.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.connect2play.entities.TimeSlot.SlotStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeSlotDTO {
	private LocalDateTime startTime;
    private LocalDateTime endTime;
    private SlotStatus status;  // Enum for status

    private TurfResponseDTO turf;  
    private List<BookingResponseDTO> bookings;  

}
//private Long turfId;  // To avoid full Turf object loading (better performance)
//private List<Long> bookingIds;  // Only store booking IDs instead of full Booking objects
//package com.connect2play.mapper;
//
//import com.connect2play.dto.TimeSlotDTO;
//import com.connect2play.entities.TimeSlot;
//
//import java.util.stream.Collectors;
//
//public class TimeSlotMapper {
//
//    public static TimeSlotDTO toDTO(TimeSlot timeSlot) {
//        return new TimeSlotDTO(
//            timeSlot.getId(),
//            timeSlot.getStartTime(),
//            timeSlot.getEndTime(),
//            timeSlot.getStatus(),
//            timeSlot.getTurf().getId(),  // Extract Turf ID
//            timeSlot.getBookings() != null ? 
//                timeSlot.getBookings().stream().map(booking -> booking.getId()).collect(Collectors.toList()) 
//                : null // Convert Booking objects to a list of Booking IDs
//        );
//    }
//}
//
//package com.connect2play.dto;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//import com.connect2play.entities.TimeSlot.SlotStatus;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//public class TimeSlotDTO {
//
//    private Long id;
//    private LocalDateTime startTime;
//    private LocalDateTime endTime;
//    private SlotStatus status;  // Enum for status
//
//    private Long turfId;  // To avoid full Turf object loading (better performance)
//    private List<Long> bookingIds;  // Only store booking IDs instead of full Booking objects
//
//    // Additional fields can be added as per future requirements.
//}

