package com.xoriant.bankingapplication.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xoriant.bankingapplication.dao.CustomerDao;
import com.xoriant.bankingapplication.enums.Gender;
import com.xoriant.bankingapplication.enums.Role;
import com.xoriant.bankingapplication.exception.IllegalArgumentException;
import com.xoriant.bankingapplication.model.Address;
import com.xoriant.bankingapplication.model.Customer;
import com.xoriant.bankingapplication.model.People;
import com.xoriant.bankingapplication.util.DateTimeFormatUtil;

public class TestCustomer {
public static void main(String[] args) throws IllegalArgumentException {
	ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
    CustomerDao customerDao = (CustomerDao) context.getBean("customerDao");
    People people =new People();
    Customer customer=new Customer();
    //people.setUserId(1);
//    customer.setName("Pert");
//    customer.setEmailId("pert@gmail.com");
//    customer.setGender(Gender.FEMALE);
//    customer.setTelephoneNumber(400645818l);
//    customer.setDateOfBirth(new DateTimeFormatUtil().dateFormattrUtil("09/11/1546"));
//    customer.setRole(Role.CUSTOMER);
    Address address =new Address();
    address.setHouseNo("023");
    address.setCity("Peace land");
    address.setState("Bliss city");
    address.setPincode(626201);
    customer.setAddress(address);
    customer.setActiveStatus(false);
    people.setName("Pert");
    people.setEmailId("pert123@gmail.com");
    people.setGender(Gender.FEMALE);
    people.setTelephoneNumber(400645818l);
    people.setDateOfBirth(new DateTimeFormatUtil().dateFormattrUtil("09/11/1546"));
    people.setRole(Role.CUSTOMER);
    try {
		//customerDao.addCustomer(customer,people);
		//System.out.println(customerDao.getCustomer(1));
    	//System.out.println(customerDao.getAccountDetails(1));
    	//System.out.println(customerDao.getPeopleById(1));
    	//customerDao.updateCustomer(customer);
    	//customerDao.updatePeople(people, 1);
    	customerDao.updateCustomer(customer,1);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
}
}
