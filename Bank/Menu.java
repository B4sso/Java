import java.util.*;
import java.io.*;
import java.lang.Boolean;


public class Menu {		
	public static void main(String[] args) throws IOException,InterruptedException
	{
		if (args[0].length() > 0)
	{
		if (args[0].equalsIgnoreCase("-new") || args[0].equalsIgnoreCase("-n"))
			bank = new Bank();
		if ((args[0].equalsIgnoreCase("-import") || args[0].equalsIgnoreCase("-i")) && args[1].length() > 0)
		{			 

			File source = new File(args[1]);
			bank = new Bank(source);
 		}
	}
	 		if (bank == null) bank = new Bank();	
			 getCommand();
			 invokeMenu("Banca dei pascoli di Vienna");
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
			deposita();
			Thread.sleep(4000);
			break;
			
			case 3:
			ritira();
			Thread.sleep(4000);
			break;
			
			case 4:
			saldo();
			Thread.sleep(4000);
			break;
			
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
		/*
			[LINE] + :  <--- INPUT UTENTE
			ex:	Immetti numero: _
			L'input deve essere una stringa\numero
			RITORNA LA STRINGA DA INPUT
			
			SE val == true, la stringa deve avere len > 1
			escludendo spazi iniziali e finali.
			RITORNA LA STRINGA TRIMMATA
			
			EX: "          I      "
			 	ACCETTATA con val = false;
				FORMATTATA IN: "I" E RIFIUTATA
		*/
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
	
	private static ProcessBuilder getCommand() //Compatibilità dei comandi. La WIN-Powershell ed il terminale Linux condividono il "clear", il win-commandprompt richiede il "cls".ja
	{
		if (OS.getVer() == "win")return new ProcessBuilder("cmd", "/c", "cls");
		else return new ProcessBuilder("clear");
	}
	
	private static int addEntry()  throws IOException
	{
		 bank.addAccount (
							Double.parseDouble(select("Totale",true)), 
							select("Nome",true),
							select("Cognome",true),
							true
						);
			
		return 1;
	}

	private static void deposita() throws IOException
	{
		Account target = bank.getAccount(Integer.parseInt(select("Inserisci ID account", false)));
		bank.deposit(target,Double.parseDouble(select("Inserisci ammontare",false)));
	}

	private static void ritira() throws IOException
	{
		Account target = bank.getAccount(Integer.parseInt(select("Inserisci ID account", false)));
		bank.withdraw(target,Double.parseDouble(select("Inserisci ammontare",false)));
	}
	private static void saldo() throws IOException
	{
		Account target = bank.getAccount(Integer.parseInt(select("Inserisci ID account", false)));
		System.out.println(Bank.getBalance(target));
	}
	
	
	private static Bank bank;	
	private static final String[]options = {"Nuovo Account","Deposita","Ritira","Bilancio","Esci"}; //Titolo opzioni
	private static final int options_count = 5; //Conta delle opzioni
	private static final ProcessBuilder clear = getCommand(); //Variabile utilizzata come riferimento all'oggetto processbuilder coerente con l'SO.
	private static final Scanner keyboard = new Scanner(System.in); //Stream d'ingresso
	
	
}
