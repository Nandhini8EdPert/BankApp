package com.xoriant.bankingapplication.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import com.xoriant.bankingapplication.enums.Gender;
import com.xoriant.bankingapplication.enums.Role;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class People {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	private String name;
	@Enumerated(EnumType.STRING)
	private Gender gender;
	private LocalDate dateOfBirth;
	@Column(unique=true)
	private Long telephoneNumber;
	private String emailId;
	@Enumerated(EnumType.STRING)
	private Role role;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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
		this.dateOfBirth =dateOfBirth;
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "People [userId=" + userId + ", name=" + name + ", gender=" + gender + ", dateOfBirth=" + dateOfBirth
				+ ", telephoneNumber=" + telephoneNumber + ", emailId=" + emailId + ", role=" + role + "]";
	}

}
