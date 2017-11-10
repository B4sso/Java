package it.java.lottomatica.gioco;
import java.util.Random;

public class CartaAlta extends Gioco{
	public CartaAlta(){ super();}
	public double play()
	{
		Random bancoPicks = new Random(); //Seme casuale del banco
		Random playerPicks = new Random(); //Seme casuale del giocatore
		player_A = playerPicks.nextInt(10)+1;
		player_B = playerPicks.nextInt(10)+1;
		banco_A = bancoPicks.nextInt(10)+1;
		banco_B = bancoPicks.nextInt(10)+1;
		if (player_A > banco_A && player_B > banco_B) return givePrize();
		return 0;
	}
	private double givePrize()
	{
		return TICKETCOST*MULTIPLIER;
	}
	
	public static final double TICKETCOST = 5.00;
	public static final int MULTIPLIER = 5;
	private int player_A;
	private int player_B;
	private int banco_A;
	private int banco_B;
}