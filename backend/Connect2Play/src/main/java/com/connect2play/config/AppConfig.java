package com.connect2play.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.connect2play.dto.UserRegistrationDTO;
import com.connect2play.entities.User;

@Configuration
public class AppConfig {

	@Bean
	public ModelMapper modelMapper() {
	    ModelMapper modelMapper = new ModelMapper();

		 modelMapper.typeMap(UserRegistrationDTO.class, User.class)
         .addMappings(mapper -> mapper.skip(User::setId));

		return modelMapper;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
}
