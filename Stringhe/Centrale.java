/*
	Il programma prende come argomento una sequenza di caratteri
	la cui lunghezza > 0 e ne stampa il carattere centrale.
*/

public class Centrale
{
	public static void main(String[] args)
	{
		String target = trimTarget(args[0]);		
		int len = (target.length());
		if ((len%2) == 0)
		{	
			System.out.format("Il Carattere centrale della stringa [%s] e':[%c] \n ",target,target.charAt((len/2)-1));
		} 
			else
		{
			System.out.format("Il Carattere centrale della stringa [%s] e':[%c] \n ",target,target.charAt(len/2));			
		}
	}
	
	//Controllo sulla PREC: la stringa deve essere n > 1 caratteri, non vuota, escludendo
	//gli spazi all'inizio e alla fine.
	private static String trimTarget(String target)
	{	
		target = target.trim();
		if (target.length() <= 1)	
		{
			System.out.println("Input non valido");
			System.exit(0);			
		}
		return target;
	}


}
