/*
	La classe istanzia un oggetto di tipo "car" costituito da: 
					| BRAND
					| TYPE
					| CAPACITY
					| KPL

	
	La classe definisce i seguenti metodi pubblici:

	getBrand (VOID) -> BRAND
	getCapacity(VOID) -> CAPACITY
	getKPL(VOID) -> KPL
	maxCapacity(VOID) -> N INTERO ( MAX NAVIGABILE @SERBATOIO PIENO)
*/
import java.util.Random;
import java.util.Scanner;

public class Car {
	
	public Car()
	{
		brand = inputLine("marca");
		type = inputLine("modello");
		capacity = randomizer();
		kpl = randomizer();
	}
	
	
////// FUNZIONI PUBBLICHE
//Un istanza di Car ammette modifiche alle variabili d'istanza solo nel costruttore.
//Teoricamente l'operatore nella "CONCESSIONARIA" non dovrebbe essere in grado di manomettere le statistiche delle auto.
	public String getType()
	{
		return type;	
	}
	public String getBrand()
	{
		return brand;	
	}
	public int getKPL()
	{
		return kpl;	
	}
	public int getCapacity()
	{
		return capacity;
	}
				
	/*Restituisce il massimo navigabile a serbatoio pieno*/
	public int maxCapacity()
	{
		return this.getKPL() * this.getCapacity();
	}
	
	
//////////FUNZIONI FORMATTAZIONE STRINGA	
	private static String inputLine(String line)
	{
		String temp;
		do  {
				System.out.print("Imposta " + line +": ");
				temp = keyboard.nextLine();
			} while ((temp = formatTarget(temp)).equals(STRING_FORMAT_ERROR));
		return temp;
	}

	private static String formatTarget(String target)
	{	
		target = target.trim();
		if (target.length() <= 1)	
		{
			System.out.println();
			System.out.println("CARS: Input non valido");
			return STRING_FORMAT_ERROR;			
		}
		target = target.toLowerCase();
		return Character.toString(target.charAt(0)).toUpperCase() + target.substring(1,target.length());
	}
////////////////////////////////////////////


/////////////RNG
	//Dato che si parla di un targa anche una funzione hash andrebbe bene.
	private static int randomizer()
	{
		Random rng = new Random();
		return rng.nextInt(100);	
	}
///////////////////

	private static final String STRING_FORMAT_ERROR = "err"; //Evitiamo di istanziare un "err" ad ogni invocazione
	private static final Scanner keyboard = new Scanner(System.in); //Valida alternativa al generare riferimenti allo stream per ogni invocazione delle funzioni I\O. Spero. 
	private int capacity;
	private int kpl;
	private String brand;
	private String type;
	
	
	
}