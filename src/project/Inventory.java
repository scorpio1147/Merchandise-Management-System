package project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;

public class Inventory implements ActionListener {
	
	JFrame frame = new JFrame();
	JTable table;
	JButton backButton = new JButton("Back");
	
	JLabel welcomeLabel = new JLabel();
	
	private HashMap<String,String> userInfo = new HashMap<String,String>();
	private HashMap<String,Person> personInfo = new HashMap<String,Person>();
	private ArrayList<UserInformation> users = new ArrayList<UserInformation>();
	private ArrayList<Person> people = new ArrayList<Person>();
	private ArrayList<Merchandise> items = new ArrayList<Merchandise>();
	private ArrayList<Transaction> transactions = new ArrayList<Transaction>();
	
	Person person;

	
	public Inventory (Person person, HashMap<String,String> originalUserInfo, HashMap<String,Person> originalPersonInfo, ArrayList<UserInformation> users,  ArrayList<Person> people, ArrayList<Merchandise> items, ArrayList<Transaction> transactions)
	{
		this.person = person;
		userInfo = originalUserInfo;
		personInfo = originalPersonInfo;
		this.users = users;
		this.people = people;
		this.transactions = transactions;
		String[] columnHeaders = {"Item Type", "Item Name", "Item Cost"};
		Object [][] data = new Object [person.getInventory().size()][3];
		ArrayList<Merchandise> sortedMerchandise = new ArrayList<Merchandise>();
		sortedMerchandise = selectionSort(person.getInventory());
		for (int i = 0; i<data.length; i++)
		{
			if (sortedMerchandise.get(i).getClass().equals(Shoe.class))
			{
				data[i][0] = "shoe";
			}
			else if (sortedMerchandise.get(i).getClass().equals(Shirt.class))
			{
				data[i][0] = "shirt";
			}
			data[i][1] = sortedMerchandise.get(i).getName();
			data[i][2] = "$" + String.format("%.2f", sortedMerchandise.get(i).getPrice());
		}
		table = new JTable(data, columnHeaders);
		table.setBounds(120, 100, 600, 500);
		
		JScrollPane pane = new JScrollPane(table);
		pane.setBounds(120, 100, 600, 500);
		frame.add(pane);
		table.setShowGrid(true);
		table.getTableHeader().setFont(new Font("SansSerif", Font.ITALIC + Font.BOLD, 12));
		table.setGridColor(Color.BLACK);
		
		welcomeLabel.setBounds(300, 25, 500, 50);
		welcomeLabel.setFont(new Font(null, Font.BOLD, 15));
		welcomeLabel.setText(person.getLoginInfo().getUsername() + "'s Inventory:");
		
		backButton.setBounds(335, 675, 150, 40);
		backButton.setFocusable(false);
		backButton.addActionListener(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 800);
		frame.setLayout(null);
		frame.setVisible(true);
		frame.add(backButton);
		frame.add(welcomeLabel);
	}
	
	public ArrayList<Merchandise> selectionSort(ArrayList<Merchandise> arg)
	{
		ArrayList<Merchandise> arg1 = arg;
		 for (int j = 0; j < arg1.size()-1; j++)
		    {
		      int min = j;
		      for (int k = j+1; k < arg1.size(); k++)
		        if (arg1.get(k).getName().compareTo(arg1.get(min).getName()) < 0 ) 
		        {
		        	min = k;  
		        }
		      Merchandise temp = arg1.get(j);
		      arg1.set(j, arg1.get(min));
		      arg1.set(min, temp);
		    }   
		    return arg1;  
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == backButton)
		{
			frame.dispose();
		}
		
	}


}
	
