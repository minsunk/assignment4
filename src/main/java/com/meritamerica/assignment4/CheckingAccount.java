package com.meritamerica.assignment4;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class CheckingAccount extends BankAccount{
	
	public CheckingAccount(double balance, double interestRate) {
		super(balance, interestRate);
	}
	
	public CheckingAccount(double balance) {
		super(balance);
	}
	
	public CheckingAccount(double balance, double interestRate, Date accountOpenedOn) {
		super(balance, interestRate, accountOpenedOn);
	}

	public CheckingAccount(long accountNumber, double balance, double interestRate, Date accountOpenedOn) {
		super(accountNumber, balance, interestRate, accountOpenedOn);
	}	
	
	//static CheckingAccount readFromString(String accountData) throws ParseException
	public static CheckingAccount readFromString(String accountData) throws NumberFormatException{
		final int NUM_FIELDS = 4;
		String[] field = accountData.split(",");
		if (field.length != NUM_FIELDS) {
			throw new NumberFormatException();
		}
		
		CheckingAccount newCheckingAccount = null;
		try {
			DateFormat dateFormat = new SimpleDateFormat("mm/dd/yyyy");
			newCheckingAccount = new CheckingAccount(Long.parseLong(field[0]), Double.parseDouble(field[1]),
					Double.parseDouble(field[2]), dateFormat.parse(field[3]));
		}
		catch (NumberFormatException e) {
			throw e;
			
		}
		catch (ParseException e) {
			throw new NumberFormatException();
		}
		
		return newCheckingAccount;
	}
}

