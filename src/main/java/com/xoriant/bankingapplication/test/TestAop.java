package com.xoriant.bankingapplication.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xoriant.bankingapplication.command.TransactionCommand;
import com.xoriant.bankingapplication.exception.TransactionFailedException;
import com.xoriant.bankingapplication.service.TransactionService;

public class TestAop {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
		TransactionService service = (TransactionService) context.getBean("transactionService");
		TransactionCommand tc=new TransactionCommand();
		tc.setAccountNo(10001l);
		tc.setAmount(1000);
		try {
			service.deposit(tc);
		} catch (TransactionFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
