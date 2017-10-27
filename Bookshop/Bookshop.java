/*
 * Bookshop consente di istanziare un oggetto di tipo Bookshop che contiene una serie di "Shelves".
 * Ogni Shelf indirizza una serie di copie di un determinato oggetto (in quest'implementazione, un tipo Libro) con il relativo numero disponibile.
 * Il Bookshop pu� essere importato da un file .txt che rispetti la seguente struttura:
 * 	
 * 	DATABASE.TXT
 * 
 * 	Nomelibro (Stringa)
 *  Autore (Stringa)
 *  Casa editrice (Stringa)
 *  Edizione (int)
 *  
 *  Alternativamente lo si pu� generare da un ArrayList di Shelf \ da un solo Shelf, oppure si pu� creare un Bookshop vuoto, per poi
 *  riempirlo. Tutti e tre i casi generano un database: db.txt | db_n.txt, dove n � il numero di database presenti in cartella lib (che viene generata
 *  se assente).
 *  
 *	COSTRUTTORI:
 *	|Bookshop(<Shelf>) -> Restituisce un Bookshop con <Shelf> elementi e database: db.txt | db_n.txt.
 *	|Bookshop(File) -> Restiuisce un Bookshop di File.<Shelf> elementi e database: File
 *	|Bookshop(Void) -> Restuisce un Bookshop di 0 elementi e database: db.txt | db_n.txt 
 *
 *	METODI:
 *	|lookAuthor(Nome) -> Restituisce tutti gli oggetti con Autore == Nome
 *	|look(Stringa) -> Restituisce tutti gli oggetti il cui Titolo contengano Stringa
 *	|lookCopy(amount,flag) -> Restituisce tutti gli oggetti le cui sono <= amount o >= amount se !flag o flag
 *	|addBook(Title,amount) -> Aggiunge amount copie allo Shelf di Titolo == Title
 * 	
 */


import java.io.*;
import java.util.*;

public class Bookshop{
	class QueryServer implements Query
	{
		public ArrayList<String> sortByAuthor(Object o,String name)
		{
			Bookshop target = (Bookshop) o;
			ArrayList<String> results = new ArrayList<String>();
			for(Shelf e: target.inventario)
			{
				ArrayList<String>details = e.getDetails();
				if (details.get(AUTORE).equalsIgnoreCase(name))
					results.add(details.get(TITOLO));
			}
			return results;
		}
		public ArrayList<String> sortByArgument(Object o,String argument)
		{
				Bookshop target = (Bookshop)o;
				ArrayList<String> results = new ArrayList<String>();
				for(Shelf e: target.inventario)
				{
					if (e.getTitle().toLowerCase().contains(argument.toLowerCase()))
						results.add(e.getTitle());
				}
				return results;
		}
		public ArrayList<String> sortByLess(Object o, int amount, Boolean flag)
		{
			Bookshop target = (Bookshop)o;
			ArrayList<String> results = new ArrayList<String>();
			for (Shelf e: target.inventario)
				if ((e.getCopies()) < amount || (e.getCopies()) == amount && flag) results.add(e.getTitle());
			return results;
		}
		public ArrayList<String> sortByMore(Object o, int amount, Boolean flag)
		{
			Bookshop target = (Bookshop)o;
			ArrayList<String> results = new ArrayList<String>();
			for (Shelf e: target.inventario)
				if ((e.getCopies()) > amount || (e.getCopies()) == amount && flag) results.add(e.getTitle());
			return results;
		}
		public void add(Object o,String title, int amount)
		{
			Bookshop target = (Bookshop)o;
			for(Shelf e : target.inventario)
				if (e.getTitle().equalsIgnoreCase(title))
					{
						e.add(amount);					
						return;
					}
		}
	}	
	public Bookshop(File sourceDB) throws IOException,FileNotFoundException
	{
		this.readDB(sourceDB);
		database = sourceDB;
	}
	public Bookshop()  throws IOException,FileNotFoundException
	{
		this.makeDB();
	}

	private void readDB(File input) throws IOException,FileNotFoundException
	{
		Scanner stream = new Scanner(database);
		while (stream.hasNextLine())
			inventario.add(new Shelf(
							stream.nextLine(),	//Titolo							
							stream.nextLine(), //Autore
							stream.nextLine(), //Casa editrice
							Integer.parseInt(stream.nextLine())) //Numero copie
						  );	
	}
	private void writeDB() throws FileNotFoundException
	{
		PrintStream printer = new PrintStream(database);
		for (Shelf e : inventario)
		{
			ArrayList<String>details = e.getDetails();		
			for (String s: details)
				printer.println(s);
			printer.println(e.getCopies());	
		}	
	}
	
	private void makeDB() throws IOException,FileNotFoundException
	{
		new File("lib").mkdir();
		int i;
		File temp = new File (PATH+EXT);
		if (!(temp.createNewFile()))
		{
			for (i = 1;i<=10;i++)
			{
				temp = new File(PATH+Integer.toString(i)+EXT);
				if ((temp.createNewFile()))
					break;
			}
			if (i > 10) database = null;
		}
		database = temp;
		return;
	}

	
	private ArrayList<Shelf> inventario;
	private File database;
	private static String PATH = "lib/db"; 
	private static String EXT = ".txt";
	private static int TITOLO = 0;
	private static int AUTORE = 1;
	private static int CASAED = 2;
	private static int MAXATTEMPT = 10;
}