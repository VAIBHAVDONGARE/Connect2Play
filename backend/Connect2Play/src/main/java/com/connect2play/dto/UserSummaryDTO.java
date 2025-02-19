package com.connect2play.dto;

import java.time.LocalDateTime;

import com.connect2play.entities.Gender;
import com.connect2play.entities.UserRole;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Lightweight DTO for basic user information.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserSummaryDTO extends BaseDTO {
    
    private String fullName;
    
    private String profileImageUrl;
    
    private String bio;
    
    private Gender gender;
    
    private UserRole user;
    
    private Boolean userStatus;  // User's status (active/inactive)

    private LocalDateTime lastLogin; 
    
}
