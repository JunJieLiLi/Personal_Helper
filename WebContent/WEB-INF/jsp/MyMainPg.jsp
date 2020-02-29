<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.util.*" %>
<%@page import="com.itcast.domain.User" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>

	<link rel="stylesheet" href="${pageContext.request.contextPath}/mainPG.css" />
	
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Insert title here</title>
	</head>
	<body>
		<div id="headder">
		</div>
		<div id="main_body">
			<div id="navigation_section">
				<div id="navigation_bar">
					<ul id="navigation_link">
						<li>
							<a href="#">Expense Tracker</a>
							
						</li>
					</ul>
				</div>
			</div>
			<div id="main_content">
				<div id="content">
					<div id="table_header">
						<table>
							<tr>
								<th id="expense_header_th">EXPENSE TRACKER</th>
								<th id="header_date_th"><%Calendar.getInstance().get(Calendar.MONTH); %></th>
							</tr>
						</table>
					</div>
					
					
					<div id="tool_button_section">
						<div id="personalIfn">
							Welcome ${userObj.username}!
						</div>
						<br>
						
						<div id="transaction_tool">
							<form method="POST" action="TransactionUIServelet">  
								<select id="do_transaction" name="do_transaction">
									<option value="add_trans"> Add Transaction</option>
									<option value="delete_trans"> Delete Transaction</option>
									<option value="edit_trans"> Edit Transaction</option>
								</select>
								<input type="submit" value="Go">
							</form>
							
							<form method="POST" action="MonthTransactionServlet">  
								<select id="viewOption" name="MonthView" >
									<option value="view_trans"> Select Month</option>
									<option value="January"> January</option>
									<option value="February"> February</option>
									<option value="March"> March</option>
									<option value="April"> April</option>
									<option value="May"> May</option>
									<option value="June"> June</option>
									<option value="July"> July</option>
									<option value="August"> August</option>
									<option value="September"> September</option>
									<option value="October"> October</option>
									<option value="November"> November</option>
									<option value="December"> December</option>
									<option value="ViewAll"> View All</option>
								</select>
								<input type="submit" value="Go">
							</form>
							<div id="filter_button">
								<form action="SortingUIServlet">
									<select name="sorting_selection" id="soring_id">
										<option name="LowToHithOutgoing" value="lowToheihgGoing">Low to High Outgoing</option>
										<option name="HighToLowOutgoing" value="hightTolowGoing">High to Low Outgoing</option>
										<option name="LowToHithIncome" value="lowToHighIncome">Low To High Income</option>
										<option name="HighToLowIncome" value="highToLowIncome">High To Low Income</option>
									</select>
									<input type="submit" value="Go">
								</form>
							</div>
							<br>
						</div>
					</div>
					<br>
					<div id="content_table">
						<table id="content_table_border">
							<tr>
								<th class="date">Date</th>
								<th id="description">Description</th> 
								<th id="income">Income</th>
								<th id="outgoing">Outgoing</th>
							</tr>
							
							<c:forEach var="transform" items="${tranList}">
								<tr>
									<td class="col1">${transform.dates}</td>
									<td class="col2">${transform.description}</td>
									<td class="col3">${transform.income}</td>
									<td class="col4">${transform.outgoing}</td>
								</tr>
							<br>
							</c:forEach>
						</table>
					</div>
					<br>
					<div id="table_footer">
						<div id="totoalIncome">
							<table id="total_income_table">
								<tr>
									<th class="income_tabler_th">TOTAL INCOME</th>
								</tr>
								<tr>
									<td> ${summary.totalIncome}</td>
								</tr>
							</table>
							
							<table id="total_outgoing_table">
								<tr>
									<th class="outgoing_tabler_th">TOTAL OUTGOINGS</th>
								</tr>
								<tr>
									<td> ${summary.totalOutgoing}</td>
								</tr>
							</table>
							
							<table id="total_balance_table">
								<tr>
									<th class="balance_tabler_th">BALANCE</th>
								</tr>
								
								<tr>
									<td>${summary.totalBalance}</td>
								</tr>
								
							</table>
						</div>
					</div>
				</div>
			</div>
					
		</div>
	</body>
</html>