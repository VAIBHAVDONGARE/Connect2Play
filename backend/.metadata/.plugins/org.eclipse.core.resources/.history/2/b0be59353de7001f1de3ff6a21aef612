package com.connect2play.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.connect2play.entities.Booking;

public interface IBookingRepository extends JpaRepository<Booking, Long> {
	List<Booking> findByUserId(Long userId);

	List<Booking> findByTurfId(Long turfId);

	//List<Booking> findByBookingDate(String date);
	
    @Query("SELECT COUNT(b) FROM Booking b WHERE b.turf.id = :turfId " +
            "AND b.timeSlot.startTime < :endTime " +
            "AND b.timeSlot.endTime > :startTime")
     long countOverlappingBookings(@Param("turfId") Long turfId, 
                                   @Param("startTime") LocalDateTime startTime, 
                                   @Param("endTime") LocalDateTime endTime);
}
