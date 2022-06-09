package project;

import java.util.*;
import java.io.*;

public class UserInformation {
	
	public static HashMap <String,String> userInfo = new HashMap<String,String>();
	private int userNumber;
	public static int numberOfUsers = 0;
	private String username;
	private String password;
	
	public UserInformation()
	{
		this.username = "username";
		this.password = "password";
		userInfo.put("username", "password");
		++numberOfUsers;
		userNumber = numberOfUsers;
	}
	
	public UserInformation(String username, String password)
	{
		this.username = username;
		this.password = password;
		readTextFile();
		userInfo.put(username, password);
		++numberOfUsers;
		userNumber = numberOfUsers;
	}
	
	public UserInformation(String username, String password, int num)
	{
		this.username = username;
		this.password = password;
		userNumber = num;
	}
	
	public void enterIntoTextFile (String username, String password, boolean append)
	{
		if (append)
		{
			PrintWriter outputStream = null;
			try
			{
				outputStream = new PrintWriter (new FileOutputStream("UserInformation.txt", true));
			
			}
			catch (FileNotFoundException e)
			{
				System.out.println ("Error opening the UserInformation file");
				System.exit(0);
			}
			outputStream.println(userNumber);
			outputStream.println(username);
			outputStream.println(password);
			outputStream.close();
		}
		else
		{
			PrintWriter outputStream = null;
			try
			{
				outputStream = new PrintWriter (new FileOutputStream("UserInformation.txt"));
			
			}
			catch (FileNotFoundException e)
			{
				System.out.println ("Error opening the UserInformation file");
				System.exit(0);
			}
			outputStream.println(userNumber);
			outputStream.println(username);
			outputStream.println(password);
			outputStream.close();
		}
	}
	
	public void readTextFile ()
	{
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
		int count = 0;
		while(inputStream.hasNextLine())
		{
			inputStream.nextLine(); // go to the next line
			count++;
		}
		numberOfUsers = count/3;
		inputStream.close();
	}
	
	public HashMap<String, String> getUserInfo ()
	{
		return userInfo;
	}
	
	public void setUserInfo (HashMap<String, String> updated)
	{
		userInfo = updated;
	}
	
	public int getUserNumber ()
	{
		return userNumber;
	}
	
	public void setUserNumber (int n)
	{
		userNumber = n;
	}
	
	public int getNumberOfUsers ()
	{
		return numberOfUsers;
	}
	
	public void setNunberOfUsers (int n)
	{
		numberOfUsers = n;
	}
	
	public String getUsername ()
	{
		return username;
	}
	
	public String getPassword ()
	{
		return password;
	}
	
	public void setUsername (String u)
	{
		username = u;
	}
	
	public void setPassword (String p)
	{
		password = p;
	}
	
	

}
