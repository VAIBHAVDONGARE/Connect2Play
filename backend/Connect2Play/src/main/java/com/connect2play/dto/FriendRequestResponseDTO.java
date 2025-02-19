package com.connect2play.dto;

import java.time.LocalDateTime;

import com.connect2play.entities.RequestStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO for returning Friend Request details.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FriendRequestResponseDTO extends BaseDTO {
    
    // A lightweight representation of the sender.
    private UserSummaryDTO sender;
    
    private UserSummaryDTO receiver;
    
    private RequestStatus status;
    
    private LocalDateTime sentTime;
    
    private LocalDateTime lastModifiedTime;
    
    private LocalDateTime responseTime;
    
    // Optional response message.
    private String responseMessage;
}

