package com.dhk.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhk.DTO.AppointmentDTO;
import com.dhk.Respository.IAppointmentRepository;
import com.dhk.Respository.IDepartmentRepository;
import com.dhk.Respository.IDoctorRepository;
import com.dhk.entity.Appointment;
import com.dhk.entity.Department;
import com.dhk.entity.Doctor;
import com.dhk.utils.ResponseJwt;

@Service
public class AppointmentService implements IAppointmentService {

	@Autowired 
	private IAppointmentRepository repository;
	
	@Autowired 
	private IDepartmentRepository departmentRepository;
	
	@Autowired
	private IDoctorRepository doctorRepository;
	
	@Override
	public List<Appointment> findAll() {
		// TODO Auto-generated method stub
		 List<Appointment> appointments = repository.findAll();
		return appointments;
	}

	@Override
	public List<Appointment> findByAccountId(int id) {
		// TODO Auto-generated method stub
		
		 return repository.findApoinAppointmentsByAccountId(id);
	}

	@Override
	public ResponseJwt createAppointment(AppointmentDTO appointmentDTO) {
		// TODO Auto-generated method stub
		
		 Optional<Doctor> doctor = doctorRepository.findById(appointmentDTO.getDoctorId());
		Optional<Department> department = departmentRepository.findById(appointmentDTO.getDepartmentId());
		
		Appointment appointment = new Appointment(appointmentDTO.getFullName(),appointmentDTO.getBirthday(),appointmentDTO.getGender(),
				appointmentDTO.getAddress(),appointmentDTO.getNumberPhone(),appointmentDTO.getDateAppointment(),appointmentDTO.getAccoutnId(), doctor.get(), department.get());
		
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
	
		 Optional<Doctor> doctor = doctorRepository.findById(appointmentDTO.getDoctorId());
		Optional<Department> department = departmentRepository.findById(appointmentDTO.getDepartmentId());
			
		ResponseJwt result = new ResponseJwt();
		if(appointment.get().getStatus().equals("PENDING") == false)
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
		appointment.get().setDateAppointment(appointmentDTO.getDateAppointment());
		appointment.get().setDoctor(doctor.get());
		appointment.get().setDepartment(department.get());
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

	@Override
	public ResponseJwt AbortAppointment(int id) {
		// TODO Auto-generated method stub
		Optional<Appointment> entity=  repository.findById(id);
		ResponseJwt result = new ResponseJwt();
		if(entity.get().getStatus().equals("PENDING")) {
			try {
			entity.get().setStatus("ABORT");
			repository.save(entity.get());
			result.setMessage("status: 200");
			
			}catch(Exception e) {
				result.setMessage("error");
			}
		}else {
			result.setMessage("error");
		}
		
		return result;
	}
	
	
}
