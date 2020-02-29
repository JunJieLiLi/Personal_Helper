package com.itcast.web.formbean;

import java.util.HashMap;
import java.util.Map;

public class RegisterForm 
{
	private int userID;
	private String userName;
	private String userPass;
	private Map errors= new HashMap();
	
	public RegisterForm() {}
	
	public RegisterForm(String _userID, String _pass)
	{
		userName=_userID;
		userPass=_pass;
	}
	
	public RegisterForm(int _userID,String _userName, String _pass)
	{
		userID=_userID;
		userName=_userName;
		userPass=_pass;
	}

	public int getUserID()
	{
		return userID;
	}
	
	public void setUSerID(int _userId)
	{
		userID=_userId;
	}
	
	public String getUserName() 
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getUserPass()
	{
		return userPass;
	}

	public void setUserPass(String userPass) 
	{
		this.userPass = userPass;
	}
	
	public Map getErrors() {
		return errors;
	}

	public void setErrors(Map errors) {
		this.errors = errors;
	}

	
	 // user can not be empty
	//  pass can not be empty
	public boolean validated()
	{
		boolean valid=false;
		if(this.userName==null || this.userName.trim().equals(""))
		{
			valid=false;
			errors.put("userName", "user name can not be empty");
		}
		else
		{
			if(!this.userName.matches("[A-Za-z]{3,8}"))
			{
				valid=false;
				errors.put("userName", "User name must be 3-8 letter");
			}
		}
		
		if(this.userPass==null || this.userName.trim().equals(""))
		{
			valid=false;
			errors.put("userPass", "Password can no be empty");
		}
		else
		{
			if(!this.userName.matches("\\d{3,8}"))
			{
				valid=false;
				errors.put("userPass", "Password must be 3-8 number");
			}
		}

		return valid;
		
	}


}
