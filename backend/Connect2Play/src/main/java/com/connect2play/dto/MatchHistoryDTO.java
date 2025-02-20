package com.connect2play.dto;

import java.time.LocalDateTime;
import java.util.Map;

import com.connect2play.entities.MatchResult;
import com.connect2play.entities.Sports;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MatchHistoryDTO {
    
    private Long matchId;  
    
   // private Long team1Id;  
   // private Long team2Id; 
    
    private TeamResponseDTO team1;
    
    private TeamResponseDTO team2;
    
    private TeamResponseDTO winner;
    
    private MatchResult result;  // WIN, LOSS, DRAW, ABANDONED

    private Map<String, Integer> scores; // Flexible scoring system


    private String matchDetails;  
    
    private Sports sportType;  // Sport Type (Football, Cricket, etc.)

    private LocalDateTime matchDate;  

}
