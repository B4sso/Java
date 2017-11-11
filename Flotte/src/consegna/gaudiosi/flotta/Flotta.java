package consegna.gaudiosi.flotta;
import java.util.ArrayList;
public class Flotta{
	public Flotta(){}
	public void addNavicella(Navicella toadd)
	{
		if (this.has(toadd)) return;
		fleet.add(toadd);
		
	}
	public Boolean has(Navicella toadd)
	{
		for(Navicella e: fleet)
			if (e.equals(toadd)) return true;
		return false;	
	}	
	private ArrayList<Navicella> fleet;
}