package com.dhk.entity;

import java.io.Serializable;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	
	@Column(name="fullName",length = 255)
	private String fullname;
	
	@Column(name="birthday")
	private Date birthday;
	
	@Column(name="gender",length = 20)
	private String gender;
	
	@Column(name="address",length = 255)
	private String address;
	
	@Column(name="numberPhone", nullable = false)
	private Long numberPhone;
	
	@Column(name="start", nullable = false)
	private SimpleDateFormat start; 
	
	@Column(name="status",nullable = false,length = 100)
	private String status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
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

	public Long getNumberPhone() {
		return numberPhone;
	}

	public void setNumberPhone(Long numberPhone) {
		this.numberPhone = numberPhone;
	}

	public SimpleDateFormat getStart() {
		return start;
	}

	public void setStart(SimpleDateFormat start) {
		this.start = start;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
