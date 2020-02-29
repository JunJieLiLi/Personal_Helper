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


@WebServlet("/SortingUIServlet")
public class SortingUIServlet extends HttpServlet {


	protected void doGet(HttpServletRequest _request, HttpServletResponse _response) throws ServletException, IOException 
	{
		doPost(_request,_response);
	}


	protected void doPost(HttpServletRequest _request, HttpServletResponse _response) throws ServletException, IOException 
	{
		System.out.println("this is sortinbgUIServelet");
		
		User user=new User();
		user=(User)_request.getSession().getAttribute("userObj");
		
		String selectedSortingType=_request.getParameter("sorting_selection");
		
		System.out.println("selectedSortingType: "+selectedSortingType );
		
		// populate the transaction of the selected month
		BusinessServiceImpl service= new BusinessServiceImpl();
		ArrayList<UserTransactionForm> list= new ArrayList<UserTransactionForm>();
		
		if(selectedSortingType!=null)
		{
			if(selectedSortingType.equals("hightTolowGoing"))
			{
				list=service.getDescdingOrderTransactions(user.getUserID(), "Outgoing");
			}
			else if(selectedSortingType.equals("lowToheihgGoing"))
			{
				list=service.getAscendingOrderTransaction(user.getUserID(), "Outgoing");
			}
			else if(selectedSortingType.equals("lowToHighIncome"))
			{
				list=service.getAscendingOrderTransaction(user.getUserID(), "Income");
			}
			else if(selectedSortingType.equals("highToLowIncome"))
			{
				list=service.getDescdingOrderTransactions(user.getUserID(), "Income");
			}
		}
		
		//send the updated trasaction list to mymainjsp

		_request.setAttribute("tranList", list);
		_request.getRequestDispatcher("/WEB-INF/jsp/MyMainPg.jsp").forward(_request, _response);
		
	}

}
