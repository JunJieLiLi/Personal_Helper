package com.itcast.Daoa;

import java.util.ArrayList;

import com.itcast.DBForm.UserTransactionForm;
import com.itcast.domain.User;

public interface UserDao {

	void add(User _user);

	User find(String _username, String _password);
	public  ArrayList<UserTransactionForm> getUserTransactionList(int _clientID);

	// check if a user is already in the database
	boolean find(String _username);
	boolean find(User _user);
	
	boolean findUserID( int _clientID);
	

}