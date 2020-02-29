<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/addTransactionCSS.css" />
	<head>
	</head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Insert title here</title>
	</head>
	<body>
		<div id="main_body">
			<div id="form_container">
				
				<div id="login_container">
					<h3>My Helper</h3>
					
					<form method="POST" action="TransactionServelet">  
						<input type="date" name="add_date"  placeholder="Date"  class="date_text_field"> 
						<br>
						<input type="text" name="add_incom"   placeholder="Income" class="income_text_field"> 
						<br>
						<input type="text" name="outgoing_incom"  placeholder="Outgoing" class="outgoing_text_field"> 
						<br>
						<textarea name="add_descript" placeholder="Description"   class="description_text_field"></textarea>
						<br>
						<input type="submit" value="Add Transaction" id="add_button">
					</form>
				</div>
				<p id="copy_right">Developed by Pablo Li (JunJie Li)</p>
			</div>
		</div>
	</body>
</html>