package com.dhk.service;

import java.util.List;

import com.dhk.entity.Department;
import com.dhk.utils.ResponseJwt;

public interface IDepartmentService {
	
	List<Department> getAllDepartments();
	
	ResponseJwt registerDepartment(Department department);

}
