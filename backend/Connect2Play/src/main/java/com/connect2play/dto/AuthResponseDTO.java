package com.connect2play.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class AuthResponseDTO extends BaseDTO {
	private String accessToken; // JWT Token or Session Token
    private String refreshToken;
	private UserDetailsResponseDTO user;
}
