package com.itcast.dao.impl;

// get the User JAvabean 
import java.sql.*;
import java.util.ArrayList;

import com.itcast.DBForm.UserTransactionForm;
import com.itcast.Daoa.UserDao;
import com.itcast.domain.User;
import com.itcast.utils.JdbcUtils;;

public class UserDaoImpl implements UserDao
{
	private Connection con=  null;
	private Statement  stmt= null;
	private ResultSet  rs=   null;
	
	
	/************************************************************
	 * Method: add
	 * @param: User _user
	 * add user to Mysql database
	 ************************************************************/
	public void add(User _user)
	{
		if(_user==null)
		{
			System.out.println("user is null");
			return;
		}
		else
		{
			try {
				String query="INSERT INTO `myhelper`.`clients` (`ClientName`, `ClientPass`,`ClientID`) VALUES ('"+ _user.getUsername()+" ',"+" '"+ _user.getPassword()+" ',"+" '"+ _user.getUserID()+" ');";
				setUpConnectionDB(query);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	/************************************************************
	 * Method: find
	 * @param: String _username, String _password
	 * find user in Mysql database
	 * return a user 
	 ************************************************************/
	public User find(String _username,String _password)
	{
		if(_username==null||_password==null)
		{
			return null;
		}
		
		User user= new User();
		
		String query="select * from  myhelper.clients where ClientName="+"'"+_username+"' and ClientPass= '"+_password+"';";
		
		//String query="INSERT INTO `myusers`.`Adminn` (`username`, `userpss`) VALUES ('"+ _username+" ',"+" '"+ _password+" ');";
		
		try 
		{
			user=Checking_exist(query);
			
			if(user.getUsername()==null && user.getPassword()==null)
			{
				System.out.println("user is not exist");
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	
		return user;
	}


	/************************************************************
	 * Method: find
	 * @param: String _username
	 * find user in Mysql database
	 * return true if a user is found else return false 
	 ************************************************************/
	public boolean find(String _username)
	{
		boolean found=false;
		if(_username!=null)
		{
			String query="select * from myhelper.clients where ClientName="+"'"+_username+"';";
			try
			{
				User user= new User();
				user=Checking_exist(query);
				if(user.getUsername()!=null)
				{
					found=true;
				}
				else
				{
					found=false;
				}
			}catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
		return found;
	}
	
	/************************************************************
	 * Method: find
	 * @param: User user
	 * find user in Mysql database
	 * return true if the user is found else return false
	 ************************************************************/
	public boolean find(User _user)
	{
		boolean found=false;
		
		if(_user!=null)
		{
			String query="select * from  myhelper.clients where ClientID="+"'"+_user.getUserID()+"';";
			try
			{
				User user= new User();
				user=Checking_exist(query);
				if(user.getUsername()!=null)
				{
					found=true;
				}
				else
				{
					found=false;
				}
			}catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
		
		return found;
	}
	
	/************************************************************
	 * Method: setUpConnectionDB
	 * @param: String _query
	 * set up sql connection using JDBC
	 * return a non
	 ************************************************************/
	private void setUpConnectionDB(String _query) throws SQLException, ClassNotFoundException
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
			
			rs = stmt.executeQuery("select * from myhelper.clients");
			
			System.out.println("printing database...");
			
			if(rs==null)
			{
				System.out.println("rs is null!");
			}
			while(rs.next())
			{
				System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3));

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
	
	
	/************************************************************
	 * Method: Checking_exist
	 * @param: String _query
	 * checking if a user is exist in the database
	 * return a user object
	 ************************************************************/
	private User Checking_exist(String _msqlquery) throws SQLException, ClassNotFoundException
	{
		User user= new User();
		if(_msqlquery!=null)
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
				
				System.out.println("query: "+_msqlquery);
				//stmt.executeLargeUpdate(_msqlquery);
				stmt=con.prepareStatement(_msqlquery);
				//rs = stmt.executeQuery("select * from myusers.Adminn");
				rs=stmt.executeQuery(_msqlquery);
				
				System.out.println("printing database...");
				if(rs!=null)
				{
					while(rs.next())
					{
						//System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3));
						//User user= new User(rs.getString(2),rs.getString(3));
						//mylist.add(user);
						System.out.println(rs.getString("ClientID")+"\t"+rs.getString("ClientName")+"\t"+rs.getString("ClientPass"));
						user.setUserID(Integer.parseInt(rs.getString("ClientID")));
						user.setUsername(rs.getString("ClientName"));
						user.setPassword(rs.getString("ClientPass"));
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
		}
		
		return user;
	}
	
	
	/************************************************************
	 * Method: getUserTransactionList
	 * @param: int _clientID
	 * get all transaction about a user and stored in the list 
	 * return a list of transactions about the user
	 ************************************************************/
	public  ArrayList<UserTransactionForm> getUserTransactionList(int _ClientId)
	{
	    ArrayList<UserTransactionForm> userTranList =new ArrayList<UserTransactionForm>();
	    
		String _query = ""
						+ "SELECT "
						+ "myhelper.clients.ClientID, "
						+ "myhelper.clients.ClientName, "
						+ "myhelper.clients.ClientPass, "
						+ "myhelper.transactions.TransactionID, "
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
					int i;
					while(rs.next())
					{
						UserTransactionForm usertranform= new UserTransactionForm();
						
						usertranform.setClientID(Integer.parseInt(rs.getString("ClientID")));
						usertranform.setTransactionID(Integer.parseInt(rs.getString("TransactionID")));
						usertranform.setClientName(rs.getString("ClientName"));
						usertranform.setClientPass(rs.getString("ClientPass"));
						usertranform.setDates(rs.getString("Datee"));
						usertranform.setDescription(rs.getString("Description"));
						usertranform.setIncome(Double.parseDouble(rs.getString("Income")));
						usertranform.setOutgoing(Double.parseDouble(rs.getString("Outgoing")));
						
						System.out.println("ClientID:	"+usertranform.getClientID());
						System.out.println("TransactionID:	"+Integer.parseInt(rs.getString("TransactionID")));
						System.out.println("ClientName:	"+usertranform.getClientName());
						System.out.println("ClientPass:	"+usertranform.getClientPass());
						System.out.println("Datee:	"+usertranform.getDates());
						System.out.println("Description:	"+usertranform.getDescription());
						System.out.println("Income:	"+usertranform.getIncome());
						System.out.println("Outgoing:	"+usertranform.getOutgoing());
						
						userTranList.add(usertranform);
						
						for(i=0;i<userTranList.size();i++)
						{
							System.out.println("client name: "+userTranList.get(i).getClientName()+"descript: "+userTranList.get(i).getDescription());
						}
						i++;
					
						
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			finally
			{
				try
				{
					if(rs!=null)
						rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}finally
				{
					try
					{
						if(stmt!=null)
							stmt.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}finally
					{
						if(con!=null)
							try {
								con.close();
							} catch (SQLException e) {
								e.printStackTrace();
							}
					}
				}
			}
		}
		
		for(int i=0;i<userTranList.size();i++)
		{
			System.out.println("clientid : "+userTranList.get(i).getClientID()+" client name: "+userTranList.get(i).getClientName()+"Client income: "+userTranList.get(i).getIncome()+"client description: "+userTranList.get(i).getDescription());
		}
		
		return userTranList;
	}
	
	
	/************************************************************
	 * Method: findUserID
	 * @param: int _clientID
	 * finds a user id 
	 * return true if the id is found else return false
	 ************************************************************/
	public boolean findUserID( int _clientID)
	{
		boolean found=false;
		
		String query= "SELECT * FROM myhelper.clients where ClientID="+_clientID+";";
		
		if(_clientID!=-1)
		{
			try
			{
				User user= new User();
				user=Checking_exist(query);
				if(user.getUsername()!=null)
				{
					found=true;
				}
				else
				{
					found=false;
				}
			}catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return found;
	}
	

}

