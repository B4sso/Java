package poo.gaudiosi.musei;
import poo.gaudiosi.client.*;

public abstract class Museo{
	public Museo(int amount)
	{
		if (amount < 0)
			throw new IllegalArgumentException(Integer.toString(amount)+" has to be >= 0");
		maxvisitor = amount;
		id = -1;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public int getId ()
	{
		return id;
	}
	public int getVisite()
	{
		return noVisite;
	}
	
	public abstract String toString();
	public abstract void addVisita(Visitor visitor);
	
	 int id;
	 int maxvisitor;
	 int noVisite;
	
}