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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhk.DTO.AppointmentDTO;
import com.dhk.entity.Appointment;
import com.dhk.service.IAppointmentService;
import com.dhk.utils.ResponseJwt;

@CrossOrigin(origins="*",maxAge = 3600)
@RestController
@RequestMapping("/api/v1/appointments")
public class AppointmentController {
	
	@Autowired
	private IAppointmentService service;
	
	@GetMapping
	public ResponseEntity<?> getAllAppointment(){
		return new ResponseEntity<List<Appointment>>(service.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/id={id}")
	public ResponseEntity<?> getAppointmentByAccountId(@PathVariable(name = "id") int id){
		return new ResponseEntity<List<Appointment>>(service.findByAccountId(id),HttpStatus.OK);	
	}
	
	
	@PostMapping("/create")
	public ResponseEntity<?> createAppointment( @Validated @RequestBody AppointmentDTO appointment ){
		ResponseJwt result = new ResponseJwt();
		if(appointment.getDateAppointment() == null || appointment.getNumberPhone() == null || appointment.getDateAppointment() =="" || appointment.getNumberPhone() =="")
		{
			result.setMessage("status: ERROR");
			result.setData("appointment date or number phone is null !");
		}else {
			result = service.createAppointment(appointment);
		}
			return  new ResponseEntity<ResponseJwt>(result,HttpStatus.OK);	
	}
	
	@PostMapping("/update")
	public ResponseEntity<?> updateAppointment( @Validated @RequestBody AppointmentDTO appointment ){
		ResponseJwt result = new ResponseJwt();
		result = service.updateAppointment(appointment);
			return  new ResponseEntity<ResponseJwt>(result,HttpStatus.OK);	
	}
	
	@PutMapping("/abort/{id}")
	public ResponseJwt AbortAppointment(@PathVariable(name="id") int id) {
		
		return service.AbortAppointment(id);
	}
	
	
}
