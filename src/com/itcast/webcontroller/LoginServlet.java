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
import com.itcast.web.formbean.LoginForm;
import com.itcast.web.formbean.TotalSummaryForm;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {


	protected void doGet(HttpServletRequest _request, HttpServletResponse _response) throws ServletException, IOException 
	{
		doPost(_request,_response);
	}

	protected void doPost(HttpServletRequest _request, HttpServletResponse _response) throws ServletException, IOException 
	{
		System.out.println("this is a login servlet");
		
		String user_name=_request.getParameter("userId");
		String user_pass=_request.getParameter("pass");
		
		if(user_name==null || user_pass==null)
		{
			System.out.println("request parameter is null");
		}
		
		LoginForm login= new LoginForm();
		
		login.setUsename(user_name);
		login.setUserpass(user_pass);
		
		BusinessServiceImpl service= new BusinessServiceImpl();
		
		ArrayList<UserTransactionForm> userTranList= new ArrayList<UserTransactionForm>();
		
		User user= new User();
		user.setUsername(user_name);
		user.setPassword(user_pass);
		
		try {
			user=service.login(user.getUsername(), user.getPassword());
			
			if(user.getUsername()!=null && user.getPassword()!=null)
			{
				userTranList=service.getUserTransaction(user.getUserID());
				
				_request.setAttribute("tranList", userTranList);
				
				_request.getSession().setAttribute("userObj", user);
				
				// get total summary for balance, income and outgoing
				TotalSummaryForm totalsummaryform= new TotalSummaryForm();
				
				totalsummaryform.setTotalOutgoing(service.getTotalOutgoing(userTranList));
				totalsummaryform.setTotalBalance(service.getTotalBalance(userTranList));
				totalsummaryform.setTotalIncome(service.getTotalIncome(userTranList));
				
				_request.setAttribute("summary", totalsummaryform);
				
				_request.getRequestDispatcher("/WEB-INF/jsp/MyMainPg.jsp").forward(_request, _response);
			}
			else
			{
				_request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(_request, _response);
			}
			
		} catch (UserExistException e) {
			e.printStackTrace();
		}
		
		
	}

}
