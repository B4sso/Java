package classe2.consegna.server;
/*
 * Bookshop consente di istanziare un oggetto di tipo Bookshop che contiene una serie di "Shelves".
 * Ogni Shelf indirizza una serie di copie di un determinato oggetto (in quest'implementazione, un tipo Libro) con il relativo numero disponibile.
 * Il Bookshop puï¿½ essere importato da un file .txt che rispetti la seguente struttura:
 * 	
 * 	DATABASE.TXT
 * 
 * 	Nomelibro (Stringa)
 *  Autore (Stringa)
 *  Casa editrice (Stringa)
 *  Edizione (int)
 *  
 *  Alternativamente lo si puï¿½ generare da un ArrayList di Shelf \ da un solo Shelf, oppure si puï¿½ creare un Bookshop vuoto, per poi
 *  riempirlo. Tutti e tre i casi generano un database: db.txt | db_n.txt, dove n ï¿½ il numero di database presenti in cartella lib (che viene generata
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
			assert (o != null); //O non può essere null
			assert (o instanceof Bookshop); // o deve essere un istanza di Bookshop per essere utilizzata da questo Server.
			Bookshop target = (Bookshop) o;
			ArrayList<String> results = new ArrayList<String>();
			for(Shelf e: target.inventario)
			{
				//Se autore == parametro OPPURE se autore in piccolo contiene il parametro in piccolo
				if (e.getAuthor().equalsIgnoreCase(name) || e.getAuthor().toLowerCase().contains(name.toLowerCase()))
					results.add(e.getTitle());
			}
			return results;
		}
		public ArrayList<String> sortByArgument(Object o,String argument)
		{
				assert (o != null); //O non può essere null
				assert (o instanceof Bookshop); // o deve essere un istanza di Bookshop per essere utilizzata da questo Server.
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
			assert (o != null); //O non può essere null
			assert (o instanceof Bookshop); // o deve essere un istanza di Bookshop per essere utilizzata da questo Server.
			Bookshop target = (Bookshop)o;
			ArrayList<String> results = new ArrayList<String>();
			for (Shelf e: target.inventario)
				if ((e.getCopies()) < amount || (e.getCopies()) == amount && flag) results.add(e.getTitle());
			return results;
		}
		public ArrayList<String> sortByMax(Object o)
		{
			assert (o != null); //O non può essere null
			assert (o instanceof Bookshop); // o deve essere un istanza di Bookshop per essere utilizzata da questo Server.
			Bookshop target = (Bookshop)o;
			ArrayList<String> results = new ArrayList<String>();
			for (Shelf e: target.inventario)
				if ((e.getCopies()) == MAXCOPY ) results.add(e.getTitle());
			return results;
		}
		public void add(Object o,String title, int amount) throws FileNotFoundException
		{
			Bookshop target = (Bookshop)o;
			for(Shelf e : target.inventario)
				if (e.getTitle().equalsIgnoreCase(title))
					{
						int copies = e.getCopies();
						if (copies >= MAXCOPY) return;
						if ((copies + amount) >= MAXCOPY) amount = MAXCOPY;
						e.add(amount);
						target.writeDB();
						return;
					}
		}
	}	
	public Bookshop(File sourceDB) throws IOException,FileNotFoundException
	{
		this.readDB(sourceDB);
		database = sourceDB;
		query = new QueryServer();
		
	}
	public Bookshop()  throws IOException,FileNotFoundException
	{
		this.makeDB();
		query = new QueryServer();
	}

	private void readDB(File input) throws IOException,FileNotFoundException
	{
		inventario = new ArrayList<Shelf>();
		Scanner stream = new Scanner(input);
		while (true)
		{
			if ((stream.nextLine()).equalsIgnoreCase("EOF")) {stream.close(); return;}
			inventario.add(new Shelf(
							stream.nextLine(),	//Titolo							
							stream.nextLine(), //Autore
							stream.nextLine(), //Casa editrice
							Integer.parseInt(stream.nextLine())) //Numero copie
		
							);
		}
	}

	private void writeDB() throws FileNotFoundException
	{
		PrintStream printer = new PrintStream(database);
		for (Shelf e : inventario)
		{
			ArrayList<String>details = e.getDetails();
			printer.println("ENTRY");
			for (String s: details)
				printer.println(s);
			printer.println(e.getCopies());	
		}
		printer.println("EOF");
		printer.close();
	}
	
	private void makeDB() throws IOException,FileNotFoundException
	{
		new File("lib").mkdir();
		int i;
		File temp = new File (PATH+EXT);
		if (!(temp.createNewFile()))
		{
			for (i = 1;i<=MAXATTEMPT;i++)
			{
				temp = new File(PATH+Integer.toString(i)+EXT);
				if ((temp.createNewFile()))
					break;
			}
			if (i > MAXATTEMPT) database = null;
		}
		database = temp;
		return;
	}
	public QueryServer call()
	{
		return query;
	}

	private QueryServer query;
	private ArrayList<Shelf> inventario;
	private File database;
	private static String PATH = "lib/db"; 
	private static String EXT = ".txt";
	private final static int MAXCOPY = 44;
	private final static int MAXATTEMPT = 10;
}