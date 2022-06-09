package project;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.*;

public class Main {
	
	public static void main (String [] args)
	{
		UserInformation admin = new UserInformation();
		
		File file = new File ("UserInformation.txt");
		File file3 = new File ("Items.txt");
		File file4 = new File ("Transactions.txt");
		File file5 = new File ("People.txt");
		// default
		if (file.length() == 0)
		{
			admin.enterIntoTextFile("username", "password", false);
		}
		
		HashMap<String,String> information = admin.getUserInfo();
		HashMap<String,Person> peopleInformation = new HashMap<String,Person>();
		ArrayList<UserInformation> users = new ArrayList<UserInformation>();
		ArrayList<Person> people = new ArrayList<Person>();
		ArrayList<Transaction> transactions = new ArrayList<Transaction>();
		ArrayList<Merchandise> items = new ArrayList<Merchandise>();
		
		peopleInformation.put(admin.getUsername(), new Person (admin));

		
		Scanner inputStream = null;
		try
		{
			inputStream = new Scanner (new FileInputStream("UserInformation.txt"));
		}
		catch (FileNotFoundException e)
		{
			System.out.print("Error opening the UserInformtion file");
			System.exit(0);
		}
		// reading user information
		File file2 = new File("UserInformation.txt");
		if (file2.length() != 0)
		{
			while(inputStream.hasNextLine())
			{
				int userNum = inputStream.nextInt();
				inputStream.nextLine();
				String user = inputStream.nextLine();
				String pass = inputStream.nextLine();
				information.put(user, pass);
				users.add(new UserInformation(user, pass, userNum));
			}
		}

		admin.setUserInfo(information);
		
		inputStream.close();
		// reading people information
		Scanner inputStream2 = null;
		try
		{
			inputStream2 = new Scanner (new FileInputStream("People.txt"));
		}
		catch (FileNotFoundException e)
		{
			System.out.print("Error opening the People file");
			System.exit(0);
		}
	
		int count = 0;
		
		while(inputStream2.hasNextLine())
		{
			int userNum = Integer.valueOf(inputStream2.nextLine());
			String user = inputStream2.nextLine();
			String accountType = inputStream2.nextLine();
			double balance = Double.valueOf(inputStream2.nextLine());
			int numberOfTransactions = Integer.valueOf(inputStream2.nextLine());
			people.add(new Person(users.get(count), numberOfTransactions, balance, accountType));
			count++;
		}
		
		
		inputStream2.close();
		
		for (Person p: people)
		{
			peopleInformation.put(p.getLoginInfo().getUsername(), p);
		}
		
		// reading in items
		Scanner inputStream3 = null;
		try
		{
			inputStream3 = new Scanner (new FileInputStream("Items.txt"));
		}
		catch (FileNotFoundException e)
		{
			System.out.print("Error opening the Items file");
			System.exit(0);
		}
		while(inputStream3.hasNextLine())
		{
			String type = inputStream3.nextLine();
			String name = inputStream3.nextLine();
			String itemName = inputStream3.nextLine();
			double price = Double.valueOf(inputStream3.nextLine());
			if(type.equals("shoe"))
			{
				items.add(new Shoe(price, peopleInformation.get(name), itemName));
			}
			else if(type.equals("shirt"))
			{
				items.add(new Shirt(price, peopleInformation.get(name), itemName));
			}
			
		}
		inputStream3.close();
		// read in transaction data
		Scanner inputStream4 = null;
		try
		{
			inputStream4 = new Scanner (new FileInputStream("Transactions.txt"));
		}
		catch (FileNotFoundException e)
		{
			System.out.print("Error opening the Transactions file");
			System.exit(0);
		}
		while(inputStream4.hasNextLine())
		{
			String customer = inputStream4.nextLine();
			String seller = inputStream4.nextLine();
			String itemType = inputStream4.nextLine();
			String itemName = inputStream4.nextLine();
			double price = Double.valueOf(inputStream4.nextLine());
			if(itemType.equals("shoe"))
			{
				transactions.add(new Transaction(peopleInformation.get(customer), peopleInformation.get(seller), (new Shoe(price, peopleInformation.get(seller), itemName))));
			}
			else if(itemType.equals("shirt"))
			{
				transactions.add(new Transaction(peopleInformation.get(customer), peopleInformation.get(seller), (new Shirt(price, peopleInformation.get(seller), itemName))));
			}
			
		}
		
		LoginScreen loginScreen = new LoginScreen (admin.getUserInfo(), peopleInformation, users, people, items, transactions);
		
	}

}
