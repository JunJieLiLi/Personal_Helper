package com.itcast.web.formbean;

public class EditTransactionFormBean 
{
	private int transactionID;
	private String dates;
	private String description;
	private double income;
	private double outgoing;
	
	public EditTransactionFormBean(){}
	
	public EditTransactionFormBean(	int    transactionID, 
									String dates,
									String description, 
									double income, 
									double outgoing) 
	{
		this.transactionID = transactionID;
		this.dates = dates;
		this.description = description;
		this.income = income;
		this.outgoing = outgoing;
	}
	
	public int getTransactionID() 
	{
		return transactionID;
	}

	public void setTransactionID(int transactionID) 
	{
		this.transactionID = transactionID;
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
