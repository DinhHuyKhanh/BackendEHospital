package com.dhk.Respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dhk.entity.Department;

@Repository
public interface IDepartmentRepository extends JpaRepository<Department, Long> {

}
