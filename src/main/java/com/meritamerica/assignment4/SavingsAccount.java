package com.meritamerica.assignment4;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SavingsAccount extends BankAccount{
	
	public SavingsAccount(double balance, double interestRate) {
		super(balance, interestRate);
	}
	
	public SavingsAccount(double balance) {
		super(balance);
	}
	
	public SavingsAccount(double balance, double interestRate, Date accountOpenedOn) {
		super(balance, interestRate, accountOpenedOn);
	}

	public SavingsAccount(long accountNumber, double balance, double interestRate, Date accountOpenedOn) {
		super(accountNumber, balance, interestRate, accountOpenedOn);
	}	
	
	//static CheckingAccount readFromString(String accountData) throws ParseException
	public static SavingsAccount readFromString(String accountData) throws NumberFormatException{
		final int NUM_FIELDS = 4; 

		String[] field = accountData.split(",");
		if (field.length != NUM_FIELDS) {
			throw new NumberFormatException();
		}
		
		SavingsAccount newSavingsAccount = null;
		try {
			DateFormat dateFormat = new SimpleDateFormat("mm/dd/yyyy");
			newSavingsAccount = new SavingsAccount(Long.parseLong(field[0]), Double.parseDouble(field[1]),
					Double.parseDouble(field[2]), dateFormat.parse(field[3]));
		}
		catch (NumberFormatException e) {
			throw e;
			
		}
		catch (ParseException e) {
			throw new NumberFormatException();
		}
		
		return newSavingsAccount;
	}
}
