package project;

public class Transaction {
	
	private Person customer;
	private Person seller;
	private Merchandise item;
	
	public Transaction (Person buyer, Person vendor, Merchandise good)
	{
		customer = buyer;
		seller = vendor;
		item = good;
	}
	
	public Person getCustomer ()
	{
		return customer;
	}
	
	public Person getSeller ()
	{
		return seller;
	}
	
	public Merchandise getItem ()
	{
		return item;
	}


}
