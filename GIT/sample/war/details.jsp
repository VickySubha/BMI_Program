<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="style.css">
<title>User Details</title>
<%@ page import="javax.servlet.ServletContext;"%>
<%
	ServletContext context = getServletContext();
%>
</head>
<body>
	<div bgcolor='#ff99ff' ; style="margin-top: 50px;" align="left">
		<h1>
			Welcome
			<%
			out.println((String) context.getAttribute("name"));
		%>
		</h1>
		<br>
		<div class="navbar navbar-inverse navbar-fixed-top">
		<td><a class="navbar-text pull-right" href="/update.jsp">Change Password</a></td>
					<form action=userdetails method="get">
						<td>
						<a class="navbar-text pull-right" href="/userdetails">user details</a></td>
					</form>
					</div>
		<h4 align="center">${servlet_value}</h4>
		<div class='row'>
			<div class='col-md-4'>


			</div><div class='col-md-4'>

				

			</div>
			<div class='col-md-4'>

				
				<form action="/searchandsort" method="get">
					<input type="email" name="Firstemail" required /> <input type=submit value=search>
				</form>


			</div></div>
			<div class="navbar navbar-default navbar-fixed-bottom">
			<div class="navbar-text pull-left">
			<td><a class="navbar-text pull-left" href="/updatedetail.jsp">Change Details</a></td>
			
					<td><a class="navbar-text " href="/delete.jsp">delete account</a></td>
			
					<td><a class="navbar-text pull-right" href="/logout.jsp">Log out</a></td>
			</div></div>
			<script
					src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
				<script
					src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>
</html>