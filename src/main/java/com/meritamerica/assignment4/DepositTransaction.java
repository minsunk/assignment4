package com.meritamerica.assignment4;

public class DepositTransaction extends Transaction {
	
	public DepositTransaction(BankAccount targetAccount, double amount) {
		super(null, targetAccount, amount, null);
	}
	public void process() throws NegativeAmountException, ExceedsAvailableBalanceException, ExceedsFraudSuspicionLimitException {
		if (amount < 0) {
			throw new NegativeAmountException();
		}
		else if (amount >= 0 && amount < FRAUD_SUSPICION_LIMIT) {
			targetAccount.deposit(amount);
		}
		else if (amount >= 0 && amount > FRAUD_SUSPICION_LIMIT) { 
			throw new ExceedsFraudSuspicionLimitException();
		}
	}

}
	
