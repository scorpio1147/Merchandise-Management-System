package project;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;

public class Purchase implements ActionListener {
	
	JFrame frame = new JFrame();
	
	JLabel welcomeLabel = new JLabel();
	JLabel messageLabel = new JLabel();
	JLabel itemOwnerLabel = new JLabel();
	JLabel itemTypeLabel = new JLabel();
	JLabel itemNameLabel = new JLabel();
	JLabel itemCostLabel = new JLabel();
	
	JButton makePurchase = new JButton("Make Purchase");
	JButton backButton = new JButton("Back");
	JButton viewAllItems = new JButton("View All Items");
	
	JTextField itemOwnerField = new JTextField();
	JTextField itemTypeField = new JTextField();
	JTextField itemNameField = new JTextField();
	JTextField itemCostField = new JTextField();
	
	Person person;
	private HashMap<String,String> userInfo = new HashMap<String,String>();
	private HashMap<String,Person> personInfo = new HashMap<String,Person>();
	private ArrayList<UserInformation> users = new ArrayList<UserInformation>();
	private ArrayList<Person> people = new ArrayList<Person>();
	private ArrayList<Merchandise> items = new ArrayList<Merchandise>();
	private ArrayList<Transaction> transactions = new ArrayList<Transaction>();
	
	public Purchase (Person person, HashMap<String,String> originalUserInfo, HashMap<String,Person> originalPersonInfo, ArrayList<UserInformation> users,  ArrayList<Person> people, ArrayList<Merchandise> items, ArrayList<Transaction> transactions)
	{	
		welcomeLabel.setBounds(25, 25, 500, 50);
		welcomeLabel.setFont(new Font(null, Font.BOLD, 15));
		welcomeLabel.setText("Please accurately fill out the fields to make a purchase.");
		
		messageLabel.setFont(new Font(null, Font.ITALIC, 20));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 800);
		frame.setLayout(null);
		frame.setVisible(true);
		
		userInfo = originalUserInfo;
		this.person = person;
		personInfo = originalPersonInfo;
		this.people = people;
		this.users = users;
		this.items = items;
		this.transactions = transactions;
		
		itemOwnerLabel.setBounds(200, 200, 200, 25);
		itemOwnerLabel.setText("Vendor Name: ");
		itemOwnerField.setBounds(350, 200, 300, 25);
		
		itemTypeLabel.setBounds(200, 275, 200, 25);
		itemTypeLabel.setText("Item Type: ");
		itemTypeField.setBounds(350, 275, 300, 25);
		
		itemNameLabel.setBounds(200, 350, 200, 25);
		itemNameLabel.setText("Item Name: ");
		itemNameField.setBounds(350, 350, 300, 25);
		
		itemCostLabel.setBounds(200, 425, 200, 25);
		itemCostLabel.setText("Item Cost: ");
		itemCostField.setBounds(350, 425, 300, 25);
		
		viewAllItems.setBounds(75, 550, 200, 50);
		viewAllItems.setFocusable(false);
		viewAllItems.addActionListener(this);
		
		makePurchase.setBounds(300, 550, 200, 50);
		makePurchase.setFocusable(false);
		makePurchase.addActionListener(this);
		
		backButton.setBounds(525, 550, 200, 50);
		backButton.setFocusable(false);
		backButton.addActionListener(this);
		
		frame.add(welcomeLabel);
		frame.add(itemOwnerLabel);
		frame.add(itemOwnerField);
		frame.add(itemTypeLabel);
		frame.add(itemTypeField);
		frame.add(itemNameLabel);
		frame.add(itemNameField);
		frame.add(itemCostLabel);
		frame.add(itemCostField);
		frame.add(makePurchase);
		frame.add(backButton);
		frame.add(viewAllItems);
		frame.add(messageLabel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == backButton)
		{
			frame.dispose();
			Menu menu = new Menu(person, userInfo, personInfo, users, people, items, transactions);
		}
		if(e.getSource() == viewAllItems)
		{
			CompleteInventory c = new CompleteInventory(person, userInfo, personInfo, users, people, items, transactions);
		}
		if (e.getSource() == makePurchase)
		{
			try
			{
				String owner = itemOwnerField.getText();
				String type = itemTypeField.getText();
				String name = itemNameField.getText();
				double cost = Double.valueOf(itemCostField.getText());
				if (person.getLoginInfo().getUsername().equals(owner))
				{
					messageLabel.setBounds(225, 610, 400, 100);
					messageLabel.setForeground(Color.RED);
					messageLabel.setText("Error: You are the owner");
				}
				else if (person.getBalance() >= cost)
				{
					Merchandise[]temp = new Merchandise [1];
					if (type.equals("shoe"))
					{
						for (Merchandise m: items)
						{
							if (owner.equals(m.getOriginalOwner().getLoginInfo().getUsername()) && name.equals(m.getName()) && m.getClass().equals(Shoe.class) && (cost == m.getPrice()))
							{
								temp[0] = m;
							}
						}
					}
					else if (type.equals("shirt"))
					{
						for (Merchandise m: items)
						{
							if (owner.equals(m.getOriginalOwner().getLoginInfo().getUsername()) && name.equals(m.getName()) && m.getClass().equals(Shirt.class) && (cost == m.getPrice()))
							{
								temp[0] = m;
							}
						}
					}
					else
					{
						messageLabel.setBounds(225, 610, 400, 100);
						messageLabel.setForeground(Color.RED);
						messageLabel.setText("Error: Invalid Item Type");
					}
					if (temp[0].equals(null))
					{
						messageLabel.setBounds(225, 610, 400, 100);
						messageLabel.setForeground(Color.RED);
						messageLabel.setText("Error: Item Could Not Be Found");
					}
					else
					{
						items.remove(temp[0]);
						if (type.equals("shoe"))
						{
							items.add(new Shoe (cost, person, name));
						}
						else if (type.equals("shirt"))
						{
							items.add(new Shirt (cost, person, name));
						}
					}
					messageLabel.setBounds(225, 610, 400, 100);
					messageLabel.setForeground(Color.LIGHT_GRAY);
					person.updateItems(items);
					person.updateTransactions(transactions);
					person.removeFromBalance(cost);
					personInfo.get(owner).addToBalance(cost);
					transactions.add(new Transaction(person, personInfo.get(owner), temp[0]));
					System.out.println(person.getLoginInfo().getUsername());
					System.out.println(personInfo.get(owner).getLoginInfo().getUsername());
					messageLabel.setText("Success! Item Purchased!");
				}
				else 
				{
					messageLabel.setBounds(225, 610, 400, 100);
					messageLabel.setForeground(Color.RED);
					person.updateItems(items);
					messageLabel.setText("Error: Insufficient Funds");
				}
				
			}
			catch (Exception a)
			{
				messageLabel.setBounds(225, 610, 400, 100);
				messageLabel.setForeground(Color.RED);
				messageLabel.setText("Error: Please re-enter the fields.");
			}
		}
		
	}

}
