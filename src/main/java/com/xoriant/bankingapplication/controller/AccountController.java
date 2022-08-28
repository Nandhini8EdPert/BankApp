package com.xoriant.bankingapplication.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xoriant.bankingapplication.command.AccountCommand;
import com.xoriant.bankingapplication.command.AccountNoCommand;
import com.xoriant.bankingapplication.command.PasswordCommand;
import com.xoriant.bankingapplication.exception.AccountCreationException;
import com.xoriant.bankingapplication.exception.NotExistsException;
import com.xoriant.bankingapplication.exception.UpdateFailedException;
import com.xoriant.bankingapplication.model.AccountDetails;
import com.xoriant.bankingapplication.service.AccountService;

@Controller
public class AccountController {
	@Autowired
	private AccountService service;

	public AccountService getService() {
		return service;
	}

	public void setService(AccountService service) {
		this.service = service;
	}

	/*
	 * Page navigation for change password by manager
	 */
	@RequestMapping(value = "/editPasswordLink")
	public String editPasswordLink(Model m) {
		m.addAttribute("accountNo", new AccountNoCommand());
		return "EditPasswordAccountNo";
	}

	/*
	 * To add attribute with accountNo and object
	 */
	@RequestMapping(value = "/editPassword", method = RequestMethod.POST)
	public String editPassword(@ModelAttribute("accountNo") AccountNoCommand accountNo, Model m) {
		m.addAttribute("accountNo", accountNo);
		m.addAttribute("passwordCommand", new PasswordCommand());
		return "EditPassword";
	}

	/*
	 * To call Update method for password
	 */
	@RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
	public String updatePassword(@ModelAttribute("passwordCommand") PasswordCommand account, Model m) {
		try {
			if (service.checkAndUpdatePassword(account)) {
				m.addAttribute("successMsg", "Updated");
				return "EditPassword";
			} else {
				m.addAttribute("errorMsg", "Not Updated!!");
				return "EditPassword";
			}
		} catch (UpdateFailedException e) {
			e.printStackTrace();
			m.addAttribute("errorMsg", e.getMessage());
			return "EditPassword";
		}
	}

	@RequestMapping(value = "/newAccountLink")
	public String newAccountLink(Model m) {
		m.addAttribute("accountCommand", new AccountCommand());
		return "CreateAccount";
	}

	@RequestMapping(value = "/createAccount",method = RequestMethod.POST)
	public String createNewAccount(@ModelAttribute("accountCommand") AccountCommand account, Model m) {
		try {
			if (service.addAccount(account)) {
				m.addAttribute("successMsg", "Account created");
			} else {
				m.addAttribute("errorMsg", "Try again");
			}
		} catch (AccountCreationException e) {
			m.addAttribute("errorMsg", e.getMessage());
		}catch(DataIntegrityViolationException e1) {
			m.addAttribute("errorMsg", "No customer exists!");
		}
		return "CreateAccount";
	}

	/*
	 * Page navigation for Edit account 
	 */
	@RequestMapping(value = "/editAccountLink")
	public String editAccountLink(Model m) {
		m.addAttribute("accountNo", new AccountNoCommand());
		return "EditAccountNo";
	}
	
	/*
	 * Edit account that calls service
	 */
	@RequestMapping(value = "/editAccount",method = RequestMethod.POST)
	public String editAccount(@ModelAttribute("accountNo")AccountNoCommand accountNo,Model m) {
		try {
			AccountDetails account= service.getAccountDetails(accountNo.getAccountNo());
			int userId= service.getUserId(accountNo.getAccountNo());
			System.out.println(userId);
			m.addAttribute("userId", userId);
			m.addAttribute("accountNumber", accountNo);
			m.addAttribute("account", account);
			m.addAttribute("command", new AccountCommand());
		} catch (NotExistsException e) {
			m.addAttribute("errorMsg",e.getMessage());
			e.printStackTrace();
		}
		return "EditAccount";
	}
	
	/*
	 * Add attributes and return to pages
	 */
	@RequestMapping(value = "/updateAccount",method = RequestMethod.POST)
	public String updateAccount(@ModelAttribute("command") AccountCommand command,Model m) {
		try {
			if(service.updateAccountDetails(command.getAccountNo(), command)) {
				m.addAttribute("successMsg","Updated");
			}else {
				m.addAttribute("errorMsg","Try again!!");
			}
		} catch (UpdateFailedException e) {
			m.addAttribute("errorMsg","Not updated!");
			e.printStackTrace();
		}
		return "EditAccount";
	}
	
	/*
	 * To navigate to delete account
	 */
	@RequestMapping(value = "/deleteAccountLink")
	public String deleteAccountLink(Model m) {
		m.addAttribute("accountNo", new AccountNoCommand());
		return "DeleteAccount";
	}
	
	/*
	 * To call service and change active status to false
	 */
	@RequestMapping(value = "/deleteAccount", method =RequestMethod.POST)
	public String deleteAccount(@ModelAttribute ("accountNo") AccountNoCommand accountNo, Model m) {
		try {
			if(service.changeActiveStatusOfAccount(accountNo.getAccountNo())) {
				m.addAttribute("successMsg", "Deleted");
			}else {
				m.addAttribute("errorMsg","Try again");
			}
		} catch (UpdateFailedException e) {
			m.addAttribute("errorMsg",e.getMessage());
			e.printStackTrace();
		}
		return "DeleteAccount";
	}
	
	/*
	 * Navigation for change password for user
	 */
	@RequestMapping(value = "/editUserPasswordLink")
	public String changePasswordUserLink(Model m) {
		m.addAttribute("accountNo", new AccountNoCommand());
		return "changePasswordUserId";
	}

	/*
	 * load objects and redirect page
	 */
	@RequestMapping(value = "/editPasswordUser", method = RequestMethod.POST)
	public String changePasswordUser(@ModelAttribute("accountNo") AccountNoCommand accountNo, Model m) {
		m.addAttribute("accountNo", accountNo);
		m.addAttribute("passwordCommand", new PasswordCommand());
		return "ChangePasswordUser";
	}

	/*
	 * To change password for particular user
	 */
	@RequestMapping(value = "/updatePasswordUser", method = RequestMethod.POST)
	public String updatePasswordUser(@ModelAttribute("passwordCommand") PasswordCommand account, Model m,HttpSession session) {
		try {
			int userId=(int) session.getAttribute("userId");
			if (service.updatePasswordByUser(account, userId)) {
				m.addAttribute("successMsg", "Updated");
				return "EditPassword";
			} else {
				m.addAttribute("errorMsg", "Not Updated!!");
				return "EditPassword";
			}
		} catch (UpdateFailedException e) {
			e.printStackTrace();
			m.addAttribute("errorMsg", e.getMessage());
			return "EditPassword";
		}
	}

}
