import java.util.Scanner;
import java.io.IOException;
import java.lang.Boolean;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Menu {		
	public static void main(String[] args) throws IOException,InterruptedException
	{
		getCommand();
		invokeMenu("SEGRETERIA UNI-AYYLMAO-SA");	
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
			if (addEntry() < 0) System.exit(0);
			break;
			
			case 2:
			compareYear();
			Thread.sleep(4000);
			break;
			
			case 3:
			System.out.format("%nEsami rimanenti: [%d]",remainingEx());
			Thread.sleep(4000);
			break;
			
			case 4:
			System.exit(0);
			
			default:
			System.exit(0);
		}
	}
	
	private static int select()
	{
		System.out.println();
		System.out.print("Seleziona la voce desiderata: ");
		return Integer.parseInt(keyboard.nextLine());
		 //SCANNER.NEXTINT() non consuma il carattere newline. Qualsiasi cosa dopo la chiamate nextint, se utilizzata con lo scanner, verrà saltata.
							   //Bisogna far in modo che la prima scansione comprenda anche il newline - e poi convertire tutto in int.
	}
	
	private static String select(String line,Boolean val)
	{
		while(true)
		{
			String tmp;
			System.out.println();
			System.out.print(line + ": ");
			tmp=keyboard.nextLine();
			if (!val) return tmp;
			if ((tmp=trimTarget(tmp)) != null) return tmp;
			System.out.println("Input non valido");		
		}
		
	}
	private static String trimTarget(String target)
	{	
		String tmp = target.trim();
		if (target.length() <= 1) return null;
		return tmp;
	}
	
	private static ProcessBuilder getCommand() //Compatibilità dei comandi. La WIN-Powershell ed il terminale Linux condividono il "clear", il win-commandprompt richiede il "cls".
	{
		if (OS.getVer() == "win")return new ProcessBuilder("cmd", "/c", "cls");
		else return new ProcessBuilder("clear");
	}
	
	private static int addEntry()
	{
		if (db_pointer > MAXLEN-1)
		{
			System.out.println("Dimensione massima raggiunta");
			return -1;
		}

		Studente temp = new Studente(
										select("Nome",true), 
										select("Cognome",true),
										select("Data di nascita",false),
										select("Comune di nascita",true),
										select("Data iscrizione",false),
										select("Corso",true)
/* 										"Ciao",
										"Ciccio",
										"15/03/1997",
										"Napoli",
										"15/06/1942",
										"Informatica" */
									);
			

		db_studente[db_pointer] = temp;
						System.out.println("ayylmaooo");
		db_pointer ++;
		return db_pointer;
	}

	private static void compareYear() throws IOException,InterruptedException
	{
		if (db_pointer < 2) 
		{
			System.out.println("Funzione non disponibile");
			return;
		}
		printDB();
		int select_a,select_b;
		String a,b;
		select_a = select();
		select_b = select();
		a = db_studente[select_a].getDetail(Studente.Det.WHENSIGNED);
		b = db_studente[select_b].getDetail(Studente.Det.WHENSIGNED);
		if (LocalDate.parse(a,formatter).isBefore(LocalDate.parse(b,formatter)))
		{
			System.out.format("%n%s si è iscritto prima di %s",db_studente[select_a].getDetail(Studente.Det.NAME),db_studente[select_b].getDetail(Studente.Det.NAME));
		} else
		{
			System.out.format("%n%s si è iscritto prima di %s",db_studente[select_b].getDetail(Studente.Det.NAME),db_studente[select_a].getDetail(Studente.Det.NAME));			
		}
		return;
	}

	
	private static int remainingEx() throws IOException,InterruptedException
	{
		if (db_pointer == 0) return 129;
		int select_a;
		printDB();
		select_a = select();
		return db_studente[select_a].remainingExams();
	}
	
	private static void printDB() throws IOException,InterruptedException
	{
		clear.inheritIO().start().waitFor();
		int i = 0;
		System.out.println("DATABASE STUDENTI");
		while (i< db_pointer)
		{
			System.out.println();
			System.out.format("Indice Studente: [%d]%n",i);
			System.out.println("Nome: " + db_studente[i].getDetail(Studente.Det.NAME));
			System.out.println("Cognome: " + db_studente[i].getDetail(Studente.Det.SURNAME));
			System.out.println("Matricola: " + db_studente[i].getDetail(Studente.Det.ID));
			i++;
		}
		return;
	}
	
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private static final String[]options = {"Nuovo Studente","Confronta Iscrizioni","Calcola esami rimanenti","Esci"}; //Titolo opzioni
	private static final int options_count = 4; //Conta delle opzioni
	private static final ProcessBuilder clear = getCommand(); //Variabile utilizzata come riferimento all'oggetto processbuilder coerente con l'SO.
	private static int db_pointer = 0;
	private static final int MAXLEN = 30;
	private static Studente[]db_studente = new Studente[MAXLEN];
	private static final Scanner keyboard = new Scanner(System.in); //Stream d'ingresso
	
	
}