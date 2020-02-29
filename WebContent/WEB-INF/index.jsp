<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<link type="text/css" style="stylesheet" href="${pageContext.request.contextPath}/main_pg.css">

<html>

	<head>
		<title>Main Page</title>
	</head>
	
	<body>
		<form id="header">
			<h3>Welcome to My Personal Helper</h3>
		</form>
		<a href="${pageContext.request.contextPath}/RegisterUIServlet">Register</a>
		<a href="${pageContext.request.contextPath}/LoginUIServlet">Login</a>	
		
	</body>
</html>