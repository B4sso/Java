package poo.gaudiosi.musei;
import poo.gaudiosi.client.*;

public abstract class Museo{
	public Museo(int amount)
	{
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
	public String toString()
	{
		if (id == -1 ) return "MUSEO:"+"[UNKNOWN]"+maxvisitor;
		return "MUSEO:"+"["+id+"]" + maxvisitor;
	}
	public abstract void addVisita(Visitor visitor);
	
	 int id;
	 int maxvisitor;
	 int noVisite;
	
}