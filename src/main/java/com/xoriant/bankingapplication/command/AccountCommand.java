package com.xoriant.bankingapplication.command;

public class AccountCommand {
	private Long accountNo;
	private int customerId;
	private String accountType;
	private double initialDeposit;
	public Long getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(Long accountNo) {
		this.accountNo = accountNo;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public double getInitialDeposit() {
		return initialDeposit;
	}
	public void setInitialDeposit(double initialDeposit) {
		this.initialDeposit = initialDeposit;
	}
	@Override
	public String toString() {
		return "AccountCommand [accountNo=" + accountNo + ", customerId=" + customerId + ", accountType=" + accountType
				+ ", initialDeposit=" + initialDeposit + "]";
	}

	
}
