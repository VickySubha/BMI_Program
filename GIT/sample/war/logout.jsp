<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="style.css">

</head>
<SCRIPT type="text/javascript">
    window.history.backward();
    function noBack() { window.history.backward(); }
</SCRIPT>
</HEAD>
<body onload="noBack();" onpageshow="if (event.persisted) noBack();"
	onunload="">
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
	<br>
	<br>
	<br>
	<h1>you are logged out successfully...!</h1>


</body>
</html>