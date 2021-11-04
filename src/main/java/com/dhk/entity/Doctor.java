package com.dhk.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Doctor")
public class Doctor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name="id")
	private int id;
	
	
	@Column(name="fullName", length = 255, nullable = false)
	private String fullName;
	
	@Column(name="birthday")
	private String birthday;
	
	
	@Column(name="gender")
	private String gender;
	
	
	@Column(name="address", length = 255)
	private String address;
	
	@Column(name="numberPhone", unique = true)
	private String numberPhone;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "department_id",referencedColumnName = "id")
	private Department department;

	public Doctor( String fullName, String birthday, String gender, String address, String numberPhone,
			Department department) {
		super();
		this.fullName = fullName;
		this.birthday = birthday;
		this.gender = gender;
		this.address = address;
		this.numberPhone = numberPhone;
		this.department = department;
	}
	
	public Doctor() {
		super();
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

	public String getDepartment() {
		return department.getDepartment();
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
	
	
	
	
}
