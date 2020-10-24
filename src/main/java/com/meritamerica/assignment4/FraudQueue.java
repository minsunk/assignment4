package com.meritamerica.assignment4;
import java.util.*;

public class FraudQueue {
	Queue<Transaction> fraudQueue;
	
	
	//FraudQueue()
	public FraudQueue() {
		fraudQueue = new LinkedList<>();
	}
	
	
	//public void addTransaction(Transaction transaction)
	public void addTransaction(Transaction transaction) {
		this.fraudQueue.add(transaction);
	}
	//public Transaction getTransaction()
	public Transaction getTransaction() {
		return this.fraudQueue.remove();
	}
}
