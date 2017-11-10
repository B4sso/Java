package it.java.lottomatica.gioco;
import java.util.Random;

public class Ambata extends Gioco{
	public Ambata(){super();}
	public double play()
	{
		Random playerPick = new Random(); //Seme casuale del giocatore
		Random bancoPick = new Random(); //Seme casuale del banco
		player = playerPick.nextInt(45) + 1; //Carta del giocatore
		for (int i = 0; i< MAXTRY;i++) //Estrazioni del banco
			if ((bancoPick.nextInt(45) + 1) == player) return givePrize();
		return 0;
	}
	private double givePrize()
	{
		return TICKETCOST*MULTIPLIER;
	}
	
	public String toString()
	{
		String type = super.getGameID()+"AMBATA";
		String player = Integer.toString(player);
		
	}
	
	public static final double TICKETCOST = 1.50;
	public static final int MULTIPLIER = 10;
	private static final int MAXTRY = 10;
	private int player; //Carta del giocatore
	
}