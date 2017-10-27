import java.util.*;
import java.io.*;
/*
 * Shelf ci permette di istanziare un oggetto contenitore composto da:
 * 		|RIFERIMENTO:  RIF. ITEM (Riferimento ad un oggetto. L'originale.)
 * 		|COPIES: INT (Numero di copie in riferimento all'oggetto in RIFERIMENTO)
 */

public class Shelf{
	
	public Shelf(Item item, int amount)
	{
		reference = item;
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
	public int getCopies()
	{
		return copies;
	}
	
	private Item reference;
	private int copies;
	
	
}
