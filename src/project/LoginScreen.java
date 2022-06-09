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


public class LoginScreen implements ActionListener {
	
	JFrame frame = new JFrame ();
	
	JButton loginButton = new JButton("Login");
	JButton registerButton = new JButton("New User");
	
	JTextField usernameField = new JTextField();
	JPasswordField passwordField = new JPasswordField();
	
	JLabel usernameLabel = new JLabel("Username: ");
	JLabel passwordLabel = new JLabel("Password: ");
	JLabel welcomeLabel = new JLabel("Welcome to the Management System! Please enter your username and password, or create a new account.");
	
	JLabel messageLabel = new JLabel();
	
	private HashMap<String,String> userInfo = new HashMap<String,String>();
	private HashMap<String,Person> personInfo = new HashMap<String,Person>();
	private ArrayList<UserInformation> users = new ArrayList<UserInformation>();
	private ArrayList<Person> people = new ArrayList<Person>();
	private ArrayList<Merchandise> items = new ArrayList<Merchandise>();
	private ArrayList<Transaction> transactions = new ArrayList<Transaction>();
	
	
	public LoginScreen (HashMap<String,String> originalUserInfo, HashMap<String,Person> originalPersonInfo, ArrayList<UserInformation> users, ArrayList<Person> people, ArrayList<Merchandise> items,ArrayList<Transaction> transactions)
	{
		this.users = users;
		this.people = people;
		this.items = items;
		this.transactions = transactions;
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
		
		File file2 = new File("UserInformation.txt");
		if (file2.length() != 0)
		{
			while(inputStream.hasNextLine())
			{
				int userNum = inputStream.nextInt();
				inputStream.nextLine();
				String user = inputStream.nextLine();
				String pass = inputStream.nextLine();
				users.add(new UserInformation(user, pass, userNum));
			}
		}
		userInfo = originalUserInfo;
		personInfo = originalPersonInfo;
		
		welcomeLabel.setBounds(50, 50, 700, 25);
		usernameLabel.setBounds(50, 200, 75, 25);
		passwordLabel.setBounds(50, 250, 75, 25);
		
		messageLabel.setBounds(300, 500, 300, 100);
		messageLabel.setFont(new Font(null, Font.ITALIC, 20));
		
		usernameField.setBounds(125, 200, 200, 25);
		passwordField.setBounds(125, 250, 200, 25);
		
		loginButton.setBounds(150, 400, 100, 25);
		loginButton.setFocusable(false);
		loginButton.addActionListener(this);
		
		registerButton.setBounds(450, 400, 100, 25);
		registerButton.setFocusable(false);
		registerButton.addActionListener(this);
		
		frame.add(welcomeLabel);
		frame.add(usernameLabel);
		frame.add(passwordLabel);
		frame.add(messageLabel);
		frame.add(usernameField);
		frame.add(passwordField);
		frame.add(loginButton);
		frame.add(registerButton);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 800);
		frame.setLayout(null);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == loginButton)
		{
			String username = usernameField.getText();
			String password = String.valueOf(passwordField.getPassword());
			
			if (userInfo.containsKey(username))
			{
				if (userInfo.get(username).equals(password))
				{
					messageLabel.setForeground(Color.LIGHT_GRAY); 
					messageLabel.setText("Successful Login!");
					Menu menu = new Menu(personInfo.get(username), userInfo, personInfo, users, people, items, transactions);
					frame.dispose();
					
				}
				else
				{
					messageLabel.setForeground(Color.RED);
					messageLabel.setText("Error: Incorrect Password");
				}
			}
			else
			{
				messageLabel.setForeground(Color.RED);
				messageLabel.setText("Error: Username not Found!");
			}
		}
		if (e.getSource() == registerButton)
		{
			String newname = usernameField.getText();
			String newpass = String.valueOf(passwordField.getPassword());
			
			if (userInfo.containsKey(newname))
			{
				messageLabel.setForeground(Color.RED); 
				messageLabel.setText("Error: Existing Username");
			}
			else
			{
				messageLabel.setText("New User Created!");
				userInfo.put(newname, newpass);
				users.add(new UserInformation (newname, newpass));
				personInfo.put(newname, new Person(new UserInformation (newname, newpass)));
				people.add(new Person(new UserInformation (newname, newpass)));
				users.get(users.size()-1).enterIntoTextFile(newname, newpass, true);
				people.get(people.size()-1).enterIntoTextFile(true);
				messageLabel.setForeground(Color.LIGHT_GRAY); 
			}
		}
	}

}
