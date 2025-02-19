package com.connect2play.dto;

import com.connect2play.entities.RequestStatus;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//Jay Shree Ram 
//Common DTO for Both Request & Response
//This will be used for sending and receiving team request data.

public class TeamRequestDTO {

    private Long id; // Request ID

    @NotNull(message = "createdBy ID is required")
    private Long createdBy; // ID of the user who sent the invite

    @NotNull(message = "requestedUser ID is required")
    private Long requestedUser; // ID of the user receiving the invite

    @NotNull(message = "Team ID is required")
    private Long teamId; // ID of the team for which the invite is sent

    @NotNull(message = "Request status is required")
    private RequestStatus status; // Request Status (Pending, Approved, Rejected)
}

