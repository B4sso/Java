package poo.gaudiosi.musei;
import java.util.ArrayList;
import poo.gaudiosi.client.*;
public class MuseoS extends Museo{
	public MuseoS(int visitatori)
	{
		super(visitatori);
		entryfee = 25;
		noVisite = 0;
		list = new ArrayList<Visitor>();
	}
	
	public double getFee()
	{
		return entryfee;
	}
	public void setFee(int amount)
	{
		entryfee = amount;
	}
	public void addVisita(Visitor target)
	{
		if (target == null) return;
		if (list.size() >= maxvisitor)
			return;
		list.add(target);
		noVisite = list.size();
		System.out.println(noVisite);
	}
	private double entryfee;
	private ArrayList<Visitor>list;
	
}