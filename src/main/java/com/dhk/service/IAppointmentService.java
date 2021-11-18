package com.dhk.service;


import java.util.List;

import com.dhk.DTO.AppointmentDTO;
import com.dhk.entity.Appointment;
import com.dhk.utils.ResponseJwt;

public interface IAppointmentService {
	
	List<Appointment> findAll();
	
	List<Appointment> 	findByAccountId(int id);
	
	ResponseJwt 	createAppointment( AppointmentDTO appointmentDTO);
	ResponseJwt		updateAppointment(AppointmentDTO appointmentDTO);
	ResponseJwt 	AbortAppointment(int id);
}
