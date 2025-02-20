package com.connect2play.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.connect2play.dto.MatchHistoryDTO;
import com.connect2play.entities.MatchResult;
import com.connect2play.entities.Sports;
import com.connect2play.entities.Team;
import com.connect2play.service.IMatchHistoryService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/matches")
@RequiredArgsConstructor
public class MatchHistoryController {

    private final IMatchHistoryService matchHistoryService;

    @GetMapping
    public ResponseEntity<List<MatchHistoryDTO>> getAllMatches() {
        return ResponseEntity.ok(matchHistoryService.getAllMatches());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatchHistoryDTO> getMatchById(@PathVariable Long id) {
        MatchHistoryDTO match = matchHistoryService.getMatchById(id);
        return (match != null) ? ResponseEntity.ok(match) : ResponseEntity.notFound().build();
    }

    @GetMapping("/team/{teamId}")
    public ResponseEntity<List<MatchHistoryDTO>> getMatchesByTeam(@PathVariable Long teamId) {
        Team team = new Team();
        team.setId(teamId);
        return ResponseEntity.ok(matchHistoryService.getMatchesByTeam(team));
    }

    @GetMapping("/sport/{sportType}")
    public ResponseEntity<List<MatchHistoryDTO>> getMatchesBySport(@PathVariable Sports sportType) {
        return ResponseEntity.ok(matchHistoryService.getMatchesBySport(sportType));
    }

    @GetMapping("/result/{result}")
    public ResponseEntity<List<MatchHistoryDTO>> getMatchesByResult(@PathVariable MatchResult result) {
        return ResponseEntity.ok(matchHistoryService.getMatchesByResult(result));
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<MatchHistoryDTO>> getMatchesByDateRange(
            @RequestParam LocalDateTime startDate,
            @RequestParam LocalDateTime endDate) {
        return ResponseEntity.ok(matchHistoryService.getMatchesByDateRange(startDate, endDate));
    }

    @GetMapping("/team/{teamId}/paged")
    public ResponseEntity<Page<MatchHistoryDTO>> getMatchesByTeamWithPagination(
            @PathVariable Long teamId, Pageable pageable) {
        Team team = new Team();
        team.setId(teamId);
        return ResponseEntity.ok(matchHistoryService.getMatchesByTeamWithPagination(team, pageable));
    }

    @PostMapping
    public ResponseEntity<MatchHistoryDTO> createMatch(@Valid @RequestBody MatchHistoryDTO matchDTO) {
        return new ResponseEntity<>(matchHistoryService.saveMatch(matchDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MatchHistoryDTO> updateMatch(
            @PathVariable Long id, @Valid @RequestBody MatchHistoryDTO matchDTO) {
        MatchHistoryDTO updatedMatch = matchHistoryService.updateMatch(id, matchDTO);
        return (updatedMatch != null) ? ResponseEntity.ok(updatedMatch) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMatch(@PathVariable Long id) {
        matchHistoryService.deleteMatch(id);
        return ResponseEntity.noContent().build();
    }
}
