package poo.gaudiosi.client;
import java.util.ArrayList;
public class Visitor{
	public Visitor(String iCf,String iMail)
	{
		cf = iCf;
		mail = iMail;
		amount = 0;
		activities = new ArrayList<String>();
	}
	public void addActivity(String target)
	{
		String tmp = target.trim().toLowerCase();
		if (activities.indexOf(tmp) == -1)
			activities.add(tmp);
	}
	public void addFee(double toadd)
	{
		amount = amount+toadd;
	}
	private String cf;
	private String mail;
	private double amount;
	ArrayList<String>activities;
}