package com.connect2play.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.connect2play.dto.TeamCreateDTO;
import com.connect2play.dto.TeamRequestDTO;
import com.connect2play.dto.TeamRequestResponseDTO;
import com.connect2play.dto.TeamResponseDTO;
import com.connect2play.dto.TeamSummaryDTO;
import com.connect2play.entities.Sports;

public interface ITeamService {

	// Fetch a team by its ID
	Optional<TeamResponseDTO> getTeamById(Long teamId);

	// Fetch all teams with pagination
	Page<TeamSummaryDTO> getAllTeams(Pageable pageable);

	// Search teams by multiple filters
	Page<TeamSummaryDTO> searchTeams(String name, Sports sportType, Integer minMembers, Integer minWins,
			Integer maxLosses, Pageable pageable);

	// Create a new team (check if the name already exists)
	void createTeam(TeamCreateDTO teamDTO);

	public TeamRequestDTO inviteUserToTeam(Long creatorId, Long userId, Long teamId, String message);

	// Fetch teams that sent challenges to the user
	List<TeamSummaryDTO> getTeamsThatSentChallenges(Long userId);

	// Fetch teams that received challenges from the user
	List<TeamSummaryDTO> getTeamsThatReceivedChallenges(Long userId);

	public List<TeamRequestResponseDTO> getPendingRequests(Long teamId);

}
