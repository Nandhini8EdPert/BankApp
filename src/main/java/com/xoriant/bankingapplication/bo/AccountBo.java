package com.xoriant.bankingapplication.bo;

import com.xoriant.bankingapplication.enums.AccountType;
import com.xoriant.bankingapplication.exception.AccountCreationException;

public class AccountBo {
	
	/*
	 * Check user account type and amount 
	 * return whether the deposited amount is valid or not
	 */
	public boolean intialDeposit(double amount, String accountType) throws AccountCreationException {
		boolean flag = false;
		if (amount >= 500 && accountType.equalsIgnoreCase(AccountType.SAVINGS.toString())) {
			flag = true;
		}else if (amount >= 2000 && accountType.equalsIgnoreCase(AccountType.CURRENT.toString())) {
			flag = true;
		}else {
			throw new AccountCreationException("Intial deposit is low!!");
		}
		return flag;
	}

	/*
	 * To find AccountType with the user entered data
	 */
	public AccountType getAccountType(String accountType) {
		if (accountType.equalsIgnoreCase("SAVINGS")) {
			return AccountType.SAVINGS;
		} else {
			return AccountType.CURRENT;
		}
	}
	
	/*
	 * To get minimum amount of amount per day can be transferred
	 */
	public double getLimit(String accountType) {
		if (accountType.equalsIgnoreCase(AccountType.SAVINGS.toString())) {
			return 5000;
		}else {
			return 10000;
		}
	}
	
	/*
	 * Compare User entered old password and password from db is 
	 * equal or not
	 */
	public boolean comparePassword(String dbPassword, String pagePassword) {
		boolean flag=false;
		if(dbPassword.equals(pagePassword)) {
			flag=true;
		}
		return flag;
	}
}
