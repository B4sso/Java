package classe2.consegna.server;
import java.util.*;
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
		return new Libro (titolo,autore,editore);
	}
	
	
	public Libro(String titolo,String autore, String editore)
	{
		this.autore = formats(autore);
		this.titolo = formats(titolo);
		this.editore = formats(editore);
	}
	public String getTitle()
	{
		return titolo;
	}
	public String getAuthor()
	{
		return autore;
	}
	public ArrayList<String> getDetails()
	{
		ArrayList<String> details = new ArrayList<String>();
		details.add(titolo);
		details.add(autore);
		details.add(editore);
		return details;
	}
	
	static private String formats(String target)
	{
		/*
		 * "  fRanCeSco " -> "Francesco"
		 */
		String tmp = target.trim();
		if(tmp.length() < 1) return "NaName";
		tmp = Character.toString(tmp.charAt(0)).toUpperCase() + tmp.substring(1,tmp.length()).toLowerCase();
		return tmp;
		
	}
	
	private String autore;
	private String titolo;
	private String editore;
}
