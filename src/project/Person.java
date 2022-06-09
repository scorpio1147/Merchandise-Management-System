package project;

import java.io.FileNotFoundException;
import java.util.*;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class Person {
	
	protected UserInformation loginInfo;
	protected int numberOfTransactions;
	protected double balance;
	protected String type;
	protected int userID;
	protected ArrayList<Merchandise> inventory = new ArrayList<Merchandise>();
	protected ArrayList<Transaction> personalTransactions = new ArrayList<Transaction>();
	
	public Person (UserInformation loginInfo)
	{
		this.loginInfo = loginInfo;
		numberOfTransactions = 0;
		balance = 0;
		type = "null";
		userID = loginInfo.getUserNumber();
	}
	
	public Person (UserInformation loginInfo, int numTrans, double bal, String type)
	{
		this.loginInfo = loginInfo;
		numberOfTransactions = numTrans;
		balance = bal;
		this.type = type;
		userID = loginInfo.getUserNumber();
	}
	
	
	public void enterIntoTextFile (boolean append)
	{
		if (append)
		{
			PrintWriter outputStream = null;
			try
			{
				outputStream = new PrintWriter (new FileOutputStream("People.txt", true));
			
			}
			catch (FileNotFoundException e)
			{
				System.out.println ("Error opening the People file");
				System.exit(0);
			}
			outputStream.println(userID);
			outputStream.println(loginInfo.getUsername());
			outputStream.println(type);
			outputStream.println(balance);
			outputStream.println(numberOfTransactions);
			outputStream.close();
		}
		else
		{
			PrintWriter outputStream = null;
			try
			{
				outputStream = new PrintWriter (new FileOutputStream("People.txt"));
			
			}
			catch (FileNotFoundException e)
			{
				System.out.println ("Error opening the People file");
				System.exit(0);
			}
			outputStream.println(userID);
			outputStream.println(loginInfo.getUsername());
			outputStream.println(type);
			outputStream.println(balance);
			outputStream.println(numberOfTransactions);
			outputStream.close();
		}
	}
	
	public void updateItems(ArrayList<Merchandise>items)
	{
		inventory.clear();
		for (Merchandise m: items)
		{
			if (m.getOriginalOwner().getLoginInfo().getUsername().equals(loginInfo.getUsername()))
			{
				inventory.add(m);
			}
		}
	}
	
	public void updateTransactions(ArrayList<Transaction>transactions)
	{
		personalTransactions.clear();
		for (Transaction t: transactions)
		{
			if (t.getCustomer().getLoginInfo().getUsername().equals(loginInfo.getUsername()) || t.getSeller().getLoginInfo().getUsername().equals(loginInfo.getUsername()))
			{
				personalTransactions.add(t);
			}
		}
	}
	
	public ArrayList<Merchandise> getInventory ()
	{
		return inventory;
	}
	
	public ArrayList<Transaction> getPersonalTransactions()
	{
		return personalTransactions;
	}
	
	public int getNumberOfTransactions()
	{
		return numberOfTransactions;
	}
	
	public UserInformation getLoginInfo()
	{
		return loginInfo;
	}
	
	public double getBalance()
	{
		return balance;
	}
	
	public void addToBalance (double payment)
	{
		balance += payment;
	}
	
	public void removeFromBalance (double payment)
	{
		balance -= payment;
	}
	
	public int getID()
	{
		return userID;
	}
	
	public void addSale ()
	{
		numberOfTransactions++;
	}
	
	public String getType()
	{
		return type;
	}
	
	public void setToCustomer()
	{
		type = "customer";
	}
	
	public void setToSeller()
	{
		type = "seller";
	}
	
}
