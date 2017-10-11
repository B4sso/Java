/*
	Il programma conta il numero di caratteri (amount) in mezzo alle due "i"
	della stringa "arrivederci" e stampa a schermo le due 
	metà invertite.
*/


public class Arrivederci {
	public static void main(String[] args)
		{
			String target = "arrivederci";
			int len = target.length();
			int firstIdx = target.indexOf('i');
			int halfIdx = (len%2 != 0)?(len/2):((len/2) - 1);
			int amount = target.substring(firstIdx,len-1).length() - 1;
			System.out.format("Fra le due i ci sono %d caratteri.\n",amount);
			System.out.format
			(
				"%s invertito risulta: %s.\n",
				target,
				(target.substring(halfIdx,len) + target.substring(0,halfIdx))
			);
		
								
		}

}
