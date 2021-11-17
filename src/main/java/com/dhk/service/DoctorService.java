package com.dhk.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhk.DTO.DoctorDTO;
import com.dhk.Respository.IDepartmentRepository;
import com.dhk.Respository.IDoctorRepository;
import com.dhk.entity.Department;
import com.dhk.entity.Doctor;
import com.dhk.utils.ResponseJwt;

@Service
public class DoctorService implements IDoctorService {

	@Autowired
	private IDoctorRepository repository;
	@Autowired
	private IDepartmentRepository departmentRepository;
	
	@Override
	public ResponseJwt registerDoctor(DoctorDTO doctorDTO) {
		// TODO Auto-generated method stub
		ResponseJwt result = new ResponseJwt();
		try {			
			Optional<Department> department = departmentRepository.findById(doctorDTO.getDepartmentId());	
			
			Doctor doctor = new Doctor(doctorDTO.getFullName(), doctorDTO.getBirthday(),doctorDTO.getGender(), doctorDTO.getAddress(), doctorDTO.getExperience()
					, doctorDTO.getDegree(), doctorDTO.getCost(), doctorDTO.getNumberPhone(), department.get() );
			
			repository.save(doctor);
			result.setMessage("status: 200");
			result.setData("Successfully !");
			
		}catch(Exception e) {
			//System.out.print("exception: " + e.getMessage());
			result.setMessage("status: error");
			result.setData("Failed!");
		}
		
		
		return result;
	}

	@Override
	public List<Doctor> getDoctorByDepartment(int id) {
		// TODO Auto-generated method stub
		Optional<Department> department = departmentRepository.findById(id);
		List<Doctor> doctor = repository.findDoctorByDepartment(department.get());
		return doctor;
	}

	
}
