package consegna.gaudiosi.flotta;
import java.util.ArrayList;

public class Flotta{
	public Flotta(){}
	public void add(Navicella ship)
	{
		if (this.has(ship)) return;
		
		
		
	}
	
	public Boolean has(Navicella ship)
	{
		for (Navicella n: fleet)
			if(n.equals(ship)) return true;
		return false;
		
	}
	public ArrayList<Navicella> fleet;
}