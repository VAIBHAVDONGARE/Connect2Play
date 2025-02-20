package com.connect2play.dto;

import com.connect2play.entities.Sports;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO for creating or sending a Challenge.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChallengeRequestDTO {
    
    private Long senderId;           // The ID of the user sending the challenge
    private Long receiverId;         // The ID of the user receiving the challenge
    private Sports sportsType;  // The type of challenge (enum)
    private String description;      // An optional description or message for the challenge
    
    @NotNull(message = "Scheduled date is required")
    private String scheduledDate;
}
