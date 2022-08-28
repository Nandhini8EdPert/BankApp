package com.xoriant.bankingapplication.bo;

import com.xoriant.bankingapplication.exception.TransactionFailedException;

public class TransactionBo {
	
	/*
	 * Add balance will just add db balance and user entered balance
	 */
	public double addBalance(double balance, double dbBalance) {
		double amount = balance + dbBalance;
		return amount;
	}
	
	/*
	 * Check withdraw amount is there in account or not
	 * then Give the balance amount to service layer.
	 */
	public double debitAmount(double balance, double dbBalance) throws TransactionFailedException {
		double amount;
		if(balance>dbBalance) {
			throw new TransactionFailedException("Your account have low balance!");
		}else {
			amount=dbBalance-balance;
			return amount;
		}
	}
}
