package com.xoriant.bankingapplication.daoimpl;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.xoriant.bankingapplication.dao.CustomerDao;
import com.xoriant.bankingapplication.enums.Gender;
import com.xoriant.bankingapplication.enums.Role;
import com.xoriant.bankingapplication.exception.CustomerRegistrationException;
import com.xoriant.bankingapplication.exception.LoginLogoutException;
import com.xoriant.bankingapplication.exception.NotExistsException;
import com.xoriant.bankingapplication.exception.UpdateFailedException;
import com.xoriant.bankingapplication.model.AccountDetails;
import com.xoriant.bankingapplication.model.Customer;
import com.xoriant.bankingapplication.model.People;

@Repository
@Component("customerDao")
public class CustomerDaoImpl implements CustomerDao {
	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Transactional
	public boolean addCustomer(Customer customer) throws CustomerRegistrationException {
		boolean flag = false;
		try {
			hibernateTemplate.save(customer);
			flag = true;
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new CustomerRegistrationException(e.getMessage());
		}
		return flag;
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Override
	public Customer getCustomer(int id) throws NotExistsException {
		return hibernateTemplate.get(Customer.class, id);
	}

	@Override
	@Transactional
	public boolean updateCustomer(Customer customer,int userId) throws UpdateFailedException {
		boolean flag = false;
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("trail");
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			Query query = em.createQuery("update Customer set address.houseNo=:houseNo,address.city=:city,"
					+ " address.state=:state,address.pincode=:pincode,activeStatus=:activeStatus where userId = :userId");
			query.setParameter("userId", userId);
			query.setParameter("houseNo", customer.getAddress().getHouseNo());
			query.setParameter("city", customer.getAddress().getCity());
			query.setParameter("state", customer.getAddress().getState());
			query.setParameter("pincode", customer.getAddress().getPincode());
			query.setParameter("activeStatus", customer.getActiveStatus());
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
	public int getCustomer(Long telephoneNumber) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("trail");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery("SELECT userId FROM People  WHERE telephoneNumber = :telephoneNumber");
		query.setParameter("telephoneNumber", telephoneNumber);
		int userId = (int) query.getSingleResult();
		System.out.println(userId);
		em.getTransaction().commit();
		emf.close();
		em.close();
		return userId;
	}

	public AccountDetails getAccountDetails(int userId) throws LoginLogoutException {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("trail");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		AccountDetails user;
		try {
			Query query = em.createQuery("SELECT c FROM AccountDetails c WHERE customer.userId = :userId and  activeStatus=true");
			query.setParameter("userId", userId);
			user = (AccountDetails) query.getSingleResult();
		} catch (NoResultException e) {
			e.printStackTrace();
			throw new LoginLogoutException("Customer have no active accounts");
		}
		em.getTransaction().commit();
		emf.close();
		em.close();
		return user;
	}

	public People getPeopleById(int userId) {
		People people=new People();
		String query = ("SELECT p.userId,p.name,p.gender,p.dateOfBirth,p.telephoneNumber,p.emailId,p.role"
				+ " FROM People p WHERE p.userId = ?0");
		Object[] param = { userId };
		List l = hibernateTemplate.find(query, param);
		Iterator it = l.iterator();
		while (it.hasNext()) {
			Object rows[] = (Object[]) it.next();
			people.setUserId((int) rows[0]);
			people.setName((String) rows[1]);
			people.setGender((Gender) rows[2]);
			people.setDateOfBirth((LocalDate) rows[3]);
			people.setTelephoneNumber((Long) rows[4]);
			people.setEmailId((String) rows[5]);
			people.setRole((Role) rows[6]);
		}
		return people;
	}

	@Override
	public boolean updatePeople(People people, int userId) throws UpdateFailedException {
		boolean flag = false;
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("trail");
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			Query query = em.createQuery("update People  SET name=:name,gender=:gender,dateOfBirth=:dateOfBirth "
					+ " ,telephoneNumber=:telephoneNumber,emailId=:emailId,role=:role  WHERE userId = :userId");
			query.setParameter("userId", userId);
			query.setParameter("name", people.getName());
			query.setParameter("gender", people.getGender());
			query.setParameter("dateOfBirth", people.getDateOfBirth());
			query.setParameter("telephoneNumber", people.getTelephoneNumber());
			query.setParameter("emailId", people.getEmailId());
			query.setParameter("role", people.getRole());
			query.executeUpdate();
			em.getTransaction().commit();
			emf.close();
			em.close();
			flag = true;
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new UpdateFailedException("Not updated");
		}
		return flag;
	}

}
