package com.xoriant.bankingapplication.dao;

import com.xoriant.bankingapplication.exception.CustomerRegistrationException;
import com.xoriant.bankingapplication.exception.LoginLogoutException;
import com.xoriant.bankingapplication.exception.NotExistsException;
import com.xoriant.bankingapplication.exception.UpdateFailedException;
import com.xoriant.bankingapplication.model.AccountDetails;
import com.xoriant.bankingapplication.model.Customer;
import com.xoriant.bankingapplication.model.People;

public interface CustomerDao {
	/*
	 * To save customer to database
	 */
	public boolean addCustomer(Customer customer) throws CustomerRegistrationException;

	/*
	 * To get customer by their id
	 */
	public Customer getCustomer(int id) throws NotExistsException;

	/*
	 * To update customer by their userid
	 */
	public boolean updateCustomer(Customer customer, int userId) throws UpdateFailedException;

	/*
	 * To update people by their userId
	 */
	public boolean updatePeople(People people, int userId) throws UpdateFailedException;

	/*
	 * To get customerId by passing telephone number
	 */
	public int getCustomer(Long telephoneNumber);

	/*
	 * Get accountdetails by their userId
	 */
	public AccountDetails getAccountDetails(int userId) throws LoginLogoutException;

	/*
	 * get people by their userId
	 */
	public People getPeopleById(int userId);
}
