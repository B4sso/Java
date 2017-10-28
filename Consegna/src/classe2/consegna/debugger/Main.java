package classe2.consegna.debugger;
import classe2.consegna.server.*;
import java.util.*;
import java.io.*;

public class Main{
	public static void main(String[]args) throws IOException,FileNotFoundException
	{
		Bookshop bookshop = new Bookshop(new File("lib\\db.txt")); //Istanzia un Bookshop leggendo da db.txt
		Query query = bookshop.call(); //Invoca il server delle queries per il tipo Bookshop 
		printer("Ricerca - Autore: CAMILLERI",query.sortByAuthor(bookshop,"Camilleri")); //Cerca tutti i libri di Camilleri
		System.out.println();
		printer("Ricerca - Contiene:'sogni'",query.sortByArgument(bookshop,"sogni")); //Cerca tutti i libri con titolo contenente "sogni"
		System.out.println();
		printer("Ricerca - Inventario pieno",query.sortByMax(bookshop)); //Cerca tutti libri le cui copie abbiano raggiunto la capacità massima consentita
		System.out.println();
		printer("Ricerca - Meno di 15 copie",query.sortByLess(bookshop,15,false)); //Cerca tutti i libri le cui copie siano < 15
		query.add(bookshop,"Sogni Rossi",30); //Aggiunge al libro "Sogni Rossi" 30 copie
												//Se le copei di "Sogni Rossi" sono a capacità, o l'ammontare immesso + copie preesistenti > capacità
												//Il numero delle copie verrà aggiornato alla capacità massima consentita.
	}
	private static void printer(String title,ArrayList<String> param)
	{
		System.out.println(title);
		for(String s : param)
		{
			System.out.println("Libro: " + s);	
		}
	}	
}