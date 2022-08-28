package com.xoriant.bankingapplication.model;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
	private String houseNo;
	private String city;
	private String state;
	private int pincode;

	@Override
	public String toString() {
		return "Address [houseNo=" + houseNo + ", city=" + city + ", state=" + state + ", pincode=" + pincode + "]";
	}

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

}
