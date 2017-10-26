import java.util.*;

public class BankAccount implements Value{

	public BankAccount(int amount)
	{
		id = rng.nextInt(MAX) + 1;
		this.amount = amount;
	}
	
	public BankAccount()
	{
		id = rng.nextInt(MAX) + 1;
		this.amount = 0;
	}
	
	public double getVal()
	{
		return amount;
	}
	
	public int getID()
	{
		return id;
	}
	
	

static private final int MAX = 100;
static private Random rng = new Random();
private int id;
private double amount;


}
