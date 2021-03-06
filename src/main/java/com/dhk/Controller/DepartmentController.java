package com.dhk.Controller;

import java.util.List;

import javax.websocket.server.PathParam;

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

import com.dhk.Respository.IDepartmentRepository;
import com.dhk.entity.Department;
import com.dhk.service.IDepartmentService;
import com.dhk.utils.ResponseJwt;

@CrossOrigin(origins="*",maxAge = 3600)
@RestController
@RequestMapping("/api/v1/departments")
public class DepartmentController {
	
	@Autowired
	private IDepartmentService departmentService;
	
	@Autowired
	private IDepartmentRepository departmentRepository;
	
	@GetMapping
	public ResponseEntity<?> getAllDepartment(){
		List<Department> departments = departmentService.getAllDepartments();
		return new ResponseEntity<List<Department>>(departments, HttpStatus.OK);
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> createDepartment( @Validated @RequestBody Department department ){
		
		return new ResponseEntity<ResponseJwt>(departmentService.registerDepartment(department), HttpStatus.OK);
	}
	
	@GetMapping("/id={id}")
	public ResponseEntity<?> getDepartmentById(@PathVariable(name="id") int id){
		return new ResponseEntity<Department>( departmentRepository.findById(id).get(), HttpStatus.OK);
	}
	
}
