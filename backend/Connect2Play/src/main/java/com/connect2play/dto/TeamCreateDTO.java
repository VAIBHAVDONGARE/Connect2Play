package com.connect2play.dto;

import java.util.List;

import com.connect2play.entities.Sports;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TeamCreateDTO extends BaseDTO {
	
	
	   @NotBlank(message = "Team name is required")
	    private String teamName;

	    @NotNull(message = "Sport type is required")
	    private Sports sportType;

	    private String description;

	    @NotNull(message = "Creator ID is required")
	    private Long creatorId; // ID of the creator

	    @NotNull(message = "Member count is required")
	    @Min(value = 1, message = "Member count must be at least 1")
	    private Integer memberCount;

	    private List<Long> registeredMemberIds; // IDs of registered members 

	    private String teamLogo; // Optional team logo URL
}



 

   

   

   


