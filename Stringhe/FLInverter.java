/*
	IL programma prende in input una stringa di n > 2 caratteri e ne inverte
	il primo e l'ultimo e stampa il risultato a video
*/


public class FLInverter
{
	public static void main(String[] args)
	{
		String recipient;
		String target = trimTarget(args[0]);
		int lastIdx = target.length() - 1;
		if (lastIdx == 1)
		{
			recipient = makeString(target.charAt(1)) + makeString(target.charAt(0));
			System.out.format("FLInverter: [%s] --> [%s]\n",target,recipient);
			System.exit(0);

		}
		recipient = makeString(target.charAt(lastIdx)) + target.substring(1,lastIdx) + makeString(target.charAt(0));
		System.out.format("FLInverter: [%s] --> [%s]\n",target,recipient);
	}


	private static String trimTarget(String target)
	{	
		target = target.trim();
		if (target.length() <= 1)	
		{
			System.out.println("FLInverter: Input non valido");
			System.exit(0);			
		}
		return target;
	}
	private static String makeString (char ch)
	{
		return Character.toString(ch);

	}
}
