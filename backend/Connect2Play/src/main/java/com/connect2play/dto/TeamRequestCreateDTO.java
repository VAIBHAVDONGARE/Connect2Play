package com.connect2play.dto;

import java.time.LocalDateTime;

import com.connect2play.entities.RequestStatus;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//Jay Shree Ram
//For Sending New Request
//This will be used when a user sends a request to join a team.
public class TeamRequestCreateDTO {
	@NotNull(message = "creater ID is required")
	private Long createdBy; // User sending the invite

	@NotNull(message = "requestedUser ID is required")
	private Long requestedUser; // User receiving the invite

	@NotNull(message = "Team ID is required")
	private Long teamId; // Team for which the invite is sent

	private RequestStatus status = RequestStatus.PENDING; // Default status is Pending

	private LocalDateTime sentTime;

	private LocalDateTime lastModifiedTime;

	private LocalDateTime responseTime;
}
