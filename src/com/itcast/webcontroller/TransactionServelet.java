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
import com.itcast.web.formbean.TotalSummaryForm;


@WebServlet("/TransactionServelet")
public class TransactionServelet extends HttpServlet {

	protected void doGet(HttpServletRequest _request, HttpServletResponse _response) throws ServletException, IOException 
	{
		doPost(_request,_response);
	}

	protected void doPost(HttpServletRequest _request, HttpServletResponse _response) throws ServletException, IOException 
	{
		System.out.println("this is transaction servlet! ");
		
		String date=_request.getParameter("add_date");
		String income=_request.getParameter("add_incom");
		String outgoing=_request.getParameter("outgoing_incom");
		String description=_request.getParameter("add_descript");
		
		if(date==null || income==null || outgoing==null ||  description==null)
		{
			System.out.println("instances are null");
		}
		
		// send the user object from register servlet to trasaction sevlet 
		User client= new User();
		client=(User) _request.getSession().getAttribute("userObj");
		
		if(client==null)
		{
			System.out.println("client=(User) _request.getAttribute client is null");
		}

		
		AddTransactionForm tranform= new AddTransactionForm();
		
		tranform.setFKClientID(client.getUserID());
		tranform.setDate(date);
		tranform.setIncome(Integer.parseInt(income));
		tranform.setOutgoing(Integer.parseInt(outgoing));
		tranform.setDescription(description);
		
		BusinessServiceImpl service= new BusinessServiceImpl();
		
		try 
		{
			service.addTransaction(tranform);
		} catch (UserExistException e) 
		{
			e.printStackTrace();
		}
		

		ArrayList<UserTransactionForm> userTranList= new ArrayList<UserTransactionForm>();
		
		userTranList=service.getUserTransaction(tranform.getFKCLientID());
		
		TotalSummaryForm totalsummaryform= new TotalSummaryForm();
		totalsummaryform.setTotalIncome(service.getTotalIncome(userTranList));
		totalsummaryform.setTotalOutgoing(service.getTotalOutgoing(userTranList));
		totalsummaryform.setTotalBalance(service.getTotalBalance(userTranList));
		
		
		
		if(userTranList.size()!=0)
		{
			_request.setAttribute("tranList", userTranList);
			
			//send the transaction to mymainjsp
			_request.setAttribute("summary", totalsummaryform);
			_request.setAttribute("tranList", userTranList);
			
			_request.getRequestDispatcher("/WEB-INF/jsp/MyMainPg.jsp").forward(_request, _response);
		}
		
		
		for(int i=0;i<userTranList.size();i++)
		{
			System.out.println("client id: "+userTranList.get(i).getClientID()+"client description: "+userTranList.get(i).getDescription());
		}
		
		System.out.println("transaction added! ");
		
	}
	
	

}
