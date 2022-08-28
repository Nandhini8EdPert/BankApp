package com.xoriant.bankingapplication.daoimpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.xoriant.bankingapplication.dao.AccountDao;
import com.xoriant.bankingapplication.dto.GetAccount;
import com.xoriant.bankingapplication.enums.AccountType;
import com.xoriant.bankingapplication.exception.AccountCreationException;
import com.xoriant.bankingapplication.exception.NotExistsException;
import com.xoriant.bankingapplication.exception.UpdateFailedException;
import com.xoriant.bankingapplication.model.AccountDetails;

@Repository
@Component("accountDao")
public class AccountDaoImpl implements AccountDao {
	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Transactional
	public boolean addAccount(AccountDetails account) {
		boolean flag = false;
		try {
			hibernateTemplate.save(account);
			flag = true;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return flag;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GetAccount> getAllAccountOfUser(int userId) throws NotExistsException, AccountCreationException {
		List<GetAccount> accounts = new ArrayList<GetAccount>();
		List<GetAccount> accountList = new ArrayList<GetAccount>();
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("trail");
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			Query query = em.createQuery("SELECT accountType,initialDeposit,accountNo,balanceAmount,password,"
					+ " lowerLimit,activeStatus,accountCreatedDateTime FROM AccountDetails  WHERE customer.userId = :userId");
			query.setParameter("userId", userId);
			accounts = query.getResultList();
			Iterator<?> it = accounts.iterator();
			
			while (it.hasNext()) {
				GetAccount account = new GetAccount();
				Object rows[] = (Object[]) it.next();
				account.setAccountType((AccountType) rows[0]);
				account.setInitialDeposit((double) rows[1]);
				account.setAccountNo((Long) rows[2]);
				account.setBalanceAmount((double) rows[3]);
				account.setPassword((String) rows[4]);
				account.setLowerLimit((double) rows[5]);
				account.setActiveStatus((Boolean) rows[6]);
				account.setAccountCreatedDateTime((LocalDateTime) rows[7]);
				accountList.add(account);
			}
			em.getTransaction().commit();
			emf.close();
			em.close();
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new AccountCreationException(e.getMessage());
		}
		return accountList;
	}

	@Override
	@Transactional
	public boolean updateAccount(GetAccount account,Long accountNo) throws UpdateFailedException {
		boolean flag = false;
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("trail");
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			Query query = em.createQuery("update AccountDetails set accountType=:accountType,initialDeposit=:initialDeposit,"
					+ "balanceAmount=:balanceAmount,password=:password,lowerLimit=:lowerLimit,"
					+ "activeStatus=:activeStatus,accountCreatedDateTime=:accountCreatedDateTime where"
					+ " accountNo=:accountNo");
			query.setParameter("accountType",account.getAccountType());
			query.setParameter("initialDeposit", account.getInitialDeposit());
			query.setParameter("balanceAmount", account.getBalanceAmount());
			query.setParameter("password", account.getPassword());
			query.setParameter("lowerLimit",account.getLowerLimit());
			query.setParameter("activeStatus", account.getActiveStatus());
			query.setParameter("accountCreatedDateTime", account.getAccountCreatedDateTime());
			query.setParameter("accountNo", accountNo);
			query.executeUpdate();
			em.getTransaction().commit();
			emf.close();
			em.close();
			flag = true;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	@Override
	public AccountDetails getAccountByAccountNo(Long accountNo) throws NotExistsException {
		return hibernateTemplate.get(AccountDetails.class, accountNo);
	}
	
	@Override
	public int getUserId(Long accountNo) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("trail");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery("SELECT customer.userId FROM AccountDetails  WHERE accountNo = :accountNo");
		query.setParameter("accountNo", accountNo);
		int userId = (int) query.getSingleResult();
		System.out.println(userId);
		em.getTransaction().commit();
		emf.close();
		em.close();
		return userId;
	}
	
}
