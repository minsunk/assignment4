package com.meritamerica.assignment4;

public class WithdrawTransaction extends Transaction {

	//WithdrawTransaction(BankAccount targetAccount, double amount)
	public WithdrawTransaction(BankAccount targetAccount, double amount) {
		super(null, targetAccount, amount, null);
	}
		
	public void process() throws NegativeAmountException, ExceedsAvailableBalanceException, ExceedsFraudSuspicionLimitException {
		if (amount < 0) {
			throw new NegativeAmountException();
		}
		else if ( amount > 0 && amount > sourceAccount.balance) {
			throw new ExceedsAvailableBalanceException();
		}
		else if (amount > 0 && amount < FRAUD_SUSPICION_LIMIT && amount < sourceAccount.balance) {
			targetAccount.withdraw(amount);
		}
		else if (amount >= 0 && amount > FRAUD_SUSPICION_LIMIT) { 
			throw new ExceedsFraudSuspicionLimitException();
		}
	}


}
