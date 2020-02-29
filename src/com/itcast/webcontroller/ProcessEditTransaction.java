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
import com.itcast.service.impl.BusinessServiceImpl;
import com.itcast.web.formbean.EditTransactionFormBean;
import com.itcast.web.formbean.TotalSummaryForm;


@WebServlet("/ProcessEditTransaction")
public class ProcessEditTransaction extends HttpServlet 
{

	protected void doGet(HttpServletRequest _request, HttpServletResponse _response) throws ServletException, IOException 
	{
		doPost(_request,_response);
	}


	protected void doPost(HttpServletRequest _request, HttpServletResponse _response) throws ServletException, IOException 
	{
		System.out.println("this is a ProcessEditTransaction servlet");
		EditTransactionFormBean tranfrom= new EditTransactionFormBean();
		
		User client= new User();
		client=(User) _request.getSession().getAttribute("userObj");
		
		if(client==null)
		{
			System.out.println("client is null");
		}
		
		int transID=(int) _request.getSession().getAttribute("TransactionID");
		String dates= _request.getParameter("dates");
		String description=_request.getParameter("description");
		String income= _request.getParameter("income");
		String outgoing=_request.getParameter("outgoing");
		
		// get data from the edit form jsp
		
		tranfrom.setTransactionID( transID);
		tranfrom.setDescription(description);
		tranfrom.setIncome(Double.parseDouble(income));
		tranfrom.setDates(dates);
		tranfrom.setOutgoing(Double.parseDouble(outgoing));
		
		// update the database
		BusinessServiceImpl service= new BusinessServiceImpl();
		service.upDateEditTransaction(tranfrom.getTransactionID(), client.getUserID(), tranfrom.getIncome(), tranfrom.getDates(), tranfrom.getOutgoing(), tranfrom.getDescription());
		
		//send the updated trasaction list to mymainjsp
		ArrayList<UserTransactionForm> userTranList= new ArrayList<UserTransactionForm>();
		userTranList=service.getUserTransaction(client.getUserID());
		_request.setAttribute("tranList", userTranList);
		
		TotalSummaryForm totalsummaryform= new TotalSummaryForm();
		totalsummaryform.setTotalIncome(service.getTotalIncome(userTranList));
		totalsummaryform.setTotalOutgoing(service.getTotalOutgoing(userTranList));
		totalsummaryform.setTotalBalance(service.getTotalBalance(userTranList));
		
		//send the transaction to mymainjsp
		_request.setAttribute("summary", totalsummaryform);
		_request.setAttribute("tranList", userTranList);
		// send the updated tranfrom obj to jsp
		_request.getRequestDispatcher("/WEB-INF/jsp/MyMainPg.jsp").forward(_request, _response);
	}

}
