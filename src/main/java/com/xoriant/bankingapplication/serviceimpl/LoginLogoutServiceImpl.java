package com.xoriant.bankingapplication.serviceimpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xoriant.bankingapplication.dao.CustomerDao;
import com.xoriant.bankingapplication.exception.LoginLogoutException;
import com.xoriant.bankingapplication.model.AccountDetails;
import com.xoriant.bankingapplication.model.Manager;
import com.xoriant.bankingapplication.service.LoginLogoutService;

@Service
public class LoginLogoutServiceImpl implements LoginLogoutService{
	@Autowired
	private CustomerDao customerDao;

	public boolean login(int userId, String password) throws LoginLogoutException {
		boolean valid=false;
		AccountDetails customer = customerDao.getAccountDetails(userId);
		/*
		 * If customer from dao is null throws error with message "No User"
		 */
		if (customer != null) {
			/*
			 * Checks login credentials if invalid throws exception
			 */
			if (customer.getCustomer().getUserId() == userId && customer.getPassword().equals(password)) {
				valid=true;
			}else {
				throw new LoginLogoutException("Invalid login credentials");
			}
		} else {
			throw new LoginLogoutException("No User Exists, kindly register!");
		}

		return valid;
	}

	public boolean getAdmin(int userId, String password) throws LoginLogoutException {
		boolean valid = false;
		Map<String, Integer> manager = Manager.getAdmin();
		try {
			for (String pwd : manager.keySet()) {
				int managerUserId = manager.get(pwd);
				if (managerUserId == (userId) && pwd.equals(password)) {
					valid = true;
					break;
				}
			}
		} catch (NullPointerException e) {
			throw new LoginLogoutException("Invalid login credentials");
		}
		return valid;
	}

	public CustomerDao getCustomerDao() {
		return customerDao;
	}

	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}
}
