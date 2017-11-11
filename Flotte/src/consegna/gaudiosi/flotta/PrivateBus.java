package consegna.gaudiosi.flotta;
import java.util.GregorianCalendar;

public class PrivateBus extends Navicella{
	public PrivateBus
					(
			
							GregorianCalendar firstFlight,
							GregorianCalendar nextFlight,
							GregorianCalendar lastRev,
							int serial,
							int booked,
							String destination,
							int cid
					)
	{
		super(firstFlight,nextFlight,lastRev,booked,serial);
		this.destination = destination;
		this.cid = cid;
	}
	public String toString()
	{
		return super.toString() + 
		"-"+
		Integer.toString(cid);
	}
	
	private String destination;
	private int cid;
	
}