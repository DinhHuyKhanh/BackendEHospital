package com.dhk.Respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dhk.entity.Appointment;

@Repository
public interface IApointmentRepository extends JpaRepository<Appointment, Long> {

}
