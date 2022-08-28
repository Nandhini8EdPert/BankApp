package com.xoriant.bankingapplication.dao;

import java.util.List;

import com.xoriant.bankingapplication.exception.TransactionFailedException;
import com.xoriant.bankingapplication.model.Transaction;

public interface TransactionDao {
	/*
	 * Add details to dTatabase
	 */
	public boolean insert(Transaction transaction) throws TransactionFailedException;

	/*
	 * Get transactional data from database
	 */
	public List<Transaction> getTransactionData() throws TransactionFailedException;
	
	/*
	 * To get last five transaction from db of particular account
	 */
	public List<Transaction> getMiniStatement(Long accountNo);
	
	/*
	 * To get customized statement for given date range
	 */
	public List<Transaction>getCustomisedStatement(Long accountNo,String fromDate,String toDate);
}
