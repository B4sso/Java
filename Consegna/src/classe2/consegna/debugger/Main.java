package classe2.consegna.debugger;
import classe2.consegna.server.*;
import java.util.*;
import java.io.*;

public class Main{
	public static void main(String[]args) throws IOException,FileNotFoundException
	{
		Bookshop bookshop = new Bookshop(new File("lib\\db.txt")); //Istanzia un Bookshop leggendo da db.txt
		Query query = bookshop.call(); //Invoca il server delle queries per il tipo Bookshop 
		printer("Ricerca - Autore: CAMILLERI",query.sortByAuthor(bookshop,"Camilleri"));
		System.out.println();
		printer("Ricerca - Contiene:'sogni'",query.sortByArgument(bookshop,"sogni"));
		System.out.println();
		printer("Ricerca - Inventario pieno",query.sortByMax(bookshop));
		System.out.println();
		printer("Ricerca - Meno di 15 copie",query.sortByLess(bookshop,15,false));
		query.add(bookshop,"Sogni Rossi",30);
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