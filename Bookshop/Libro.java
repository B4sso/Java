import java.util.*;
import java.io.*;
/*
 * Libro ci permette di istanziare un oggetto Libro che contiene:
 * 	 |AUTORE : (Stringa)
 *   |TITOLO : (Stringa)
 *   |EDITORE :(Stringa)
 *   |EDIZIONE :(Intero che rappresenta un'edizione diversa dello stesso libro virtuale)
 *   
 */
public class Libro implements Item{
	
	public Item(String titolo, String autore, String editore, int edizione)
	{
		return new Libro (autore,titolo,editore,edizione);
	}
	
	
	public Libro(String autore,String titolo, String editore, int edizione)
	{
		this.autore = formats(autore);
		this.titolo = formats(titolo);
		this.editore = formats(editore);
		this.edizione = edizione;
	}
	public String[] getDetails()
	{
		String[4]details = {autore,titolo,editore,Integer.toString(edizione)};
		return details;
	}
	
	private String autore;
	private String titolo;
	private String editore;
	private int edizione;
}