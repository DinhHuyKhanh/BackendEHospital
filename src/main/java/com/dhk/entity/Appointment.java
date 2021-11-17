package com.dhk.entity;

import java.io.Serializable;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name="Appointment")
public class Appointment implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="fullName",length = 255, nullable = false)
	private String fullName;
	
	@Column(name="birthday")
	private String birthday;
	
	@Column(name="gender",length = 20)
	private String gender;
	
	@Column(name="address",length = 255)
	private String address;
	
	@Column(name="numberPhone", nullable = false)
	private String numberPhone;
	
	@Column(name="dateAppointment", nullable = false)
	private String dateAppointment; 
	
	@Column(name="status",nullable = false,length = 100)
	private String status="PENDING";
	
	
	@Column(name="accountId",nullable = false)
	private int accountId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "doctorId",referencedColumnName = "id")
	private Doctor doctor;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "departmentId",referencedColumnName = "id")
	private Department department;
	
//	@Column(name="doctorId")
//	private int doctorId;
	
	public Appointment() {
		super();
	}


	public Appointment(String fullName, String birthday, String gender, String address, String numberPhone,
			String dateAppointment, int accountId, Doctor doctor,Department department ) {
		//super();
		this.fullName = fullName;
		this.birthday = birthday;
		this.gender = gender;
		this.address = address;
		this.numberPhone = numberPhone;
		this.dateAppointment = dateAppointment;
		this.accountId = accountId;
		this.doctor = doctor;
		this.department = department;
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNumberPhone() {
		return numberPhone;
	}

	public void setNumberPhone(String numberPhone) {
		this.numberPhone = numberPhone;
	}

	public String getDateAppointment() {
		return dateAppointment;
	}

	public void setDateAppointment(String dateAppointment) {
		this.dateAppointment = dateAppointment;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getAccoutnId() {
		return accountId;
	}

	public void setAccoutnId(int accountId) {
		this.accountId = accountId;
	}


	public int getAccountId() {
		return accountId;
	}


	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}


	public Doctor getDoctor() {
		return doctor;
	}


	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}


	public Department getDepartment() {
		return department;
	}


	public void setDepartment(Department department) {
		this.department = department;
	}	
}
