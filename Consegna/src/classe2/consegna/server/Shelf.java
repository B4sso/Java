package classe2.consegna.server;
import java.util.*;
public class Shelf{
	
	public Shelf(String titolo, String autore, String publisher,int amount)
	{
		reference = new Libro(titolo,autore,publisher); /*
														*E, per adesso, questo livello di riutilizzabilità è accettabile, dato che non
														*occorre gestire più tipi item.
														*/							
		copies = amount;
	}
	
	public void clear()
	{
		copies = 0;
	}
	public void add()
	{
		copies++;
	}
	public void add(int amount)
	{
		copies = copies+amount;
	}
	public void remove()
	{
		copies--;
	}
	public ArrayList<String> getDetails()
	{
		return reference.getDetails();
	}
	public String getTitle()
	{
		return reference.getTitle();
	}
	public String getAuthor()
	{
		return reference.getAuthor();
	}
	public int getCopies()
	{
		return copies;
	}
	
	private Item reference;
	private int copies;
	
	
}
