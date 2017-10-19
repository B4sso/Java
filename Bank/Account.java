public class Account {
	
	/*	
		CLASSE ACCOUNT
		Implementa una classe utilizzabile nel BankDB.
		
		COSTRUTTORI
			| Account(Decimale >= 0, Stringa,Stringa) -> Account (Totale = Decimale, Nome = Stringa, Cognome = Stringa)
		
		METODI
			| get(("NOME","COGNOME","TOTALE")) -> (Nome,Cognome,Totale)
			| set (Decimale) = (Totale = Totale + Decimale)
		
		VARIABILI
			| Totale = Decimale
			| Nome = Stringa
			| Cognome = Stringa
	*/
	
	public Account(double amount,String nam,String snam)
	{
		total = amount;
		name = nam;
		surname = snam;
	}
	
	public String get(Det target)
	{
		switch(target)
		{
			case NAME:
			return name;
			
			case SURNAME:
			return surname;
			
			case TOTAL:
			return Double.toString(total);
			
			default: return name;
		}
	}
	
	void set(double amount)
	{
		total = total + amount;
	}
	
	public enum Det{NAME,SURNAME,TOTAL};
	private double total;
	private String name;
	private String surname;	
}
