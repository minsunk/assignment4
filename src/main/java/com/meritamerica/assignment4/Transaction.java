package com.meritamerica.assignment4;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public abstract class Transaction {

	protected BankAccount sourceAccount = null;
	protected BankAccount targetAccount = null;
	protected double amount = 0;
	protected Date date = null;
	protected final double FRAUD_SUSPICION_LIMIT = 1000;
	protected boolean processedByFraudTeam;
	protected String rejectionReason;

	public Transaction(BankAccount sourceAccount, BankAccount targetAccount, double amount, Date date) {
		this.sourceAccount = sourceAccount;
		this.targetAccount = targetAccount;
		this.amount = amount;
		this.date = date;
		this.processedByFraudTeam = false;
		this.rejectionReason = "";
	}
	
	//public BankAccount getTargetAccount()
	public BankAccount getTargetAccount() {
		return this.targetAccount;
	}
	//public void setTargetAccount(BankAccount targetAccount)
	public void setTargetAccount(BankAccount targetAccount) {
		this.targetAccount =targetAccount;
	}
	//public double getAmount()
	public double getAmount() {
		return this.amount;
	}
	//public void setAmount(double amount)
	public void setAmount(double amount) {
		this.amount = amount;
	}
	//public java.util.Date getTransactionDate()
	public Date getTransactionDate() {
		return this.date;
	}
	//public void setTransactionDate(java.util.Date date)
	public void setTransactionDate(Date date) {
		this.date = date;
	}
	//public String writeToString() 
	public String writeToString() {
		DateFormat dateFormat = new SimpleDateFormat("mm/dd/yyyy");
		if (sourceAccount == null && amount > 0) {
			return "-1," + this.targetAccount.getAccountNumber() + "," + this.amount + "," + dateFormat.format(this.date);
		} else if(sourceAccount == null && amount < 0) {
			return "-1," + this.targetAccount.getAccountNumber() + ",-" + this.amount + "," + dateFormat.format(this.date);
		} else if(sourceAccount != null) {
			return this.sourceAccount.getAccountNumber() + "," + this.targetAccount.getAccountNumber() + "," + this.amount 
					+ "," + dateFormat.format(this.date);
		}

	//public static Transaction readFromString(String transactionDataString) 
	public static Transaction readFromString(String transactionDataString) throws NumberFormatException {
		final int NUM_FIELDS = 4;
		String[] field = transactionDataString.split(",");
		
		if (field.length != NUM_FIELDS) {
			throw new NumberFormatException();
		}
		if (Integer.parseInt(field[0]) != -1) {
			// transfer
			TransferTransaction transferTransaction  = new TransferTransaction(MeritBank.getBankAccount(Long.parseLong(field[0])), MeritBank.getBankAccount(Long.parseLong(field[1])),
					Double.parseDouble(field[2]));
			return transferTransaction;
			
		} else if (Double.parseDouble(field[2]) >= 0) { //deposit
			DepositTransaction depositTransaction = new DepositTransaction(MeritBank.getBankAccount(Long.parseLong(field[0])),Double.parseDouble(field[2]));
			return depositTransaction;
			
		} else if (Double.parseDouble(field[2]) < 0)) { //withdrawal
			WithdrawTransaction withdrawTransaction = new WithdrawTransaction(MeritBank.getBankAccount(Long.parseLong(field[0])),Double.parseDouble(field[2]));
			return withdrawTransaction;
			
		} 
		
		Transaction newTransaction = null;
		try {
			DateFormat dateFormat = new SimpleDateFormat("mm/dd/yyyy");
			if (Long.parseLong(field[0]) == -1 && Double.parseDouble(field[2]) > 0) {
				newTransaction = new DepositTransaction(MeritBank.getBankAccount(Long.parseLong(field[1])), Double.parseDouble(field[2]));
			}
			else if (Long.parseLong(field[0]) == -1 && Double.parseDouble(field[2]) < 0) {
				newTransaction = new WithdrawTransaction(MeritBank.getBankAccount(Long.parseLong(field[1])), Double.parseDouble(field[2]));
			}
			else if (Long.parseLong(field[0]) >= 0) {
				newTransaction = new TransferTransaction(MeritBank.getBankAccount(Long.parseLong(field[0])),
						MeritBank.getBankAccount(Long.parseLong(field[1])), Double.parseDouble(field[2]));
			}
			else
				throw new NumberFormatException();
		}
		catch (NumberFormatException e) {
			throw e;
			
		}
		catch (ParseException e) {
			throw new NumberFormatException();
		}
		
		return newTransaction;
	}
	

	//public abstract void process() throws NegativeAmountException,
	//ExceedsAvailableBalanceException, ExceedsFraudSuspicionLimitException
	public abstract void process() throws NegativeAmountException, ExceedsAvailableBalanceException, 
	ExceedsFraudSuspicionLimitException;
	//public boolean isProcessedByFraudTeam()
	public boolean isProcessedByFraudTeam() {
		return this.processedByFraudTeam;
	}
	
	//public void setProcessedByFraudTeam(boolean isProcessed)
	public void setProcessedByFraudTeam(boolean isProcessed) {
		this.processedByFraudTeam = isProcessed;
	}
	//public String getRejectionReason()
	public String getRejectionReason() {
		return this.rejectionReason;
	}
	
	//public void setRejectionReason(String reason)
	public void setRejectionReason(String reason) {
		this.rejectionReason = reason;
	}
	
	
	
	
}
