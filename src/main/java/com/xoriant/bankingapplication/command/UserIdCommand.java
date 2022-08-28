package com.xoriant.bankingapplication.command;

public class UserIdCommand {
	private int userId;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "UserId [userId=" + userId + "]";
	}
}
