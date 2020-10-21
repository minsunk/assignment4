package com.meritamerica.assignment4;

import java.text.*;
import java.util.*;

//import com.meritamerica.assignment2.MeritBank;

//import com.meritamerica.assignment2.MeritBank;

public class BankAccount {
	
	protected double balance;
	protected double interestRate;
	protected long accountNumber;
	protected Date openDate;
	private final static double DEFAULT_INTEREST_RATE = 0.01;
	private final static String DEFAULT_DATE_STRING = "01/01/2020";
	

	//	1. BankAccount(double balance, double interestRate)
	public BankAccount(double balance, double interestRate) {
		this.accountNumber = MeritBank.getNextAccountNumber();
		this.balance = balance;
		this.interestRate = interestRate;
		try {
			this.openDate = (new SimpleDateFormat("mm/dd/yyyy")).parse(DEFAULT_DATE_STRING);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public BankAccount(double balance) {
		this.accountNumber = MeritBank.getNextAccountNumber();
		this.balance = balance;
		this.interestRate = DEFAULT_INTEREST_RATE;
		try {
			this.openDate = (new SimpleDateFormat("mm/dd/yyyy")).parse(DEFAULT_DATE_STRING);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// 2. BankAccount(double balance, double interestRate, java.util.Date accountOpenedOn)
	public BankAccount(double balance, double interestRate, Date accountOpenedOn) {
		this(MeritBank.getNextAccountNumber(), balance, interestRate, accountOpenedOn);
	}

	//	3. BankAccount(long accountNumber, double balance, double interestRate, java.util.Date getOpenedOn)
	public BankAccount(long accountNumber, double balance, double interestRate, Date accountOpenedOn) {
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.interestRate = interestRate;
		this.openDate = accountOpenedOn;
	}	
	
	// 4. java.util.Date getOpenedOn()
	public Date getOpenedOn() {
		return this.openDate;
	}
	
	// 5. static BankAccount readFromString(String accountData) throws ParseException
	//Should throw a java.lang.NumberFormatException if String cannot be correctly parsed
	public static BankAccount readFromString(String accountData) throws NumberFormatException {
		final int NUM_FIELDS = 4;
		String[] field = accountData.split(",");
		
		if (field.length != NUM_FIELDS) {
			throw new NumberFormatException();
		}
		
		BankAccount newBankAccount = null;
		try {
			DateFormat dateFormat = new SimpleDateFormat("mm/dd/yyyy");
			newBankAccount = new BankAccount(Long.parseLong(field[0]), Double.parseDouble(field[1]),
					Double.parseDouble(field[2]), dateFormat.parse(field[3]));
		}
		catch (NumberFormatException e) {
			throw e;
			
		}
		catch (ParseException e) {
			throw new NumberFormatException();
		}
		
		return newBankAccount;
	}
	
	//6. String writeToString()
	public String writeToString() {
		DateFormat dateFormat = new SimpleDateFormat("mm/dd/yyyy");
		return this.accountNumber + "," + this.balance + "," + String.format("%.4f", this.interestRate) + "," + dateFormat.format(this.openDate);
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
	//***double futureValue(int years) *** from assignment 2
	public double futureValue(int years){
		return this.balance * Math.pow(1+ this.interestRate, years);
	}
}

