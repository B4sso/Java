/*Classico "Hello, World" - modificato */

public class hello 
{
	public static void main(String[] args)
	{
	/*
	System.out.println(PARAMETRO) // OUTPUT: PARAMETRO
	STRING_A  <-- STRING_B.replace("A","B"); //Trova tutte le occorrenze A in String_B
											   e le rimpiazza con B
	INT <--- STRING.lenght(); // STRLEN
	*/

        String welcome = "Welcome to H E L L";
		System.out.println(welcome);
		welcome.replace("Welcome","Benvenuto");
		System.out.println(welcome.replace("H E L L","HELL"));
		System.out.println((welcome.replace("H E L L","HELL")).length());
	}
}
