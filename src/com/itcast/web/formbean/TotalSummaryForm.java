package com.itcast.web.formbean;

public class TotalSummaryForm 
{
	private double totalIncome;
	private double totalOutgoing;
	private double totalBalance;
	
	public TotalSummaryForm(){}
	
	public TotalSummaryForm(	double totalIncome, 
								double totalOutgoing,
								double totalBalance) 
	{
		this.totalIncome = totalIncome;
		this.totalOutgoing = totalOutgoing;
		this.totalBalance = totalBalance;
	}

	public double getTotalIncome() 
	{
		return totalIncome;
	}

	public void setTotalIncome(double totalIncome) 
	{
		this.totalIncome = totalIncome;
	}

	public double getTotalOutgoing() 
	{
		return totalOutgoing;
	}

	public void setTotalOutgoing(double totalOutgoing) 
	{
		this.totalOutgoing = totalOutgoing;
	}

	public double getTotalBalance() 
	{
		return totalBalance;
	}

	public void setTotalBalance(double totalBalance)
	{
		this.totalBalance = totalBalance;
	}
	
	
	

}
