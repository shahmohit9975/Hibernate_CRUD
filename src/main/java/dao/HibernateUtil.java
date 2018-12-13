package dao;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import bean.Employee;


public class HibernateUtil {
	private static final SessionFactory sessionFactory;
	static {
		try {
			Configuration cf = new Configuration().configure().addAnnotatedClass(Employee.class);
			sessionFactory = cf.buildSessionFactory();
		} catch (Throwable th) {
			System.err.println("SessionFactory creation failed" + th);
			throw new ExceptionInInitializerError(th);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}