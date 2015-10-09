<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="../CSS/Login.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="../CSS/bootstrap.min.css">
</head>
<body>
	<div class="navbar navbar-inverse navbar-static-top">
		<div class="container">
			<div class=" navbar-left navbar-brand">
				<strong>Body Mass Index</strong>
			</div>
			<div id="my-button">
				<button class="navbar-toggle" data-toggle="collapse"
					data-target=".navHeaderCollapse">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
			</div>
			<div id="add-br"></div>
			<div class="collapse navbar-collapse navHeaderCollapse">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="/">HOME</a></li>
					<li><a href="../Index.jsp">Calculate BMI</a></li>
					<li><a href="#contact" data-toggle="modal">Contact</a></li>
				</ul>
			</div>
		</div>
	</div>

	<a href="../SignUp.jsp">
		<button type="button" class="btn btn-primary pull-right"
			style="margin-right: 5px;">Sign up</button>
	</a>
	<br>
	<br>
	<div class="modal-dialog " style="clear: both">
		<div style="background-color: rgba(255, 255, 255, 0.2);"
			class="modal-content">
			<div class="modal-header">
				<h4>
					<b>Login</b>
				</h4>
			</div>
			<div class="modal-body">
				<form class="form-horizontal" action="/ValidateLogin" method="post">
					<div class="form-group">
						<label for="usrname" class="control-label col-sm-2">Username</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="usrname"
								placeholder="Nickname" name="uName" required>
						</div>
					</div>
					<div class="form-group">
						<label for="email" class="control-label col-sm-2">Password</label>
						<div class="col-sm-10">
							<input type="password" class="form-control" id="email"
								placeholder="Password" name="password" required>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">

							<button type="submit" class="btn btn-primary">Login</button>
						</div>
					</div>
					</div>
				</form>
			</div>
		</div>
	</div>

	<div class="modal fade" id="contact">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4>
						<b>Contact me</b>
					</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" action="/MailSend">
						<div class="form-group">
							<label for="usrname" class="control-label col-lg-2">Name</label>
							<div class="col-lg-10">
								<input type="text" class="form-control" id="usrname"
									placeholder="Full name" name="name" required>
							</div>
						</div>
						<div class="form-group">
							<label for="email" class="control-label col-lg-2">E-mail</label>
							<div class="col-lg-10">
								<input type="email" class="form-control" id="email"
									placeholder="you@yourdomain.com" name="email" required>
							</div>
						</div>
						<div class="form-group">
							<label for="msg" class="control-label col-lg-2">Message</label>
							<div class="col-lg-10">
								<textarea rows="8" id="msg" name="msg" class="form-control"
									placeholder="Your Message" required></textarea>
							</div>
						</div>
						<div class="form-group">
							<button type="submit" class="btn btn-primary pull-right"
								style="margin-right: 5px">Send</button>
							<button type="button" class="btn btn-default pull-right"
								style="margin-right: 5px" data-dismiss="modal">Cancel</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script src="../js/bootstrap.min.js"></script>
	<script type="text/javascript" src="../js/AddBr.js"></script>

</body>
</html>