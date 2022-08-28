package com.xoriant.bankingapplication.command;

public class PasswordCommand {
	private Long accountNo;
	private String oldPassword;
	private String newPassword;
	private String confirmPassword;

	public Long getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(Long accountNo) {
		this.accountNo = accountNo;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	@Override
	public String toString() {
		return "PasswordCommand [accountNo=" + accountNo + ", oldPassword=" + oldPassword + ", newPassword="
				+ newPassword + ", confirmPassword=" + confirmPassword + "]";
	}

}
