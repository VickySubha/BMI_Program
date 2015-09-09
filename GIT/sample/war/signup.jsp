<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sign Up</title>
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
					<li><a href="signin.jsp">login</a></li>
					<li><a href="signup.jsp">Sign Up for Free</a></li>

				</ul>
			</div>
		</div>
	</div>
	<%@ page import="javax.servlet.ServletContext;" %>
<%ServletContext context=getServletContext(); %>
	<div bgcolor='ffff99' ; style="margin-top: 50px;" align="center">
		<h1>Sign up...!</h1>
		<form action="/sample" method="get">
			<br> <br> <br>
			<table cellspacing="3" cellpadding="3">
				<tr>

					<td align="left">First Name</td>
					<td><input type="text" name="FirstName"
						onkeypress="return RestrictSpace()" required></td>
				</tr>
<tr>

					<td align="left">Last Name</td>
					<td><input type="text" name="LastName"
						onkeypress="return RestrictSpace()" required></td>
				</tr>
				<tr>
					<td align="left">E-Mail</td>
					<td><input type="email" name="Email"
						  required /></td>
				</tr>
				<tr>
					<td align="left">Password</td>
					<td><input type="password" name="password"
						onkeypress="return RestrictSpace()" required></td>
				</tr>

				<tr>
					<td align="left">Gender</td>
					<div id="radio_button">

						<td><input type="radio" name="Gender" value="Male" required>
							<label>Male</label> &nbsp;&nbsp;&nbsp; <input type="radio"
							name="Gender" value="Female" required> <label>Female</label></td>

					</div>
				</tr>
				<tr>
					<td align="left">Date of birth</td>
					<td><input type="date" name="date" required>
						
					<td>
				</tr>
				<tr>
					<td align="left">MobileNumber</td>
					<td><input type='tel' name="MobileNumber"
						pattern="[0-9]{10,10}" maxlength="10"
						onkeypress="return RestrictSpace()" required></td>
				</tr>
				<tr>
					<td align="center"></td>
					<td><input type="submit" value="register" /></td>
				</tr>

			</table>
		</form>
		<h3>${servlet_value}</h3>
	</div>


	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script
		src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>
</html>