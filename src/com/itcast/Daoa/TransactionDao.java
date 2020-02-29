package com.itcast.Daoa;

import java.sql.SQLException;
import java.util.ArrayList;

import com.itcast.DBForm.UserTransactionForm;
import com.itcast.domain.Transaction;
import com.itcast.web.formbean.AddTransactionForm;

public interface TransactionDao {

	void addTransaction(Transaction _trans);
	
	public void deleteTransaction(int transactionID);
	
	public void editTransaction(Transaction _trans);
	
	public ArrayList<UserTransactionForm> getDBTransaction( int _ClientId );

	Transaction findTransaction(String date, String _income, String _outgoing);
	
	boolean findTransaction(int _date);

	boolean findTransaction(String _date);
	
	ArrayList<AddTransactionForm> getDBTransaction();  // needs to be fixed
	
	public Transaction getSingleEditTransactionDB(int _transactionID);
	
	public void upDateEditTransactionDB(int _transactionID, int _clientID, double _income, String _datee, double _outgoing, String _description);
	
	public ArrayList<UserTransactionForm> getSelectedMonthListFromDao(int _clientID, String _firstday, String lastday);
	
	public ArrayList<UserTransactionForm> getDescdingOrderTransaction(int _clientID, String _catagory);
	
	public ArrayList<UserTransactionForm> getAscendingOrderTransaction(int _clientID, String _catagory);

}