package com.dhk.DTO;

public class DoctorDTO {
	private String fullName;
	private String birthday;
	private String gender;
	private String address;
	private String numberPhone;
	private int departmentId;
	
	
	public DoctorDTO(String fullName, String birthday, String gender, String address, String numberPhone,
			int departmentId) {
		super();
		this.fullName = fullName;
		this.birthday = birthday;
		this.gender = gender;
		this.address = address;
		this.numberPhone = numberPhone;
		this.departmentId = departmentId;
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
	public int getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	
}
