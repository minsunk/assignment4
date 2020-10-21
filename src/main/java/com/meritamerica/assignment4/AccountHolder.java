package com.meritamerica.assignment4;


import com.meritamerica.assignment4.CDAccount;
import com.meritamerica.assignment4.CDOffering;
import com.meritamerica.assignment4.CheckingAccount;
import com.meritamerica.assignment4.SavingsAccount;

public class AccountHolder implements Comparable<AccountHolder> {

	private String myFirstName;
	private String myMiddleName;
	private String myLastName;
	private String mySsn;
	private CheckingAccount[] checkingAccountList;
	private SavingsAccount[] savingsAccountList;
	private CDAccount[] cdAccountList;
	private final double CHECKING_ACCOUNT_INTEREST_RATE = 0.0001;
	private final double SAVINGS_ACCOUNT_INTEREST_RATE = 0.01;
	private final double COMBINED_BALANCE_LIMIT = 250000;

	//1. AccountHolder(String firstName, String middleName, String lastName, String ssn)
	
	public AccountHolder(String myFirstName, String myMiddleName, String myLastName, String mySsn) {
		
		this.myFirstName = myFirstName;
		this.myMiddleName = myMiddleName;
		this.myLastName = myLastName;
		this.mySsn = mySsn;
		this.checkingAccountList = null;
		this.savingsAccountList = null;

	}
	//2.Sting getFirstName()	
	public String getFirstName() {
		return myFirstName;
	}

	//3.void setFirstName()	
	public void setFirstName(String firstName) {
		myFirstName = firstName;
	}

	//4.String getMiddleName()	
	public String getMiddleName() {
		return myMiddleName;
	}
	
	//5.void setMiddleName()
	public void setMiddleName(String middleName) {
		myMiddleName = middleName;
	}

	//6.String getLastName()
	public String getLastName() {
		return myLastName;
	}

	//7.void setLastName()	
	public void setLastName(String lastName) {
		myLastName = lastName;
	}
	
	//8.String getSSN()	
	public String getSSN() {
		return mySsn;
	}

	//9.void setSSN()	
	public void setSSN(String ssn) {
		mySsn = ssn;
	}

	//	10. CheckingAccount addCheckingAccount(double openingBalance)
	public CheckingAccount addCheckingAccount(double openingBalance) {
		if (getCheckingBalance() + getSavingsBalance() + openingBalance > COMBINED_BALANCE_LIMIT || openingBalance < 0) {
			return null;
		} else {
			if (checkingAccountList == null) {
				checkingAccountList = new CheckingAccount[1];
				
				checkingAccountList[0] = new CheckingAccount(openingBalance, CHECKING_ACCOUNT_INTEREST_RATE);
			} else {
				CheckingAccount[] tempCheckingAccount = new CheckingAccount[checkingAccountList.length + 1];
				for (int i = 0; i < checkingAccountList.length; i++) {
					tempCheckingAccount[i] = checkingAccountList[i];
				}
				tempCheckingAccount[checkingAccountList.length] = new CheckingAccount(openingBalance, CHECKING_ACCOUNT_INTEREST_RATE);
				checkingAccountList = tempCheckingAccount;
			}
			
			return checkingAccountList[checkingAccountList.length -1];
		}
	}
	//	11. CheckingAccount addCheckingAccount(CheckingAccount checkingAccount)
	public CheckingAccount addCheckingAccount(CheckingAccount checkingAccount) {
		if (getCheckingBalance() + getSavingsBalance() + checkingAccount.getBalance() > COMBINED_BALANCE_LIMIT) {
			return null;
		} else {
			if (checkingAccountList == null) {
				checkingAccountList = new CheckingAccount[1];
				checkingAccountList[0] = checkingAccount;
			} else {
				CheckingAccount[] tempCheckingAccount = new CheckingAccount[checkingAccountList.length + 1];
				for (int i = 0; i < checkingAccountList.length; i++) {
					tempCheckingAccount[i] = checkingAccountList[i];
				}
				tempCheckingAccount[checkingAccountList.length] = checkingAccount;
				checkingAccountList = tempCheckingAccount;
			}
			
			return checkingAccountList[checkingAccountList.length -1];
		}
	}

	//12. CheckingAccount[] getCheckingAccounts()
	public CheckingAccount[] getCheckingAccounts() {
		return checkingAccountList;
	}
	
	//13. int getNumberOfCheckingAccounts()
	public int getNumberOfCheckingAccounts() {
		if (checkingAccountList == null) {
			return 0;
		}
		else {
			return checkingAccountList.length;
		}
	}
	//14. double getCheckingBalance()
	public double getCheckingBalance() {
		double totalBalance = 0.0;

		if (checkingAccountList != null) {
			for (int i=0; i < checkingAccountList.length; i++) {
				totalBalance += checkingAccountList[i].getBalance();
			}
		} 
		
		return totalBalance;
	}
	//15. SavingsAccount addSavingsAccount(double openingBalance)
	public SavingsAccount addSavingsAccount(double openingBalance) {
		if (getCheckingBalance() + getSavingsBalance() + openingBalance > COMBINED_BALANCE_LIMIT || openingBalance < 0) {
			return null;
		} else {
			if (savingsAccountList == null) {
				savingsAccountList = new SavingsAccount[1];
				
				savingsAccountList[0] = new SavingsAccount(openingBalance, SAVINGS_ACCOUNT_INTEREST_RATE);
			} else {
				SavingsAccount[] tempSavingsAccount = new SavingsAccount[savingsAccountList.length + 1];
				for (int i = 0; i < savingsAccountList.length; i++) {
					tempSavingsAccount[i] = savingsAccountList[i];
				}
				tempSavingsAccount[savingsAccountList.length] = new SavingsAccount(openingBalance, SAVINGS_ACCOUNT_INTEREST_RATE);
				savingsAccountList = tempSavingsAccount;
			}
			return savingsAccountList[savingsAccountList.length - 1];
		}
	}
	
	
	//16. SavingsAccount addSavingsAccount(SavingsAccount savingsAccount)
	public SavingsAccount addSavingsAccount(SavingsAccount savingsAccount) {
		if (getCombinedBalance() + savingsAccount.getBalance() > COMBINED_BALANCE_LIMIT) {
			return null;
		} else {
			if (savingsAccountList == null) {
				savingsAccountList = new SavingsAccount[1];
				
				savingsAccountList[0] = savingsAccount;			
			} else {
				SavingsAccount[] tempSavingsAccount = new SavingsAccount[savingsAccountList.length +1];
				for (int i = 0; i < savingsAccountList.length; i++) {
					tempSavingsAccount[i] = savingsAccountList[i];
				}
				tempSavingsAccount[savingsAccountList.length] = savingsAccount;
				savingsAccountList = tempSavingsAccount;
			}
			return savingsAccountList[savingsAccountList.length - 1];
		}
	}
	
	//17. SavingsAccount[]getSavingsAccounts()
	public SavingsAccount[] getSavingsAccounts(){
		return savingsAccountList;
	}
	//18. int getNumberOfSavingsAccounts()
	public int getNumberOfSavingsAccounts() {
		if (savingsAccountList != null) {
			return savingsAccountList.length;
		} else {
			return 0;
		}
	}
	//19. double getSavingsBalance()
	public double getSavingsBalance() {
		double totalBalance = 0.0; 

		if (savingsAccountList != null) {
			for (int i = 0 ; i < savingsAccountList.length ; i++) {
				totalBalance += savingsAccountList[i].getBalance();
			}
		}
		
		return totalBalance;
	}

	//20. CDAccount addCDAccount(CDOffering offering, double openingBalance)
	public void addCDAccount(CDOffering offering, double openingBalance){
		if (offering != null && openingBalance >= 0) {
			if (cdAccountList == null) {
				cdAccountList = new CDAccount[1];
				cdAccountList[0] = new CDAccount(offering, openingBalance);
			} else {
				CDAccount[] tempCDAccount = new CDAccount[cdAccountList.length + 1];
				for ( int i=0; i < cdAccountList.length; i++) {
					cdAccountList[i] = tempCDAccount[i];
				}
				tempCDAccount[cdAccountList.length] = new CDAccount(offering, openingBalance);
				cdAccountList = tempCDAccount;
			}
		}
	}

	//21. CDAccount addCDAccount(CDAccount cdAccount)
	public void addCDAccount(CDAccount cdAccount) {
		if (cdAccountList == null) {
			cdAccountList = new CDAccount[1];
			cdAccountList[0] = cdAccount;
		} else {
			CDAccount[] tempCdAccount = new CDAccount[cdAccountList.length + 1];
			for (int i = 0; i < cdAccountList.length; i++) {
				tempCdAccount[i] = cdAccountList[i];
			}
			tempCdAccount[cdAccountList.length] = cdAccount;
			cdAccountList = tempCdAccount;
		}
	}

	//22. CDAccount[] getCDACcounts()
	public CDAccount[] getCDAccounts() {
		return cdAccountList;
	}

	//23. int getNumberOfCDAccounts()
	public int getNumberOfCDAccounts() {
		if (cdAccountList != null) {
			return cdAccountList.length;
		} else {
			return 0;
		}
	}

	//24. double getCDBalance()
	public double getCDBalance() {
		double totalCdBalance = 0.0;

		if (cdAccountList != null) {
			for (int i = 0; i < cdAccountList.length; i++){
				totalCdBalance += cdAccountList[i].getBalance();
			}
		}

		return totalCdBalance;
	}
	
	//25. double getCombinedBalance()
	public double getCombinedBalance(){
		return getCheckingBalance() + getSavingsBalance() + getCDBalance();
	}
	//implement the compareTo(AccountHolder otherAccountHolder) method
	@Override
	public int compareTo(AccountHolder otherAccountHolder) {
		if (this.getCombinedBalance() > otherAccountHolder.getCombinedBalance())
			return 1;
		else if (this.getCombinedBalance() < otherAccountHolder.getCombinedBalance())
			return -1;
		else
			return 0;
	}

	//String writeToString()
	public String writeToString() {
		String returnStr = "";
		returnStr += this.myLastName + "," + this.myMiddleName + "," + this.myFirstName + "," + this.mySsn;
				
		return returnStr;
	}
	
	public static AccountHolder readFromString(String accountHolderInfo) {
		String[] accountinfo = accountHolderInfo.split(",");
		AccountHolder accountHolder = new AccountHolder(accountinfo[2], accountinfo[1], accountinfo[0], accountinfo[3]);
		return accountHolder;
	}
	
	
}