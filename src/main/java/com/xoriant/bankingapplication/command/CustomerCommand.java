package com.xoriant.bankingapplication.command;

public class CustomerCommand {
	private String houseNo;
	private String city;
	private String state;
	private int pincode;
	private String name;
	private String gender;
	private String dateOfBirth;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
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

	@Override
	public String toString() {
		return "CustomerCommand [houseNo=" + houseNo + ", city=" + city + ", state=" + state + ", pincode=" + pincode
				+ ", name=" + name + ", gender=" + gender + ", dateOfBirth=" + dateOfBirth + ", telephoneNumber="
				+ telephoneNumber + ", emailId=" + emailId + "]";
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
}
