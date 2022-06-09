package project;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class CompleteInventory implements ActionListener {
	
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
	
	public CompleteInventory (Person person, HashMap<String,String> originalUserInfo, HashMap<String,Person> originalPersonInfo, ArrayList<UserInformation> users,  ArrayList<Person> people, ArrayList<Merchandise> items, ArrayList<Transaction> transactions)
	{
		this.person = person;
		userInfo = originalUserInfo;
		personInfo = originalPersonInfo;
		this.users = users;
		this.people = people;
		this.transactions = transactions;
		String[] columnHeaders = {"Owner", "Item Type", "Item Name", "Item Cost"};
		Object [][] data = new Object [items.size()][4];
		for (int i = 0; i<data.length; i++)
		{
			data[i][0] = items.get(i).getOriginalOwner().getLoginInfo().getUsername();
			if (items.get(i).getClass().equals(Shoe.class))
			{
				data[i][1] = "shoe";
			}
			else if (items.get(i).getClass().equals(Shirt.class))
			{
				data[i][1] = "shirt";
			}
			data[i][2] = items.get(i).getName();
			data[i][3] = "$" + String.format("%.2f", items.get(i).getPrice());
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
		welcomeLabel.setText("Complete Inventory:");
		
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
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == backButton)
		{
			frame.dispose();
		}
		
	}

}
