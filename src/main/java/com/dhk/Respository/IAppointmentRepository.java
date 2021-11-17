package com.dhk.Respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dhk.DTO.AppointmentDTO;
import com.dhk.entity.Appointment;
import com.dhk.utils.ResponseJwt;

@Repository
public interface IAppointmentRepository extends JpaRepository<Appointment, Integer> {

	List<Appointment> 	findApoinAppointmentsByAccountId(int id);

}
