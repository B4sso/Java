package consegna.gaudiosi.flotta;
import java.util.Calendar;
import java.util.GregorianCalendar;
public class Navicella{
	
	public Navicella(GregorianCalendar firstFlight, GregorianCalendar nextFlight,GregorianCalendar lastRev, int booked)
	{
		GregorianCalendar today = new GregorianCalendar();
		assert this.firstFlight.before(today);
		assert !(this.nextFlight.before(today));
		if ((this.firstFlight.before(today)) ||!(this.nextFlight.before(today)))
			return;
		
		this.firstFlight = firstFlight;
		this.nextFlight = nextFlight;
		this.lastRev = lastRev;
		this.booked = booked;
	}
	public String get(Details param)
	{
		switch(param)
		{
			case FIRSTF: //Primo volo
			return firstFlight.toString();
			
			case NEXTF: //volo successivo
			return nextFlight.toString();
			
			case LASTREV://ultima revisione
			return lastRev.toString();
			
			case BOOKED://numero registrati
			return Integer.toString(booked);
			
			default:
			return firstFlight.toString();	
		}
	}
	
	public void set(Details param,GregorianCalendar date)
	{
		GregorianCalendar today = new GregorianCalendar();
		switch(param)
		{
			case FIRSTF: //Primo volo
			if (date.before(today))
				firstFlight = date;
			return;
			
			case NEXTF: //volo successivo
			if (date.after(today) && date.after(nextFlight))
				nextFlight = date;
			return;
			
			case LASTREV: //ultima revisione
			if (date.before(today))
					nextFlight = date;
			return;
			
			default:
			return;	
		}	
	}
	
	public void setBooked(int amount)
	{
		if (amount == 0)return;
		if (amount < 0) amount = amount * -1;
		booked = booked + amount;
	}
	
	public void resetBooked()
	{
		booked = 0;
	}
	
	public Boolean isAncient()
	{
		GregorianCalendar today = new GregorianCalendar();
		GregorianCalendar tmp = new GregorianCalendar();
		tmp.setTime(firstFlight.getTime());
		tmp.add(Calendar.YEAR, ANCIENTTRESHOLD);
		if (today.before(tmp)) return false;
		return true;
	}
	
	public Boolean isExpiredRevision()
	{
		GregorianCalendar today = new GregorianCalendar();
		GregorianCalendar tmp = new GregorianCalendar();
		tmp.setTime(lastRev.getTime());
		tmp.add(Calendar.YEAR, EXPIREDTRESHOLD);
		if (today.before(tmp)) return false;
		return true;
	}
	
	public void makeRevision()
	{
		lastRev = new GregorianCalendar(); 
		
	}
	
	public String toString()
	{
		return firstFlight.toString() +
			   "-"+
			   nextFlight.toString()+ 
			   "-"+
			   lastRev.toString() +
			   "-"+
			   Integer.toString(booked);
	}
	
	public Boolean equals(Navicella compared)
	{
		return this.toString().equals(compared.toString());
	}
	
	public final int EXPIREDTRESHOLD = 3;
	public final int ANCIENTTRESHOLD = 10;
	public static enum Details{FIRSTF,NEXTF,LASTREV,BOOKED};
	private GregorianCalendar firstFlight;
	private GregorianCalendar nextFlight;
	private GregorianCalendar lastRev;
	private int booked;
	
	
}