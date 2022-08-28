package com.xoriant.bankingapplication.dao;

import java.util.List;

import com.xoriant.bankingapplication.dto.GetAccount;
import com.xoriant.bankingapplication.exception.AccountCreationException;
import com.xoriant.bankingapplication.exception.NotExistsException;
import com.xoriant.bankingapplication.exception.UpdateFailedException;
import com.xoriant.bankingapplication.model.AccountDetails;

public interface AccountDao {
	public boolean addAccount(AccountDetails account);
	
	/*
	 * To get account from db
	 * If there is no account -> NotExistsException
	 * If error while accessing db -> AccountCreationException
	 */
	public List<GetAccount> getAllAccountOfUser(int userId) throws NotExistsException, AccountCreationException;
	
	/*
	 * Update account details like active status , password,
	 * and other details of account
	 */
	public boolean updateAccount(GetAccount account, Long accountNo)throws UpdateFailedException;

	/*
	 * To get account by account number
	 */
	public AccountDetails getAccountByAccountNo(Long accountNo) throws NotExistsException;
	
	/*
	 * Get userId with the help of account number
	 */
	public int getUserId(Long accountNo);
}
