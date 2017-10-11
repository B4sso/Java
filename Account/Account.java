import java.util.Random;

public class Account //MODO D'ACCESSO class NOME VARIABILE
{
	private String name; //Nome intestatario
	private String surname;//Cognome intestatario
	private int id;//ID
	private double check;//Saldo

//COSTRUTTORI

	/* APRE un account con ID Random e SALDO 0 */
	public Account (String name_account, String surname_account)
	{
		Random rng = new Random();
		name = name_account;
		surname = surname_account;
		id = rng.nextInt(100);
		check = 0; 
	}
	
//METODI
	
	/* Restituisce il saldo */
	public double getCheck()
	{
		return check;
	}
	
	/*Restituisce l'ID*/
	public int getID()
	{
		return id;

	}

	/*Restituisce Nome*/
	public String getName()
	{
		return name;
	}

	/*Restituisce Cognome*/
	public String getSurname()
	{
		return surname;
	}

	/*Stampa a video nome e cognome, restituendo l'ID*/
	public int getUser()
	{
		System.out.format("Nome: %s %n",name);
		System.out.format("Cognome: %s %n",surname);
		System.out.format("ID: [%s] %n",id);
		return id;
	}
	
	/*Aggiunge un amount @check*/
	public void editCheck (double amount)
	{
		check = check + amount;
	}
	/*Se il parametro è erase, azzera il conto*/
	public void editCheck (String command)
	{
		command = command.toLowerCase();
		if ( command == "erase") 
		{
			check = 0;
			id = 0;
			System.out.println("Il conto è stato azzerato");
			return;
		}
			System.out.println("Comando non riconosciuto");
	}
}
