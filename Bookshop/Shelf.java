import java.util.*;
import java.io.*;
/*
 * Shelf ci permette di istanziare un oggetto contenitore composto da:
 * 		|RIFERIMENTO:  RIF. ITEM (Riferimento ad un oggetto. L'originale.)
 * 		|COPIES: INT (Numero di copie in riferimento all'oggetto in RIFERIMENTO)
 */
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
	public int getCopies()
	{
		return copies;
	}
	
	private Item reference;
	private int copies;
	
	
}
