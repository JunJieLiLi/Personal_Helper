package com.itcast.webcontroller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itcast.domain.Transaction;
import com.itcast.service.impl.BusinessServiceImpl;


@WebServlet("/EditTransactionServlet")
public class EditTransactionServlet extends HttpServlet 
{

	protected void doGet(HttpServletRequest _request, HttpServletResponse _response) throws ServletException, IOException 
	{
		doPost(_request,_response);
	}

	protected void doPost(HttpServletRequest _request, HttpServletResponse _response) throws ServletException, IOException 
	{
		System.out.println("this is a edit transaction servlet");
		
		// get the selected transaction
		Transaction transcation= new Transaction();
		
		int transactionID=  Integer.parseInt(_request.getParameter("transactionID"));

		BusinessServiceImpl service= new BusinessServiceImpl();
		
		transcation=service.getEditTransaction(transactionID);
		
		// send the selected transaction to edit transaction jsp
		
		_request.getSession().setAttribute("TransactionID", transactionID);
		
		
		_request.setAttribute("selectedTransaction", transcation);
		_request.getRequestDispatcher("/WEB-INF/jsp/EditTransactionForm.jsp").forward(_request, _response);
		
	}

}
