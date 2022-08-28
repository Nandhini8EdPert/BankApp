package com.xoriant.bankingapplication.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xoriant.bankingapplication.command.AccountNoCommand;
import com.xoriant.bankingapplication.command.CustomizedStatementcommand;
import com.xoriant.bankingapplication.command.FundTransferCommand;
import com.xoriant.bankingapplication.command.TransactionCommand;
import com.xoriant.bankingapplication.exception.TransactionFailedException;
import com.xoriant.bankingapplication.model.Transaction;
import com.xoriant.bankingapplication.service.TransactionService;

@Controller
public class TransactionController {
	@Autowired
	private TransactionService service;

	public TransactionService getService() {
		return service;
	}

	public void setService(TransactionService service) {
		this.service = service;
	}

	@RequestMapping("/depositLink")
	public String depositLink(Model m) {
		m.addAttribute("Command", new TransactionCommand());
		return "Deposit";
	}

	@RequestMapping(value = "/deposit", method = RequestMethod.POST)
	public String deposit(Model m, @ModelAttribute("Command") TransactionCommand command) {
		try {
			if (service.deposit(command)) {
				m.addAttribute("successMsg", "Deposited");
			} else {
				m.addAttribute("errorMsg", "Try again");
			}
		} catch (TransactionFailedException e) {
			m.addAttribute("errorMsg", e.getMessage());
			e.printStackTrace();
		}
		return "Deposit";
	}

	@RequestMapping("/withdrawLink")
	public String withdrawLink(Model m) {
		m.addAttribute("Command", new TransactionCommand());
		return "Withdraw";
	}

	@RequestMapping(value = "/withdraw", method = RequestMethod.POST)
	public String withdraw(Model m, @ModelAttribute("Command") TransactionCommand command) {
		try {
			if (service.withraw(command)) {
				m.addAttribute("successMsg", "Amount debited");
			} else {
				m.addAttribute("errorMsg", "Try again");
			}
		} catch (TransactionFailedException e) {
			m.addAttribute("errorMsg", e.getMessage());
			e.printStackTrace();
		}
		return "Withdraw";
	}

	@RequestMapping("/fundTransferLink")
	public String fundTransferLink(Model m) {
		m.addAttribute("Command", new FundTransferCommand());
		return "FundTransfer";
	}

	@RequestMapping(value = "/fundTransfer", method = RequestMethod.POST)
	public String fundTransfer(Model m, @ModelAttribute("Command") FundTransferCommand command) {
		try {
			if (service.fundTransfer(command)) {
				m.addAttribute("successMsg", "Amount transferred");
			} else {
				m.addAttribute("errorMsg", "Try again");
			}
		} catch (TransactionFailedException e) {
			m.addAttribute("errorMsg", e.getMessage());
			e.printStackTrace();
		}
		return "FundTransfer";
	}

	@RequestMapping("/fundTransferLinkUser")
	public String fundTransferLinkUser(Model m) {
		m.addAttribute("Command", new FundTransferCommand());
		return "FundTransferUser";
	}

	@RequestMapping(value = "/fundTransferUser", method = RequestMethod.POST)
	public String fundTransferUser(Model m, @ModelAttribute("Command") FundTransferCommand command,
			HttpSession session) {
		try {
			int userId = (int) session.getAttribute("userId");
			if (service.userFundTransfer(command, userId)) {
				m.addAttribute("successMsg", "Amount transferred");
			} else {
				m.addAttribute("errorMsg", "Try again");
			}
		} catch (Exception e) {
			m.addAttribute("errorMsg", e.getMessage());
			e.printStackTrace();
		}
		return "FundTransferUser";
	}

	@RequestMapping("/balanceLink")
	public String balanceLink(Model m) {
		m.addAttribute("Command", new AccountNoCommand());
		return "balance";
	}

	@RequestMapping(value = "/balance", method = RequestMethod.POST)
	public String balance(Model m, @ModelAttribute("Command") AccountNoCommand command) {
		try {
			double amount = service.getBalance(command.getAccountNo());
			System.out.println(amount);
			if (amount != 0) {
				m.addAttribute("amount", amount);
			} else {
				m.addAttribute("errorMsg", "No Account exists");
			}
		} catch (Exception e) {
			m.addAttribute("errorMsg", e.getMessage());
			e.printStackTrace();
		}
		return "balance";
	}

	@RequestMapping("/balanceUserLink")
	public String balanceUserLink(Model m) {
		m.addAttribute("Command", new AccountNoCommand());
		return "balanceUser";
	}

	@RequestMapping(value = "/balanceUser", method = RequestMethod.POST)
	public String balanceUser(Model m, @ModelAttribute("Command") AccountNoCommand command, HttpSession session) {
		try {
			int userId = (int) session.getAttribute("userId");
			double amount = service.getBalanceUser(command.getAccountNo(), userId);
			System.out.println(amount);
			if (amount != 0) {
				m.addAttribute("amount", amount);
			} else {
				m.addAttribute("errorMsg", "No Account exists");
			}
		} catch (Exception e) {
			m.addAttribute("errorMsg", e.getMessage());
			e.printStackTrace();
		}
		return "balanceUser";
	}

	@RequestMapping(value = "/miniStatementLinkManager")
	public String miniStatmentLink(Model m) {
		m.addAttribute("command", new AccountNoCommand());
		return "miniStatement";
	}

	@RequestMapping(value = "/miniStmtCallService", method = RequestMethod.POST)
	public String miniStmtGetAccountNo(Model m, @ModelAttribute("command") AccountNoCommand accountNo) {
		try {
			List<Transaction> transactions = service.getMiniStatement(accountNo.getAccountNo());
			if (transactions == null) {
				m.addAttribute("msg", "No transaction made!");
			} else {
				m.addAttribute("transactions", transactions);
			}
		} catch (TransactionFailedException e) {
			e.printStackTrace();
			m.addAttribute("errorMsg", e.getMessage());
		}
		return "miniStatement";
	}

	@RequestMapping(value = "/miniStatementLinkUser")
	public String miniStatmentLinkUser(Model m) {
		m.addAttribute("command", new AccountNoCommand());
		return "miniStatementUser";
	}

	@RequestMapping(value = "/miniStmtCallServiceByUser", method = RequestMethod.POST)
	public String miniStmtGetAccountNoUser(Model m, @ModelAttribute("command") AccountNoCommand accountNo,
			HttpSession session) {
		try {
			int userId = (int) session.getAttribute("userId");
			List<Transaction> transactions = service.getMiniStatementUser(accountNo.getAccountNo(), userId);
			if (transactions == null) {
				m.addAttribute("msg", "No transaction made!");
			} else {
				m.addAttribute("transactions", transactions);
			}
		} catch (TransactionFailedException e) {
			e.printStackTrace();
			m.addAttribute("errorMsg", e.getMessage());
		}
		return "miniStatementUser";
	}

	@RequestMapping(value = "/customizedStatementLink")
	public String customStatement(Model m) {
		m.addAttribute("command", new CustomizedStatementcommand());
		return "customizedStatement";
	}

	@RequestMapping(value = "/customizedStamentCallService", method = RequestMethod.POST)
	public String CustomStatementCallService(Model m, @ModelAttribute("command") CustomizedStatementcommand command) {
		try {
			List<Transaction> transactions = service.getCustomisedStatement(command);
			if (transactions != null) {
				m.addAttribute("transactions", transactions);
			} else {
				m.addAttribute("msg", "No transaction made on specified date!");
			}

		} catch (TransactionFailedException e) {
			m.addAttribute("errorMsg", e.getMessage());
			e.printStackTrace();
		}
		return "customizedStatement";
	}

	@RequestMapping(value = "/customizedStatementUserLink")
	public String customStatementUser(Model m) {
		m.addAttribute("command", new CustomizedStatementcommand());
		return "customizedStatementUser";
	}

	@RequestMapping(value = "/customizedStamentCallServiceUser", method = RequestMethod.POST)
	public String CustomStatementCallServiceUser(Model m, @ModelAttribute("command") CustomizedStatementcommand command,
			HttpSession session) {
		try {
			int userId = (int) session.getAttribute("userId");
			List<Transaction> transactions = service.getCustomisedStatementUser(command,userId);
			if (transactions != null) {
				m.addAttribute("transactions", transactions);
			} else if(transactions==null){
				m.addAttribute("msg", "No transaction made on specified date!");
			}

		} catch (TransactionFailedException e) {
			m.addAttribute("errorMsg", e.getMessage());
			e.printStackTrace();
		}
		return "customizedStatementUser";
	}
}
