package com.connect2play.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.connect2play.dto.TeamCreateDTO;
import com.connect2play.dto.TeamRequestDTO;
import com.connect2play.dto.TeamRequestResponseDTO;
import com.connect2play.dto.TeamResponseDTO;
import com.connect2play.dto.TeamSummaryDTO;
import com.connect2play.entities.Sports;
import com.connect2play.service.ITeamService;

@RestController
@RequestMapping("/api/teams")
public class TeamController {

    @Autowired
    private ITeamService teamService;

    // Get a team by ID
    @GetMapping("/{teamId}")
    public ResponseEntity<TeamResponseDTO> getTeamById(@PathVariable Long teamId) {
        Optional<TeamResponseDTO> team = teamService.getTeamById(teamId);
        return team.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get all teams with pagination
    @GetMapping
    public ResponseEntity<Page<TeamSummaryDTO>> getAllTeams(Pageable pageable) {
        return ResponseEntity.ok(teamService.getAllTeams(pageable));
    }

    // Search teams with filters
    @GetMapping("/search")
    public ResponseEntity<Page<TeamSummaryDTO>> searchTeams(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Sports sportType,
            @RequestParam(required = false) Integer minMembers,
            @RequestParam(required = false) Integer minWins,
            @RequestParam(required = false) Integer maxLosses,
            Pageable pageable) {
        return ResponseEntity.ok(teamService.searchTeams(name, sportType, minMembers, minWins, maxLosses, pageable));
    }

    // Create a new team
    @PostMapping
    public ResponseEntity<String> createTeam(@RequestBody TeamCreateDTO teamDTO) {
        teamService.createTeam(teamDTO);
        return ResponseEntity.ok("Team created successfully");
    }

    // Invite a user to a team
    @PostMapping("/{teamId}/invite/{userId}")
    public ResponseEntity<TeamRequestDTO> inviteUserToTeam(
            @PathVariable Long teamId,
            @PathVariable Long userId,
            @RequestParam Long creatorId,
            @RequestParam String message) {
        return ResponseEntity.ok(teamService.inviteUserToTeam(creatorId, userId, teamId, message));
    }

    // Get pending invitations for a team
    @GetMapping("/{teamId}/pending-requests")
    public ResponseEntity<List<TeamRequestResponseDTO>> getPendingRequests(@PathVariable Long teamId) {
        return ResponseEntity.ok(teamService.getPendingRequests(teamId));
    }

    // Get teams that sent challenges to a user
    @GetMapping("/challenges/sent/{userId}")
    public ResponseEntity<List<TeamSummaryDTO>> getTeamsThatSentChallenges(@PathVariable Long userId) {
        return ResponseEntity.ok(teamService.getTeamsThatSentChallenges(userId));
    }

    // Get teams that received challenges from a user
    @GetMapping("/challenges/received/{userId}")
    public ResponseEntity<List<TeamSummaryDTO>> getTeamsThatReceivedChallenges(@PathVariable Long userId) {
        return ResponseEntity.ok(teamService.getTeamsThatReceivedChallenges(userId));
    }
}
