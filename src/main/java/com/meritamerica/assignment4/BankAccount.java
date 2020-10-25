package com.meritamerica.assignment4;

import java.text.*;
import java.util.*;



public abstract class BankAccount {
	
	protected double balance;
	protected double interestRate;
	protected long accountNumber;
	protected Date openDate;
	private final static double DEFAULT_INTEREST_RATE = 0.01;
	private final static String DEFAULT_DATE_STRING = "01/01/2020";
	ArrayList<Transaction> transactions = new ArrayList<Transaction>();
	

	//BankAccount(double balance, double interestRate)
	public BankAccount(double balance, double interestRate) throws ParseException{
		this(MeritBank.getNextAccountNumber(), balance, interestRate, (new SimpleDateFormat("mm/dd/yyyy")).parse(DEFAULT_DATE_STRING));
	}
	
	public BankAccount(double balance) throws ParseException {
		this(MeritBank.getNextAccountNumber(), balance, DEFAULT_INTEREST_RATE, (new SimpleDateFormat("mm/dd/yyyy")).parse(DEFAULT_DATE_STRING));
	}
	
	// BankAccount(double balance, double interestRate, java.util.Date accountOpenedOn)
	public BankAccount(double balance, double interestRate, Date accountOpenedOn) {
		this(MeritBank.getNextAccountNumber(), balance, interestRate, accountOpenedOn);
	}

	//BankAccount(long accountNumber, double balance, double interestRate, java.util.Date getOpenedOn)
	public BankAccount(long accountNumber, double balance, double interestRate, Date accountOpenedOn) {
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.interestRate = interestRate;
		this.openDate = accountOpenedOn;
	}	
	
	// java.util.Date getOpenedOn()
	public Date getOpenedOn() {
		return this.openDate;
	}
	
	public static BankAccount readFromString(String accountData) throws ParseException, NumberFormatException {
		try {
			String[] holding = accountData.split(",");
			SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
			Long accountNumber = Long.parseLong(holding[0]);
			double balance = Double.parseDouble(holding[1]);
			double interestRate = Double.parseDouble(holding[2]);
			Date accountOpenedOn = date.parse(holding[3]);
			if (interestRate == 0.01) {
				return new SavingsAccount(accountNumber, balance, interestRate, accountOpenedOn);
			} else
				return new CheckingAccount(accountNumber, balance, interestRate, accountOpenedOn);

		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return null;
		}

	}
	//String writeToString()
	public String writeTostring() {
		DateFormat dateFormat = new SimpleDateFormat("mm/dd/yyyy");
		return this.accountNumber + "," + this.balance + "," + this.interestRate + "," 
				+ dateFormat.format(this.openDate);
	}
		
	//***long getAccountNumber() *** from assignment 2
	public long getAccountNumber(){
		return this.accountNumber;
	}

	//***double getBalance() *** from assignment 2
	public double getBalance(){
		return this.balance;
	}
	
	//***double getInterestRate() *** from assignment 2
	public double getInterestRate(){
		return this.interestRate;
	}
	//***boolean withdraw(double amount) *** from assignment 2
	public boolean withdraw(double amount){
		if (amount >= 0 && amount <= this.balance) {
		this.balance -= amount;
			return true;
		}
		else {
			return false;
		}
	}
	//***boolean deposit(double amount) *** from assignment 2
	public boolean deposit(double amount){
		if (amount >= 0) {
		this.balance += amount;
			return true;
		}
		else {
			return false;
		}
	}
	
	public double futureValue(int years){
		return this.balance * Math.pow(1+ this.interestRate, years);
	}
	public String writeToSpring() {

		SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");

		return this.accountNumber + "," + this.balance + "," + this.interestRate + ","
				+ dateFormatter.format(this.openDate);
	}
	public void addTransaction(Transaction transaction) {
		this.transactions.add(transaction);
	}
	
	public List<Transaction> getTransactions(){
		return transactions;
	}

}


