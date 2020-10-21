package com.meritamerica.assignment4;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CDAccount extends BankAccount{

  	private int term;
	
	// CDAccount(CDOffering offering, double balance)
	public CDAccount(CDOffering offering, double balance) {
		super(balance, offering.getInterestRate());
		this.term = offering.getTerm();
	}

	public CDAccount(CDOffering offering, double balance, Date accountOpenedOn) {
		super(balance, offering.getInterestRate(), accountOpenedOn);
		this.term = offering.getTerm();
	}

	public CDAccount(long accountNumber, CDOffering offering, double balance, Date accountOpenedOn) {
		super(accountNumber, balance, offering.getInterestRate(), accountOpenedOn);
		this.term = offering.getTerm();
	}

	// double futureValue ()
	public double futureValue () {
		return super.balance * Math.pow(1 + this.interestRate, this.term);
	}
	
	
	public boolean deposit(double amount){
		return false;
	}
	
	public boolean withdraw(double amount) {
		return false;
	}
	
	public static CDAccount readFromString(String accountData) throws NumberFormatException{
		final int NUM_FIELDS = 5;
		String[] field = accountData.split(",");
		
		if (field.length != NUM_FIELDS) {
			throw new NumberFormatException();
		}
		CDAccount newCDAccount = null;
		try {
			DateFormat dateFormat = new SimpleDateFormat("mm/dd/yyyy");
			CDOffering newCDOffering = new CDOffering (Integer.parseInt(field[4]), Double.parseDouble(field[2]));
			newCDAccount = new CDAccount(Long.parseLong(field[0]), newCDOffering, Double.parseDouble(field[1]), dateFormat.parse(field[3]));
		}
		catch (NumberFormatException e) {
			throw e;
			
		}
		catch (ParseException e) {
			throw new NumberFormatException();
		}
		
		return newCDAccount;
	}

	//6. String writeToString()
	
	@Override
	public String writeToString() {
		DateFormat dateFormat = new SimpleDateFormat("mm/dd/yyyy");
		return this.accountNumber + "," + this.balance + "," + this.interestRate + "," + dateFormat.format(this.openDate) + "," + this.term;
	}

}


