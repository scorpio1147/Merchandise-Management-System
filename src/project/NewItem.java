package project;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

import javax.swing.*;

public class NewItem implements ActionListener {
	
	JFrame frame = new JFrame ();
	
	JButton addFunds = new JButton("Add Funds");
	JButton addItem = new JButton("Add Item");
	JButton backButton = new JButton("Back");
	
	JTextField itemTypeField = new JTextField();
	JTextField itemNameField = new JTextField();
	JTextField itemCostField = new JTextField();
	JTextField addNewFundsField = new JTextField();
	
	JLabel welcomeLabel = new JLabel("Please add funds or add an item: ");
	JLabel itemType = new JLabel("Item Type (shoe or shirt): ");
	JLabel itemName = new JLabel("Item Name: ");
	JLabel itemCost = new JLabel("Item Cost: ");
	JLabel addNewFunds = new JLabel("New Fund Amount: ");
	JLabel divider = new JLabel("---------------------------------------------------------------------------------------------");
	
	JLabel messageLabel = new JLabel();
	
	Person person;
	private HashMap<String,String> userInfo = new HashMap<String,String>();
	private HashMap<String,Person> personInfo = new HashMap<String,Person>();
	private ArrayList<UserInformation> users = new ArrayList<UserInformation>();
	private ArrayList<Person> people = new ArrayList<Person>();
	private ArrayList<Merchandise> items = new ArrayList<Merchandise>();
	private ArrayList<Transaction> transactions = new ArrayList<Transaction>();
	
	public NewItem (Person person, HashMap<String,String> originalUserInfo, HashMap<String,Person> originalPersonInfo, ArrayList<UserInformation> users,  ArrayList<Person> people, ArrayList<Merchandise> items, ArrayList<Transaction> transactions)
	{
		this.person = person;
		userInfo = originalUserInfo;
		personInfo = originalPersonInfo;
		this.users = users;
		this.people = people;
		this.items = items;
		this.transactions = transactions;
		
		messageLabel.setBounds(225, 225, 400, 100);
		messageLabel.setFont(new Font(null, Font.ITALIC, 20));
		
		welcomeLabel.setBounds(25, 25, 500, 50);
		welcomeLabel.setFont(new Font(null, Font.BOLD, 15));
		
		addNewFunds.setBounds(50, 200, 200, 25);
		addNewFundsField.setBounds(250, 200, 200, 25);	
		addFunds.setBounds(500, 200, 200, 25);
		addFunds.setFocusable(false);
		addFunds.addActionListener(this);
		
		divider.setBounds(25, 300, 800, 25);
		
		itemType.setBounds(150, 400, 200, 25);
		itemTypeField.setBounds(375, 400, 200, 25);
		
		itemName.setBounds(150, 450, 200, 25);
		itemNameField.setBounds(375, 450, 200, 25);
		
		itemCost.setBounds(150, 500, 200, 25);
		itemCostField.setBounds(375, 500, 200, 25);
		
		addItem.setBounds(325, 575, 200, 50);
		addItem.setFocusable(false);
		addItem.addActionListener(this);
		
		backButton.setBounds(335, 700, 150, 40);
		backButton.setFocusable(false);
		backButton.addActionListener(this);
		
		frame.add(welcomeLabel);
		frame.add(itemType);
		frame.add(itemTypeField);
		frame.add(itemName);
		frame.add(itemNameField);
		frame.add(itemCost);
		frame.add(itemCostField);
		frame.add(addNewFunds);
		frame.add(addNewFundsField);
		frame.add(addItem);
		frame.add(addFunds);
		frame.add(divider);
		frame.add(backButton);
		frame.add(messageLabel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 800);
		frame.setLayout(null);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == backButton)
		{
			frame.dispose();
			Menu menu = new Menu(person, userInfo, personInfo, users, people, items, transactions);
		}
		if (e.getSource() == addFunds)
		{
			try
			{
				double funds = Double.valueOf(addNewFundsField.getText());
				person.addToBalance(funds);
				messageLabel.setBounds(225, 225, 400, 100);
				messageLabel.setForeground(Color.LIGHT_GRAY);
				messageLabel.setText("Success! Funds added!");
			}
			catch (Exception a)
			{
				messageLabel.setBounds(225, 225, 400, 100);
				messageLabel.setForeground(Color.RED);
				messageLabel.setText("Error: Please enter a valid amount.");
			}
		}
		if (e.getSource() == addItem)
		{
			try
			{
				String type = itemTypeField.getText();
				String name = itemNameField.getText();
				double cost = Double.valueOf(itemCostField.getText());
				if (type.equals("shoe"))
				{
					items.add(new Shoe (cost, person, name));
					messageLabel.setBounds(225, 610, 400, 100);
					messageLabel.setForeground(Color.LIGHT_GRAY);
					person.updateItems(items);
					messageLabel.setText("Success! Item added!");
				}
				else if (type.equals("shirt"))
				{
					items.add(new Shirt (cost, person, name));
					messageLabel.setBounds(225, 610, 400, 100);
					messageLabel.setForeground(Color.LIGHT_GRAY);
					person.updateItems(items);
					messageLabel.setText("Success! Item added!");
				}
				else 
				{
					messageLabel.setBounds(225, 610, 400, 100);
					messageLabel.setForeground(Color.RED);
					messageLabel.setText("Error: Invalid Type");
				}
				
			}
			catch (Exception a)
			{
				messageLabel.setBounds(225, 610, 400, 100);
				messageLabel.setForeground(Color.RED);
				messageLabel.setText("Error: Please enter a valid amount.");
			}
		}
		
	}

}
