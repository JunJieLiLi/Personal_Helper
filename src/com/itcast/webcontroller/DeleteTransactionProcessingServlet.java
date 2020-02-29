package com.itcast.webcontroller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itcast.DBForm.UserTransactionForm;
import com.itcast.domain.User;
import com.itcast.exception.UserExistException;
import com.itcast.service.impl.BusinessServiceImpl;
import com.itcast.web.formbean.AddTransactionForm;


@WebServlet("/DeleteTransactionProcessingServlet")
public class DeleteTransactionProcessingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest _request, HttpServletResponse _response) throws ServletException, IOException 
	{
		doPost(_request,_response);
	}


	protected void doPost(HttpServletRequest _request, HttpServletResponse _response) throws ServletException, IOException 
	{
		System.out.println("this is delete transaction process servlet");
		
		User user= new User();
		
		user=(User)_request.getSession().getAttribute("userObj");
		
		if(user==null)
		{
			System.out.println("user is null");
		}
			
		String transactionID= _request.getParameter("transactionID");
		
		BusinessServiceImpl service= new BusinessServiceImpl();
		
		service.deleteTransaction( Integer.parseInt(transactionID));
		
		ArrayList<UserTransactionForm> userTranList= new ArrayList<UserTransactionForm>();
		userTranList=service.getUserTransaction(user.getUserID());
		
		_request.setAttribute("tranList", userTranList);
		_request.getRequestDispatcher("/WEB-INF/jsp/MyMainPg.jsp").forward(_request, _response);
	}

}
