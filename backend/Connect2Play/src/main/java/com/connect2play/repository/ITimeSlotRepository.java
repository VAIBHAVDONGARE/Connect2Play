package com.connect2play.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.connect2play.entities.TimeSlot;
import com.connect2play.entities.TimeSlot.SlotStatus;

public interface ITimeSlotRepository extends JpaRepository<TimeSlot, Long>{
	List<TimeSlot> findByTurfId(Long turfId);

    List<TimeSlot> findByStatus(SlotStatus status);

    List<TimeSlot> findByStartTimeBetween(LocalDateTime start, LocalDateTime end);

}
