package com.xoriant.bankingapplication.dto;

import java.time.LocalDateTime;

import com.xoriant.bankingapplication.enums.AccountType;

public class GetAccount {
	private AccountType accountType;
	private double initialDeposit;
	private Long accountNo;
	private double balanceAmount;
	private String password;
	private double lowerLimit;
	private Boolean activeStatus;
	private LocalDateTime accountCreatedDateTime;

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

	@Override
	public String toString() {
		return "GetAccount [accountType=" + accountType + ", initialDeposit=" + initialDeposit + ", accountNo="
				+ accountNo + ", balanceAmount=" + balanceAmount + ", password=" + password + ", lowerLimit="
				+ lowerLimit + ", activeStatus=" + activeStatus + ", accountCreatedDateTime=" + accountCreatedDateTime
				+ "]";
	}

}
