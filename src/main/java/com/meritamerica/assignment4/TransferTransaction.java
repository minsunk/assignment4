package com.meritamerica.assignment4;

public class TransferTransaction extends Transaction {

	//TransferTransaction(BankAccount sourceAccount, BankAccount targetAccount, double amount)
	public TransferTransaction ( BankAccount sourceAccount, BankAccount targetAccount, double amount) {
		super(sourceAccount, targetAccount, amount, null);
	}

public void process() throws NegativeAmountException, ExceedsAvailableBalanceException, ExceedsFraudSuspicionLimitException{
	if (amount < 0 ) {
		throw new NegativeAmountException();
		
		} else if ( amount > 0 && amount > sourceAccount.balance) {
			throw new ExceedsAvailableBalanceException();
			
		} else if ( amount > 0 && amount < sourceAccount.balance && amount < FRAUD_SUSPICION_LIMIT) {
			sourceAccount.withdraw(amount);
			targetAccount.deposit(amount);
			
			
		} else if ( amount > 0 && amount > FRAUD_SUSPICION_LIMIT) {
			throw new ExceedsFraudSuspicionLimitException();
			
		}
}


}