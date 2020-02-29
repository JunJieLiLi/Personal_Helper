package com.itcast.web.UI;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoginUIServlet")
public class LoginUIServlet extends HttpServlet 
{

	protected void doGet(HttpServletRequest _request, HttpServletResponse _response) throws ServletException, IOException 
	{
		doPost(_request,_response);
	}

	protected void doPost(HttpServletRequest _request, HttpServletResponse _response) throws ServletException, IOException 
	{
		_request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(_request, _response);
	}

}
