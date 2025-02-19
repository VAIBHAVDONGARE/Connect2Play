package com.connect2play.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserBasicDetailsDTO {

    private Long userId;        // User ID
    private String fullName;    // Full name of the user
    private String email;       // Email address
    private String phoneNumber; // Contact number
}
