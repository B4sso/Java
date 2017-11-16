package poo.gaudiosi.debugger;
import java.util.ArrayList;
import java.util.Random;
import poo.gaudiosi.client.*;
import poo.gaudiosi.musei.*;
public class Tester{
	
	public static void main(String[]args)
	{
		int counter = 0;
		ArrayList<Visitor>visitorList = new ArrayList<Visitor>();
		for (int i = 0;i<20;i++)
			visitorList.add(new Visitor(cfgen(),mailgen()));

		LCCNC ministero = new LCCNC();
		for (int i = 0;i<3;i++)
		{
			Museo e = new MuseoB(100);
			ministero.addMuseo(e);
			e.setId(i);
		}
		
		for (int i = 2; i<5;i++)
		{
			Museo e = new MuseoS(100);
			ministero.addMuseo(e);
			e.addVisita(visitorList.get(counter));
			visitorList.get(counter); //Il programma non somma l'entry fee
			e.setId(i);
			counter++;
		}
		for (int i =0;i<10;i++)
		{
			ministero.addVisitor(visitorList.get(rng.nextInt(10)),rng.nextInt(5));
		}
		System.out.println(ministero.getMuse(ministero.maxVisite()).toString());
	}
	
	private static String mailgen()
	{

		int set = rng.nextInt(250) + 9201;
		return Integer.toString(set) + "@unisa.com";
	}
	
	private static String cfgen()
	{
		int cf = rng.nextInt(1000);
		Character[] charlist = {'a','b','c','z','f'};
		return "G" + charlist[rng.nextInt(4)] + cf + charlist[rng.nextInt(2)];
	}
	
	
	private static Random rng = new Random();
	
}