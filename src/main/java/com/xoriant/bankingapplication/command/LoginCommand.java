package com.xoriant.bankingapplication.command;

public class LoginCommand {
	private int userId;
	private String password;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "LoginCommand [userId=" + userId + ", password=" + password + "]";
	}

}
