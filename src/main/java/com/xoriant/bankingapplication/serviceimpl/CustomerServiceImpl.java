package com.xoriant.bankingapplication.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xoriant.bankingapplication.bo.CustomerBo;
import com.xoriant.bankingapplication.command.CustomerCommand;
import com.xoriant.bankingapplication.command.EditCustomerCommand;
import com.xoriant.bankingapplication.dao.CustomerDao;
import com.xoriant.bankingapplication.dto.EditCustomerDto;
import com.xoriant.bankingapplication.enums.Role;
import com.xoriant.bankingapplication.exception.CustomerRegistrationException;
import com.xoriant.bankingapplication.exception.IllegalArgumentException;
import com.xoriant.bankingapplication.exception.NotExistsException;
import com.xoriant.bankingapplication.exception.UpdateFailedException;
import com.xoriant.bankingapplication.model.Address;
import com.xoriant.bankingapplication.model.Customer;
import com.xoriant.bankingapplication.model.People;
import com.xoriant.bankingapplication.service.CustomerService;
import com.xoriant.bankingapplication.util.DateTimeFormatUtil;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDao customerDao;

	public boolean addCustomer(CustomerCommand customerCommand) throws CustomerRegistrationException {
		boolean flag = false;

		try {
			Address address = new Address();
			address.setHouseNo(customerCommand.getHouseNo());
			address.setCity(customerCommand.getCity());
			address.setState(customerCommand.getState());
			address.setPincode(customerCommand.getPincode());
			Customer customer = new Customer();
			customer.setAddress(address);
			customer.setDateOfBirth(new DateTimeFormatUtil().dateFormattrUtil(customerCommand.getDateOfBirth()));
			customer.setEmailId(customerCommand.getEmailId());
			customer.setGender(new CustomerBo().getGender(customerCommand.getGender()));
			customer.setName(customerCommand.getName());
			customer.setRole(Role.CUSTOMER);
			customer.setTelephoneNumber(customerCommand.getTelephoneNumber());
			System.out.println(customer);
			customerDao.addCustomer(customer);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new CustomerRegistrationException(e.getMessage());
		}
		return flag;
	}

	@Override
	public int getUsedId(Long telephoneNumber) {
		return customerDao.getCustomer(telephoneNumber);
	}

	@Override
	public EditCustomerDto getEditCustomerData(int userId) throws NotExistsException {
		EditCustomerDto dto = null;
		try {
			if (customerDao.getCustomer(userId) != null) {
				dto = new CustomerBo().deserializeCustomerPeople(customerDao.getCustomer(userId),
						customerDao.getPeopleById(userId));
			} else {
				throw new NotExistsException("User Not Exixts");
			}
		} catch (NotExistsException e) {
			e.printStackTrace();
			throw new NotExistsException(e.getMessage());
		}
		return dto;
	}

	public boolean changeActiveStatusOfCustomer(int userId) throws UpdateFailedException {
		boolean flag = false;
		Customer customer;
		Customer updateCustomer = new Customer();
		Address address = new Address();
		try {
			customer = customerDao.getCustomer(userId);
			if (customer != null && customer.getActiveStatus()) {
				address.setHouseNo(customer.getAddress().getHouseNo());
				address.setCity(customer.getAddress().getCity());
				address.setState(customer.getAddress().getState());
				address.setPincode(customer.getAddress().getPincode());
				updateCustomer.setAddress(address);
				updateCustomer.setActiveStatus(false);
				System.out.println(updateCustomer);
				customerDao.updateCustomer(updateCustomer, userId);
				flag = true;
			}
		} catch (NotExistsException e) {
			e.printStackTrace();
			throw new UpdateFailedException(e.getMessage());
		}
		return flag;
	}

	@Override
	public boolean updateCustomerDetails(EditCustomerCommand customer) throws UpdateFailedException {
		boolean flag =false;
		try {
			EditCustomerDto updatedCustomer = new EditCustomerDto();
			updatedCustomer.setUserId(customer.getUserId());
			updatedCustomer.setHouseNo(customer.getHouseNo());
			updatedCustomer.setCity(customer.getCity());
			updatedCustomer.setState(customer.getState());
			updatedCustomer.setPincode(customer.getPincode());
			updatedCustomer.setName(customer.getName());
			updatedCustomer.setGender(new CustomerBo().getGender(customer.getGender()));
			updatedCustomer.setDateOfBirth(new DateTimeFormatUtil().dateFormattrUtil(customer.getDateOfBirth()));
			updatedCustomer.setTelephoneNumber(customer.getTelephoneNumber());
			updatedCustomer.setEmailId(customer.getEmailId());
			flag= true;

			Customer customer1 = new CustomerBo().serializeCustomer(updatedCustomer,
					customerDao.getCustomer(updatedCustomer.getUserId()));
			People people = new CustomerBo().serializePeople(updatedCustomer,
					customerDao.getPeopleById(updatedCustomer.getUserId()));
			customerDao.updateCustomer(customer1, updatedCustomer.getUserId());
			customerDao.updatePeople(people, updatedCustomer.getUserId());
		} catch (NotExistsException | IllegalArgumentException e) {
			e.printStackTrace();
			throw new UpdateFailedException(e.getMessage());
		}
		return flag;
	}
}
