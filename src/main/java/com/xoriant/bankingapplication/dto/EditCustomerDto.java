package com.xoriant.bankingapplication.dto;

import java.time.LocalDate;

import com.xoriant.bankingapplication.enums.Gender;

public class EditCustomerDto {
	private int userId;
	private String houseNo;
	private String city;
	private String state;
	private int pincode;
	private String name;
	private Gender gender;
	private LocalDate dateOfBirth;
	private Long telephoneNumber;
	private String emailId;

	public String getHouseNo() {
		return houseNo;
	}

	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Long getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(Long telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "EditCustomerDto [userId=" + userId + ", houseNo=" + houseNo + ", city=" + city + ", state=" + state
				+ ", pincode=" + pincode + ", name=" + name + ", gender=" + gender + ", dateOfBirth=" + dateOfBirth
				+ ", telephoneNumber=" + telephoneNumber + ", emailId=" + emailId + "]";
	}
}
