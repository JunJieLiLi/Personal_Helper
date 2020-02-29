package com.itcast.utils;

import java.sql.*;


public class JdbcUtils 
{
	private static final String url="jdbc:mysql://localhost:3306/myhelper";
	private static final String user="root";
	private static final String pw="huangqian";
	
	private JdbcUtils(){}
	
	static 
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException e)
		{
			throw new ExceptionInInitializerError(e);
		}
	}

	public static Connection getConnection() throws SQLException
	{
		return DriverManager.getConnection(url,user,pw);
	}
	
	public static void free(ResultSet _rs,Statement _stmt, Connection _con) 
	{
		
	}
	
}
