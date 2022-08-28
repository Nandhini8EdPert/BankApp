package com.xoriant.bankingapplication.command;

public class CustomizedStatementcommand {
	private String fromDate;
	private String toDate;
	private Long accountNo;

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public Long getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(Long accountNo) {
		this.accountNo = accountNo;
	}

	@Override
	public String toString() {
		return "CustomizedStatementcommand [fromDate=" + fromDate + ", toDate=" + toDate + ", accountNo=" + accountNo
				+ "]";
	}
}
