package com.itcast.dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;

import com.itcast.DBForm.UserTransactionForm;
import com.itcast.Daoa.TransactionDao;
import com.itcast.domain.Transaction;
import com.itcast.domain.User;
import com.itcast.utils.JdbcUtils;
import com.itcast.web.formbean.AddTransactionForm;

public class TransactionDaoImpl implements TransactionDao 
{

	private Connection con;
	private Statement  stmt;
	private ResultSet  rs;
	
	private ArrayList<AddTransactionForm> transacform;
	
	public TransactionDaoImpl() 
	{
		Connection con=  null;
		Statement  stmt= null;
		ResultSet  rs=   null;
		transacform = new ArrayList<AddTransactionForm>();
	}
	
	/************************************************************
	 * Method: addTransaction
	 * @param: Transaction _transaction
	 * add user transaction into mysql database
	 * return non
	 ************************************************************/
	public void addTransaction(Transaction _trans)
	{
		if( _trans==null)
		{
			System.out.println("instance(s) is null");
			return;
		}
		
		try {
			//String query="INSERT INTO `myhelper`.`transactions` (`tranDate`, `tranDescrip`,`tranIncom`,`tranOutgo`) VALUES ('"+ _trans.getDate()+" ',"+" '"+ _trans.getDescription()+" ',"+" '"+ _trans.getIncome()+" ',"+" '"+_trans.getOutgoing()+" ');";
			
			String query="INSERT INTO `myhelper`.`transactions` (`ClientID`,`Datee`,`Description`,`Income`,`Outgoing`) VALUES ('"+_trans.getfkClientID()+" ',"+" '"+ _trans.getDate()+" ',"+" '"+ _trans.getDescription()+" ',"+" "+ _trans.getIncome()+" ,"+" "+ _trans.getOutgoing()+" );";
			setUpConnectionDB(query);
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/************************************************************
	 * Method: deleteTransaction
	 * @param: int _transactionId
	 * delete a transaction base on the transaction id
	 * return non
	 ************************************************************/
	public void deleteTransaction(int transactionID)
	{
		try
		{
			String query = ""
							+ "DELETE FROM `myhelper`.`transactions` "
							+ "WHERE TransactionID ="+ transactionID+";";
			
			setUpConnectionDB(query);
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/************************************************************
	 * Method: editTransaction
	 * @param: Transaction _transactionId
	 * edits a transaction base on the transaction id
	 * return non
	 ************************************************************/
	public void editTransaction(Transaction _trans)
	{
		try
		{
			String query ="UPDATE `MYTRANSACTION`.`myTransaction` SET `tranDate` = '"+ _trans.getDate()+"', `tranDescrip` = '"+ _trans.getDescription()+"', `tranIncom` = '"+ _trans.getOutgoing()+"', `tranOutgo` = '"+ _trans.getDate()+"' WHERE (`tranDate` = '99');";
			setUpConnectionDB(query);
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	/************************************************************
	 * Method: setUpConnectionDB
	 * @param: String _query
	 * setting up the mysql connection using JDBC
	 * return non
	 ************************************************************/
	private void setUpConnectionDB(String _query) throws SQLException 
	{
		try
		{
			// set up connection for mysql
			System.out.println("get connection...");
			con=JdbcUtils.getConnection();
			System.out.println("database is: "+ con.getMetaData().getDatabaseProductName());
			
			// creating statement for mysql query
			System.out.println("setting up statemetn query for mysql");
			stmt=con.createStatement();
			
			//execute stement query
			System.out.println("executing statement query");
			
			//String query="select * from Adminn;";
			System.out.println("query: "+_query);
			stmt.executeLargeUpdate(_query);
			
			rs = stmt.executeQuery("select * from myhelper.transactions");
			
			System.out.println("printing database...");
			
			if(rs==null)
			{
				System.out.println("rs is null!");
			}
			
			while(rs.next())
			{
				System.out.println( "ClientID: "+rs.getString(2)+"\t"
									+"Datee: "+rs.getString(3)+"\t"
									+"Description: "+rs.getString(4)+"\t"
									+"Income: "+rs.getString(5)+"\t"
									+"Outgoing: "+rs.getString(6));
				
				// store data into lsit
				AddTransactionForm tranform= new AddTransactionForm();
				tranform.setFKClientID(Integer.parseInt(rs.getString("ClientID")));
				tranform.setDate(rs.getString("Datee"));
				tranform.setDescription(rs.getString("Description"));
				tranform.setIncome(Double.parseDouble(rs.getString("Income")));
				tranform.setOutgoing(Double.parseDouble(rs.getString("Outgoing")));
				transacform.add(tranform);
			}
		}
		finally
		{
			try
			{
				if(rs!=null)
					rs.close();
			}finally
			{
				try
				{
					if(stmt!=null)
						stmt.close();
				}finally
				{
					if(con!=null)
						con.close();
				}
			}
		}
		
		if(transacform.size()==0)
		{
			System.out.println("transacform list is empty");
		}
		
	}

	// needs to be implemented
	public Transaction findTransaction(String date, String _income, String _outgoing)
	{
		Transaction trans= new Transaction();
		return trans;
	}
	
	/******************************************************************
	 * Method: findTransaction
	 * @param: String _date
	 * finds a transaction base on the date
	 * note: this method will be reimplemented  using transaction id 
	 * return non
	 ******************************************************************/
	public boolean findTransaction(String _date)
	{ 
		boolean found=false;
		
		if(_date!=null)
		{
			String query="select * from  MYTRANSACTION.myTransaction where tranDate="+"'"+_date+"';";
			try
			{
				Transaction trans= new Transaction();
				trans=Checking_exist(query);
				if(trans.getDate()!=null)
				{
					found=true;
				}
				else
				{
					found=false;
				}
			}
			catch (SQLException e) 
			{
				e.printStackTrace();
			} 
			catch (ClassNotFoundException e) 
			{
				e.printStackTrace();
			}
		}
		return found;
	}
	
	/******************************************************************
	 * Method: Checking_exist
	 * @param: String _msqlquery
	 * checking if a transaction is exist in the database
	 * return the found transaction
	 ******************************************************************/
	private Transaction Checking_exist(String _msqlquery) throws SQLException, ClassNotFoundException
	{
		Transaction trans= new Transaction();
		try
		{
			// set up connection for mysql
			System.out.println("get connection...");
			con=JdbcUtils.getConnection();
			System.out.println("database is: "+ con.getMetaData().getDatabaseProductName());
			
			// creating statement for mysql query
			System.out.println("setting up statemetn query for mysql");
			stmt=con.createStatement();
			
			//execute stement query
			System.out.println("executing statement query");
			
			System.out.println("query: "+_msqlquery);
			stmt=con.prepareStatement(_msqlquery);
			rs=stmt.executeQuery(_msqlquery);
			
			System.out.println("printing database...");
			if(rs!=null)
			{
				while(rs.next())
				{
					System.out.println(rs.getString("tranDate")+rs.getString("tranDescrip")+rs.getString("tranIncom")+rs.getString("tranOutgo"));
					trans.setDate(rs.getString("tranDate"));
					trans.setDate(rs.getString("tranDescrip"));
					trans.setDate(rs.getString("tranIncom"));
					trans.setDate(rs.getString("tranOutgo"));
				}
			}
		}
		finally
		{
			try
			{
				if(rs!=null)
					rs.close();
			}finally
			{
				try
				{
					if(stmt!=null)
						stmt.close();
				}finally
				{
					if(con!=null)
						con.close();
				}
			}
		}
		return trans;
		
	}
	
	/******************************************************************
	 * Method: getDBTransaction
	 * @param: int _ClientId
	 * getting a list of transaction using client id
	 * return a UserTransactionForm type list
	 ******************************************************************/
	public ArrayList<UserTransactionForm> getDBTransaction( int _ClientId )
	{
		ArrayList<UserTransactionForm> userTranList= new ArrayList<UserTransactionForm>();
		
		UserTransactionForm usertranform= new UserTransactionForm();
		String _query = ""
						+ "SELECT "
						+ "myhelper.clients.ClientID, "
						+ "myhelper.clients.ClientName, "
						+ "myhelper.clients.ClientPass, "
						+ "myhelper.transactions.Datee, "
						+ "myhelper.transactions.Description, "
						+ "myhelper.transactions.Income, "
						+ "myhelper.transactions.Outgoing "
						+ "FROM myhelper.clients "
						+ "INNER JOIN myhelper.transactions "
						+ "ON myhelper.clients.ClientID=myhelper.transactions.ClientID "
						+ "WHERE myhelper.clients.ClientID="+_ClientId+";";
		
		if(_ClientId!=-1)
		{
			try
			{
				// set up connection for mysql
				System.out.println("get connection...");
				con=JdbcUtils.getConnection();
				System.out.println("database is: "+ con.getMetaData().getDatabaseProductName());
				
				// creating statement for mysql query
				System.out.println("setting up statemetn query for mysql");
				stmt=con.createStatement();
				
				//execute stement query
				System.out.println("executing statement query");
				
				System.out.println("query: "+_query);

				stmt=con.prepareStatement(_query);

				rs=stmt.executeQuery(_query);
			
				if(rs!=null)
				{
					while(rs.next())
					{
						
						usertranform.setClientID(Integer.parseInt(rs.getString("ClientID")));
						usertranform.setClientName(rs.getString("ClientName"));
						usertranform.setClientPass(rs.getString("ClientPass"));
						usertranform.setDates(rs.getString("Datee"));
						usertranform.setDescription(rs.getString("Description"));
						usertranform.setIncome(Double.parseDouble(rs.getString("Income")));
						usertranform.setOutgoing(Double.parseDouble(rs.getString("Outgoing")));
						
						userTranList.add(usertranform);
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally
			{
				try
				{
					if(rs!=null)
						rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally
				{
					try
					{
						if(stmt!=null)
							stmt.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}finally
					{
						if(con!=null)
							try {
								con.close();
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					}
				}
			}
		}
		return userTranList;
	}

	@Override
	public ArrayList<AddTransactionForm> getDBTransaction() {  // needs to be fixed
		// TODO Auto-generated method stub
		return null;
	}
	
	/******************************************************************
	 * Method: findTransaction
	 * @param: int _transactionID
	 * looking for existence transaction in the database using transaction id
	 * return true if the transaction is found else return false
	 ******************************************************************/
	public boolean findTransaction(int _transactionID) // needs to be implemented
	{
		boolean found=false;
		try
		{
			String query = ""
							+ "SELECT * FROM `myhelper`.`transactions` "
							+ "WHERE TransactionID ="+ _transactionID+";";
			
			// set up connection for mysql
			System.out.println("get connection...");
			con=JdbcUtils.getConnection();
			System.out.println("database is: "+ con.getMetaData().getDatabaseProductName());
			
			// creating statement for mysql query
			System.out.println("setting up statemetn query for mysql");
			stmt=con.createStatement();
			
			//execute stement query
			System.out.println("executing statement query");
			
			System.out.println("query: "+query);

			stmt=con.prepareStatement(query);

			rs=stmt.executeQuery(query);
			
			if(rs.next())
			{
				found=true;
			}
			else
			{
				found=false;
			}
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return found;
	}
	
	/******************************************************************
	 * Method: getSingleEditTransactionDB
	 * @param: int _transactionID
	 * get the edited transaction using transaction id
	 * return  an edited transaction 
	 ******************************************************************/
	public Transaction getSingleEditTransactionDB(int _transactionID)
	{
		Transaction transaction= new Transaction();
		boolean found=findTransaction(_transactionID);
		
		if(found)
		{
			try
			{
				String query = ""
								+ "SELECT * FROM `myhelper`.`transactions` "
								+ "WHERE TransactionID ="+ _transactionID+";";
				
				// set up connection for mysql
				System.out.println("get connection...");
				con=JdbcUtils.getConnection();
				System.out.println("database is: "+ con.getMetaData().getDatabaseProductName());
				
				// creating statement for mysql query
				System.out.println("setting up statemetn query for mysql");
				stmt=con.createStatement();
				
				//execute stement query
				System.out.println("executing statement query");
				
				System.out.println("query: "+query);

				stmt=con.prepareStatement(query);

				rs=stmt.executeQuery(query);
				
				if(rs!=null)
				{
					while(rs.next())
					{
						
						//usertranform.setClientID(Integer.parseInt(rs.getString("ClientID")));
						transaction.setId(Integer.parseInt(rs.getString("TransactionID")));
						transaction.setFKClientID(Integer.parseInt(rs.getString("ClientID")));
						transaction.setDescription(rs.getString("Description"));
						transaction.setIncome(Double.parseDouble(rs.getString("Income")));
						transaction.setOutgoing(Double.parseDouble(rs.getString("Outgoing")));
						transaction.setDate(rs.getString("Datee"));
					}
				}
			}
			catch (SQLException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return transaction;
	}
	
	
	/********************************************************************************************************************
	 * Method: upDateEditTransactionDB
	 * @param: int _transactionID, int _clientID, double _income, String _datee, double _outgoing, String _description
	 * update the mysql database
	 * return  none
	 *********************************************************************************************************************/
	public void upDateEditTransactionDB(int _transactionID, int _clientID, double _income, String _datee, double _outgoing, String _description)
	{
		boolean found=findTransaction(_transactionID);
		
		if(found)
		{
			String query= "UPDATE myhelper.transactions SET Description='"+_description+"', Outgoing='"+_outgoing+"', Datee='"+_datee+"', Income='"+_income+"' WHERE ClientID="+_clientID+" AND TransactionID="+_transactionID+";";
			try 
			{
				executingQuery(query);
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	/******************************************************************
	 * Method: executingQuery
	 * @param: String _query
	 * executing query using JDBC
	 * return none
	 ******************************************************************/
	private void executingQuery(String _query) throws SQLException
	{
		try
		{
			// set up connection for mysql
			System.out.println("get connection...");
			con=JdbcUtils.getConnection();
			System.out.println("database is: "+ con.getMetaData().getDatabaseProductName());
			
			// creating statement for mysql query
			System.out.println("setting up statemetn query for mysql");
			stmt=con.createStatement();
			
			//execute stement query
			System.out.println("executing statement query");
			
			//String query="select * from Adminn;";
			System.out.println("query: "+_query);
			stmt.executeLargeUpdate(_query);
			
			rs = stmt.executeQuery("select * from myhelper.transactions");
			
			System.out.println("printing database...");
			
			if(rs==null)
			{
				System.out.println("rs is null!");
				
			}
		}
		finally
		{
			try
			{
				if(rs!=null)
					rs.close();
			}finally
			{
				try
				{
					if(stmt!=null)
						stmt.close();
				}finally
				{
					if(con!=null)
						con.close();
				}
			}
		}
	}

	/******************************************************************
	 * Method: getSelectedMonthListFromDao
	 * @param: int _clientID, String _firstday, String lastday
	 * getting the selected month from database and store in the list
	 * return the selected month list
	 ******************************************************************/
	public ArrayList<UserTransactionForm> getSelectedMonthListFromDao(int _clientID, String _firstday, String lastday)
	{
		ArrayList<UserTransactionForm> list= new ArrayList<UserTransactionForm>();

		String query="SELECT * FROM myhelper.transactions WHERE Datee BETWEEN '"+_firstday+"' AND '"+lastday+"' AND ClientID='"+_clientID+"';";
		UserTransactionForm usertranform=null;
		
		try
		{
			// set up connection for mysql
			System.out.println("get connection...");
			con=JdbcUtils.getConnection();
			System.out.println("database is: "+ con.getMetaData().getDatabaseProductName());
			
			// creating statement for mysql query
			System.out.println("setting up statemetn query for mysql");
			stmt=con.createStatement();
			
			//execute stement query
			System.out.println("executing statement query");
			
			System.out.println("query: "+query);

			stmt=con.prepareStatement(query);

			rs=stmt.executeQuery(query);
			
			if(rs!=null)
			{
				while(rs.next())
				{
					usertranform=new UserTransactionForm();
					usertranform.setTransactionID(Integer.parseInt(rs.getString("TransactionID")));
					usertranform.setClientID(Integer.parseInt(rs.getString("ClientID")));
					usertranform.setDescription(rs.getString("Description"));
					usertranform.setDates(rs.getString("Datee"));
					usertranform.setIncome(Double.parseDouble(rs.getString("Income")));;
					usertranform.setOutgoing(Double.parseDouble(rs.getString("Outgoing")));
					list.add(usertranform);
				}
			}
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}
	
	/******************************************************************
	 * Method: getDescdingOrderTransaction
	 * @param: int _clientID, String _catagory
	 * sorting the transaction in descending order and store it in a list
	 * return a sorted list in descending order
	 ******************************************************************/
	public ArrayList<UserTransactionForm> getDescdingOrderTransaction(int _clientID, String _catagory)  
	{
		ArrayList<UserTransactionForm> list= new ArrayList<UserTransactionForm>();
		
		UserTransactionForm form=null;
		
		String query="SELECT * FROM myhelper.transactions WHERE  ClientID='"+_clientID+"' ORDER BY "+_catagory+" DESC;";
		
		try
		{
			// set up connection for mysql
			System.out.println("get connection...");
			con=JdbcUtils.getConnection();
			System.out.println("database is: "+ con.getMetaData().getDatabaseProductName());
			
			// creating statement for mysql query
			System.out.println("setting up statemetn query for mysql");
			stmt=con.createStatement();
			
			//execute stement query
			System.out.println("executing statement query");
			
			System.out.println("query: "+query);

			stmt=con.prepareStatement(query);

			rs=stmt.executeQuery(query);
			
			if(rs!=null)
			{
				while(rs.next())
				{
					form=new UserTransactionForm();
					form.setTransactionID(Integer.parseInt(rs.getString("TransactionID")));
					form.setClientID(Integer.parseInt(rs.getString("ClientID")));
					form.setDescription(rs.getString("Description"));
					form.setDates(rs.getString("Datee"));
					form.setIncome(Double.parseDouble(rs.getString("Income")));;
					form.setOutgoing(Double.parseDouble(rs.getString("Outgoing")));
					list.add(form);
				}
			}
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	/******************************************************************
	 * Method: getAscendingOrderTransaction
	 * @param: int _clientID, String _catagory
	 * sorting the transaction in ascending order and store it in a list
	 * return a sorted list in ascending order
	 ******************************************************************/
	public ArrayList<UserTransactionForm> getAscendingOrderTransaction(int _clientID, String _catagory)
	{
		ArrayList<UserTransactionForm> list= new ArrayList<UserTransactionForm>();
		
		UserTransactionForm form=null;
		
		String query="SELECT * FROM myhelper.transactions WHERE  ClientID='"+_clientID+"' ORDER BY "+_catagory+" ASC;";
		
		try
		{
			// set up connection for mysql
			System.out.println("get connection...");
			con=JdbcUtils.getConnection();
			System.out.println("database is: "+ con.getMetaData().getDatabaseProductName());
			
			// creating statement for mysql query
			System.out.println("setting up statemetn query for mysql");
			stmt=con.createStatement();
			
			//execute stement query
			System.out.println("executing statement query");
			
			System.out.println("query: "+query);

			stmt=con.prepareStatement(query);

			rs=stmt.executeQuery(query);
			
			if(rs!=null)
			{
				while(rs.next())
				{
					form=new UserTransactionForm();
					form.setTransactionID(Integer.parseInt(rs.getString("TransactionID")));
					form.setClientID(Integer.parseInt(rs.getString("ClientID")));
					form.setDescription(rs.getString("Description"));
					form.setDates(rs.getString("Datee"));
					form.setIncome(Double.parseDouble(rs.getString("Income")));;
					form.setOutgoing(Double.parseDouble(rs.getString("Outgoing")));
					list.add(form);
				}
			}
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}


}
