/*
 * Bookshop consente di istanziare un oggetto di tipo Bookshop che contiene una serie di "Shelves".
 * Ogni Shelf indirizza una serie di copie di un determinato oggetto (in quest'implementazione, un tipo Libro) con il relativo numero disponibile.
 * Il Bookshop può essere importato da un file .txt che rispetti la seguente struttura:
 * 	
 * 	DATABASE.TXT
 * 
 * 	Nomelibro (Stringa)
 *  Autore (Stringa)
 *  Casa editrice (Stringa)
 *  Edizione (int)
 *  
 *  Alternativamente lo si può generare da un ArrayList di Shelf \ da un solo Shelf, oppure si può creare un Bookshop vuoto, per poi
 *  riempirlo. Tutti e tre i casi generano un database: db.txt | db_n.txt, dove n è il numero di database presenti in cartella lib (che viene generata
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
	public Bookshop(ArrayList<Shelf> batch)
	{
		this.makeDB();
		inventario = batch.clone();
		this.writeDB();
		batch.removeAll();
	}
	
	public Bookshop(File sourceDB)
	{
		this.readDB(sourceDB);
		database = sourceDB;
	}
	public Bookshop(){}
	
	private void readDB(File input)
	{
		Scanner stream = new Scanner(database);
		while (stream.hasNextLine())
			inventario.add(new Item(
							stream.nextLine(),	//Titolo							
							stream.nextLine(), //Autore
							stream.nextLine()) //Casa editrice
						  );	
	}
	private void writeDB()
	{
		PrintStream printer = new PrintStream(database);
		for (int j = 0; j < inventario.length(); j++)
		{
			String[]details = inventario[j].getDetails();		
			for (int i = 0; i<details.length(); i++)
				printer.println(details[i]);
			printer.println(inventario[j].getCopies());	
		}	
	}
	
	private void makeDB()
	{
		new File("lib").mkdir();
		File temp;
		if (!(temp = File(PATH+EXT).createNewFile()))
		{
			for (int i = 1;i<=10;i++)
				if ((temp = File(PATH+Integer.toString(i)+EXT).createNewFile()))
					break;
			if (i > 10) database = null;
		}
		database = temp;
		return;
	}
	public ArrayList<Item> lookAuthor(String name)
	{
		for(Item e: inventario)
			if (name.compareToIgnoreCase())
			
		
		
	}
	
	public ArrayList<Item> look(String target)
	{}
	
	public ArrayList<Item> lookCopy(int amount, Boolean flag)
	{}

	public addBook(String title, int amount)
	{
		
		
	}
	
	
	
	private ArrayList<Shelf> inventario;
	private File database;
	private static String PATH = "lib/db"; 
	private static String EXT = ".txt";
	private static int MAXCPY = 60;
	private static int WARNING = 10;
	private static int MAXATTEMPT = 10;
}