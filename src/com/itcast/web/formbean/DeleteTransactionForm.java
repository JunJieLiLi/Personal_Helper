package com.itcast.web.formbean;

public class DeleteTransactionForm 
{
	
	private int clientID;
	private int transactionId;
	private String clientName;
	private String clientPass;
	private String dates;
	private String description;
	private double income;
	private double outgoing;
	
	public DeleteTransactionForm() {}
	
	public DeleteTransactionForm(	int clientID, 
									String clientName,
									String clientPass, 
									String dates, 
									int transactionId,
									String description, 
									double income, 
									double outgoing) 
	{
		this.clientID = clientID;
		this.clientName = clientName;
		this.clientPass = clientPass;
		this.dates = dates;
		this.transactionId = transactionId;
		this.description = description;
		this.income = income;
		this.outgoing = outgoing;
	}

	public int getClientID() {
		return clientID;
	}

	public void setClientID(int clientID) {
		this.clientID = clientID;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getClientPass() {
		return clientPass;
	}

	public void setClientPass(String clientPass) {
		this.clientPass = clientPass;
	}

	public String getDates() {
		return dates;
	}

	public void setDates(String dates) {
		this.dates = dates;
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
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
