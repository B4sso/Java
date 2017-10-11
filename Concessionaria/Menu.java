import java.util.Scanner;
import java.io.IOException;
/*
	Questo "menu" mette a disposizione un ambiente su cui far lavorare le classi Account e Car
	ed un'interfaccia console con cui interagire.

*/



public class Menu {	
	private static class dbCar{
		/*
			dbCar => DATABASE CAR
			Sostanzialmente una classe di supporto per realizzare un database temporaneo.
			L'istanza di classe contiene;
								|ENTITY ->RIF @CAR 
								|AMOUNT -> INT (Quantità virtuale di ENTITY disponibili)
								
			Solo i costruttori permettono la modifica delle variabili d'istanza.
			
		*/
		
		//COSTRUTTORE DI BASE
		//Crea una nuova istanza CAR e ne virtualizza un'unità.
		public dbCar()
		{
			entity = new Car();
			amount = 1;
		}
		
		//COSTRUTTORE ALTERNATIVO
		//Associa ed ENTITY un rif @CAR e virtualizza n unità se n > 0, altrimenti 0
		public dbCar(Car user_car, int n)
		{
			entity = user_car;
			amount = (n < 0)?(0):(n);
		}
		/////////////////////////////////////////////////////

		//METODI PUBBLICI PROPRI
		//Restituiscono le variabili d'istanza
		public int getdbAmount()
		{
			return amount;
		}
		public Car getdbEntity()
		{
			return entity;
		}
		
		/////METODI PUBBLICI IMPROPRI
		//Sostanzialmente riflessi dei metodi di CAR
		public String getdbType()
		{
			return entity.getType();
		}
		public void sumAmount (int n)
		{
			int temp = amount;
			if ((temp + n )<= 0) {amount = 0;}
		}
		
		//METODI PUBBLICI DB
		//Chiamata  caller.isPresent(dbCar[] DATABASE)
		// Controlla se in DATABASE esiste un oggetto di tipo dbCar con TYPE == TYPE di CALLER
		public int isPresent(dbCar[] db)
		{
			int i;
			for (i=0; i<10;i++)
			{
				if (db[i] == null) break; //Le posizioni dell'array database, dopo la scrittura, rimarranno comunque inizializzate.
											//Se l'array è vuoto, il ciclo si interromperà alla prima iterazione - se un rif. null è incontrato, l'elemento non sarà presente nel database.
											//L'array non ammette spazi vuoti intervallati.
											
				if (this.getdbType() == db[i].getdbType()) {return i;}
			}
			return (i == 0)?(-12):(-i);
			
			
		}		
		Car entity;
		int amount;
	}
	
	public static void main(String[] args) throws IOException,InterruptedException
	{
		getCommand();
		invokeMenu("Concessionaria");	
	}
	
	private static void invokeMenu(String line) throws IOException,InterruptedException
	{	
	
	do { //Il menu è l'iterazione dello stesso ciclo.
		clear.inheritIO().start().waitFor();
		System.out.println(line);
		System.out.println("OPZIONI:");
		spawnOptions(false,options_count);
	}while (true);

	}
	
	private static void spawnOptions(Boolean flushScreen,int n) throws IOException,InterruptedException
	{
		int i;
		if (flushScreen) {clear.inheritIO().start().waitFor();}
		System.out.println();
		for (i=0; i<n;i++)
		{
			System.out.format("[%d]%s%n",i+1,options[i]);		//ITERATORE DELL'OPZIONE
		}
		switch(select()) 
		{ //Sostanzialmente i CASE sono sub-metodi
		 //E' possibile un'implementazione modulare tramite il java.reflective
		//Se capissi come fare, sarebbe figo.
			case 1:
			addEntry(new Account("Pierpablo","Verouomo",carDB[addEntryCar(-1)].getdbEntity())); //Non è stato implementato un input per il nome\cognome utente perchè irrilevante allo scopo dell'esercizio.
			break;	
			
			case 2:
			if(addEntryCar(1) == -1) System.exit(0);	
			break;
			
			case 3:
			int db_index;
			String marchio = select("Marchio");
			String modello = select("Modello");
			if ((db_index = hasModel(modello)) >= 0)
			{
				System.out.println();
				System.out.format("Autonomia massima: %d%n",carDB[db_index].getdbEntity().maxCapacity());
				Thread.sleep(4000);
				break;
			}
			System.out.println();
			System.out.println("Modello inesistente");
			break;
			
			case 4:
			System.exit(0);
			
			default:
			System.exit(0);
		}
	}
	
	private static void addEntry(Account entry)
	{
		if (entry == null || db_pointer >= 10)
		{
			System.out.println("CONCESSIONARIA: Database non aggiornato!");
			return;
		}
		userDB[db_pointer] = entry;
		db_pointer ++;
	}
	
	
	private static int select()
	{
		System.out.println();
		System.out.print("Seleziona l'opzione desiderata: ");
		return Integer.parseInt(keyboard.nextLine());
		 //SCANNER.NEXTINT() non consuma il carattere newline. Qualsiasi cosa dopo la chiamate nextint, se utilizzata con lo scanner, verrà saltata.
							   //Bisogna far in modo che la prima scansione comprenda anche il newline - e poi convertire tutto in int.
	}
	
	private static String select(String line)
	{
		System.out.println();
		System.out.print(line + ": ");
		return keyboard.nextLine();	
	}
	
	private static ProcessBuilder getCommand() //Compatibilità dei comandi. La WIN-Powershell ed il terminale Linux condividono il "clear", il win-commandprompt richiede il "cls".
	{
		if (OS.getVer() == "win")return new ProcessBuilder("cmd", "/c", "cls");
		else return new ProcessBuilder("clear");
	}
	
	private static int addEntryCar(int n)
	{
		if (car_pointer >= 10)
		{
			System.out.println("Dimensione massima raggiunta");
			return -1;
		}
		int db_index;
		dbCar temp = new dbCar(new Car(),n);
		if ((db_index = temp.isPresent(carDB)) >= 0) 
		{
			carDB[db_index].sumAmount(n);
			return db_index;
		}

		db_index = (db_index == -12)?(0):(db_index*-1);
		carDB[db_index] = temp;
		car_pointer ++;
		return db_index;
		
	}
	private static int hasModel(String model)
	{
		int i;
		for (i=0; i<10;i++)
		{
			if (carDB[i] == null) break;
			if (model.equals(carDB[i].getdbType())) {return i;}
		}
		return -1;
	}
	
	private static final String[]options = {"Nuovo Cliente","Nuovo Mezzo","Calcola autonomia","Esci"}; //Titolo opzioni
	private static final int options_count = 4; //Conta delle opzioni
	
	private static final ProcessBuilder clear = getCommand(); //Variabile utilizzata come riferimento all'oggetto processbuilder coerente con l'SO.
	private static int db_pointer = 0; //Indice database utenti
	private static int car_pointer = 0;//Indice database veicoli
	private static Account[] userDB = new Account[10]; //DB Utenti
	private static dbCar[] carDB = new dbCar[10]; //DB Veicoli
	private static final Scanner keyboard = new Scanner(System.in); //Stream d'ingresso
	
	
}