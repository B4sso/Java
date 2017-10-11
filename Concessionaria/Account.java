import java.util.Random;

public class Account
{

//COSTRUTTORI

	/* APRE un account con ID Random */
	//Il primo costruttore è per l'apertura di un account semplice, senza collegamenti ad oggetti Car
	public Account (String name_account, String surname_account)
	{
		Random rng = new Random();
		name = name_account;
		surname = surname_account;
		id = rng.nextInt(100);
	}
	//Il secondo costruttore riceve in input un riferimento @Car e lo lega univocamente @Account
	public Account (String name_account, String surname_account,Car input_car)
	{
		Random rng = new Random();
		name = name_account;
		surname = surname_account;
		id = rng.nextInt(100);
		car = input_car;
	}
	
//METODI
	
	/* Restituisce il riferimento ad un oggetto di tipo Car*/
	public Car getCar()
	{
/* 		System.out.println("Marchio: " + car.getBrand());
		System.out.println("Modello: " + car.getType());
		System.out.format("KPL: %d\n",car.getKPL());
		System.out.format("Capacity: %d\n",car.getCapacity()); */
		return car;	
	}
	public void setCar(Car new_car)
	{
		car = new_car;
		
	}
	public void setCar ()
	{
		car = new Car();
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
	private String name; //Nome intestatario
	private String surname;//Cognome intestatario
	private int id;//TARGA
	private Car car;//RIF @CAR


}
