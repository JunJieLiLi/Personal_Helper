package com.itcast.domain;

public class Transaction
{
	private int id;
	private int fkClientID;
	private String date;
	private String description;
	private double income;
	private double outgoing;

	public Transaction() {}
	
	public Transaction(int _fkClientID,String _date, String _description, double _income, double _outgoing) 
	{
		fkClientID=_fkClientID;
		date=_date;
		description=_description;
		income=_income;
		outgoing=_outgoing;
	}
	
	
	public Transaction(int _id,int _fkClientID, String _date, String _description, double _income, double _outgoing) 
	{
		id=_id;
		fkClientID=_fkClientID;
		date=_date;
		description=_description;
		income=_income;
		outgoing=_outgoing;
	}

	public int getId()
	{
		return id;
	}
	public void setId( int _id)
	{
		id=_id;
	}
	
	public int getfkClientID()
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
}
