<%@page import="dao.Emp_Dao" %>
<jsp:useBean id="obj" class="bean.Employee"></jsp:useBean>
<jsp:setProperty property="*" name="obj" />

<%
	int i = Emp_Dao.save(obj);
	if (i > 0) {
		response.sendRedirect("add_emp-success.jsp");
	} else {
		response.sendRedirect("add_emp-error.jsp");
	}
%>
