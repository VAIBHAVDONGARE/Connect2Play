package com.connect2play.dto;

import com.connect2play.entities.Sports;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//Jay Shree Ram
// Used when listing teams (without full details).
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompactTeamResponseDTO {

	private Long id;
	private String name;
	private Sports sportType;
	private String teamLogo;
	private int totalWins;
	private int totalLosses;
	private int points;

}
