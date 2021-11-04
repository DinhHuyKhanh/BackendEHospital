package com.dhk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhk.Respository.IDepartmentRepository;
import com.dhk.entity.Department;
import com.dhk.utils.ResponseJwt;

@Service
public class DepartmentService implements IDepartmentService{
	
	@Autowired
	IDepartmentRepository repository;
	
	@Override
	public List<Department> getAllDepartments() {
		// TODO Auto-generated method stub
		
		return repository.findAll();
	}

	@Override
	public ResponseJwt registerDepartment(Department department) {
		// TODO Auto-generated method stub
			ResponseJwt result = new ResponseJwt();
		if(repository.existsByDepartment( department.getDepartment() ) )
		{
			result.setMessage("Failed.");
			result.setData("Department already exists !");
		}else {
			result.setMessage("status: 200");
			result.setData("Successfully !");			
			repository.save(department);
		}
		
		return result;
	}
	
	

	
}
