package com.itcast.webcontroller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itcast.DBForm.UserTransactionForm;
import com.itcast.domain.User;
import com.itcast.service.impl.BusinessServiceImpl;
import com.itcast.web.formbean.TotalSummaryForm;


@WebServlet("/MonthTransactionServlet")
public class MonthTransactionServlet extends HttpServlet {

	protected void doGet(HttpServletRequest _request, HttpServletResponse _response) throws ServletException, IOException 
	{
		doPost(_request,_response);
	}

	protected void doPost(HttpServletRequest _request, HttpServletResponse _response) throws ServletException, IOException
	{
		System.out.println("this is MonthTransactionServlet");
		
		User user=new User();
		user=(User)_request.getSession().getAttribute("userObj");
		
		//get the selected month
		String selectedOption=_request.getParameter("MonthView");
		
		System.out.println("The selected option is: "+selectedOption);
		
		// populate the transaction of the selected month
		BusinessServiceImpl service= new BusinessServiceImpl();
		ArrayList<UserTransactionForm> list= new ArrayList<UserTransactionForm>();
		
		//Date date= new Date();

		// needs a better way to implement this
		if(selectedOption!=null)
		{
			// get the selected trasactions of the month
			if(selectedOption.equals("January"))
			{
				list=service.getSelectedMonthList(user.getUserID(), "2020-01-01", "2020-01-31");
			}
			else if(selectedOption.equals("February"))
			{
				list=service.getSelectedMonthList(user.getUserID(), "2020-02-01", "2020-02-28");
			}
			else if(selectedOption.equals("March"))
			{
				list=service.getSelectedMonthList(user.getUserID(), "2020-03-01", "2020-03-31");
			}
			else if(selectedOption.equals("April"))
			{
				list=service.getSelectedMonthList(user.getUserID(), "2020-04-01", "2020-04-30");
			}
			else if(selectedOption.equals("May"))
			{
				list=service.getSelectedMonthList(user.getUserID(), "2020-05-01", "2020-05-31");
			}
			else if(selectedOption.equals("June"))
			{
				list=service.getSelectedMonthList(user.getUserID(), "2020-06-01", "2020-06-30");
			}
			else if(selectedOption.equals("August"))
			{
				list=service.getSelectedMonthList(user.getUserID(), "2020-08-01", "2020-08-30");
			}
			else if(selectedOption.equals("September"))
			{
				list=service.getSelectedMonthList(user.getUserID(), "2020-09-01", "2020-09-31");
			}
			else if(selectedOption.equals("October"))
			{
				list=service.getSelectedMonthList(user.getUserID(), "2020-10-01", "2020-10-30");
			}
			else if(selectedOption.equals("November"))
			{
				list=service.getSelectedMonthList(user.getUserID(), "2020-11-01", "2020-11-30");
			}
			else if(selectedOption.equals("December"))
			{
				list=service.getSelectedMonthList(user.getUserID(), "2020-12-01", "2020-12-31");
			}
			else
			{
				list=service.getUserTransaction(user.getUserID());
			}
			
		}
		
		TotalSummaryForm totalsummaryform= new TotalSummaryForm();
		totalsummaryform.setTotalIncome(service.getTotalIncome(list));
		totalsummaryform.setTotalOutgoing(service.getTotalOutgoing(list));
		totalsummaryform.setTotalBalance(service.getTotalBalance(list));
		
		//send the transaction to mymainjsp
		_request.setAttribute("summary", totalsummaryform);
		_request.setAttribute("tranList", list);
		
		_request.getRequestDispatcher("/WEB-INF/jsp/MyMainPg.jsp").forward(_request, _response);
	}

}
