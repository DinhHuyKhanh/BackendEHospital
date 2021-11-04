package com.dhk.service;


import com.dhk.DTO.AppointmentDTO;
import com.dhk.utils.ResponseJwt;

public interface IAppointmentService {
	
	ResponseJwt 	findAll();
	
	ResponseJwt 	findByAccountId(int id);
	
	ResponseJwt 	createAppointment( AppointmentDTO appointmentDTO);
	ResponseJwt		updateAppointment(AppointmentDTO appointmentDTO);
}
