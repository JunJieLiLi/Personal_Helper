package com.itcast.web.formbean;

public class AddTransactionForm 
{
	private int 	fkClientID;
	private String 	date;
	private	double  income;
	private double 	outgoing;
	private String  description;
	
	public AddTransactionForm() {}

	public AddTransactionForm(String _date, double _income, double _outgoing, String _description) 
	{
		date=_date;
		income=_income;
		outgoing=_outgoing;
		description=_description;
	}
	public AddTransactionForm(int _fkClientID, String _date, double _income, double _outgoing, String _description) 
	{
		fkClientID=_fkClientID;
		date=_date;
		income=_income;
		outgoing=_outgoing;
		description=_description;
	}
	
	public int getFKCLientID()
	{
		return fkClientID;
	}
	
	public void setFKClientID(int _fkID)
	{
		fkClientID=_fkID;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public double getIncome() {
		return income;
	}
	public void setIncome(double income) {
		this.income = income;
	}
	public double getOutgoing() {
		return outgoing;
	}
	public void setOutgoing(double outgoing) {
		this.outgoing = outgoing;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
