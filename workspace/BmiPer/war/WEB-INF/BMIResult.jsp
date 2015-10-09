<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Your BMI value</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="../CSS/bootstrap.min.css">
</head>
<body>    
	<%if((session.getAttribute("isLoggedIn")==null)|| ((Boolean)session.getAttribute("isLoggedIn")==false)){%>
	<script>
          alert("You are not logged in!");
          window.location = 'Welcome.jsp';
     </script> 
     <%} %>
	<div class="navbar navbar-inverse navbar-static-top">
		<div class="container">
			<div class=" navbar-left navbar-brand">
				<strong>Body Mass Index</strong>
			</div>
			<div id="my-button">
			<button class="navbar-toggle" data-toggle="collapse" data-target=".navHeaderCollapse">
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
			</button>
			</div>
			<div id="add-br">
			</div>
			<div class="collapse navbar-collapse navHeaderCollapse" >
				<ul class="nav navbar-nav navbar-right">
						<li><a href="/">HOME</a></li>
						<li><a href="../Index.jsp">Calculate BMI</a></li>
						<li><a href="#contact" data-toggle="modal">Contact</a></li>
				</ul>
			</div>
		</div>
	</div>
	<a href="../Logout">
		<button style="margin-right:5px" class="btn btn-danger pull-right">Log out</button>
	</a>
	<br>	
	<center><p class="text-info lead"><b>Your BMI value is ${bmi}</b> !</p></center>
	<div style="margin:10px">
	<c:if test="${bmi>27.5}">
		<p class="text-danger lead">Attention! You are overweighted. We strictly recommend you to visit any doctor and follow some medications. Here is something that we can offer you!</b></p>
		<ul>
			<li class="lead"><a href="http://authoritynutrition.com/20-most-weight-loss-friendly-foods/">Here are some <span class="text-danger">foods</span>  that will help you reduce your weight.</a></li>
			<li class="lead"><a href="http://www.dailymail.co.uk/femail/article-2935611/Lose-weight-yoga-forget-fad-diets-agonising-workouts-10-steps-trim-tum.html">These are some basic <span class="text-danger">exercises</span> that will help you reduce your weight.</a></li>
		</ul>
	</c:if>
	<c:if test="${bmi<18.5}">
		<p class="text-danger lead">Attention! You are underweighted.  We strongly recommend you to follow some food habits that will make your life comfortable and better.</b></p>
		<ul>
			<li class="lead"><a style="color:black" href="http://www.thehealthsite.com/fitness/weight-gain-foods-sa114/">Here are some <span class="text-danger">foods</span>  that will help you increase your weight.</a></li>
			<li class="lead"><a style="color:black" href="http://www.stylecraze.com/articles/effective-yoga-exercises-to-gain-weight/">These are some basic <span class="text-danger">exercises</span> that will help you increase your weight.</a></li>
		</ul>
	</c:if>
	<c:if test="${(bmi>18.4)&&(bmi<27.6)}">
		<p class="text-primary lead"><b>You are pretty much healthier. You don't have to be worried about your weight. But make sure you check your BMI value at least once per week to ensure that you are healthy from time to time</b></p>	
	</c:if>
	</div>
	
	 <div class="modal fade" id="contact">
		<div class="modal-dialog" >
			<div class="modal-content">
				<div class="modal-header" >
					<h4><b>Contact me</b></h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" action="/MailSend" >
						<div class="form-group">
							<label for="usrname" class="control-label col-lg-2">Name</label>
							<div class="col-lg-10">
								<input type="text" class="form-control" id="usrname" placeholder="Full name" name="name" required>
							</div>
						</div>
						<div class="form-group">
							<label for="email" class="control-label col-lg-2">E-mail</label>
							<div class="col-lg-10">
								<input type="email" class="form-control" id="email" placeholder="you@yourdomain.com" name="email" required>
							</div>
						</div>
						<div class="form-group">
							<label for="msg" class="control-label col-lg-2">Message</label>
								<div class="col-lg-10">
									<textarea rows="8" id="msg" name="msg" class="form-control" placeholder="Your Message" required></textarea>
								</div>					
						</div>
						<div class="form-group">
							<button type="submit" class="btn btn-primary pull-right" style="margin-right:5px">Send</button>
							<button type="button" class="btn btn-default pull-right"  style="margin-right:5px" data-dismiss="modal">Cancel</button>
						</div>
					</form>
				</div>
			</div>				
		</div>
	</div>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script src="../js/bootstrap.min.js"></script>
	<script type="text/javascript" src="../js/AddBr.js"></script>
</body>
</html>