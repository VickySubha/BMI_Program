<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%-- <% boolean b = (Boolean)session.getAttribute("isLoggedIn");%> --%>
<%-- <%
	session.setAttribute("isLoggedIn", true);
%> --%>
<%
	response.sendRedirect("https://accounts.google.com/o/oauth2/auth?redirect_uri=http://1-dot-oauthmvcc.appspot.com/log&response_type=code&client_id=640072184216-s3c9gq3r8erkhekb8rtgjhmjvh6blimj.apps.googleusercontent.com&approval_prompt=force&scope=email&access_type=online");
%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>authenticating...</title>
</head>
<body>

</body>
</html>