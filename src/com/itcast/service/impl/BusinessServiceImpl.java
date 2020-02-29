package com.itcast.service.impl;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


import java.util.Date;

import com.itcast.dao.impl.TransactionDaoImpl;
import com.itcast.dao.impl.UserDaoImpl;
import com.itcast.domain.Transaction;
import com.itcast.domain.User;
import com.itcast.exception.UserExistException;
import com.itcast.web.formbean.AddTransactionForm;
import com.itcast.web.formbean.TotalSummaryForm;
import com.itcast.DBForm.UserTransactionForm;
import com.itcast.Daoa.TransactionDao;
import com.itcast.Daoa.UserDao;;

public class BusinessServiceImpl
{
	private UserDao dao=new UserDaoImpl();
	private TransactionDao tranDao= new TransactionDaoImpl();
	
	
	/******************************************************************
	 * Method: register
	 * @param: User _user
	 * add a user into database
	 * return none
	 ******************************************************************/
	public void register(User _user) throws UserExistException
	{
		//boolean found=dao.find(_user.getUsername());
		User user= new User();
		user=dao.find(_user.getUsername(),_user.getPassword());
		
		if(user.getUsername()==null && user.getPassword()==null)
		{
			dao.add(_user);
		}
		else
		{
			throw new UserExistException();  
		}
		
	}
	
	/******************************************************************
	 * Method: login
	 * @param: String _username, String _pass
	 * find a existence user 
	 * return a user who is in the database
	 ******************************************************************/
	public User login(String _username, String _pass) throws UserExistException
	{
		if(dao.find(_username, _pass)==null)
		{
			throw new UserExistException();  
		}
		else
		{
			return dao.find(_username, _pass);
		}
		
	}
	
	/******************************************************************
	 * Method: addTransaction
	 * @param: none
	 * add transaction into database
	 * return none
	 ******************************************************************/
	public void addTransaction(AddTransactionForm tranform) throws UserExistException
	{
		// 1. before add a new transaction, check whether a transaction is being enters is exist in the database
		boolean found= tranDao.findTransaction(tranform.getDate());
		if(found)
		{
			throw new UserExistException();
		}
		else
		{
			Transaction trans= new Transaction();
			trans.setFKClientID(tranform.getFKCLientID());
			trans.setDate(tranform.getDate());
			trans.setDescription(tranform.getDescription());
			trans.setIncome(tranform.getIncome());
			trans.setOutgoing(tranform.getOutgoing());
	
			tranDao.addTransaction(trans);
		}
		
	}
	
	/******************************************************************
	 * Method: getDBTransactionFromDao
	 * @param: none
	 * getting the transaction list
	 * return a transactionform object list 
	 ******************************************************************/
	public ArrayList<AddTransactionForm> getDBTransactionFromDao()
	{
		ArrayList<AddTransactionForm> transacform= new ArrayList<AddTransactionForm>();
		transacform=tranDao.getDBTransaction();		
		
		return transacform;
	}
	
	/******************************************************************
	 * Method: getUserTransaction
	 * @param: int _clientID
	 * getting the transaction list using client id
	 * return a UserTransactionForm object list 
	 ******************************************************************/
	public 	ArrayList<UserTransactionForm> getUserTransaction(int _clientID)
	{
		ArrayList<UserTransactionForm> userTranList= new ArrayList<UserTransactionForm>();
		userTranList=dao.getUserTransactionList(_clientID);
		return userTranList;
	}
	
	/*************************************************************************
	 * Method: checkDuplicateUserId
	 * @param: int _clientID
	 * checking if a user is exist in the database
	 * return true if a user is already in the database else return false
	 *************************************************************************/
	public boolean checkDuplicateUserId(int _clinetID)
	{
		boolean found=false;
		
		if(dao.findUserID(_clinetID))
		{
			found=true;
		}
		else 
		{
			found=false;
		}
		
		return found;
	}
	
	
	/************************************************************************************************
	 * Method: getUserTransaction
	 * @param: String _userName, String _pass
	 * getting trasnaction using user name and user pass and store in the UserTransactionForm list
	 * return UserTransactionForm list 
	 *************************************************************************************************/
	public 	ArrayList<UserTransactionForm> getUserTransaction(String _userName, String _pass)
	{
		ArrayList<UserTransactionForm> userTranList= new ArrayList<UserTransactionForm>();

		User user= new User();
		user=dao.find(_userName,_pass);
		userTranList=dao.getUserTransactionList(user.getUserID());
		return userTranList;
	}
	
	
	/************************************************************************************************
	 * Method: deleteTransaction
	 * @param: int _transactionID
	 * deletes a transaction in the database
	 * return none
	 *************************************************************************************************/
	public void deleteTransaction(int _transactionID)
	{
		boolean found=tranDao.findTransaction(_transactionID);
		
		if(found)
		{
			tranDao.deleteTransaction(_transactionID);
		}
		else
		{
			System.out.println("transaction not found");  // this needs to be thourw an exception to user
		}
	}
	
	/************************************************************************************************
	 * Method: editTransaction
	 * @param: Transaction _tran
	 * editting a transaction in the database
	 * return none
	 *************************************************************************************************/
	public void editTransaction(Transaction _tran)   // no need for now
	{
		//boolean found=dao.
	}
	
	
	/************************************************************************************************
	 * Method: editTransaction
	 * @param: int _transactionID
	 * editting a transaction in the database
	 * return none
	 *************************************************************************************************/
	public Transaction getEditTransaction(int _transactionID)
	{
		Transaction transaction= new Transaction();
		transaction=tranDao.getSingleEditTransactionDB(_transactionID);
		
		return transaction;	
	}
	
	/*********************************************************************************************************************
	 * Method: upDateEditTransaction
	 * @param: int _transactionID, int _clientID, double _income, String _datee, double _outgoing, String _description
	 * updating transaction in the database
	 * return none
	 ***********************************************************************************************************************/
	public void upDateEditTransaction(int _transactionID, int _clientID, double _income, String _datee, double _outgoing, String _description)
	{
		tranDao.upDateEditTransactionDB(_transactionID, _clientID, _income, _datee, _outgoing, _description);
		
	}
	
	/************************************************************************************************
	 * Method: getTotalIncome
	 * @param: 	ArrayList<UserTransactionForm> _list
	 * calculates the total income of a user
	 * return total income
	 *************************************************************************************************/
	public double getTotalIncome (	ArrayList<UserTransactionForm> _list)
	{
		double sum=0;
		if(_list.size()!=0)
		{
			for(int i=0;i<_list.size();i++)
			{
				sum=sum+_list.get(i).getIncome();
			}
		}
		
		return sum;
		
	}
	
	
	/************************************************************************************************
	 * Method: getTotalOutgoing
	 * @param: 	ArrayList<UserTransactionForm> _list
	 * calculates the total outgoing of a user
	 * return total outgoing
	 *************************************************************************************************/
	public double getTotalOutgoing (	ArrayList<UserTransactionForm> _list)
	{
		double sum=0;
		if(_list.size()!=0)
		{
			for(int i=0;i<_list.size();i++)
			{
				sum=sum+_list.get(i).getOutgoing();
			}
		}
		return sum;
	}
	
	/************************************************************************************************
	 * Method: getTotalBalance
	 * @param: 	ArrayList<UserTransactionForm> _list
	 * calculates the Total Balance of a user
	 * return total Total Balance
	 *************************************************************************************************/
	public double getTotalBalance (	ArrayList<UserTransactionForm> _list)
	{
		double sum=0;
		
		sum=getTotalIncome(_list)-getTotalOutgoing(_list);
		
		return sum;
	}
	
	
	/************************************************************************************************
	 * Method: getSelectedMonthList
	 * @param: 	int _clientID, String _firstDay, String _lastDay
	 * getting the selected month
	 * return all transactions within that month
	 *************************************************************************************************/
	public ArrayList<UserTransactionForm> getSelectedMonthList(int _clientID, String _firstDay, String _lastDay)
	{
		ArrayList<UserTransactionForm> list= new ArrayList<UserTransactionForm>();
		list=tranDao.getSelectedMonthListFromDao(_clientID, _firstDay, _lastDay);
		
		return list;
	}
	
	/************************************************************************************************
	 * Method: getDescdingOrderTransactions
	 * @param: 	int _clientID, String _catagory
	 * get the descending order transaction list
	 * return a descending order transaction list
	 *************************************************************************************************/
	public ArrayList<UserTransactionForm> getDescdingOrderTransactions (int _clientID, String _catagory)
	{
		 ArrayList<UserTransactionForm> list= new  ArrayList<UserTransactionForm>();
		 
		 list=tranDao.getDescdingOrderTransaction(_clientID,_catagory);
		 
		 return list;
	}
	
	/************************************************************************************************
	 * Method: getAscendingOrderTransaction
	 * @param: 	int _clientID, String _catagory
	 * get the ascending order transaction list
	 * return a ascending order transaction list
	 *************************************************************************************************/
	public ArrayList<UserTransactionForm> getAscendingOrderTransaction(int _clientID, String _catagory)
	{
		 ArrayList<UserTransactionForm> list= new  ArrayList<UserTransactionForm>();

		 list=tranDao.getAscendingOrderTransaction(_clientID, _catagory);
		 
		 return list;
	}

}
