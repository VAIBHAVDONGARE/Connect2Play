package com.connect2play.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO for sending Friend Request.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FriendRequestDTO {
    
    private Long senderId;  // Sender's user ID
    private Long receiverId; // Receiver's user ID
    private String message;  // Optional message with the request (e.g., "Let's be friends!")
}
