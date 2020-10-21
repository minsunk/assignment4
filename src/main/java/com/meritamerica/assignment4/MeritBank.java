package com.meritamerica.assignment4;

import java.io.*;
import java.util.*;
import java.util.Arrays;
import java.util.Collections;



//import com.meritamerica.assignment2.AccountHolder;
//import com.meritamerica.assignment2.CDOffering;

public class MeritBank {
	private static AccountHolder[] accountHolderList = null;
	private static CDOffering[] cdOfferingList = null;
	private static int accountNumber = 999;
	static List<Integer> number;
	static List<String> firstWord;
	static List<String> rest;
	private static long nextAccountNumber = 0;
	private static AccountHolder AccountHoldersArray[] = new AccountHolder[0];
	private static CDOffering CDOfferingsArray[] = new CDOffering[0];

	//1. static void addAccountHolder(AccountHolder accountHolder)
	public static void addAccountHolder(AccountHolder accountHolder) {
		if (accountHolderList == null) {
			accountHolderList = new AccountHolder[1];
			accountHolderList[0] = accountHolder;
		} else {
			AccountHolder[] tempAccountHolderList = new AccountHolder[accountHolderList.length + 1];
			for (int i = 0; i < accountHolderList.length; i++) {
				tempAccountHolderList[i] = accountHolderList[i];
			}
			tempAccountHolderList[accountHolderList.length] = accountHolder;
			accountHolderList = tempAccountHolderList;
		}
	}

	//2.static AccountHolder[] getAccountHolders()
	public static AccountHolder[] getAccountHolders() {
		return accountHolderList;
	}
	//3.static CDOffering[] getCDOfferings(){
	public static CDOffering[] getCDOfferings() {
		return cdOfferingList;
	}
	//static CDOffering getBestCDOffering(double depositAmount)
	public static CDOffering getBestCDOffering(double depositAmount) {
		if (cdOfferingList == null){
			return null;
		} else {
		
			int bestOfferingIndex = 0;
		
			for (int i = 1; i < cdOfferingList.length; i++){
				if (Math.pow(1 + cdOfferingList[i].getInterestRate(), cdOfferingList[i].getTerm()) > 

				Math.pow(1 + cdOfferingList[bestOfferingIndex].getInterestRate(), cdOfferingList[bestOfferingIndex].getTerm())) {
					bestOfferingIndex = i;
				}                     
			}
			
			return cdOfferingList[bestOfferingIndex];
		}
	}
	//static CDOffering getSecondBestCDOffering(double depositAmount)
	public static CDOffering getSecondBestCDOffering(double depositAmount) {
		if (cdOfferingList == null){
			return null;
		} else {
			int bestOfferingIndex = 0;
			int secondBestOfferingIndex = 0;
			
			for (int i = 1; i < cdOfferingList.length; i++){
				if (Math.pow(1 + cdOfferingList[i].getInterestRate(), cdOfferingList[i].getTerm()) > 
				Math.pow(1 + cdOfferingList[bestOfferingIndex].getInterestRate(), cdOfferingList[bestOfferingIndex].getTerm())) {
					secondBestOfferingIndex = bestOfferingIndex;
					bestOfferingIndex = i;
				}                     
			}
		
			return cdOfferingList[secondBestOfferingIndex];
	
		}
	}
	//static void clearCDOfferings()
	public static void clearCDOfferings(){
		cdOfferingList = null;
	}
	
	//static void setCDOfferings(CDOffering[] offerings)
	public static void setCDOfferings(CDOffering[] offerings) {
		cdOfferingList = offerings;
	}
	
	//static long getNextAccountNumber()
	public static int getNextAccountNumber(){
		accountNumber++;
		return accountNumber;
	}
	
	//static double totalBalances()
	public static double totalBalances(){
		double balanceTotal = 0;
		for (int i = 0; i < accountHolderList.length; i++) {
			balanceTotal += (accountHolderList[i].getCombinedBalance());
		}
		return balanceTotal;		
	}
	//static double futureValue(double presentValue, double interestRate, int term)
	public static double futureValue(double presentValue, double interestRate, int term){
		return Math.pow(1+interestRate, term)*presentValue;
	}
/*
	public static AccountHolder readFromString(String accountHolderData) {
	    String[] holding = accountHolderData.split(",");
	    String firstName = holding[2];
	    String middleName = holding[1];
	    String lastName = holding[0];
	    String ssn = holding[3];	
	    return new AccountHolder(firstName, middleName, lastName, ssn);
	}
*/
	static boolean readFromFile( String fileName) {
		CDOffering offering[] = new CDOffering[0];
		try {
			FileReader reader = new FileReader (fileName);
			BufferedReader bufferedReader = new BufferedReader(reader);
			Long nextAccountNumber = Long.valueOf(bufferedReader.readLine());
			int holdOfferNum = Integer.valueOf(bufferedReader.readLine());
				for(int i = 0; i < holdOfferNum; i++) {
					offering = Arrays.copyOf(offering, offering.length + 1);
					offering [offering.length-1] = CDOffering.readFromString(bufferedReader.readLine());
				}
			int numOfAcctHld = Integer.valueOf(bufferedReader.readLine());
			AccountHolder [] newAccountHolders = new AccountHolder[numOfAcctHld];
			for(int i = 0; i<numOfAcctHld; i++) {				
				AccountHolder acctH = AccountHolder.readFromString(bufferedReader.readLine());
				int numOfChecking = Integer.valueOf(bufferedReader.readLine());
					for(int j = 0; j<numOfChecking; j++) {
						acctH.addCheckingAccount(CheckingAccount.readFromString(bufferedReader.readLine()));				
					}
				int numOfSavings = Integer.valueOf(bufferedReader.readLine());
					for(int k = 0; k<numOfSavings; k++) {
						acctH.addSavingsAccount(SavingsAccount.readFromString(bufferedReader.readLine()));				
					}
				int numOfCD = Integer.valueOf(bufferedReader.readLine());
					for(int m = 0; m<numOfCD; m++) {
						acctH.addCDAccount(CDAccount.readFromString(bufferedReader.readLine()));
					}
				newAccountHolders[i] = acctH;	
			}
			setNextAccountNumber(nextAccountNumber);
			CDOfferingsArray = offering;
			AccountHoldersArray = newAccountHolders;
			reader.close();
			return true;
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		catch (NumberFormatException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	static boolean writeToFile( String fileName)  {
		try {
		FileWriter writer = new FileWriter(fileName);
		BufferedWriter bufferedWriter = new BufferedWriter(writer);
		bufferedWriter.write(String.valueOf(nextAccountNumber));
		bufferedWriter.newLine();
		bufferedWriter.write(String.valueOf(CDOfferingsArray.length));
		bufferedWriter.newLine();
			for(int i = 0; i<CDOfferingsArray.length;i++) {
				bufferedWriter.write(CDOfferingsArray[i].writeToString());
				bufferedWriter.newLine();
			}
			
		bufferedWriter.write(String.valueOf(AccountHoldersArray.length));
		bufferedWriter.newLine();
			for(int j = 0; j <AccountHoldersArray.length;j++) {
				bufferedWriter.write(AccountHoldersArray[j].writeToString());
				bufferedWriter.newLine();
				bufferedWriter.write(String.valueOf(AccountHoldersArray[j].getNumberOfCheckingAccounts()));
				bufferedWriter.newLine();
					for(int k = 0; k < AccountHoldersArray[j].getNumberOfCheckingAccounts();k++) {
							bufferedWriter.write(AccountHoldersArray[j].getCheckingAccounts()[k].writeToString());
							bufferedWriter.newLine();
					}
				bufferedWriter.write(String.valueOf(AccountHoldersArray[j].getNumberOfSavingsAccounts()));
				bufferedWriter.newLine();
					for(int m = 0; m < AccountHoldersArray[j].getNumberOfSavingsAccounts();m++) {
							bufferedWriter.write(AccountHoldersArray[j].getSavingsAccounts()[m].writeToString());
							bufferedWriter.newLine();
					}

				bufferedWriter.write(String.valueOf(AccountHoldersArray[j].getNumberOfCDAccounts()));
				bufferedWriter.newLine();
					for(int n = 0; n < AccountHoldersArray[j].getNumberOfCDAccounts();n++) {
							bufferedWriter.write(AccountHoldersArray[j].getCDAccounts()[n].writeToString());
							bufferedWriter.newLine();
					}			
			}
			bufferedWriter.close();
			return true;
		}
		catch (Exception e) {
			return false;
		}
	
	}
	
	static AccountHolder[] sortAccountHolders() {
		Arrays.sort(AccountHoldersArray, Collections.reverseOrder());
			for(AccountHolder a : AccountHoldersArray) {
				System.out.println(a);
			}
		return AccountHoldersArray;
	}
	
	static void setNextAccountNumber( long accountNumber) {
		nextAccountNumber = accountNumber;
		
	}
		}


/**import java.io.*;
import java.util.*;

//import com.meritamerica.assignment2.AccountHolder;
//import com.meritamerica.assignment2.CDOffering;

public class MeritBank {
	private static AccountHolder[] accountHolderList = null;
	private static CDOffering[] cdOfferingList = null;
	private static long accountNumber = 999;
	

	//1. static void addAccountHolder(AccountHolder accountHolder)
	public static void addAccountHolder(AccountHolder accountHolder) {
		if (accountHolderList == null) {
			accountHolderList = new AccountHolder[1];
			accountHolderList[0] = accountHolder;
		} else {
			AccountHolder[] tempAccountHolderList = new AccountHolder[accountHolderList.length + 1];
			for (int i = 0; i < accountHolderList.length; i++) {
				tempAccountHolderList[i] = accountHolderList[i];
			}
			tempAccountHolderList[accountHolderList.length] = accountHolder;
			accountHolderList = tempAccountHolderList;
		}
	}

	//2.static AccountHolder[] getAccountHolders()
	public static AccountHolder[] getAccountHolders() {
		return accountHolderList;
	}
	//3.static CDOffering[] getCDOfferings(){
	public static CDOffering[] getCDOfferings() {
		return cdOfferingList;
	}
	//static CDOffering getBestCDOffering(double depositAmount)
	public static CDOffering getBestCDOffering(double depositAmount) {
		if (cdOfferingList == null){
			return null;
		} else {
		
			int bestOfferingIndex = 0;
		
			for (int i = 1; i < cdOfferingList.length; i++){
				if (Math.pow(1 + cdOfferingList[i].getInterestRate(), cdOfferingList[i].getTerm()) > 
				Math.pow(1 + cdOfferingList[bestOfferingIndex].getInterestRate(), cdOfferingList[bestOfferingIndex].getTerm())) {
					bestOfferingIndex = i;
				}                     
			}
			
			return cdOfferingList[bestOfferingIndex];
		}
	}
	//static CDOffering getSecondBestCDOffering(double depositAmount)
	public static CDOffering getSecondBestCDOffering(double depositAmount) {
		if (cdOfferingList == null){
			return null;
		} else {
			int bestOfferingIndex = 0;
			int secondBestOfferingIndex = 0;
			
			for (int i = 1; i < cdOfferingList.length; i++){
				if (Math.pow(1 + cdOfferingList[i].getInterestRate(), cdOfferingList[i].getTerm()) > 
				Math.pow(1 + cdOfferingList[bestOfferingIndex].getInterestRate(), cdOfferingList[bestOfferingIndex].getTerm())) {
					secondBestOfferingIndex = bestOfferingIndex;
					bestOfferingIndex = i;
				}                     
			}
		
			return cdOfferingList[secondBestOfferingIndex];
	
		}
	}
	//static void clearCDOfferings()
	public static void clearCDOfferings(){
		cdOfferingList = null;
	}
	
	//static void setCDOfferings(CDOffering[] offerings)
	public static void setCDOfferings(CDOffering[] offerings) {
		cdOfferingList = offerings;
	}
	
	//static long getNextAccountNumber()
	public static long getNextAccountNumber(){
		accountNumber++;
		return accountNumber;
	}
	
	//static double totalBalances()
	public static double totalBalances(){
		double balanceTotal = 0;
		for (int i = 0; i < accountHolderList.length; i++) {
			balanceTotal += (accountHolderList[i].getCombinedBalance());
		}
		return balanceTotal;		
	}
	//static double futureValue(double presentValue, double interestRate, int term)
	public static double futureValue(double presentValue, double interestRate, int term){
		return Math.pow(1+interestRate, term)*presentValue;
	}
	
	public static boolean readFromFile(String fileName) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(fileName)));

			// next account number
			String line = br.readLine();
			int lineNum = 0;
			MeritBank.accountNumber = Long.parseLong(line);
			
			// number of CD offerings
			line = br.readLine();
			lineNum++;
			int numCDOffering = Integer.parseInt(line);
			clearCDOfferings();

			// create CDOffering objects
			CDOffering[] newCDOffering = new CDOffering[numCDOffering];
			for (int i = 0; i < numCDOffering; i++) {
				line = br.readLine();
				lineNum++;
				String[] field = line.split(",");
				if (field.length != 2) {
					System.out.println("The file \"" + fileName + "\" cannot be parsed correctly at line " + lineNum + ".");
					br.close();
					return false;
				}
				newCDOffering[i] = new CDOffering(Integer.parseInt(field[0]), Double.parseDouble(field[1]));
			}
			setCDOfferings(newCDOffering);
						
			// number of account holders
			line = br.readLine();
			lineNum++;
			int numAccounts = Integer.parseInt(line);
			
			// for each account holder do the following
			for (int i = 0; i < numAccounts; i++) {

				// account holder personal info
				line = br.readLine();
				lineNum++;
				String[] field = line.split(",");
				if (field.length != 4) {
					System.out.println("The file \"" + fileName + "\" cannot be parsed correctly at line " + lineNum + ".");
					br.close();
					return false;
				}
				AccountHolder newAccountHolder = new AccountHolder(field[0], field[1], field[2], field[3]);
				addAccountHolder(newAccountHolder);
				
				// number of checking accounts
				line = br.readLine();
				lineNum++;
				int numCheckingAccountNum = Integer.parseInt(line);
				
				// create CheckingAccount objects
				for (int j = 0; j < numCheckingAccountNum; j++) {
					line = br.readLine();
					lineNum++;
					newAccountHolder.addCheckingAccount(CheckingAccount.readFromString(line));
				}
				
				// number of savings accounts
				line = br.readLine();
				lineNum++;
				int numSavingsAccountNum = Integer.parseInt(line);
				
				// create SavingsAccount objects
				for (int j = 0; j < numSavingsAccountNum; j++) {
					line = br.readLine();
					lineNum++;
					newAccountHolder.addSavingsAccount(SavingsAccount.readFromString(line));
				}
				
				
				// number of CD accounts
				line = br.readLine();
				lineNum++;
				int numCDAccountNum = Integer.parseInt(line);
				
				// create CDAccount objects
				for (int j = 0; j < numCDAccountNum; j++) {
					line = br.readLine();
					lineNum++;
					newAccountHolder.addCDAccount(CDAccount.readFromString(line));
				}				
			}
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("The file \"" + fileName + "\" is not found.");
			return false;
		} catch (IOException e) {
			System.out.println("The file \"" + fileName + "\" cannot be parsed correctly.");
			return false;
		} catch (NumberFormatException e) {
			System.out.println("The file \"" + fileName + "\" cannot be parsed correctly.");
			return false;
		} 
		
		return true;
	}

	public static boolean writeToFile(String fileName) {
		BufferedWriter bw = null;
		try {
			File file = new File(fileName);

			if (!file.exists()) {
			     file.createNewFile();
			}

			FileWriter fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
		
			String line ="";

			// next account number
			bw.write(Long.toString(accountNumber));
			bw.newLine();
			
			// number of CD offerings
			bw.write(Integer.toString(cdOfferingList.length));
			bw.newLine();

			// CDOffering objects
			for (int i = 0; i < cdOfferingList.length; i++) {
				bw.write(cdOfferingList[i].writeToString());
				bw.newLine();
			}
						
			// number of account holders
			int numAccounts = accountHolderList.length;
			bw.write(Integer.toString(numAccounts));
			bw.newLine();
			
			// for each account holder do the following
			for (int i = 0; i < numAccounts; i++) {

				// account holder personal info
				bw.write(accountHolderList[i].writeTostring());
				bw.newLine();
				
				// number of checking accounts
				int numCheckingAccountNum = accountHolderList[i].getNumberOfCheckingAccounts();
				CheckingAccount[] checkingAccountList = accountHolderList[i].getCheckingAccounts();
				
				// create CheckingAccount objects
				for (int j = 0; j < numCheckingAccountNum; j++) {
					bw.write(checkingAccountList[j].writeTostring());
				}
				
				// number of savings accounts
				int numSavingsAccountNum = accountHolderList[i].getNumberOfSavingsAccounts();
				SavingsAccount[] savingsAccountList = accountHolderList[i].getSavingsAccounts();
				
				// create SavingsAccount objects
				for (int j = 0; j < numSavingsAccountNum; j++) {
					bw.write(savingsAccountList[j].writeTostring());
				}
				
				
				// number of CD accounts
				int numCDAccountNum = accountHolderList[i].getNumberofCDAccounts();
				CDAccount[] cdAccountList = accountHolderList[i].getCDAccounts();
				
				// create CDAccount objects
				for (int j = 0; j < numCDAccountNum; j++) {
					bw.write(cdAccountList[j].writeTostring());
				}
			}
			bw.close();
		} catch (FileNotFoundException e) {
			System.out.println("The file \"" + fileName + "\" could not be written.");
			return false;
		} catch (IOException e) {
			System.out.println("The file \"" + fileName + "\" could not be written.");
			return false;
		} catch (NumberFormatException e) {
			System.out.println("The file \"" + fileName + "\" could not be written.");
			return false;
		} 
		
		return true;
	}

	public static AccountHolder[] sortAccountHolders() {
		AccountHolder[] sortedList = MeritBank.accountHolderList;
		ArrayList<AccountHolder> arrayList = new ArrayList<AccountHolder>();
		
		for (int i = 0; i < sortedList.length; i++) {
			arrayList.add(sortedList[i]);
		}
		
		Collections.sort(arrayList);

		for (int i = 0; i < sortedList.length; i++) {
			sortedList[i] = arrayList.get(i);
		}
		
		return sortedList;
	}
	
	public static void setNextAccountNumber(long nextAccountNumber) {
		MeritBank.accountNumber = nextAccountNumber;
	}

}
*/