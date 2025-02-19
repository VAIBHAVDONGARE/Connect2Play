package com.connect2play.dto;

import com.connect2play.entities.RequestStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//Jay Shree Ram
//For Sending Response to Frontend
//This DTO is used to return the response of the team request details.
public class TeamRequestResponseDTO extends BaseDTO {
	private UserSummaryDTO createdBy;

	// The user who is invited to join the team (lightweight).
	private UserSummaryDTO requestedUser;

	// The team for which the invite is being sent (lightweight summary).
	private TeamSummaryDTO team;

	private RequestStatus status;
}
