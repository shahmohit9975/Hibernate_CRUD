package dao;
import bean.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;

/**
 * Servlet implementation class Display_employee
 */

public class Display_employee extends HttpServlet {
	private static final long serialVersionUID = 1L;
	SessionFactory session_factory;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Display_employee() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int pageIndex = 0;
		int totalNumberOfRecords = 0;
		int numberOfRecordsPerPage = 4;

		String sPageIndex = request.getParameter("pageIndex");

		if(sPageIndex ==null)
		{
		pageIndex = 1;
		}else
		{
		pageIndex = Integer.parseInt(sPageIndex);
		}
		session_factory = HibernateUtil.getSessionFactory();
		// getting session object from session factory
		Session session = session_factory.openSession();
		// getting transaction object from session object
		Transaction tx = session.beginTransaction();
		
		int s = (pageIndex*numberOfRecordsPerPage) -numberOfRecordsPerPage;

		@SuppressWarnings("deprecation")
		Criteria crit = session.createCriteria(Employee.class);
		crit.setFirstResult(s);
		crit.setMaxResults(numberOfRecordsPerPage);

		List l = crit.list();
		Iterator it = l.iterator();
		
		PrintWriter pw = response.getWriter();
		pw.println("<table border=1>");
		pw.println("<tr>");
		pw.println("<th>ID</th><th>NAME</th><th>Email</th><th>Password</th><th>Department</th><th>Sex</th><th>Country</th><th>EDIT</th><th>DELETE</th>");
		pw.println("</tr>");

		while(it.hasNext())
		{
		Employee emp = (Employee)it.next();
		String edit_url = "Edit_employee_form.jsp?id="+emp.getId();
		String delete_url = "Delete_employee.jsp?id="+emp.getId();
		pw.println("<tr>");
		pw.println("<td>"+emp.getId()+"</td>");
		pw.println("<td>"+emp.getName()+"</td>");
		pw.println("<td>"+emp.getEmail()+"</td>");
		pw.println("<td>"+emp.getPassword()+"</td>");
		pw.println("<td>"+emp.getDepartment()+"</td>");
		pw.println("<td>"+emp.getSex()+"</td>");
		pw.println("<td>"+emp.getCountry()+"</td>");
		pw.println("<td><a href="+edit_url+">Edit</a></td>");
		pw.println("<td><a href="+delete_url+">Delete</a></td>");
		pw.println("</tr>");
		}

		pw.println("<table>");
		pw.println("");

		Criteria crit1 = session.createCriteria(Employee.class);
		crit1.setProjection(Projections.rowCount());

		List l1=crit1.list();

		// pw.println(l1.size());
		//returns 1, as list() is used to execute the query if true will returns 1

		Iterator it1 = l1.iterator();

		if(it1.hasNext())
		{
		Object o=it1.next();
		totalNumberOfRecords = Integer.parseInt(o.toString());
		}

		int noOfPages = totalNumberOfRecords/numberOfRecordsPerPage;
		if(totalNumberOfRecords > (noOfPages * numberOfRecordsPerPage))
		{
		noOfPages = noOfPages + 1;
		}
		pw.println("<br>");
		for(int i=1;i<=noOfPages;i++)
		{
		String myurl = "Display_employee?pageIndex="+i;
		pw.println(" <a href="+myurl+">"+i+"</a>|");
		
		}
		pw.println("<br><br><b><a href='index.jsp'>Go To Main Page</a></b>");
		session.close();
		pw.close();

	}

}
