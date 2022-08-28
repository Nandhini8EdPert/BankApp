package com.xoriant.bankingapplication.service;

import java.util.List;

import com.xoriant.bankingapplication.command.CustomizedStatementcommand;
import com.xoriant.bankingapplication.command.FundTransferCommand;
import com.xoriant.bankingapplication.command.TransactionCommand;
import com.xoriant.bankingapplication.exception.TransactionFailedException;
import com.xoriant.bankingapplication.model.Transaction;

public interface TransactionService {
	/*
	 * Method to deposit 
	 */
	public boolean deposit(TransactionCommand transactionCommand) throws TransactionFailedException;

	/*
	 * Method used to withraw
	 */
	public boolean withraw(TransactionCommand transactionCommand) throws TransactionFailedException;

	/*
	 * Fund tranferred by manager
	 */
	public boolean fundTransfer(FundTransferCommand fundTransferCommand) throws TransactionFailedException;
	
	/*
	 * Fund transferred by user
	 */
	public boolean userFundTransfer(FundTransferCommand fundTransferCommand,int userId)throws TransactionFailedException;
	
	/*
	 * To get account balance by passing account number(manager)
	 */
	public double getBalance(Long accountNo) throws TransactionFailedException ;
	
	/*
	 * get balance with accountNo and particular user account only
	 * user-> can check only for their account
	 */
	public double getBalanceUser(Long accountNo,int UserId) throws TransactionFailedException;
	
	/*
	 * To get last five transaction from db of particular account
	 */
	public List<Transaction> getMiniStatement(Long accountNo)throws TransactionFailedException;
	
	/*
	 * To get customized statement for given date range
	 */
	public List<Transaction>getCustomisedStatement(CustomizedStatementcommand command)throws TransactionFailedException;
	
	/*
	 * Customer can check only his account details so separate method for them
	 */
	public List<Transaction> getMiniStatementUser(Long accountNo,int userId)throws TransactionFailedException;
	
	/*
	 *  Customer can check only his account details so separate method to 
	 *  find their customized statement
	 */
	public List<Transaction>getCustomisedStatementUser(CustomizedStatementcommand command,int userId)throws TransactionFailedException;
	
}
