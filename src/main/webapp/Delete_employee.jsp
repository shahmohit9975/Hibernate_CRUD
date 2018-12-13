<%@page import="org.hibernate.Transaction"%>
<%@page import="bean.Employee"%>
<%@page import="org.hibernate.Session"%>
<%@page import="dao.Display_employee,dao.HibernateUtil"%>
<%@page import="org.hibernate.SessionFactory"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Delete Form</title>
</head>
<body>
	<%
		Transaction tx = null;
		String id = request.getParameter("id");
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		// getting session object from session factory
		Session sess = sessionFactory.openSession();
		// getting transaction object from session object
		tx = sess.beginTransaction();
		Employee emp = (Employee) sess.get(Employee.class, Integer.parseInt(id));
		sess.delete(emp);
		tx.commit();
		sess.close();
		response.sendRedirect("delete.jsp");
	%>

</body>
</html>