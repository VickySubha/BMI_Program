<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<%@ page import="javax.servlet.ServletContext;"%>
	<%ServletContext context=getServletContext(); %>
</head>
<body>
<form action="/userdetails" method="post">
<table cellspacing="3" cellpadding="3" border="2">
			<tr>

				<td align="left">Email Id         :</td>
				<td><input type="email" name="Email"  value=<%out.println((String)context.getAttribute("email") ); %> readonly required /></td>
			</tr>
			<br>
			<tr>
				<td align="right">Password :</td>
				<td><input type="password" name="p1" onkeypress="return RestrictSpace()" required /></td>
			</tr>
			<br>
			</table>
			<input type="submit">
			</form>
</body>
</html>