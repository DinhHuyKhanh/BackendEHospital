package com.dhk.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhk.DTO.AppointmentDTO;
import com.dhk.Respository.IAppointmentRepository;
import com.dhk.entity.Appointment;
import com.dhk.utils.ResponseJwt;

@Service
public class AppointmentService implements IAppointmentService {

	@Autowired 
	private IAppointmentRepository repository;
	@Override
	public ResponseJwt findAll() {
		// TODO Auto-generated method stub
		ResponseJwt result = new ResponseJwt();
		try {
			result.setMessage("status: 200");
			result.setData(repository.findAll());
		}catch(Exception e){
			result.setMessage(" http error 500 !");
			result.setData("Failed !");
			return result;
		}
		return result;
	}

	@Override
	public ResponseJwt findByAccountId(int id) {
		// TODO Auto-generated method stub
		ResponseJwt result = new ResponseJwt();
		try {
			result.setMessage("status: 200");
			result.setData(repository.findApoinAppointmentsByAccountId(id));
		}catch(Exception e){
			result.setMessage(" http error 500 !");
			result.setData("Failed !");
			return result;
		}
		return result;
	}

	@Override
	public ResponseJwt createAppointment(AppointmentDTO appointmentDTO) {
		// TODO Auto-generated method stub
		Appointment appointment = new Appointment(appointmentDTO.getFullName(),appointmentDTO.getBirthday(),appointmentDTO.getGender(),
				appointmentDTO.getAddress(),appointmentDTO.getNumberPhone(),appointmentDTO.getStart(),appointmentDTO.getAccoutnId(), appointmentDTO.getDoctorId());
		
		ResponseJwt result = new ResponseJwt();
		try {
			repository.save(appointment);
			result.setMessage("status: 200");
			result.setData("Successfully !");
		}catch(Exception e) {
			System.out.println("exception: "+ e);
			result.setMessage("status: error");
			result.setData("Failed !");
		}
		
		return result;
	}

	@Override
	public ResponseJwt updateAppointment(AppointmentDTO appointmentDTO) {
		// TODO Auto-generated method stub
		Optional<Appointment> appointment = repository.findById( appointmentDTO.getId());
		ResponseJwt result = new ResponseJwt();
		if(appointment.get().getStatus().equals("APPROVAL") == false)
		{
			result.setMessage("status: ERROR");
			result.setData("state cannot be changed !");
			return result;
		}
		
		appointment.get().setFullName(appointmentDTO.getFullName());
		appointment.get().setBirthday(appointmentDTO.getBirthday());
		appointment.get().setAddress(appointmentDTO.getAddress());
		appointment.get().setGender(appointmentDTO.getGender());
		appointment.get().setNumberPhone(appointmentDTO.getNumberPhone());
		appointment.get().setStart(appointmentDTO.getStart());
		appointment.get().setDoctorId(appointmentDTO.getDoctorId());
		appointment.get().setStatus(appointmentDTO.getStatus());;
		
		try {
			repository.save(appointment.get());
			result.setMessage("status: 200");
			result.setData("Successfully !");
		}catch(Exception e) {
			result.setMessage("status: error");
			result.setData("Failed !");
		}
		
		return result;
	}
}
