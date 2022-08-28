package com.xoriant.bankingapplication.model;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.CreationTimestamp;

import com.xoriant.bankingapplication.enums.AccountType;

@Entity
public class AccountDetails {
	@Enumerated(EnumType.STRING)
	private AccountType accountType;
	private double initialDeposit;
	@TableGenerator(name = "accountNo_Gen", table = "ID_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", pkColumnValue = "Addr_Gen", initialValue = 10000, allocationSize = 1000)
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "accountNo_Gen")
	private Long accountNo;
	private double balanceAmount;
	@Column(name = "password", columnDefinition = "varchar(255) default 'abc123'", nullable = false)
	private String password = "abc123";
	private double lowerLimit;
	@Column(name = "activeStatus", columnDefinition = "boolean default true", nullable = false)
	private Boolean activeStatus = true;
	@CreationTimestamp
	private LocalDateTime accountCreatedDateTime;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "userId", nullable = false)
	private Customer customer;

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public double getInitialDeposit() {
		return initialDeposit;
	}

	public void setInitialDeposit(double initialDeposit) {
		this.initialDeposit = initialDeposit;
	}

	public Long getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(Long accountNo) {
		this.accountNo = accountNo;
	}

	public double getBalanceAmount() {
		return balanceAmount;
	}

	public void setBalanceAmount(double balanceAmount) {
		this.balanceAmount = balanceAmount;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getLowerLimit() {
		return lowerLimit;
	}

	public void setLowerLimit(double lowerLimit) {
		this.lowerLimit = lowerLimit;
	}

	public Boolean getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(Boolean activeStatus) {
		this.activeStatus = activeStatus;
	}

	public LocalDateTime getAccountCreatedDateTime() {
		return accountCreatedDateTime;
	}

	public void setAccountCreatedDateTime(LocalDateTime accountCreatedDateTime) {
		this.accountCreatedDateTime = accountCreatedDateTime;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "AccountDetails [accountType=" + accountType + ", initialDeposit=" + initialDeposit + ", accountNo="
				+ accountNo + ", balanceAmount=" + balanceAmount + ", password=" + password + ", lowerLimit="
				+ lowerLimit + ", activeStatus=" + activeStatus + ", accountCreatedDateTime=" + accountCreatedDateTime
				+ ", customer=" + customer + "]";
	}

}
