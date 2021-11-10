package com.dhk.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhk.DTO.JwtResponse;
import com.dhk.DTO.LoginRequest;
import com.dhk.DTO.MessageResponse;
import com.dhk.DTO.SignupRequest;
import com.dhk.service.IAccountDetailsService;

@CrossOrigin(origins="*",maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AccountController {
	
	@Autowired
	private IAccountDetailsService service;
	String urlScope = "https://oauth-api.cloud.huawei.com/rest.php";

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateAccount( @Validated @RequestBody LoginRequest loginRequest){
		return  new ResponseEntity<JwtResponse> ( service.authenticateAccount(loginRequest), HttpStatus.OK );
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> registerAccount(@Validated @RequestBody SignupRequest signupRequest){
		
		return new ResponseEntity<MessageResponse> (service.registerAccount(signupRequest),HttpStatus.OK);
	}
	
}
