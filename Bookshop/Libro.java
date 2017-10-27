import java.util.*;
import java.io.*;
/*
 * Libro ci permette di istanziare un oggetto Libro che contiene:
 * (n.b. Stringa formattata = Stringa senza spazi vuoti iniziali e finali, non vuota, di len > 3 e di carattere iniziale in maiusc)
 * 	 |AUTORE : (Stringa formattata)
 *   |TITOLO : (Stringa formattata)
 *   |EDITORE :(Stringa formattata)
 *   
 *   
 */
public class Libro implements Item{
	
	public Item newItem(String titolo, String autore, String editore)
	{
		return new Libro (autore,titolo,editore);
	}
	
	
	public Libro(String autore,String titolo, String editore)
	{
		this.autore = formats(autore);
		this.titolo = formats(titolo);
		this.editore = formats(editore);
	}
	public String[] getDetails()
	{
		String[]details = {autore,titolo,editore};
		return details;
	}
	
	static private String formats(String target)
	{
		/*
		 * "  fRanCeSco " -> "Francesco"
		 */
		String tmp = target.trim();
		if(tmp.length() < 1) return "NaName";
		return Character.toString(tmp.charAt(0)).toUpperCase() + tmp.substring(1,tmp.length().toLowerCase());
		
	}
	
	private String autore;
	private String titolo;
	private String editore;
}
