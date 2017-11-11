package consegna.gaudiosi.flotta;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class LineBus extends Navicella{
	public LineBus
					(
							GregorianCalendar firstFlight,
							GregorianCalendar nextFlight,
							GregorianCalendar lastRev,
							int booked,
							int serial,
							ArrayList<String> route,
							int flightID,
							int flightTime
					)
	{
		super(firstFlight,nextFlight,lastRev,booked,serial);
		this.route = route;
		this.flightID = flightID;
		this.flightTime = flightTime;
	}
	public String toString()
	{
		return super.toString() + "FID["+flightID+"]";
	}
	public void addStop(String stop)
	{
		route.add(stop);
	}
	
	private ArrayList<String>route;
	private int flightID;
	private int flightTime;
}