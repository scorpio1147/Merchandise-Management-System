package project;

public class Merchandise {
	
	private double price;
	private Person originalOwner;
	private String name;
	

	public Merchandise (double cost, Person owner, String theName)
	{
		originalOwner = owner;
		price = cost;
		name = theName;
	}
	
	public double getPrice()
	{
		return price;
	}
	
	public Person getOriginalOwner ()
	{
		return originalOwner;
	}
	
	public String getName() 
	{
		return name;
	}
	
	public void changeOwner (Person p)
	{
		originalOwner = p;
	}

}
