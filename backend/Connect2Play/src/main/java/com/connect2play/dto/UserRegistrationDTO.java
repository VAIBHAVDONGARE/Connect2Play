package com.connect2play.dto;

import com.connect2play.entities.Gender;
import com.connect2play.entities.UserRole;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationDTO extends BaseDTO {
	@JsonProperty(access = Access.READ_ONLY)
	private Long id;

	@NotBlank(message = "Full name is required")
	@Size(max = 50, message = "Full name must be less than 50 characters")
	private String fullName;

	@NotBlank(message = "Email is required")
	@Email(message = "Invalid email format")
	@Size(max = 50, message = "Email must be less than 50 characters")
	private String email;

	@NotBlank(message = "Password is required")
	@Size(min = 8, max = 50, message = "Password must be between 8 and 50 characters")
	private String password;

	@NotBlank(message = "Phone number is required")
	@Pattern(regexp = "\\d{10}", message = "Phone number must be 10 digits")
	private String phNumber;

	private String bio;

	private String profileImageUrl;

	@NotNull(message = "Gender is required")
	private Gender gender;

	@NotNull(message = "User role is required")
	private UserRole role;
}
