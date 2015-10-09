<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Online BMI</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="../CSS/bootstrap.min.css">
<link
	href='http://fonts.googleapis.com/css?family=Lato:400,700,400italic'
	rel='stylesheet' type='text/css'>
<script type="text/javascript" src="html2canvas.js"></script>
<script type="text/javascript" src="jquery-1.11.3.min.js"></script>
</head>
<body>
	<script type="text/javascript">
	function generare(){
		
	$("#second").click(function(){
		$("#contact").hide();
		   $("#first").trigger('click');
		   
		   return false;
		});}
		function generate() {
			
			html2canvas(document.body, {
				onrendered : function(canvas) {
					//$("#contact").hide();
					 
					var img = canvas.toDataURL()
					window.open(img);
					//setTimeout(function() { $("#contact").(); }, 5000);
					//$("#contact").show();
				}
			});
			$("#contact").show();
		}
	</script>

	<div class="navbar navbar-inverse navbar-static-top">
		<div class="container">
			<a href="#" class="logo"><div class=" navbar-left navbar-brand">

					Body Mass Index</div></a>
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
					<li id="home" class="active"><a href="#">HOME</a></li>
					<li><a href="/Index">Calculate BMI</a></li>
					<li id="#cntct"><a href="#contact" data-toggle="modal">Feedback
							& Support</a></li>
				</ul>
			</div>
		</div>
	</div>
	<input type="submit" Onclick="generate() " id="first" value="Take a snapshot" hidden>
	<a href="../SignUp.jsp">
		<button type="button" class="btn btn-primary pull-right"
			style="margin-right: 5px">Sign Up</button>
	</a>


	<%
		if ((session.getAttribute("isLoggedIn") == null)
				|| ((Boolean) session.getAttribute("isLoggedIn") == false)) {
	%>

	<a href="../oauth.jsp"><button type="button"
			class="btn btn-success pull-right" style="margin-right: 5px">Login
			with Google</button></a>
	<a href="../Login.jsp">
		<button type="button" class="btn btn-success pull-right"
			style="margin-right: 5px">Login</button>
	</a>
	<%
		} else {
	%>
	<a href="/Logout">
		<button type="button" id="logout" class="btn btn-danger pull-right"
			style="margin-right: 5px">Logout</button>
	</a>
	<%
		}
	%>
	<br>
	<br>
	<br>
	<center>
		<div id="myCarousel" class="carousel slide" data-ride="carousel">
			<ol class="carousel-indicators">
				<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
				<li data-target="#myCarousel" data-slide-to="1"></li>
				<li data-target="#myCarousel" data-slide-to="2"></li>
				<li data-target="#myCarousel" data-slide-to="3"></li>
			</ol>

			<div class="carousel-inner">
				<div class="item active">
					<img src="../images/carousel/quote0.jpg" alt="none" height="850"
						width="1400">
				</div>
				<div class="item">
					<img src="../images/carousel/quote1.jpg" alt="none" height="850"
						width="1400">
				</div>
				<div class="item">
					<img src="../images/carousel/quote2.jpg" alt="none" height="850"
						width="1400">
				</div>
				<div class="item">
					<img src="../images/carousel/quote3.jpg" alt="none" height="850"
						width="1400">
				</div>
			</div>

			<a class="carousel-control left" href="#myCarousel" data-slide="prev">
				<span class="icon-prev"></span>
			</a> <a class="carousel-control right" href="#myCarousel"
				data-slide="next"> <span class="icon-next"></span>
			</a>
		</div>
	</center>

	<div class="modal" id="contact">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4>
						<b>Feedback & Support</b>
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
						<input type="submit" id="second" value="Take snapshot" Onclick="generare()">

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