package consegna.gaudiosi.flotta;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class LineBus extends Navicella{
	public LineBus(
			GregorianCalendar firstFlight,
			GregorianCalendar nextFlight,
			GregorianCalendar lastRev,
			int booked,
			ArrayList<String> route,
			int flightID,
			int flightTime)
	{
		super(firstFlight,nextFlight,lastRev,booked);
		this.route = route;
		this.flightID = flightID;
		this.flightTime = flightTime;
	}
	public String toString()
	{
		String part = "ROUTE:";
		for (String s: route)
			part = part + s;
		return super.toString() + "-"+ "ID:"+ Integer.toString(flightID)+part+Integer.toString(flightTime);
	}

	public Boolean equals(LineBus compared)
	{
		return (this.toString().equals(compared.toString()));
	}
	
	
	private ArrayList<String>route;
	private int flightID;
	private int flightTime;
}