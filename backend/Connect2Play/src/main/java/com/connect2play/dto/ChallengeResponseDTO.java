package com.connect2play.dto;

import java.time.LocalDateTime;

import com.connect2play.entities.RequestStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ChallengeResponseDTO extends BaseDTO{
	private Long challengeId;
	private TeamSummaryDTO challenger; // ✅ Instead of full TeamDetailsResponseDTO, a summary DTO
    private TeamSummaryDTO opponent;
    private RequestStatus status;
    private LocalDateTime scheduledTime;
    private TurfResponseDTO turf;
    private String challengeMessage;
    private LocalDateTime expiryTime;
    private LocalDateTime responseTime;
    private String responseMessage;
    private boolean cancelled;
    private String cancellationReason;
    private boolean reminderSent;
    private BookingResponseDTO booking;
}
