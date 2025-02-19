package com.connect2play.dto;

import java.util.List;

import com.connect2play.entities.Sports;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
//Jay Shree Ram
public class TeamResponseDTO extends BaseDTO {
	private String name;
    private Sports sportType;
    private String description;
    private UserSummaryDTO creator;
    private Integer memberCount;
    private List<String> members;
    private List<UserSummaryDTO> registeredMembers;
    private String teamLogo;
    private int totalWins;
    private int totalLosses;
    private int points;
    private List<TeamRequestResponseDTO> teamRequests; // List of requests related to this team
    private List<ChallengeResponseDTO> challengesSent; //   DTO for sent challenges
    private List<ChallengeResponseDTO> challengesReceived; //   DTO for received challenges

}




