package com.in28minutes.springboot.myfirstwebapp.security;

import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SpringSecurityConfiguration {
	
	// The passwordEncoer function of the UserDetails object builder takes a lambda function.
	// Build the lambda function that takes a String password input and return a String output. 
	// This encodes the input string with BCryptPasswordEncoder and then return String.
	
	
	// Use in memory for ease of setup
	// Use a local BCryptPasswordEncoder	
	@Bean
	public InMemoryUserDetailsManager createUserDetailsManager() {
		// Create a new InMemoryUserDetailManager consuming an User object producing an userDetails interface
		UserDetails userDetails1 = createNewUser("in28minutes", "dummy");
		UserDetails userDetails2 = createNewUser("ranga", "dummydummy");
		return new InMemoryUserDetailsManager(userDetails1, userDetails2);
	}

	private UserDetails createNewUser(String username, String password) {
		Function<String, String> passwordEncoder = input -> passwordEncoder().encode(input);
		UserDetails userDetails = User.builder()
									.passwordEncoder(passwordEncoder)
									.username(username)
									.password(password)
									.roles("USER", "ADMIN")
									.build();
		return userDetails;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		// Create a BCrypt Password Encoder
		return new BCryptPasswordEncoder();
	}
	
	
}
