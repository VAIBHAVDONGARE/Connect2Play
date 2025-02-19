package com.connect2play.service;

import java.time.LocalDateTime;
import java.util.List;

import com.connect2play.dto.BookingRequestDTO;
import com.connect2play.dto.TurfAvailabilityDTO;
import com.connect2play.dto.TurfResponseDTO;

public interface IBookingService {
    public TurfResponseDTO bookTurf(BookingRequestDTO request);
    
    public String cancelBooking(Long bookingId);
    
    public TurfAvailabilityDTO checkTurfAvailability(Long turfId, LocalDateTime startTime, LocalDateTime endTime);

    public List<TurfResponseDTO> getAllBookings();


}
