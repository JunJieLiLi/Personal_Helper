package com.itcast.web.formbean;

public class LoginForm 
{
	private int userID;
	private String usename;
	private String userpass;
	
	public LoginForm() {}
	
	public LoginForm(String _name, String _pass) 
	{
		usename=_name;
		userpass=_pass;
	}
	
	public LoginForm(int _userID,String _name, String _pass) 
	{
		userID=_userID;
		usename=_name;
		userpass=_pass;
	}

	public int getUserID()
	{
		return userID;
	}
	
	public void setUserID(int _UserID)
	{
		userID=_UserID;
	}
	
	public String getUsename() 
	{
		return usename;
	}

	public void setUsename(String usename) 
	{
		this.usename = usename;
	}

	public String getUserpass() 
	{
		return userpass;
	}

	public void setUserpass(String userpass) 
	{
		this.userpass = userpass;
	}
	
	

}
