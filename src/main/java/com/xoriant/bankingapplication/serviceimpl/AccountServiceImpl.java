package com.xoriant.bankingapplication.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xoriant.bankingapplication.bo.AccountBo;
import com.xoriant.bankingapplication.command.AccountCommand;
import com.xoriant.bankingapplication.command.PasswordCommand;
import com.xoriant.bankingapplication.dao.AccountDao;
import com.xoriant.bankingapplication.dao.CustomerDao;
import com.xoriant.bankingapplication.dto.GetAccount;
import com.xoriant.bankingapplication.enums.AccountType;
import com.xoriant.bankingapplication.exception.AccountCreationException;
import com.xoriant.bankingapplication.exception.NotExistsException;
import com.xoriant.bankingapplication.exception.UpdateFailedException;
import com.xoriant.bankingapplication.model.AccountDetails;
import com.xoriant.bankingapplication.model.Customer;
import com.xoriant.bankingapplication.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
	@Autowired
	private AccountDao dao;

	@Autowired
	private CustomerDao customerDao;

	public boolean addAccount(AccountCommand accountCommand) throws AccountCreationException {
		boolean flag = false;
		try {
			AccountDetails account = new AccountDetails();
			Customer customer = customerDao.getCustomer(accountCommand.getCustomerId());
			account.setAccountType(new AccountBo().getAccountType(accountCommand.getAccountType()));
			if (new AccountBo().intialDeposit(accountCommand.getInitialDeposit(), accountCommand.getAccountType())) {
				account.setBalanceAmount(accountCommand.getInitialDeposit());
				account.setInitialDeposit(accountCommand.getInitialDeposit());
			}
			account.setLowerLimit(new AccountBo().getLimit(accountCommand.getAccountType()));
			account.setCustomer(customer);
			System.out.println(account);
			dao.addAccount(account);
			flag = true;
		} catch (AccountCreationException | NotExistsException e) {
			throw new AccountCreationException(e.getMessage());
		}
		return flag;
	}

	public AccountDao getDao() {
		return dao;
	}

	public void setDao(AccountDao dao) {
		this.dao = dao;
	}

	public CustomerDao getCustomerDao() {
		return customerDao;
	}

	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	@Override
	public boolean changeActiveStatusOfAccount(Long accountNo) throws UpdateFailedException {
		boolean flag = false;
		try {
			AccountDetails wholeAccountDetails = dao.getAccountByAccountNo(accountNo);
			if (wholeAccountDetails != null && wholeAccountDetails.getActiveStatus().equals(true)) {	
				GetAccount account = new GetAccount();
				account.setAccountType(wholeAccountDetails.getAccountType());
				account.setInitialDeposit(wholeAccountDetails.getInitialDeposit());
				account.setBalanceAmount(wholeAccountDetails.getBalanceAmount());
				account.setLowerLimit(wholeAccountDetails.getLowerLimit());
				account.setActiveStatus(false);
				account.setPassword(wholeAccountDetails.getPassword());
				account.setAccountCreatedDateTime(wholeAccountDetails.getAccountCreatedDateTime());
				dao.updateAccount(account, accountNo);
				flag = true;
			} else {
				throw new NotExistsException("Kindly open account");
			}
		} catch (Exception e) {
			throw new UpdateFailedException(e.getMessage());
		}
		return flag;
	}

	@Override
	public boolean updateAccountDetails(Long accountNo, AccountCommand account) throws UpdateFailedException {
		boolean flag = false;
		try {
			AccountDetails accountDetails = dao.getAccountByAccountNo(accountNo);
			if (accountDetails != null && accountDetails.getActiveStatus()) {
				GetAccount updatedAccount = new GetAccount();
				updatedAccount.setAccountType(AccountType.valueOf(account.getAccountType()));
				updatedAccount.setInitialDeposit(accountDetails.getInitialDeposit());
				updatedAccount.setBalanceAmount(accountDetails.getBalanceAmount());
				updatedAccount.setLowerLimit(accountDetails.getLowerLimit());
				updatedAccount.setActiveStatus(accountDetails.getActiveStatus());
				updatedAccount.setPassword(accountDetails.getPassword());
				updatedAccount.setAccountCreatedDateTime(accountDetails.getAccountCreatedDateTime());
				dao.updateAccount(updatedAccount, accountNo);
				flag = true;
			} else {
				throw new NotExistsException("Kindly open account");
			}
		} catch (Exception e) {
			throw new UpdateFailedException(e.getMessage());
		}
		return flag;
	}

	@Override
	public boolean updatePassword(Long accountNo, String password) throws UpdateFailedException {
		boolean flag = false;
		try {
			AccountDetails wholeAccountDetails = dao.getAccountByAccountNo(accountNo);
			if (wholeAccountDetails != null && wholeAccountDetails.getActiveStatus()) {
				GetAccount account = new GetAccount();
				account.setAccountType(wholeAccountDetails.getAccountType());
				account.setInitialDeposit(wholeAccountDetails.getInitialDeposit());
				account.setBalanceAmount(wholeAccountDetails.getBalanceAmount());
				account.setLowerLimit(wholeAccountDetails.getLowerLimit());
				account.setActiveStatus(wholeAccountDetails.getActiveStatus());
				account.setPassword(password);
				account.setAccountCreatedDateTime(wholeAccountDetails.getAccountCreatedDateTime());
				dao.updateAccount(account, accountNo);
				flag = true;
			} else {
				throw new NotExistsException("Kindly open account");
			}
		} catch (Exception e) {
			throw new UpdateFailedException(e.getMessage());
		}
		return flag;
	}

	public boolean checkAndUpdatePassword(PasswordCommand passwordCommand) throws UpdateFailedException {
		boolean flag = false;
		try {
			AccountDetails account = dao.getAccountByAccountNo(passwordCommand.getAccountNo());
			if (new AccountBo().comparePassword(account.getPassword(), passwordCommand.getOldPassword())) {
				updatePassword(passwordCommand.getAccountNo(), passwordCommand.getNewPassword());
				flag = true;
			} else {
				throw new UpdateFailedException("Your Old password is wrong");
			}
		} catch (UpdateFailedException | NotExistsException e) {
			throw new UpdateFailedException(e.getMessage());
		}
		return flag;
	}

	@Override
	public AccountDetails getAccountDetails(Long accountNo) throws NotExistsException {
		AccountDetails account=null;
		try {
			account = dao.getAccountByAccountNo(accountNo);
			if (account == null) {
				throw new NotExistsException("Kindly create account!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new NotExistsException(e.getMessage());
		}
		return account;
	}
	
	@Override
	public int getUserId(Long accountNo) {
		return dao.getUserId(accountNo);
	}
	
	@Override
	public boolean updatePasswordByUser(PasswordCommand passwordCommand,int userId) throws UpdateFailedException {
		boolean flag = false;
		try {
			int dbUserId=getUserId(passwordCommand.getAccountNo());
			if (userId==dbUserId) {
				checkAndUpdatePassword(passwordCommand);
				flag = true;
			} else {
				throw new NotExistsException("Change password for your account only!");
			}
		} catch (Exception e) {
			throw new UpdateFailedException(e.getMessage());
		}
		return flag;
	}
}
