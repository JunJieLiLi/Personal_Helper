<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="stylesheet" href="${pageContext.request.contextPath}/DeletePgCSS.css" />
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Insert title here</title>
	</head>
	<body>
		<div id="headd">
			<div id="headsentence">
				<h3 style=text-align:center> Welcome to selected edit transaction form pg</h3>
			</div>
		</div>
		
		<div id="bodyy">
			<div id="transaction_outer_container">
				<div id="contrascition_inner_container">
				
					<div id="transaction_head">
						<h3 id="transaction_date">transacation date to be displayed</h3>
					</div>
				
					<div id="main_transaction_list_container">
						
					<div id="main_transaction_list_container">
						<form  method="POST" action=ProcessEditTransaction>
							<table id="transaction_table" width="100%">
								<tr>
									<th id="datess">Date</th>
									<th id="description">Description</th> 
									<th id="income">Income</th>
									<th id="outgoing">Outgoing</th>
								</tr>
								
								<tr>
									<td class="col1"><input type="text" name="dates" value="${selectedTransaction.date}"></td>
									<td class="col2"><input type="text" name="description" value="${selectedTransaction.description}"></td>
									<td class="col3"><input type="text" name="income" value="${selectedTransaction.income}"></td>
									<td class="col4"><input type="text" name="outgoing" value="${selectedTransaction.outgoing}"></td>
									<td> <input type="submit"></td>
								</tr>
							</table>
							
						</form>
						
					</div>
						
					</div>
					
				</div>
			</div>
		</div>

	<body>
</html>