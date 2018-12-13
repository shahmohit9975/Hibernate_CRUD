package dao;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import bean.Employee;

/**
 * Servlet implementation class Edit_emp
 */
public class Edit_emp extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Transaction tx = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Edit_emp() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter pw=response.getWriter();
		HttpSession session1=request.getSession();
		int id=(int) session1.getAttribute("id");
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		String department=request.getParameter("department");
		String sex=request.getParameter("sex");
		String country=request.getParameter("country");
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		// getting session object from session factory
		Session session = sessionFactory.openSession();
		// getting transaction object from session object
		tx = session.beginTransaction();
		Employee emp = (Employee) session.get(Employee.class, id);
		emp.setName(name);
		emp.setEmail(email);
		emp.setPassword(password);
		emp.setDepartment(department);
		emp.setSex(sex);
		emp.setCountry(country);
		
		
		session.update(emp);
		System.out.println("Update Successfully......");
		tx.commit();
		session.close();
		pw.println("Update Successfully......<br><br>");
		
		request.getRequestDispatcher("Display_employee").include(request, response);
	}

}
