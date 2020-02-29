package com.itcast.domain;

public class User 
{
	private int userID;
	private String username;
	private String password;
	
	public User(){}
	
	public User(int _userID,String _name, String pass)
	{
		userID=_userID;
		username=_name;
		password=pass;
	}
	public int getUserID()
	{
		return userID;
	}
	
	public void setUserID(int _userID)
	{
		userID=_userID;
	}
	
	public String getUsername()
	{
		return username;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public void setUsername(String _username)
	{
		username=_username;
	}
	
	public void setPassword( String _pass)
	{
		password=_pass;
	}

}
