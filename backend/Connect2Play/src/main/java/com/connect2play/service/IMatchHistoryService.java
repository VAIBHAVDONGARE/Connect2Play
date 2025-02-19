package com.connect2play.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.connect2play.dto.MatchHistoryDTO;
import com.connect2play.entities.MatchResult;
import com.connect2play.entities.Sports;
import com.connect2play.entities.Team;

public interface IMatchHistoryService {

    List<MatchHistoryDTO> getAllMatches();

    MatchHistoryDTO getMatchById(Long id);

    List<MatchHistoryDTO> getMatchesByTeam(Team team);

    List<MatchHistoryDTO> getMatchesBySport(Sports sport);

    List<MatchHistoryDTO> getMatchesByResult(MatchResult result);

    List<MatchHistoryDTO> getMatchesByDateRange(LocalDateTime startDate, LocalDateTime endDate);

    Page<MatchHistoryDTO> getMatchesByTeamWithPagination(Team team, Pageable pageable);

    MatchHistoryDTO saveMatch(MatchHistoryDTO matchDTO);

    MatchHistoryDTO updateMatch(Long id, MatchHistoryDTO matchDTO);

    void deleteMatch(Long id);
}
