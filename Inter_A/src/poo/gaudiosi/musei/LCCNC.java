package poo.gaudiosi.musei;
import poo.gaudiosi.client.*;
import java.util.ArrayList;
public class LCCNC{
	public LCCNC()
	{
		ministero = new ArrayList<Museo>();
	}
	public void addMuseo(Museo toadd)
	{
		if (ministero.contains(toadd))
			return;
		toadd.setId(ministero.size());
		ministero.add(toadd);
	}
	public void addVisitor(Visitor visitor,int museoID)
	{
		ministero.get(museoID).addVisita(visitor);
	}
	public int maxVisite()
	{
		int max = 0;
		int maxamount = 0;
		for (Museo e: ministero)
			if (e.getVisite() > maxamount)
			{
				maxamount = e.getVisite();
				max = ministero.indexOf(e);
			}	
		return max;
	}
	public Museo getMuse(int ID)
	{
		return ministero.get(ID);
		
	}

	private ArrayList<Museo>ministero;
	
}