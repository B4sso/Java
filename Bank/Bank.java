import java.util.*;
import java.io.*;

public class Bank{
	/*	
		CLASSE ACCOUNT
		Implementa una classe per la gestione degli Account
		
		COSTRUTTORI
			| Bank () -> Bank (accountDB vuoto)
			
		METODI
			| addAccount(Decimale >= 0,Stringa,Stringa) -> Account in posizione (size) di accountDB
			| deposit(account,amount) -> Account.total = Account.total + |amount|
			| withdraw(account,amount) -> Account.total = Account.total - |amount|
			| getBalance(account) = Account.total
			| transfer (account_A,account_B,amount) = Account_A.total = Account_A.total - |amount|; Account_B.total = Account_B.total + |amount|
		
		VARIABILI
			| accountDB = Lista Account
	*/
	/*
		NOTA BENE: PrintStream = new PrintStream(file)
				   Scanner = new Scanner(file)
				   Non vanno bene per la lettura\scrittura contemporanea dei file, dato che non condividono un offset del puntatore.
				   Le funzioni associate, seppur incomplete, sono state lasciate nel source code.
	
	
	
		RandomAccessFile
		NOTE:
				writeChars scrive caratteri in sequenze di 2-byte.
				writeBytes scrive caratteri in sequenze di 1-byte -METTI IL NEWLINE-  quindi leggibili e direttamente utilizzabili.
	*/
	public Bank() throws IOException
	{
		accountDB = new ArrayList<Account>();
		File dbLib;
		new File(LIB).mkdir(); //Non importa sapere se la directory esista o meno.
		if ((dbLib = new File(PTH)).exists())
		{
			int i = 1;
			while ((dbLib = new File(PTH +"_"+Integer.toString(i))).exists())
				i++;	
		}
		dbLib.createNewFile();
		dbStream = new RandomAccessFile(dbLib,"rw"); //Crea un nuovo stream in lettura\scrittura e lo associa all'oggetto.	
		dbStream.writeBytes("0\n"); 
								
/* 		ps = new PrintStream(dbLib);
		ps.println("DATABASE ENTRIES");
		ps.println("0");
		ps.format("%n%n%n%n");
		 */
		
	}
	public Bank(File source)  throws IOException
	{
		dbLib = source;
		dbStream = new RandomAccessFile(dbLib,"rw");
		entries = Integer.parseInt(dbStream.readLine());
		for (int i = 0; i < entries; i++)
		{
			String placeholder[] = dbStream.readLine().split("_");
			this.addAccount(Double.parseDouble(placeholder[3]),placeholder[0],placeholder[1],false);
		}
		
/* 		sf.nextLine();
		entries = Integer.valueof(sf.nextLine());
		if (entries != 0)
		{
			while(i<4) sf.nextLine();
				for (i=0;i<entries;i++)
				{
					String placeholder[] = sf.nextLine().split("_");
					accountDB.add(new Account(Double.parseDouble(placeholder[3]),placeholder[0],placeholder[1]);
				}	
		}	 */
	}

	public void addAccount(double amount,String name,String surname,Boolean reg)  throws IOException
	{
		String format_name = formatS(name);
		String format_sname = formatS(surname);
		Account temp = new Account(amount,format_name,format_sname);
		accountDB.add(temp);
		entries = accountDB.size();
		if (reg) //Se si vuole registrare l'entry nel database
		{
			dbStream.seek(0);
			dbStream.writeInt(entries);
			//Il FP viene forzato alla prima locazione.
			for (int i = 0; i < entries-1; i++)
				dbStream.readLine();
			dbStream.writeBytes(
								spawndbEntry(format_name)+
								"_"+
								spawndbEntry(format_surname)+
								"_"+
								spawndbEntry(Double.toString(amount))
							   );
			
		}
		
		
		/* int i = 0;
		int entries;
		sf.nextLine();
		entries = Integer.valueof(sf.nextLine());
		while(i<4) sf.nextLine();
		for (int i = 0; i<entries;i++)
			sf.nextLine();
		ps.println(name+"_"+surname+"_"+Double.toString(amount)); */

	}

	private void refresh(Account target)
	{

		dbStream.seek(0);
		dbStream.skip(4);
		String targetEntry;
		String placeholder[];
		long start,end;
		int i = this.indexOf(target)
		for(int j = 0; j < i;j++)
			dbStream.readLine();
		start = dbStream.getFilePointer();
		dbStream.readLine().split("_");
		stop = dbStream.getFilePointer();
		placeholder[3] = target.get(Account.Det.TOTAL);
				
		
	}
	
	public void deposit(Account target, double amount)
	{
		target.set((amount < 0)?(-amount):(amount));
	}
	public static void withdraw(Account target, double amount)
	{
		target.set((amount > 0)?(-amount):(amount));
	}
	
	public static double getBalance(Account account)
	{
		return Double.parseDouble(account.get(Account.Det.TOTAL));
	}
	
	public static void transfer(Account account_a,Account account_b,double amount)
	{
		amount = (amount < 0)?(-amount):(amount);
		account_a.set(-amount);
		account_b.set(amount);
	}
	
	private static String spawndbEntry (String target)
	{
		Character[STRLEN - target.length()] box = {'0'};
		return Character.toString(box) + target;	
	}
	
	private static String formatS (String target)
	{
		target = target.trim();
		if (target.length() == 0) return "n\\A";
		return Character.toString(Character.toUpperCase(target.charAt(0))) + target.substring(1,target.length());

	}
	
	private static final String LIB = "lib";
	private static final int STRLEN = 35;
	private static final int TOJUMP = 4;
	private static final String PTH = "lib\\accountdb";
	private ArrayList<Account> accountDB;
	private int entries;
	private File dbLib;
	private RandomAccessFile dbStream;
	
	
}