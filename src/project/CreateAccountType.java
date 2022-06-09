package project;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class CreateAccountType implements ActionListener {
	
	Person person;
	private HashMap<String,String> userInfo = new HashMap<String,String>();
	private HashMap<String,Person> personInfo = new HashMap<String,Person>();
	private ArrayList<UserInformation> users = new ArrayList<UserInformation>();
	private ArrayList<Person> people = new ArrayList<Person>();
	private ArrayList<Merchandise> items = new ArrayList<Merchandise>();
	private ArrayList<Transaction> transactions = new ArrayList<Transaction>();
	
	JFrame frame = new JFrame ();
	
	JButton setCustomer = new JButton("Customer");
	JButton setSeller = new JButton("Seller");
	
	JLabel welcomeLabel = new JLabel("Please choose an account type. You cannot make changes afterward. ");
	
	
	public CreateAccountType (Person user, HashMap<String,String> userInfo, HashMap<String,Person> personInfo, ArrayList<UserInformation> users, ArrayList<Person> people, ArrayList<Merchandise> items, ArrayList<Transaction> transactions)
	{
		this.users = users;
		this.people = people;
		this.userInfo = userInfo;
		this.personInfo = personInfo;
		this.items = items;
		this.transactions = transactions;
		person = user;
		welcomeLabel.setBounds(50, 50, 700, 50);
		
		setCustomer.setBounds(150, 400, 200, 50);
		setCustomer.setFocusable(false);
		setCustomer.addActionListener(this);
		
		setSeller.setBounds(450, 400, 200, 50);
		setSeller.setFocusable(false);
		setSeller.addActionListener(this);
		
		frame.add(welcomeLabel);
		frame.add(setCustomer);
		frame.add(setSeller);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 800);
		frame.setLayout(null);
		frame.setVisible(true);
	}
	
	public Person getUpdatedPerson()
	{
		return person;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == setCustomer)
		{
			person.setToCustomer();
			frame.dispose();
			Menu menu = new Menu(person, userInfo, personInfo, users, people, items, transactions);
		}
		if (e.getSource() == setSeller)
		{
			person.setToSeller();
			frame.dispose();
			Menu menu = new Menu(person, userInfo, personInfo, users, people, items, transactions);
		}
			
			
		
	}

}
