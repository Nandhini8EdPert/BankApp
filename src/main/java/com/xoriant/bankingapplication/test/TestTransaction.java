package com.xoriant.bankingapplication.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xoriant.bankingapplication.dao.CustomerDao;
import com.xoriant.bankingapplication.dao.TransactionDao;
import com.xoriant.bankingapplication.enums.TransactionType;
import com.xoriant.bankingapplication.exception.TransactionFailedException;
import com.xoriant.bankingapplication.filter.TransactionFilter;
import com.xoriant.bankingapplication.model.Transaction;

public class TestTransaction {
public static void main(String[] args) throws TransactionFailedException {
	ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
    TransactionDao dao = (TransactionDao) context.getBean("transactionDao");
    Transaction tx=new Transaction();
    tx.setTransactionType(TransactionType.DEPOSIT);
    tx.setAmount(500);
    tx.setAccountNo(10001l);
   // dao.insert(tx);
    //System.out.println(dao.getTransactionData());
   //new TransactionFilter().miniStatement(dao.getTransactionData(), 10001l) ;
    //dao.getMiniStatement(10001l);
    dao.getCustomisedStatement(10001l,"2022-03-05","2022-03-07");
}
}
