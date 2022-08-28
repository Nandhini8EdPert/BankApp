package com.xoriant.bankingapplication.daoimpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.xoriant.bankingapplication.dao.TransactionDao;
import com.xoriant.bankingapplication.enums.TransactionType;
import com.xoriant.bankingapplication.exception.TransactionFailedException;
import com.xoriant.bankingapplication.model.Transaction;

@Repository
@Component("transactionDao")
public class TransactionalDaoImpl implements TransactionDao {
	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Override
	@Transactional
	public boolean insert(Transaction transaction) throws TransactionFailedException {
		boolean flag = false;
		try {
			hibernateTemplate.save(transaction);
			flag = true;
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new TransactionFailedException("Transaction failed!");
		}
		return flag;
	}

	@Override
	public List<Transaction> getTransactionData() throws TransactionFailedException {
		List<Transaction> TransactionList = null;
		try {
			TransactionList = hibernateTemplate.loadAll(Transaction.class);
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new TransactionFailedException("Try Later! Database issue!");
		}
		return TransactionList;
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Override
	public List<Transaction> getMiniStatement(Long accountNo) {
		List<Transaction> transactions = new ArrayList<>();
		String query = "select id,accountNo,amount,dateTime,description,transactionType from \r\n"
				+ "Transaction where accountNo=?0 ORDER BY dateTime DESC";
		Object[] param = { accountNo };

		hibernateTemplate.setMaxResults(5);
		List<Transaction> mini = (List<Transaction>) hibernateTemplate.find(query, param);
		Iterator<?> it = mini.iterator();

		while (it.hasNext()) {
			Transaction transaction = new Transaction();
			Object rows[] = (Object[]) it.next();
			transaction.setId((int) rows[0]);
			transaction.setAccountNo((Long) rows[1]);
			transaction.setAmount((double) rows[2]);
			transaction.setDateTime((LocalDateTime) rows[3]);
			transaction.setDescription((String) rows[4]);
			transaction.setTransactionType((TransactionType) rows[5]);
			transactions.add(transaction);
		}
		return transactions;
	}

	@Override
	public List<Transaction> getCustomisedStatement(Long accountNo,String fromDate,String toDate) {
		List<Transaction> transactions = new ArrayList<>();
		String query = "select id,accountNo,amount,dateTime,description,transactionType from \r\n"
				+ " Transaction where accountNo=?0 and dateTime between CONCAT(?1, 'T12:00:00') and concat(?2,'T23:59:00')";
		Object[] params= {accountNo,fromDate,toDate};
		List<Transaction> mini = (List<Transaction>) hibernateTemplate.find(query,params);
		Iterator<?> it = mini.iterator();

		while (it.hasNext()) {
			Transaction transaction = new Transaction();
			Object rows[] = (Object[]) it.next();
			transaction.setId((int) rows[0]);
			transaction.setAccountNo((Long) rows[1]);
			transaction.setAmount((double) rows[2]);
			transaction.setDateTime((LocalDateTime) rows[3]);
			transaction.setDescription((String) rows[4]);
			transaction.setTransactionType((TransactionType) rows[5]);
			transactions.add(transaction);
		}
		System.out.println(transactions);
		return transactions;
	}

}
