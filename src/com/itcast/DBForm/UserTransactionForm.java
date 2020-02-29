package com.itcast.DBForm;

public class UserTransactionForm {
	

	private int clientID;
	private int transactionID;
	private String clientName;
	private String ClientPass;
	private String dates;
	private String description;
	private double income;
	private double outgoing;
	
	public UserTransactionForm(){}
	
	public UserTransactionForm(	int clientID, 
								int transactionID,
								String clientName, 
								String clientPass, 
								String dates,
								String description, 
								double income, 
								double outgoing) 
	{
		this.clientID = clientID;
		this.transactionID = transactionID;
		this.clientName = clientName;
		ClientPass = clientPass;
		this.dates = dates;
		this.description = description;
		this.income = income;
		this.outgoing = outgoing;
	}
	
	public UserTransactionForm(	String clientName,
								String clientPass, 
								String dates, 
								String description, 
								double income,
								double outgoing) 
	{
		this.clientName = clientName;
		ClientPass = clientPass;
		this.dates = dates;
		this.description = description;
		this.income = income;
		this.outgoing = outgoing;
	}
	
	public UserTransactionForm(	int clientID, 
								String clientName,
								String clientPass, 
								String dates, 
								String description, 
								double income,
								double outgoing) 
	{
		this.clientID = clientID;
		this.clientName = clientName;
		ClientPass = clientPass;
		this.dates = dates;
		this.description = description;
		this.income = income;
		this.outgoing = outgoing;
	}

	public int getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(int transactionID) {
		this.transactionID = transactionID;
	}
	
	public int getClientID() {
		return clientID;
	}
	public void setClientID(int clientID) {
		this.clientID = clientID;
	}
	
	public String getClientName() 
	{
		return clientName;
	}

	public void setClientName(String clientName) 
	{
		this.clientName = clientName;
	}

	public String getClientPass() 
	{
		return ClientPass;
	}

	public void setClientPass(String clientPass) 
	{
		ClientPass = clientPass;
	}

	public String getDates() 
	{
		return dates;
	}

	public void setDates(String dates) 
	{
		this.dates = dates;
	}

	public String getDescription() 
	{
		return description;
	}

	public void setDescription(String description) 
	{
		this.description = description;
	}

	public double getIncome() 
	{
		return income;
	}

	public void setIncome(double income) 
	{
		this.income = income;
	}

	public double getOutgoing() 
	{
		return outgoing;
	}

	public void setOutgoing(double outgoing) 
	{
		this.outgoing = outgoing;
	}

	
	

	
}
