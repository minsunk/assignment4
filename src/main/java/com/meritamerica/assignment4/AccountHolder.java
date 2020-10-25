package com.meritamerica.assignment4;
import java.util.Arrays;

import java.text.ParseException;

public class AccountHolder implements Comparable<AccountHolder> {

	private String myFirstName;
	private String myMiddleName;
	private String myLastName;
	private String mySsn;
	private CheckingAccount[] checkingAccount;
	private SavingsAccount[] savingsAccount;
	private CDAccount[] cdAccount;
	private final double COMBINED_BALANCE_LIMIT = 250000;

	//AccountHolder(String firstName, String middleName, String lastName, String ssn)
	
	public AccountHolder(String myFirstName, String myMiddleName, String myLastName, String mySsn) {
		
		this.myFirstName = myFirstName;
		this.myMiddleName = myMiddleName;
		this.myLastName = myLastName;
		this.mySsn = mySsn;
		this.checkingAccount = null;
		this.savingsAccount = null;

	}
	//Sting getFirstName()	
	public String getFirstName() {
		return myFirstName;
	}

	//void setFirstName()	
	public void setFirstName(String firstName) {
		myFirstName = firstName;
	}

	//String getMiddleName()	
	public String getMiddleName() {
		return myMiddleName;
	}
	
	//void setMiddleName()
	public void setMiddleName(String middleName) {
		myMiddleName = middleName;
	}

	//String getLastName()
	public String getLastName() {
		return myLastName;
	}

	//void setLastName()	
	public void setLastName(String lastName) {
		myLastName = lastName;
	}
	
	//String getSSN()	
	public String getSSN() {
		return mySsn;
	}

	//void setSSN()	
	public void setSSN(String ssn) {
		mySsn = ssn;
	}

	//CheckingAccount addCheckingAccount(double openingBalance)
	public CheckingAccount addCheckingAccount(double openingBalance) throws ExceedsFraudSuspicionLimitException, 
	ExceedsCombinedBalanceLimitException, NegativeAmountException, ParseException
	{
		if (getCheckingBalance() + getSavingsBalance() + openingBalance > COMBINED_BALANCE_LIMIT) 
		{
			throw new ExceedsCombinedBalanceLimitException("Balance of accounts exceeds $250,000.");
		}
			CheckingAccount newAccount = new CheckingAccount(openingBalance);
		
			DepositTransaction transaction = new DepositTransaction(newAccount, openingBalance);
			try{
				MeritBank.processTransaction(transaction);
			}
			catch(Exception exception)
			{
				exception.printStackTrace();
			}
		
			
			CheckingAccount[] holding = new CheckingAccount[this.checkingAccount.length + 1];
			for(int i = 0; i<this.checkingAccount.length; i++)
			{
				holding[i] = this.checkingAccount[i];
			}
		
			holding[holding.length - 1] = newAccount;
			this.checkingAccount = holding;
		
		
			return newAccount;
		}
		

	//	CheckingAccount addCheckingAccount(CheckingAccount checkingAccount)
	public CheckingAccount addCheckingAccount(CheckingAccount checkingAccount) throws ExceedsFraudSuspicionLimitException, 
		ExceedsCombinedBalanceLimitException, NegativeAmountException, ParseException
	{
		if(getCheckingBalance() + getSavingsBalance() + checkingAccount.getBalance() >= 250000) {
			throw new ExceedsCombinedBalanceLimitException("Aggregate balance of your Checking and Savings accounts exceeds $250,000.");
		}
		CheckingAccount newAccount = new CheckingAccount(checkingAccount.getBalance());
		
		DepositTransaction transaction = new DepositTransaction(newAccount, checkingAccount.getBalance());
		
		try{
			MeritBank.processTransaction(transaction);
		}
		catch(Exception exception) 
		{
			exception.printStackTrace();
		}
		
		
		
		CheckingAccount[] holding = new CheckingAccount[this.checkingAccount.length + 1];
		for(int i = 0; i<this.checkingAccount.length; i++) {
			holding[i] = this.checkingAccount[i];
		}
		holding[holding.length - 1] = newAccount;
		this.checkingAccount = holding;
		return newAccount;
	}


	//CheckingAccount[] getCheckingAccounts()
	public CheckingAccount[] getCheckingAccounts() {
		return checkingAccount;
	}
	
	//getNumberOfCheckingAccounts()
	public int getNumberOfCheckingAccounts() {
		if (checkingAccount == null) {
			return 0;
		}
		else {
			return checkingAccount.length;
		}
	}
	//double getCheckingBalance()
	public double getCheckingBalance() {
		double totalBalance = 0.0;

		if (checkingAccount != null) {
			for (int i=0; i < checkingAccount.length; i++) {
				totalBalance += checkingAccount[i].getBalance();
			}
		} 
		
		return totalBalance;
	}
	//SavingsAccount addSavingsAccount(double openingBalance)
public SavingsAccount addSavingsAccount(double openingBalance) throws ExceedsFraudSuspicionLimitException, NegativeAmountException, ExceedsCombinedBalanceLimitException {
		
		if(getCheckingBalance() + getSavingsBalance() + openingBalance >= 250000) {
			throw new ExceedsCombinedBalanceLimitException("Aggregate balance of your Checking and Savings accounts exceeds $250,000.");
		}
		SavingsAccount newAccount = new SavingsAccount(openingBalance);
		
		DepositTransaction transaction = new DepositTransaction(newAccount, openingBalance);
		
		try{
			MeritBank.processTransaction(transaction);
		}
		catch(Exception exception) {
			exception.printStackTrace();
		}
		
		
		SavingsAccount[] holding = new SavingsAccount[savingsAccount.length + 1];
		for(int i = 0; i<savingsAccount.length; i++) {
			holding[i] = savingsAccount[i];
		}
		holding[holding.length - 1] = newAccount;
		savingsAccount = holding;
		return newAccount;
	}

	
	
	//SavingsAccount addSavingsAccount(SavingsAccount savingsAccount)
	public SavingsAccount addSavingsAccount(SavingsAccount savingsAccount) throws ExceedsFraudSuspicionLimitException, 
	NegativeAmountException, ExceedsCombinedBalanceLimitException 
	{
		if(getCheckingBalance() + getSavingsBalance() + savingsAccount.getBalance()>= 250000) 
		{
			throw new ExceedsCombinedBalanceLimitException("Aggregate balance of your Checking and Savings accounts exceeds $250,000.");
		}
		DepositTransaction transaction = new DepositTransaction(savingsAccount, savingsAccount.getBalance());
			try{
				MeritBank.processTransaction(transaction);
				}
					catch(Exception exception) {
		
			}
	
	SavingsAccount[] holding = new SavingsAccount[this.savingsAccount.length + 1];
	for(int i = 0; i<this.savingsAccount.length; i++) {
		holding[i] = this.savingsAccount[i];
	}
	holding[holding.length - 1] = savingsAccount;
	this.savingsAccount = holding;
	return savingsAccount;
}
	
	//SavingsAccount[]getSavingsAccounts()
	public SavingsAccount[] getSavingsAccounts(){
		return savingsAccount;
	}
	//getNumberOfSavingsAccounts()
	public int getNumberOfSavingsAccounts() {
		if (savingsAccount != null) {
			return savingsAccount.length;
		} else {
			return 0;
		}
	}
	//double getSavingsBalance()
	public double getSavingsBalance() {
		double totalBalance = 0.0; 

		if (savingsAccount != null) {
			for (int i = 0 ; i < savingsAccount.length ; i++) {
				totalBalance += savingsAccount[i].getBalance();
			}
		}
		
		return totalBalance;
	}

	// CDAccount addCDAccount(CDOffering offering, double openingBalance)
	public CDAccount addCDAccount(CDOffering offering, double openingBalance) throws NegativeAmountException, 
	ExceedsFraudSuspicionLimitException, ParseException {
		
	
		if(offering == null) 
		{
			return null;
		}
		CDAccount newAccount = new CDAccount(offering, openingBalance);
		DepositTransaction transaction = new DepositTransaction(newAccount, openingBalance);
		
		try{
			MeritBank.processTransaction(transaction);
		}
		catch(Exception exception) 
		{
			
		}
		
		
		CDAccount[] holding = Arrays.copyOf(cdAccount, cdAccount.length+1);
		
		for(int i = 0; i<cdAccount.length; i++) {
			holding[i] = cdAccount[i];
		}
		holding[holding.length - 1] = newAccount;
		cdAccount = holding;
		return newAccount;
	}

	
	public CDAccount addCDAccount(CDAccount cdAccount) throws ExceedsFraudSuspicionLimitException, NegativeAmountException {
		
		DepositTransaction transaction = new DepositTransaction(cdAccount, cdAccount.getBalance());
		try{
			MeritBank.processTransaction(transaction);
		}
		catch(Exception exception) {
			
		}
		CDAccount[] holding = Arrays.copyOf(this.cdAccount,this.cdAccount.length+1);
		for(int i = 0; i<this.cdAccount.length; i++) {
			holding[i] = this.cdAccount[i];
		}
		holding[holding.length - 1] = cdAccount;
		this.cdAccount = holding;
		return cdAccount;
	}


	//CDAccount[] getCDACcounts()
	public CDAccount[] getCDAccounts() {
		return cdAccount;
	}

	//getNumberOfCDAccounts()
	public int getNumberofCDAccounts() {
		if (cdAccount != null) {
			return cdAccount.length;
		} else {
			return 0;
		}
	}

	//double getCDBalance()
	public double getCDBalance() {
		double totalCdBalance = 0.0;

		if (cdAccount != null) {
			for (int i = 0; i < cdAccount.length; i++){
				totalCdBalance += cdAccount[i].getBalance();
			}
		}

		return totalCdBalance;
	}
	
	//double getCombinedBalance()
	public double getCombinedBalance(){
		return getCheckingBalance() + getSavingsBalance() + getCDBalance();
	}

	@Override
	public int compareTo(AccountHolder otherAccountHolder) {
		if(getCombinedBalance() == otherAccountHolder.getCombinedBalance()) {
			return 0;
		} else if (getCombinedBalance() > otherAccountHolder.getCombinedBalance()) {
			return 1;
		} else {
			return -1;
		}
	}

	public String writeToString(){
		return myFirstName+","+myMiddleName+","+myLastName+","+mySsn;
	}

	public static AccountHolder readFromString(String accountHolderInfo) {
		String[] accountinfo = accountHolderInfo.split(",");
		AccountHolder accountHolder = new AccountHolder(accountinfo[0], accountinfo[1], accountinfo[2], accountinfo[3]);
		return accountHolder;
	}
}