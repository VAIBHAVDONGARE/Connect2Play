package com.connect2play.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.connect2play.dto.MatchHistoryDTO;
import com.connect2play.entities.MatchHistory;
import com.connect2play.entities.MatchResult;
import com.connect2play.entities.Sports;
import com.connect2play.entities.Team;
import com.connect2play.repository.IMatchHistoryRepository;

@Service
@Transactional
public class MatchHistoryServiceImpl implements IMatchHistoryService {

    @Autowired
    private IMatchHistoryRepository matchHistoryRepository;

    @Autowired
    private ModelMapper modelMapper;  // Injecting ModelMapper

    @Override
    public List<MatchHistoryDTO> getAllMatches() {
        return matchHistoryRepository.findAll().stream()
                .map(match -> modelMapper.map(match, MatchHistoryDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public MatchHistoryDTO getMatchById(Long id) {
        Optional<MatchHistory> match = matchHistoryRepository.findById(id);
        return match.map(m -> modelMapper.map(m, MatchHistoryDTO.class)).orElse(null);
    }

    @Override
    public List<MatchHistoryDTO> getMatchesByTeam(Team team) {
        return matchHistoryRepository.findByTeam(team).stream()
                .map(match -> modelMapper.map(match, MatchHistoryDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<MatchHistoryDTO> getMatchesBySport(Sports sport) {
        return matchHistoryRepository.findBySportType(sport).stream()
                .map(match -> modelMapper.map(match, MatchHistoryDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<MatchHistoryDTO> getMatchesByResult(MatchResult result) {
        return matchHistoryRepository.findByResult(result).stream()
                .map(match -> modelMapper.map(match, MatchHistoryDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<MatchHistoryDTO> getMatchesByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return matchHistoryRepository.findByMatchDateBetween(startDate, endDate).stream()
                .map(match -> modelMapper.map(match, MatchHistoryDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Page<MatchHistoryDTO> getMatchesByTeamWithPagination(Team team, Pageable pageable) {
        return matchHistoryRepository.findByTeamWithPagination(team, pageable)
                .map(match -> modelMapper.map(match, MatchHistoryDTO.class));
    }

    @Override
    @Transactional
    public MatchHistoryDTO saveMatch(MatchHistoryDTO matchDTO) {
        MatchHistory match = modelMapper.map(matchDTO, MatchHistory.class);
        return modelMapper.map(matchHistoryRepository.save(match), MatchHistoryDTO.class);
    }

    @Override
    @Transactional
    public MatchHistoryDTO updateMatch(Long id, MatchHistoryDTO matchDTO) {
        Optional<MatchHistory> existingMatch = matchHistoryRepository.findById(id);
        if (existingMatch.isPresent()) {
            MatchHistory match = modelMapper.map(matchDTO, MatchHistory.class);
            match.setId(id);
            return modelMapper.map(matchHistoryRepository.save(match), MatchHistoryDTO.class);
        }
        return null;
    }

    @Override
    @Transactional
    public void deleteMatch(Long id) {
        matchHistoryRepository.deleteById(id);
    }
}
