package com.xoriant.bankingapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xoriant.bankingapplication.command.CustomerCommand;
import com.xoriant.bankingapplication.command.EditCustomerCommand;
import com.xoriant.bankingapplication.command.UserIdCommand;
import com.xoriant.bankingapplication.dto.EditCustomerDto;
import com.xoriant.bankingapplication.exception.NotExistsException;
import com.xoriant.bankingapplication.exception.UpdateFailedException;
import com.xoriant.bankingapplication.service.CustomerService;

@Controller
public class CustomerController {
	@Autowired
	private CustomerService service;

	public CustomerService getService() {
		return service;
	}

	public void setService(CustomerService service) {
		this.service = service;
	}

	/*
	 * Navigation to->To register the customer
	 */
	@RequestMapping("/reg_form")
	public String registrationForm(Model m) {
		m.addAttribute("user", new CustomerCommand());
		return "reg_form";
	}

	/*
	 * To call service method and success or error msg to page
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(@ModelAttribute("user") CustomerCommand user, Model m) {
		try {
			if (service.addCustomer(user)) {
				System.out.println(service.getUsedId(user.getTelephoneNumber()));
				m.addAttribute("userId", service.getUsedId(user.getTelephoneNumber()));
				m.addAttribute("successMsg", "Registered Successfully");
			}
		} catch (Exception e) {
			m.addAttribute("errorMsg", "Not registered, Try again");
			e.printStackTrace();
		}

		return "reg_form";
	}

	/*
	 * To call and load service and load objects
	 */
	@RequestMapping(value = "/editCustomer", method = RequestMethod.POST)
	public String EditCustomer(@ModelAttribute("userId") UserIdCommand userId, Model m) {
		try {
			EditCustomerDto customer = service.getEditCustomerData(userId.getUserId());
			if (customer != null) {
				m.addAttribute("customer", customer);
				m.addAttribute("editCustomer", new EditCustomerCommand());
				return "EditCustomer";
			} else {
				return "EditCustomerUserId";
			}
		} catch (NotExistsException e) {
			m.addAttribute("errorMsg", e.getMessage());
			return "EditCustomerUserId";
		}

	}

	/*
	 * To update Customer
	 */
	@RequestMapping(value = "/updateCustomer", method = RequestMethod.POST)
	public String updatedCustomer(@ModelAttribute("editCustomer") EditCustomerCommand customer, Model m) {
		try {
			if (service.updateCustomerDetails(customer)) {
				m.addAttribute("successMsg", "Updated");
			}
		} catch (UpdateFailedException e) {
			e.printStackTrace();
			m.addAttribute("errorMsg", e.getMessage());
		}
		return "EditCustomer";
	}

	/*
	 * To edit Customer Link to admin dashboard
	 */
	@RequestMapping(value = "/editCustomerUserLink")
	public String editCustomerLink(Model m) {
		m.addAttribute("userId", new UserIdCommand());
		return "EditCustomerUserId";
	}

	/*
	 * To delete link to admin
	 */
	@RequestMapping(value = "/deleteCustomerLink")
	public String deleteAccountLink(Model m) {
		m.addAttribute("userId", new UserIdCommand());
		return "DeleteCustomer";
	}

	/*
	 * To call service and add messages
	 */
	@RequestMapping(value = "/deleteCustomer", method = RequestMethod.POST)
	public String deleteAccount(@ModelAttribute("userId") UserIdCommand userId, Model m) {
		try {
			if (service.changeActiveStatusOfCustomer(userId.getUserId())) {
				m.addAttribute("successMsg", "Deleted");
			} else {
				m.addAttribute("errorMsg", "Kindly register");
			}
		} catch (UpdateFailedException e) {
			m.addAttribute("errorMsg", e.getMessage());
			e.printStackTrace();
		}
		return "DeleteCustomer";
	}

}
