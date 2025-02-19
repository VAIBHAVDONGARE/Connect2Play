package com.connect2play.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginDTO extends BaseDTO {
	
	 	@NotBlank(message = "Email is required")
	    @Email(message = "Invalid email format")
	    private String email;

	    @NotBlank(message = "Password is required")
	    private String password;
	    
	    private String token;
	    private String message;
	    
	    public UserLoginDTO(String token, String message) {
	        this.token = token;
	        this.message = message;
	    }
}




   

