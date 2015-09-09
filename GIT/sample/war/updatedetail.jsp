<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h2>${servlet_value}</h2>
	<h4>Only Enter the values you want to Change</h4>
	<%@ page import="javax.servlet.ServletContext;"%>
	<%
		ServletContext context = getServletContext();
	%>
	<form action="/update" method="get">
		<br> <br> <br>
		<table cellspacing="3" cellpadding="3">
			<tr>

				<td align="left">Email Id</td>
				<td><input type="email" name="Email"
					value=<%out.println((String) context.getAttribute("email"));%>
					readonly required /></td>
			</tr>
			
			<tr>

				<td align="left">User Name</td>
				<td><input type="text" name="FirstName"
					value=<%out.println((String) context.getAttribute("name"));%> onkeypress="return RestrictSpace()"></td>
			</tr>
<%String gender= (String) context.getAttribute("gender");%>
			
			<tr>
				<td align="left">Gender</td>
				<div id="radio_button">

					<td><input type="radio" name="Gender" value="Male" checked> <label>Male</label>
						&nbsp;&nbsp;&nbsp; <input type="radio" name="Gender"
						value="Female" > <label>Female</label></td>

				</div>
			</tr>
			<tr>
				<td align="left">Date of birth</td>
				<td><input type="text" name="date" value=<%out.println((String) context.getAttribute("date"));%>> 
				<td>
			</tr>
			<tr>
				<td align="left">MobileNumber</td>
				<td><input type='tel' name="MobileNumber"
					pattern="[0-9]{10,10}" maxlength="10"
					onkeypress="return RestrictSpace()" value=<%out.println((String) context.getAttribute("mobile"));%>></td>
			</tr>

			<tr>
				<td align="center"></td>
				<td><input type="submit" value="Change Value" /></td>
			</tr>

		</table>
	</form>
	<a href="details.jsp">back</a>
</body>
</html>