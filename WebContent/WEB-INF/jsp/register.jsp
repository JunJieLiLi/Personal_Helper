<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/reg.css" />
	<head>
		<title>Insert title here</title>
	</head>
	<body>
		<div id="header">
			
		</div>
		<div id="main_body">
			<div id="form_container">
				
				<div id="login_container">
					<h3>My Helper</h3>
					<form  action="${pageContext.request.contextPath}/RegisterServlet" method="POST">
						<input type="text" name="userId" placeholder="User Name" class="user_text_field" >
						<br>
						<input type="password" name="pass" placeholder="Password" class="pass_text_field">
						<br>
						<input type="password" name="conferm_pass" placeholder="Conform Password" class="pass_text_field">
						<br>
						<input type="submit" value="Register Now"  id="register_botton" >
					</form>
				</div>
				
				<p id="copy_right">Developed by Pablo Li (JunJie Li)</p>
			</div>
	<body>
</html>