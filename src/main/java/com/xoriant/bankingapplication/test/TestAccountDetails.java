package com.xoriant.bankingapplication.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xoriant.bankingapplication.dao.AccountDao;
import com.xoriant.bankingapplication.dao.CustomerDao;
import com.xoriant.bankingapplication.dto.GetAccount;
import com.xoriant.bankingapplication.enums.AccountType;
import com.xoriant.bankingapplication.exception.AccountCreationException;
import com.xoriant.bankingapplication.exception.NotExistsException;
import com.xoriant.bankingapplication.exception.UpdateFailedException;

public class TestAccountDetails {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
		AccountDao dao = (AccountDao) context.getBean("accountDao");
		CustomerDao customerDao = (CustomerDao) context.getBean("customerDao");
		// AccountDetails account = new AccountDetails();
		GetAccount account = new GetAccount();
		try {
			// account.setCustomer(customerDao.getCustomer(1));
//			account.setAccountType(AccountType.CURRENT);
//			account.setInitialDeposit(3000);
//			account.setBalanceAmount(2000);
//			account.setLowerLimit(10000);
//			//List<GetAccount>list=dao.getAllAccountOfUser(1);
//			for (GetAccount getAccount :list ) {
//				System.out.println(getAccount.getAccountNo());
//				if(getAccount.getAccountNo().equals(10001l)) {
//					getAccount.setLowerLimit(10000);
//					dao.updateAccount(getAccount,10001l );
//				}
//		}
			//	dao.getAccountByAccountNo(11002l);
//			account.setAccountType(AccountType.CURRENT);
//			account.setInitialDeposit(3000);
//			account.setBalanceAmount(2000);
//			account.setLowerLimit(10000);
//			account.setActiveStatus(false);
//			account.setPassword("abc122");
//			dao.updateAccount(account,11002l);
			System.out.println(dao.getUserId(10001l));
		} catch (  Exception e) {
			e.printStackTrace();
		}

	}

}
