package com.xoriant.bankingapplication.serviceimpl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xoriant.bankingapplication.bo.TransactionBo;
import com.xoriant.bankingapplication.command.CustomizedStatementcommand;
import com.xoriant.bankingapplication.command.FundTransferCommand;
import com.xoriant.bankingapplication.command.TransactionCommand;
import com.xoriant.bankingapplication.dao.AccountDao;
import com.xoriant.bankingapplication.dao.TransactionDao;
import com.xoriant.bankingapplication.dto.GetAccount;
import com.xoriant.bankingapplication.enums.TransactionType;
import com.xoriant.bankingapplication.exception.IllegalArgumentException;
import com.xoriant.bankingapplication.exception.NotExistsException;
import com.xoriant.bankingapplication.exception.TransactionFailedException;
import com.xoriant.bankingapplication.exception.UpdateFailedException;
import com.xoriant.bankingapplication.model.AccountDetails;
import com.xoriant.bankingapplication.model.Transaction;
import com.xoriant.bankingapplication.service.TransactionService;
import com.xoriant.bankingapplication.util.DateTimeFormatUtil;

@Service("transactionService")
@Component("transactionService")
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private AccountDao accountDao;

	@Autowired
	private TransactionDao transactionDao;

	@Override
	@Transactional
	public boolean deposit(TransactionCommand transactionCommand) throws TransactionFailedException {
		// before Transaction, check whether account is present
		// then it is active or not
		// then only deposit it in transaction table
		// then update balance in accountDetails table
		boolean flag = false;
		AccountDetails account;
		Transaction transaction = new Transaction();
		try {
			account = accountDao.getAccountByAccountNo(transactionCommand.getAccountNo());
			if (account != null && account.getActiveStatus()) {
				transaction.setAccountNo(transactionCommand.getAccountNo());
				transaction.setAmount(transactionCommand.getAmount());
				transaction.setDescription(transactionCommand.getDescription());
				transaction.setTransactionType(TransactionType.DEPOSIT);
				if (transactionDao.insert(transaction)) {
					GetAccount updatedAccount = new GetAccount();
					updatedAccount.setAccountType(account.getAccountType());
					updatedAccount.setInitialDeposit(account.getInitialDeposit());
					updatedAccount.setLowerLimit(account.getLowerLimit());
					updatedAccount.setActiveStatus(account.getActiveStatus());
					updatedAccount.setPassword(account.getPassword());
					updatedAccount.setAccountCreatedDateTime(account.getAccountCreatedDateTime());
					updatedAccount.setBalanceAmount(
							new TransactionBo().addBalance(transactionCommand.getAmount(), account.getBalanceAmount()));
					accountDao.updateAccount(updatedAccount, transactionCommand.getAccountNo());
					flag = true;
				}
			}

		} catch (NotExistsException | TransactionFailedException | UpdateFailedException e) {
			e.printStackTrace();
			throw new TransactionFailedException(e.getMessage());
		}
		return flag;
	}

	@Override
	@Transactional
	public boolean withraw(TransactionCommand transactionCommand) throws TransactionFailedException {
		// check account exists or not
		// check balance before withdraw
		// balance amt > withdraw amt
		// then update both transaction and accountDetails
		boolean flag = false;
		AccountDetails account;
		Transaction transaction = new Transaction();
		try {
			account = accountDao.getAccountByAccountNo(transactionCommand.getAccountNo());
			if (account != null && account.getActiveStatus()) {
				transaction.setAccountNo(transactionCommand.getAccountNo());
				transaction.setAmount(transactionCommand.getAmount());
				transaction.setDescription(transactionCommand.getDescription());
				transaction.setTransactionType(TransactionType.WITHRAW);
				if (transactionDao.insert(transaction)) {
					GetAccount updatedAccount = new GetAccount();
					updatedAccount.setAccountType(account.getAccountType());
					updatedAccount.setInitialDeposit(account.getInitialDeposit());
					updatedAccount.setLowerLimit(account.getLowerLimit());
					updatedAccount.setActiveStatus(account.getActiveStatus());
					updatedAccount.setPassword(account.getPassword());
					updatedAccount.setAccountCreatedDateTime(account.getAccountCreatedDateTime());
					updatedAccount.setBalanceAmount(new TransactionBo().debitAmount(transactionCommand.getAmount(),
							account.getBalanceAmount()));
					accountDao.updateAccount(updatedAccount, transactionCommand.getAccountNo());
					flag = true;
				}
			}

		} catch (NotExistsException | TransactionFailedException | UpdateFailedException e) {
			e.printStackTrace();
			throw new TransactionFailedException(e.getMessage());
		}
		return flag;
	}

	@Override
	public boolean fundTransfer(FundTransferCommand fundTransferCommand) throws TransactionFailedException {
		boolean flag = false;
		TransactionCommand fromAccount = new TransactionCommand();
		TransactionCommand toAccount = new TransactionCommand();

		// deserialize data from page and serialize into two class objects
		// to and from and deposit one and withdraw from one

		fromAccount.setAccountNo(fundTransferCommand.getFromAccount());
		fromAccount.setAmount(fundTransferCommand.getAmount());
		fromAccount.setDescription(fundTransferCommand.getDescription());

		toAccount.setAccountNo(fundTransferCommand.getToAccount());
		toAccount.setAmount(fundTransferCommand.getAmount());
		toAccount.setDescription(fundTransferCommand.getDescription());
		

		try {
			if (deposit(toAccount) && withraw(fromAccount)) {
				flag = true;
			}

		} catch (TransactionFailedException e) {
			e.printStackTrace();
			throw new TransactionFailedException(e.getMessage());
		}

		return flag;
	}

	public AccountDao getAccountDao() {
		return accountDao;
	}

	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	public TransactionDao getTransactionDao() {
		return transactionDao;
	}

	public void setTransactionDao(TransactionDao transactionDao) {
		this.transactionDao = transactionDao;
	}

	@Override
	public boolean userFundTransfer(FundTransferCommand fundTransferCommand, int userId)
			throws TransactionFailedException {
		boolean flag = false;
		if (accountDao.getUserId(fundTransferCommand.getFromAccount()) == userId) {
			fundTransfer(fundTransferCommand);
			flag = true;
		}
		return flag;
	}

	@Override
	public double getBalance(Long accountNo) throws TransactionFailedException {
		AccountDetails account;
		double amount = 0;
		try {
			account = accountDao.getAccountByAccountNo(accountNo);
			if (account != null) {
				amount = account.getBalanceAmount();
			}
		} catch (NotExistsException e) {
			e.printStackTrace();
			throw new TransactionFailedException(e.getMessage());
		}
		return amount;

	}

	@Override
	public double getBalanceUser(Long accountNo, int UserId) throws TransactionFailedException {
		AccountDetails account;
		double amount = 0;
		try {
			account = accountDao.getAccountByAccountNo(accountNo);
			if (account != null && account.getCustomer().getUserId() == UserId) {
				amount = account.getBalanceAmount();
			}
		} catch (NotExistsException e) {
			e.printStackTrace();
			throw new TransactionFailedException(e.getMessage());
		}
		return amount;

	}

	@Override
	public List<Transaction> getMiniStatement(Long accountNo) throws TransactionFailedException {
		AccountDetails account;
		List<Transaction> transactions = null;
		try {
			account = accountDao.getAccountByAccountNo(accountNo);
			// checking whether account exists then date is correct or not
			if (account != null && account.getActiveStatus()) {
				transactions = transactionDao.getMiniStatement(accountNo);
			}
		} catch (NotExistsException e) {
			e.printStackTrace();
			throw new TransactionFailedException(e.getMessage());
		}
		return transactions;
	}

	@Override
	public List<Transaction> getCustomisedStatement(CustomizedStatementcommand command)
			throws TransactionFailedException {
		Long accountNo=command.getAccountNo();
		String fromDate=command.getFromDate();
		String toDate=command.getToDate();
		AccountDetails account;
		DateTimeFormatUtil dt = new DateTimeFormatUtil();
		List<Transaction> transactions = null;
		LocalDate date;
		try {
			account = accountDao.getAccountByAccountNo(accountNo);
			date = dt.dateFormattrUtil(toDate);
			if (dt.checkDate(date) && dt.dateFormattrUtil(fromDate) != null && dt.dateFormattrUtil(toDate) != null
					&& account != null && account.getActiveStatus()) {
				transactions = transactionDao.getCustomisedStatement(accountNo, fromDate, toDate);
			} else {
				throw new TransactionFailedException("Enter correct date or correct account number!");
			}
		} catch (IllegalArgumentException | NotExistsException e) {
			e.printStackTrace();
			throw new TransactionFailedException(e.getMessage());
		}
		return transactions;
	}

	@Override
	public List<Transaction> getMiniStatementUser(Long accountNo, int userId) throws TransactionFailedException {
		List<Transaction>transactions=null;
		if(accountDao.getUserId(accountNo)==userId) {
			transactions=getMiniStatement(accountNo);
		}else {
			throw new TransactionFailedException("Try only for your accounts!");
		}
		return transactions;
	}

	@Override
	public List<Transaction> getCustomisedStatementUser(CustomizedStatementcommand command, int userId)
			throws TransactionFailedException {
		List<Transaction>transactions=null;
		if(accountDao.getUserId(command.getAccountNo())==userId) {
			transactions=getCustomisedStatement(command);
		}else {
			throw new TransactionFailedException("Try only for your accounts!");
		}
		return transactions;
	}

}
