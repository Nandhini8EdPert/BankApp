package com.xoriant.bankingapplication.command;

public class FundTransferCommand {
	private Long fromAccount;
	private Long toAccount;
	private double amount;
	private String description;

	public Long getFromAccount() {
		return fromAccount;
	}

	public void setFromAccount(Long fromAccount) {
		this.fromAccount = fromAccount;
	}

	public Long getToAccount() {
		return toAccount;
	}

	public void setToAccount(Long toAccount) {
		this.toAccount = toAccount;
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
		return "FundTransferCommand [fromAccount=" + fromAccount + ", toAccount=" + toAccount + ", amount=" + amount
				+ ", description=" + description + "]";
	}
}
