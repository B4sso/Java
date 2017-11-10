package it.java.lottomatica.client;
import java.util.Random;
import java.util.ArrayList;
import it.java.lottomatica.gioco.*;

public class Client{
	public Client()
	{
		cf = new Random().nextInt(4231)+50000;
		cash = 0;
	}
	public void addCash(double amount)
	{
		cash = cash + amount;
	}
	public void removeCash(double amount)
	{
		if (cash <= 0) return;
		cash = cash - amount;
	}
	public void buyATicket()
	{
		if (cash >= Ambata.TICKETCOST)
			cash = cash - Ambata.TICKETCOST;
		tickets.add(new Ambata());
	}
	public void buyCTicket()
	{
		if (cash >= CartaAlta.TICKETCOST)
			cash = cash - CartaAlta.TICKETCOST;
		tickets.add(new CartaAlta());
	}
	public String toString()
	{
		String tickets = Integer.toString(cf) + Double.toString(cash);
		for (Gioco e: tickets)
			tickets + e.toString();
		
	}
	
	ArrayList<Gioco> tickets;
	private int cf;
	private double cash;
}