<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Password Change</title>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="style.css">
<script type="text/javascript">
	function RestrictSpace() {
		if (event.keyCode == 32) {
			return false;
		}
	}
</script>
</head>
<body>
<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a href="index.html" class="navbar-brand">Data Store</a>
			<button class="navbar-toggle" data-toggle="collapse"
				data-target=".navHeaderCollapse">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<div class="collapse navbar-collapse navHeaderCollapse">
				<ul class="nav navbar-nav navbar-right ">
					<li class="active"><a href="index.html">Home</a></li>
					<li><a href="signin.jsp" >login</a></li>
					<li><a href="signup.jsp" >Sign Up for Free</a></li>
					<li><a href="logout.jsp" >logout</a></li>
				</ul>
			</div>
		</div>
	</div>
	<br>
	<br>
	<br>
	<%@ page import="javax.servlet.ServletContext;" %>
<%ServletContext context=getServletContext(); %>

	<div bgcolor='#99ffff' ; style="margin-top: 50px;" align="center">
		<form action="/update" method="post">
		<h1> Change Password</h1>
			
			<h3>${servlet_value2}</h3>
			<table cellspacing="3" cellpadding="3" border="2">
			<tr>

				<td align="left">Email Id         :</td>
				<td><input type="email" name="Email"  value=<%out.println((String)context.getAttribute("email") ); %> readonly required /></td>
			</tr>
			<br>
			<tr>
				<td align="right">Current Password :</td>
				<td><input type="password" name="p1" onkeypress="return RestrictSpace()" required /></td>
			</tr>
			<br>
			<tr>
				<td align="right">New Password     :</td>
				<td><input type="password" name="p2" onkeypress="return RestrictSpace()" required /></td>
			</tr>
			</table>
<br>
			<button value="/update">Change Password</button>
			
		</form>
		<a href="details.jsp">back</a>
	</div>
</body>
</html>