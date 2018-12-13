<%@page import="bean.Employee,dao.Emp_Dao,java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Employee</title>
</head>
<body>
<%  
Employee emp=null;
String id=request.getParameter("id");  
Iterator it=Emp_Dao.getRecordById(Integer.parseInt(id));  
// while(it.hasNext())
// {
 emp=(Employee)it.next();
 String sex=emp.getSex();
 String department=emp.getDepartment();
 String country=emp.getCountry();
 session.setAttribute("id", emp.getId());
//}
%> 

<form action="Edit_emp" method="post">
		<table>
			<tr>
				<td>Id:</td>
				<td><input type="text" name="id" value="<%= emp.getId()%>" disabled="disabled"/></td>
			</tr>
			<tr>
				<td>Name:</td>
				<td><input type="text" name="name" value="<%= emp.getName()%>" /></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type="password" name="password" value="<%= emp.getPassword()%>"/></td>
			</tr>
			<tr>
				<td>Email:</td>
				<td><input type="email" name="email" value="<%= emp.getEmail()%>"/></td>
			</tr>
			<tr>
				<td>Department:</td>
				<td><select name="department" style="width: 155px" >
						<option>Production</option>
						<option>R & D</option>
						<option>Marketing</option>
						<option>Accounting</option>
						<option>Finance</option>
				</select></td>
			</tr>
			<tr>
				<td>Sex:</td>
				<td><input type="radio" name="sex" value="male" checked="checked"/>Male <input
					type="radio" name="sex" value="female" />Female</td>
			</tr>
			<tr>
				<td>Country:</td>
				<td><select name="country" style="width: 155px">
						<option >India</option>
						<option>Pakistan</option>
						<option>USA</option>
						<option>China</option>
						<option>Other</option>
				</select></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Edit Employee" /></td>
			</tr>
		</table>
		
		<br>
		<br>
		<br>
		<a href="index.jsp">Go to Index...</a>
	</form>

</body>
</html>