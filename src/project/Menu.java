package project;

import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import javax.swing.*;

public class Menu implements ActionListener {
	
	private HashMap<String,String> userInfo = new HashMap<String,String>();
	private HashMap<String,Person> personInfo = new HashMap<String,Person>();
	private ArrayList<UserInformation> users = new ArrayList<UserInformation>();
	private ArrayList<Person> people = new ArrayList<Person>();
	private ArrayList<Merchandise> items = new ArrayList<Merchandise>();
	private ArrayList<Transaction> transactions = new ArrayList<Transaction>();
	
	Person person;
	
	JFrame frame = new JFrame();
	JLabel welcomeLabel = new JLabel("Hello!");
	JLabel accountTypeLabel = new JLabel("Account Type: ");
	JLabel balanceLabel = new JLabel("Balance: ");
	
	JButton backButton = new JButton("Log Out");
	JButton addItem = new JButton("Add New Item");
	JButton makePurchase = new JButton("Make Purchase");
	JButton transactionHistory = new JButton("View Transaction History");
	JButton createAccountType = new JButton("Set Account Type");
	JButton inventory = new JButton("View Inventory");
	JButton exitButton = new JButton("Exit");

	
	
	public Menu (Person person, HashMap<String,String> originalUserInfo, HashMap<String,Person> originalPersonInfo, ArrayList<UserInformation> users,  ArrayList<Person> people, ArrayList<Merchandise> items, ArrayList<Transaction> transactions)
	{
		this.users = users;
		this.people = people;
		this.person = person;
		this.items = items;
		this.transactions = transactions;
		userInfo = originalUserInfo;
		personInfo = originalPersonInfo;
		person.updateItems(items);
		welcomeLabel.setBounds(25, 25, 500, 50);
		welcomeLabel.setFont(new Font(null, Font.BOLD, 15));
		welcomeLabel.setText("Welcome " + person.getLoginInfo().getUsername() + "! Please select an option to continue.");
		
		accountTypeLabel.setBounds(25, 100, 500, 50);
		accountTypeLabel.setText("Account Type: " + person.getType());
		
		balanceLabel.setBounds(25, 150, 500, 50);
		balanceLabel.setText("Balance: $" + String.format("%.2f", person.getBalance()));
		
		frame.add(welcomeLabel);
		frame.add(accountTypeLabel);
		frame.add(balanceLabel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 800);
		frame.setLayout(null);
		frame.setVisible(true);
		
		inventory.setBounds(50, 250, 200, 50);
		inventory.setFocusable(false);
		inventory.addActionListener(this);
		
		addItem.setBounds(300, 250, 200, 50); 
		addItem.setFocusable(false);
		addItem.addActionListener(this);
		
		makePurchase.setBounds(550, 250, 200, 50);
		makePurchase.setFocusable(false);
		makePurchase.addActionListener(this);
		
		transactionHistory.setBounds(50, 400, 200, 50);
		transactionHistory.setFocusable(false);
		transactionHistory.addActionListener(this);
		
		createAccountType.setBounds(300, 400, 200, 50);
		createAccountType.setFocusable(false);
		createAccountType.addActionListener(this);
		
		backButton.setBounds(550, 400, 200, 50);
		backButton.setFocusable(false);
		backButton.addActionListener(this);
		
		exitButton.setBounds(300, 550, 200, 50);
		exitButton.setFocusable(false);
		exitButton.addActionListener(this);
		
		frame.add(backButton);
		frame.add(addItem);
		frame.add(makePurchase);
		frame.add(transactionHistory);
		frame.add(createAccountType);
		frame.add(inventory);
		frame.add(exitButton);
		
	}

	public Person getPerson()
	{
		return person;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == backButton)
		{
			LoginScreen loginScreen = new LoginScreen (userInfo, personInfo, users, people, items, transactions);
			frame.dispose();
		}
		if (e.getSource() == createAccountType)
		{
			CreateAccountType accountScreen = new CreateAccountType (person, userInfo, personInfo, users, people, items, transactions);
			frame.dispose();
		}
		if (e.getSource() == exitButton)
		{
			// write people
			PrintWriter outputStream = null;
			try
			{
				outputStream = new PrintWriter (new FileOutputStream("People.txt"));
			
			}
			catch (FileNotFoundException a)
			{
				System.out.println ("Error opening the People file");
				System.exit(0);
			}
			for (Person p: people)
			{
				outputStream.println(p.getID());
				outputStream.println(p.getLoginInfo().getUsername());
				outputStream.println(p.getType());
				outputStream.println(p.getBalance());
				outputStream.println(p.getNumberOfTransactions());
			}
			outputStream.close();
			// write items
			PrintWriter outputStream2 = null;
			try
			{
				outputStream2 = new PrintWriter (new FileOutputStream("Items.txt"));
						
			}
			catch (FileNotFoundException a)
			{
				System.out.println ("Error opening the Items file");
				System.exit(0);
			}
			for (Merchandise i: items)
			{
				if (i.getClass() == Shoe.class)
				{
					outputStream2.println("shoe");
					outputStream2.println(i.getOriginalOwner().getLoginInfo().getUsername());
					outputStream2.println(i.getName());
					outputStream2.println(i.getPrice());
				}
				else if (i.getClass() == Shirt.class)
				{
					outputStream2.println("shirt");
					outputStream2.println(i.getOriginalOwner().getLoginInfo().getUsername());
					outputStream2.println(i.getName());
					outputStream2.println(i.getPrice());
				}
			}
			outputStream2.close();
			// write transactions
			PrintWriter outputStream3 = null;
			try
			{
				outputStream3 = new PrintWriter (new FileOutputStream("Transactions.txt"));
						
			}
			catch (FileNotFoundException a)
			{
				System.out.println ("Error opening the Transactions file");
				System.exit(0);
			}
			for (Transaction t: transactions)
			{
				outputStream3.println(t.getCustomer().getLoginInfo().getUsername());
				outputStream3.println(t.getSeller().getLoginInfo().getUsername());
				if (t.getItem().getClass() == Shoe.class)
				{
					outputStream3.println("shoe");
					outputStream3.println(t.getItem().getName());
					outputStream3.println(t.getItem().getPrice());
				}
				else if (t.getItem().getClass() == Shirt.class)
				{
					outputStream3.println("shirt");
					outputStream3.println(t.getItem().getName());
					outputStream3.println(t.getItem().getPrice());
				}
			}
			outputStream3.close();
			frame.dispose();
		}
		if (e.getSource() == addItem)
		{
			frame.dispose();
			NewItem n = new NewItem(person, userInfo, personInfo, users, people, items, transactions);
		}
		if (e.getSource() == inventory)
		{
			Inventory i = new Inventory (person, userInfo, personInfo, users, people, items, transactions);
		}
		if (e.getSource() == makePurchase)
		{
			frame.dispose();
			Purchase p = new Purchase (person, userInfo, personInfo, users, people, items, transactions);
		}
		if (e.getSource() == transactionHistory)
		{
			TransactionHistory t = new TransactionHistory (person, userInfo, personInfo, users, people, items, transactions);
		}
		
	}

}
