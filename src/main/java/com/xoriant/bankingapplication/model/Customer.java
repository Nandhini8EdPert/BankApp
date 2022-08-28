package com.xoriant.bankingapplication.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Customer extends People {
	@CreationTimestamp
	private LocalDateTime RegisteredDateTime;
	@Embedded
	private Address address;
	@Column(name = "activeStatus", columnDefinition = "boolean default true", nullable = false)
	private Boolean activeStatus = true;

	public LocalDateTime getRegisteredDateTime() {
		return RegisteredDateTime;
	}

	public void setRegisteredDateTime(LocalDateTime registeredDateTime) {
		RegisteredDateTime = registeredDateTime;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Boolean getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(Boolean activeStatus) {
		this.activeStatus = activeStatus;
	}

	@Override
	public String toString() {
		return "Customer [RegisteredDateTime=" + RegisteredDateTime + ", address=" + address + ", activeStatus="
				+ activeStatus + "]";
	}
}
