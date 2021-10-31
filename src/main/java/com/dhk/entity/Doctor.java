package com.dhk.entity;

import java.io.Serializable;
import java.sql.Date;

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
	private Date birthday;
	
	
	@Column(name="gender")
	private String gender;
	
	
	@Column(name="address", length = 255)
	private String address;
	
	@Column(name="numberPhone")
	private Long numberPhone;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "department_id",referencedColumnName = "id")
	private Department department;	

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

	public String getDepartment() {
		return department.getDepartment();
	}
	
}
