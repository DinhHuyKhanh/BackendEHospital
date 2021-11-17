package com.dhk.Respository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dhk.entity.Department;
import com.dhk.entity.Doctor;

@Repository
public interface IDoctorRepository extends JpaRepository<Doctor, Integer>{
	List<Doctor> findDoctorByDepartment(Department department);
}
