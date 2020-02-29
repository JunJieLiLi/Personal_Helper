package com.itcast.webcontroller;

import java.io.IOException;
import java.util.Random;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itcast.domain.User;
import com.itcast.exception.UserExistException;
import com.itcast.service.impl.BusinessServiceImpl;
import com.itcast.web.formbean.RegisterForm;

// chu li zhu ce qing qiu
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet 
{
	
	protected void doGet(HttpServletRequest _request, HttpServletResponse _response) throws ServletException, IOException 
	{
		doPost(_request,_response);
	}

	
	protected void doPost(HttpServletRequest _request, HttpServletResponse _response) throws ServletException, IOException 
	{
		System.out.println("This is a register servlet");
		// 1. jiao yan biao dan de he fa xing
		String user_name=_request.getParameter("userId");
		String user_pass=_request.getParameter("pass");
		String user_conferm_pass=_request.getParameter("conferm_pass");
		
		if((user_name==null || user_name=="")||(user_pass==null ||user_pass=="")||(user_conferm_pass==null || user_conferm_pass==""))
		{
			_request.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(_request, _response);
		}
		
		else if(!user_pass.equals(user_conferm_pass))
		{
			_request.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(_request, _response);
		}
		
		else
		{
			BusinessServiceImpl service= new BusinessServiceImpl();
			
			int user_id=getUniqueID();
			
			RegisterForm form= new RegisterForm();
			
			form.setUSerID(user_id);
			form.setUserName(user_name);
			form.setUserPass(user_pass);
			
			//boolean isValid= form.validated();
			boolean isValid= true;
			
			if(isValid)  // if login info is valid
			{
				_request.setAttribute("form", form);
				
				System.out.println("It is valid");

				
				// convert from form bean to user bean
				User users= new User();
				users.setUserID(user_id);
				users.setUsername(user_name);
				users.setPassword(user_pass);
				
				try {
					service.register(users);
					// send user id as a fk id to loging servlet
					_request.setAttribute("userName", users);
					_request.getSession().setAttribute("userObj", users);
					//_request.getRequestDispatcher("/TransactionServelet").forward(_request, _response);
				} catch (UserExistException e) {
					
					e.printStackTrace();
				}
				_request.getRequestDispatcher("/WEB-INF/jsp/MyMainPg.jsp").forward(_request, _response);
			}
			
			else
			{
				//_request.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(_request, _response);
				System.out.println("It is not valid");
			}
		}
		
	}
	
	private int getUniqueID()
	{
		Random random = new Random();
		return random.nextInt(10000);
	}

}
