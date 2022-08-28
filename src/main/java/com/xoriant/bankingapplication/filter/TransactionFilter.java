package com.xoriant.bankingapplication.filter;

import java.util.ArrayList;
import java.util.List;

import com.xoriant.bankingapplication.model.Transaction;

public class TransactionFilter {
	public List<Transaction> miniStatement(List<Transaction> list, Long accountNo) {
		Long l= new Long(accountNo);
		int i=l.intValue();
		List<Transaction>miniStatement=new ArrayList<Transaction>();
		for (Transaction transaction : list) {
			if(transaction.getAccountNo()==i) {
				for(int j=0;j<5;j++) {
					miniStatement.add(transaction);
				}
				}
			
		}
		System.out.println(miniStatement);
		return miniStatement;
	}
}
