package com.xoriant.bankingapplication.command;

public class AccountNoCommand {
	private Long accountNo;

	public Long getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(Long accountNo) {
		this.accountNo = accountNo;
	}

	@Override
	public String toString() {
		return "AccountNoCommand [accountNo=" + accountNo + "]";
	}
}
