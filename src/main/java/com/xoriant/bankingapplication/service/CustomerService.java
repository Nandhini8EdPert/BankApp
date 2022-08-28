package com.xoriant.bankingapplication.service;

import com.xoriant.bankingapplication.command.CustomerCommand;
import com.xoriant.bankingapplication.command.EditCustomerCommand;
import com.xoriant.bankingapplication.dto.EditCustomerDto;
import com.xoriant.bankingapplication.exception.CustomerRegistrationException;
import com.xoriant.bankingapplication.exception.NotExistsException;
import com.xoriant.bankingapplication.exception.UpdateFailedException;

public interface CustomerService {
	public boolean addCustomer(CustomerCommand customer) throws CustomerRegistrationException;

	public int getUsedId(Long telephoneNumber);

	/*
	 * data from db got and page needs to show certain details only so filtering
	 * data that need to be display before goes to form page
	 */
	public EditCustomerDto getEditCustomerData(int userId) throws NotExistsException;

	/*
	 * If customer wish to delete, change the active status to false
	 */
	public boolean changeActiveStatusOfCustomer(int userId) throws UpdateFailedException;

	/*
	 * To update customer details
	 */
	public boolean updateCustomerDetails(EditCustomerCommand customer) throws UpdateFailedException;
}
