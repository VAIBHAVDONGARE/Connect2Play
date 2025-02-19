package com.connect2play.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for showing team statistics.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamStatsDTO {
    private Long teamId;
    private String teamName;
    private int totalWins;
    private int totalLosses;
    private int points;
}
