package consegna.gaudiosi.flotta;
import java.util.GregorianCalendar;

public class PrivateBus extends Navicella{
	public PrivateBus(
			GregorianCalendar firstFlight,
			GregorianCalendar nextFlight,
			GregorianCalendar lastRev,
			int booked,String destination,int cid)
	{
		super(firstFlight,nextFlight,lastRev,booked);
		this.destination = destination;
		this.cid = cid;
	}
	public String toString()
	{
		return super.toString() + 
		"-"+
		Integer.toString(cid)+
		"-"+
		destination;
	}

	public Boolean equals(PrivateBus compared)
	{
		return (this.toString().equals(compared.toString()));
	}
	
	private String destination;
	private int cid;
	
}