package com.connect2play.dto;

import com.connect2play.entities.Sports;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamSummaryDTO extends BaseDTO {
	  
	    private String name;

	    private Sports sportType;

	    private Integer memberCount;
	    
	    private String teamLogo;

}
