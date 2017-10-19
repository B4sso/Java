import java.util.*;
import java.io.*;

public class Bank{
	/*	
		CLASSE ACCOUNT
		Implementa una classe per la gestione degli Account
		
		NOTA: Bank (vuoto): accountDB = vuoto, entries = 0, dbLib = null, dbStream = null
		COSTRUTTORI
			| Bank () -> Bank (vuoto) + dbLib = file + dbStream = file)
			| Bank (DATABASE) -> Bank.obj (locale) sync Bank.obj (DATABASE)
			
		METODI
			| addAccount(Decimale >= 0,Stringa,Stringa,BOOL) -> Account in posizione (size) di accountDB
																if (BOOL) Account sync posizione (size) di Bank.obj(DATABASE)

			| deposit(account,amount) -> Account.total = Account.total + |amount|
										 account.total sync con posizione (pos.account.total) di Bank.obj(DATABASE)

			| withdraw(account,amount) -> Account.total = Account.total - |amount|
										 account.total sync con posizione (pos.account.total) di Bank.obj(DATABASE)

			| getBalance(account) = Account.total
			| transfer (account_A,account_B,amount) = Account_A.total = Account_A.total - |amount|;
													  Account_B.total = Account_B.total + |amount|;
													  account_(A&B).total sync con posizione (pos.account(A&B).total) di Bank.obj(DB)		
		VARIABILI
			| accountDB = Lista Account
		    | entries = size accountDB
			| dbLib = rif Bank.obj(DB)
			| dbStream = stream Bank.obj(DB)	
	

		FORMAT BANK.OBJ(DB)
			INT\n
			0000000000000000000nome_0000000000000000cognomeDOUBLE_\n
			000000000000ciaobellola_0000000000000000cognomeDOUBLE_\n
			0000000000000000000nome_00000000000MATHUcognomeDOUBLE_\n
			0000000000000000000nome_0000000000000000cognomeDOUBLE_\n
			0000000000000000000nome_0000000000000000cognomeDOUBLE_\n

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
				Nono sono formattati in UTF-8, quindi NON TANTO leggibili. 	

		*/
	
	//////////////////////////COSTRUTTORI
	public Bank() throws IOException
	{	
		accountDB = new ArrayList<Account>();
		entries = 0;
			new File(LIB).mkdir(); //Non importa sapere se la directory esista o meno.
			if ((dbLib = new File(PTH+EXT)).exists())
			{
				int i = 1;
				while ((dbLib = new File(PTH +"_"+Integer.toString(i)+EXT)).exists())
					i++;	
			}
			dbLib.createNewFile();
			dbStream = new RandomAccessFile(dbLib,"rw"); //Crea un nuovo stream in lettura\scrittura e lo associa all'oggetto.	
			dbStream.writeBytes("0\n"); 
	}
	public Bank(File source)  throws IOException
	{
		accountDB = new ArrayList<Account>();
		dbLib = source;	
		dbStream = new RandomAccessFile(dbLib,"rw");
		dbStream.seek(0);
		entries = Integer.parseInt(dbStream.readLine());
		for (int i = 0; i < entries; i++)
		{
			//LINEA: 000nome_00cognomeDOUBLE_\n
			byte[] buf = new byte[71]; 
			dbStream.readFully(buf);
			double amount = dbStream.readDouble();
			String[] temp;
			String bufferString = new String(buf);
			temp = bufferString.split("_");
			for (int j = 0; j<2;j++)
				temp[j] = getdbEntry(temp[j]);
			accountDB.add(new Account(amount,temp[0],temp[1]));
			dbStream.readLine();
		}
	}

	////////////////////////METODI
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
			dbStream.writeBytes(Integer.toString(entries)+"\n");
			dbStream.seek(dbStream.length());
 			// 0000nome_000cognomeDOUBLE_\n
			dbStream.writeBytes(spawndbEntry(format_name)+"_"+spawndbEntry(format_sname));
			dbStream.writeDouble(amount);
			dbStream.writeBytes("_\n");
			
		}
	}

	private void refresh(Account target) throws IOException
	{
		int idx = this.accountDB.indexOf(target);	
		dbStream.seek(0);
		dbStream.readLine();
		for(int i = 0; i <= idx;i++)
			dbStream.readLine();
		dbStream.skipBytes(71);
		dbStream.writeDouble(getBalance(target));			
	}
	
	public void deposit(Account target, double amount) throws IOException
	{
		target.set((amount < 0)?(-amount):(amount));
		this.refresh(target);
	}
	public void withdraw(Account target, double amount) throws IOException
	{
		target.set((amount > 0)?(-amount):(amount));
		this.refresh(target);
	} 
	
	public static double getBalance(Account account) throws IOException
	{
		return Double.parseDouble(account.get(Account.Det.TOTAL));
	}
	
	public void transfer(Account account_a,Account account_b,double amount) throws IOException
	{
		amount = (amount < 0)?(-amount):(amount);
		account_a.set(-amount);
		account_b.set(amount);
		this.refresh(account_a);
		this.refresh(account_b);
	}
	
	private static String spawndbEntry (String target) throws IOException
	{
		char[] box = new char[STRLEN - target.length()]; //Byte di maschera necessari a sovrascrivere le entries precedenti
		return (new String(box)) + target;	
	}
	public Account getAccount(int i)
	{
		return accountDB.get(i);
	}

	private static String getdbEntry(String target)
	{
		int i = target.lastIndexOf('0');
		return target.substring(i+1,target.length());
	}
	
	private static String formatS (String target)
	{
		target = target.trim();
		if (target.length() == 0) return "n\\A";
		return Character.toString(Character.toUpperCase(target.charAt(0))) + target.substring(1,target.length());

	}
	
	private static final String EXT = ".txt";
	private static final String LIB = "lib";
	private static final int STRLEN = 35;
	private static final int TOJUMP = 4;
	private static final String PTH = "lib/accountdb";
	private ArrayList<Account> accountDB;
	private int entries;
	private File dbLib;
	private RandomAccessFile dbStream;	
}
