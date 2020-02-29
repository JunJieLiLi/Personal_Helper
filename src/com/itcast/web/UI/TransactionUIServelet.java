package com.itcast.web.UI;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itcast.DBForm.UserTransactionForm;
import com.itcast.domain.User;
import com.itcast.service.impl.BusinessServiceImpl;


@WebServlet("/TransactionUIServelet")
public class TransactionUIServelet extends HttpServlet {

	protected void doGet(HttpServletRequest _request, HttpServletResponse _response) throws ServletException, IOException 
	{
		doPost(_request,_response);
	}

	protected void doPost(HttpServletRequest _request, HttpServletResponse _response) throws ServletException, IOException 
	{
		// 1. get the selected option from user
		// 2. forward the correspoding jsp to the selected option
		
		System.out.println("this is transaction UI servlet");
		String selected_option=_request.getParameter("do_transaction");
		System.out.println(selected_option);
		
		if(selected_option!=null)
		{
			if(selected_option.equals("add_trans"))
			{
				_request.getRequestDispatcher("/WEB-INF/jsp/addTransaction.jsp").forward(_request, _response);
			}
			
			else if(selected_option.equals("delete_trans"))
			{
				String jspName="/WEB-INF/jsp/deleteTransaction.jsp";
				populateTransactionList(_request,_response,jspName);
				
			}
			else
			{
				String jspName="/WEB-INF/jsp/editTransaction.jsp";
				populateTransactionList(_request,_response,jspName);
			}
		}
		else
		{
			return;
		}
	}
	
	private void populateTransactionList(HttpServletRequest _request, HttpServletResponse _response, String _filePath) throws ServletException, IOException
	{

		User client= new User();
		client=(User) _request.getSession().getAttribute("userObj");
		
		BusinessServiceImpl service= new BusinessServiceImpl();
		
		
		ArrayList<UserTransactionForm> userTranList= new ArrayList<UserTransactionForm>();
		userTranList=service.getUserTransaction(client.getUserID());
		
		if(userTranList.size()!=0)
		{
			_request.setAttribute("tranList", userTranList);
			_request.getRequestDispatcher(_filePath).forward(_request, _response);
		}
	}

}
