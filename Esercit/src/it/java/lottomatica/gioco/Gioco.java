package it.java.lottomatica.gioco;
import java.util.Random;
public abstract class Gioco{
	public Gioco() 
	{
		gameid = new Random().nextInt(132) + 1;
	}
	public int getGameID()
	{
		return gameid;
	}
	public abstract String toString();
	public abstract Boolean equals();
	private int gameid;
	
}