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
				<h3 style=text-align:center> Welcome to edite html page</h3>
			</div>
		</div>
		
		<div id="bodyy">
			<div id="transaction_outer_container">
				<div id="contrascition_inner_container">
				
					<div id="transaction_head">
						<h3 id="transaction_date">transacation date to be displayed</h3>
					</div>
				
					<div id="filter_button">
						<form action="/EditTransactionServlet">
							<select name="sorting_selection">
								<option name="sortByDate">Sort by Date</option>
								<option name="LowToHithOutgoing">Low to High Outgoing</option>
								<option name="HighToLowOutgoing">High to Low Outgoing</option>
								<option name="LowToHithIncome">Low To High Income</option>
								<option name="HighToLowIncome">High To Low Income</option>
							</select>
							<input type="submit" value="Go">
						</form>
					</div>
				
					<div id="main_transaction_list_container">
						<form  method="POST" action="EditTransactionServlet">
							<table id="transaction_table" width="100%">
								<tr>
									<th id="datess">Date</th>
									<th id="description">Description</th> 
									<th id="income">Income</th>
									<th id="outgoing">Outgoing</th>
									<th>Select</th>
								</tr>
							
								<c:forEach var="transform" items="${tranList}">
									<tr>
										<td class="col1">${transform.dates}</td>
										<td class="col2">${transform.description}</td>
										<td class="col3">${transform.income}</td>
										<td class="col4">${transform.outgoing}</td>
										<td>
											<input type="radio" name="transactionID" value="${transform.transactionID}" >
										</td>
									</tr>
								</c:forEach>	
							</table>
							 <input type="submit" value="Submit">
						</form>
						
					</div>
					
				</div>
			</div>
		</div>

	<body>
</html>