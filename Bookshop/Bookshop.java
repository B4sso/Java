import java.io.*;
import java.util.*;
public class Bookshop{
	public Bookshop(ArrayList<Shelf> batch)
	{
		new File("lib").mkdir();
		File temp;
		if (!(temp = File(PATH+EXT).createNewFile()))
		{
			for (int i = 1;i<=10;i++)
				if ((temp = File(PATH+Integer.toString(i)+EXT).createNewFile()))
					break;
			if (i > 10) return;
		}
		database = temp;
		PrintStream printer = new PrintStream(database);
		inventario = batch.clone();
		batch.removeAll();
		for (int j = 0; j < inventario.length(); j++)
		{
			String[]details = inventario[j].getDetails();		
			for (int i = 0; i<details.length(); i++)
				printer.println(details[i]);
			printer.println(inventario[j].getCopies());	
		}	
	}
	
	public Bookshop(File sourceDB)
	{
		database = sourceDB;
		Scanner stream = new Scanner(database);
		while (stream.hasNextLine())
			inventario.add(new Item(
							stream.nextLine(),	//Titolo							
							stream.nextLine(), //Autore
							stream.nextLine()) //Editore
							Integer.parseInt(stream.nextLine()); //Edizione
						  );
	}
	
	private ArrayList<Shelf> inventario;
	private File database;
	private static String PATH = "lib/db"; 
	private static String EXT = ".txt";
	private static int MAXATTEMPT = 10;
}