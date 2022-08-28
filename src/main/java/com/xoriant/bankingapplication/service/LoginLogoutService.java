package com.xoriant.bankingapplication.service;

import com.xoriant.bankingapplication.exception.LoginLogoutException;

public interface LoginLogoutService {
	/*
	 * To get Login data from db and compare in bo layer
	 */
	public boolean login(int userId, String password) throws LoginLogoutException;

	/*
	 * To check data of admin
	 */
	public boolean getAdmin(int userId, String password) throws LoginLogoutException;
}
