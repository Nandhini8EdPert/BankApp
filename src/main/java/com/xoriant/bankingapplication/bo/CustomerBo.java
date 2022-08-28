package com.xoriant.bankingapplication.bo;

import com.xoriant.bankingapplication.dto.EditCustomerDto;
import com.xoriant.bankingapplication.enums.Gender;
import com.xoriant.bankingapplication.model.Address;
import com.xoriant.bankingapplication.model.Customer;
import com.xoriant.bankingapplication.model.People;

public class CustomerBo {
	
	/*
	 * Checking data with user entered data and accept null values too
	 */
	public Gender getGender(String gender) {
		if (gender.equalsIgnoreCase("female")) {
			return Gender.FEMALE;
		} else if (gender.equals(null)) {
			return null;
		} else {
		}
		return Gender.MALE;
	}

	/*
	 * For Edit Account feature, data from two tables is required.
	 * But not all the fields.
	 * So fetching data and binding in to another class
	 * which has required data for front end
	 */
	public EditCustomerDto deserializeCustomerPeople(Customer customer, People people) {
		EditCustomerDto deserialisedCustomer = new EditCustomerDto();
		deserialisedCustomer.setUserId(people.getUserId());
		deserialisedCustomer.setHouseNo(customer.getAddress().getHouseNo());
		deserialisedCustomer.setCity(customer.getAddress().getCity());
		deserialisedCustomer.setState(customer.getAddress().getState());
		deserialisedCustomer.setPincode(customer.getAddress().getPincode());
		deserialisedCustomer.setName(people.getName());
		deserialisedCustomer.setDateOfBirth(people.getDateOfBirth());
		deserialisedCustomer.setEmailId(people.getEmailId());
		deserialisedCustomer.setGender(people.getGender());
		deserialisedCustomer.setTelephoneNumber(people.getTelephoneNumber());
		return deserialisedCustomer;
	}

	/*
	 * Edit account jsp produces the class and that class is serialized to
	 * the customer then updated in database
	 */
	public Customer serializeCustomer(EditCustomerDto editCustomer, Customer customer) {
		Customer updateCustomer = new Customer();
		Address address = new Address();
		address.setHouseNo(editCustomer.getHouseNo());
		address.setCity(editCustomer.getCity());
		address.setState(editCustomer.getState());
		address.setPincode(editCustomer.getPincode());
		updateCustomer.setAddress(address);
		updateCustomer.setActiveStatus(customer.getActiveStatus());
		updateCustomer.setActiveStatus(customer.getActiveStatus());
		return updateCustomer;
	}

	/*
	 * Edit account jsp produces the class and that class is serialized to
	 * the people then updated in database
	 */
	public People serializePeople(EditCustomerDto editCustomer, People people) {
		People updatePeople = new People();
		updatePeople.setName(editCustomer.getName());
		updatePeople.setGender(editCustomer.getGender());
		updatePeople.setDateOfBirth(editCustomer.getDateOfBirth());
		updatePeople.setEmailId(editCustomer.getEmailId());
		updatePeople.setTelephoneNumber(editCustomer.getTelephoneNumber());
		updatePeople.setRole(people.getRole());
		return updatePeople;
	}

}
