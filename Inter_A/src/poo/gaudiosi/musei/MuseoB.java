package poo.gaudiosi.musei;
import poo.gaudiosi.client.*;
import java.util.ArrayList;
public class MuseoB extends Museo{
	public MuseoB(int visitatori)
	{
		super(visitatori);
		noVisite = 0;
		activities = new ArrayList<String>();
		activities.add(DEFAULTACTIVITY);
	}
	
	public void addVisita(Visitor visitor)
	{
		if (visitor == null)return;
		if (noVisite >= maxvisitor)
			return;
		noVisite++;
		visitor.addActivity(DEFAULTACTIVITY);
	}
	public void addActivity(String target)
	{
		String tmp = target.trim().toLowerCase();
		if (activities.indexOf(tmp) == -1)
			activities.add(tmp);
	}
	public String toString()
	{
		if (id == -1 ) return "MUSEOBORBO:"+"[UNKNOWN]"+maxvisitor;
		return "MUSEOBORBO:"+"["+id+"]" + maxvisitor;
	}
	private int noVisite;
	private ArrayList<String>activities;
	
	public static final String DEFAULTACTIVITY = "Visita";
}