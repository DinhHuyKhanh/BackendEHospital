package com.dhk.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhk.DTO.DoctorDTO;
import com.dhk.Respository.IDoctorRepository;
import com.dhk.entity.Doctor;
import com.dhk.service.IDoctorService;
import com.dhk.utils.ResponseJwt;

@CrossOrigin(origins="*",maxAge = 3600)
@RestController
@RequestMapping("/api/v1/doctors")
public class DoctorController {
	
	@Autowired
	IDoctorService service;
	
	@Autowired
	IDoctorRepository repository;
	
	@PostMapping("/create")
	public ResponseEntity<?> createDoctor( @Validated @RequestBody DoctorDTO doctor ){
		
		return new ResponseEntity<ResponseJwt>(service.registerDoctor(doctor), HttpStatus.OK);
	}
	
	@GetMapping("/department/id={id}")
	public ResponseEntity<?> getDoctorByDepartmentId(@PathVariable(name="id") int id){
		
		return new ResponseEntity<List<Doctor>>( service.getDoctorByDepartment(id), HttpStatus.OK);
	}
	
	
	@GetMapping("/id={id}")
	public ResponseEntity<?> getDoctorById( @PathVariable(name="id") int id){
			return new ResponseEntity<Doctor>(repository.findById(id).get(), HttpStatus.OK);
	}

}
