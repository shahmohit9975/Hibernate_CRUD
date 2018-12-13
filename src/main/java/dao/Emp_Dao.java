package dao;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import dao.HibernateUtil;
import bean.Employee;

public class Emp_Dao {
	static Transaction tx = null;

	public static int save(Employee emp) {
		int status = 0;
		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			// getting session object from session factory
			Session session = sessionFactory.openSession();
			// getting transaction object from session object
			tx = session.beginTransaction();

			session.save(emp);
			System.out.println("Inserted Successfully......");
			tx.commit();
			session.close();
			status = 1;
		} catch (Exception e) {

			System.out.println(e);
			tx.rollback();
		}
		return status;
	}

	public static Iterator getRecordById(int id) {
		Iterator itr = null;
		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			// getting session object from session factory
			Session session = sessionFactory.openSession();
			// getting transaction object from session object
			tx = session.beginTransaction();

			@SuppressWarnings("deprecation")
			Criteria crit = session.createCriteria(Employee.class);
			Criterion cn = Restrictions.eq("id", id);
			crit.add(cn);
			List l = crit.list();
			itr = l.iterator();
			tx.commit();
			session.close();
		} catch (Exception e) {

			System.out.println(e);
			tx.rollback();
		}
		return itr;

	}

}
