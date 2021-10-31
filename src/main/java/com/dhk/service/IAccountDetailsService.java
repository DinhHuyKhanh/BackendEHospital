package com.dhk.service;

import com.dhk.DTO.JwtResponse;
import com.dhk.DTO.LoginRequest;
import com.dhk.DTO.MessageResponse;
import com.dhk.DTO.SignupRequest;

public interface IAccountDetailsService {
	
	JwtResponse authenticateAccount( LoginRequest loginRequest );
	MessageResponse  registerAccount(SignupRequest signupRequest);
	
}
