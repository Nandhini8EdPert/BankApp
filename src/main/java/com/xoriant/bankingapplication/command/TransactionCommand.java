package com.xoriant.bankingapplication.command;

public class TransactionCommand {
	private Long accountNo;
	private double amount;
	private String description;

	public Long getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(Long accountNo) {
		this.accountNo = accountNo;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "TransactionCommand [accountNo=" + accountNo + ", amount=" + amount + ", description=" + description
				+ "]";
	}
}
