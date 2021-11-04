package com.dhk.service;

import com.dhk.DTO.DoctorDTO;
import com.dhk.entity.Doctor;
import com.dhk.utils.ResponseJwt;

public interface IDoctorService {

	ResponseJwt registerDoctor(DoctorDTO doctor);
}
