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

import com.dhk.DTO.DoctorDTO;
import com.dhk.entity.Doctor;
import com.dhk.service.IDoctorService;
import com.dhk.utils.ResponseJwt;

@CrossOrigin(origins="*",maxAge = 3600)
@RestController
@RequestMapping("/api/v1/doctors")
public class DoctorController {
	
	@Autowired
	IDoctorService service;
	
	@PostMapping("/create")
	public ResponseEntity<?> createDepartment( @Validated @RequestBody DoctorDTO doctor ){
		
		return new ResponseEntity<ResponseJwt>(service.registerDoctor(doctor), HttpStatus.OK);
	}

}
