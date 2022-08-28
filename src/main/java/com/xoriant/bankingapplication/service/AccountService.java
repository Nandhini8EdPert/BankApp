package com.xoriant.bankingapplication.service;

import com.xoriant.bankingapplication.command.AccountCommand;
import com.xoriant.bankingapplication.command.PasswordCommand;
import com.xoriant.bankingapplication.exception.AccountCreationException;
import com.xoriant.bankingapplication.exception.NotExistsException;
import com.xoriant.bankingapplication.exception.UpdateFailedException;
import com.xoriant.bankingapplication.model.AccountDetails;

public interface AccountService {
	public boolean addAccount(AccountCommand accoundCommand) throws AccountCreationException;
	
	/*
	 * If customer wish to delete, change the active status to false
	 */
	public boolean changeActiveStatusOfAccount(Long accountNo) throws UpdateFailedException;
	
	/*
	 * To update customer details
	 */
	public boolean updateAccountDetails(Long accountNo,AccountCommand account)throws UpdateFailedException;
	
	/*
	 * To update user Password
	 */
	public boolean updatePassword(Long accountNo,String password)throws UpdateFailedException;
	
	/*
	 * Get account details from db with account No
	 */
	public AccountDetails getAccountDetails(Long accountNo)throws NotExistsException;
	
	/*
	 * To check and update password
	 */
	public boolean checkAndUpdatePassword(PasswordCommand passwordCommand) throws UpdateFailedException;
	
	/*
	 * To get user Id using account number
	 */
	public int getUserId(Long accountNo);
	
	/*
	 * Change password by user
	 */
	public boolean updatePasswordByUser(PasswordCommand passwordCommand,int userId) throws UpdateFailedException;
}
