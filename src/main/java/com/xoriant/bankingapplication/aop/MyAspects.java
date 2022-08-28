package com.xoriant.bankingapplication.aop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import org.aspectj.lang.annotation.After;
//import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

//@Aspect
//@Component
public class MyAspects {
	 private static final Logger logger = LogManager.getLogger(MyAspects.class);
//
//	@After("execution(* com.xoriant.bankingapplication.serviceimpl.TransactionServiceImpl.deposit(..))")
//	public void afterDeposit() {
//		logger.info("Amount deposited!");
//		System.out.println("credit");
//	}
//
//	@After("execution(* com.xoriant.bankingapplication.serviceimpl.TransactionServiceImpl.withraw(..))")
//	public void afterWithdraw() {
//		logger.info("Amount debited!");
//		System.out.println("debit");
//	}

}
