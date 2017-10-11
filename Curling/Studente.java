import java.util.Calendar;
import java.lang.Boolean;

public class Studente {
	//Istanzia un oggetto Studente 
	/* STUDENTE
		|Nome (Stringa.len > 0)
		|Cognome (Stringe.len > 0)
		|Data di nascita (CALENDAR)
		|Comune di nascita (Stringa.len > 0)
		|Data iscrizione (CALENDAR)
	
		|Matricola ("CURLING" + Nome[0] + Cognome[0]  + GG iscr + AAAA )
  					EX: 442  5    4    05        03        2012
					   |ID | N | C | ISCR.GG |ISCR.MM| ISCR.AAAA
					   
	La matricola serve a distinguere due obj Studente in caso di omonimia.
	*/
	
	public Studente (String i_name, String i_surname,String whenb,String whereb,String account_signed) //Istanzia uno studente
	{
		name = i_name;
		surname = i_surname;
		whenB = stringtoCal(whenb);
		whereB = whereb;
		inBy = stringtoCal(account_signed);
		id = "CURLING" + Character.toString(name.charAt(0)) + Character.toString(surname.charAt(0)) + inBy.get(Calendar.DATE) + inBy.get(Calendar.MONTH) + inBy.get(Calendar.YEAR);
	}
	
	public String getDetail(Detailfield target) //Restituisce il campo specificato dal target
	{
		switch (target)
		{
		case NAME:
		return name;
		
		case SURNAME:
		return surname;
		
		case WHEREBIRTH:
		return whereB;
		
		case WHENBIRTH:
		return whenB.get(Calendar.DATE) + "/" + whenB.get(Calendar.MONTH) + "/" + whenB.get(Calendar.YEAR);
		
		case WHENSIGNED:
		return inBy.get(Calendar.DATE) + "/" + inBy.get(Calendar.MONTH) + "/" + inBy.get(Calendar.YEAR);
		
		case ID:
		return id;
		
		case ROLE:
		switch (role)
		{
			case ATTAC:
			return "Attaccante";
			
			case DEFENS:
			return "Difensore";
			
			case GOAL:
			return "Portiere";
			
			default :
				return "aYYLMAOOOO";
		}
		
		case PLAYED:
		return Integer.toString(pldMatch);
		
		case INJUR:
		return Integer.toString(injuries);
		
		case SPENT:
		return Double.toString(spent);
	
		default:
		return name;
		}
	}
	
	public Boolean addStat(Detailfield stat)
	{
		switch (stat)
		{
			case INJUR:
			injuries ++;
			return true;
			
			case PLAYED:
			pldMatch++;
			return true;	
			
			default:
			return false;
		}
	}
	
	public Boolean addStat(Roles new_role)
	{
		role = new_role;
		return true;
	}
	
	public Boolean addStat (double val)
	{
		if (val < 0) return false;
		spent = spent+val;
		return true;
	}
	
	public Boolean eraseStat ()
	{
		pldMatch = 0;
		injuries = 0;
		spent = 0;
		return true;
	}


	private static Calendar stringtoCal(String target) //Prende una stringa con format GG-MM-AAAA la formatta, restituendo un calendario.
	{
		int year = Integer.parseInt(target.substring(6,10));
		int month = Integer.parseInt(target.substring(3,5));
		int day = Integer.parseInt(target.substring(0,2));
		Calendar temp = Calendar.getInstance();
		temp.set(year,month,day);
		return temp;
	}

	public static enum Detailfield {NAME,SURNAME,WHEREBIRTH,WHENBIRTH,WHENSIGNED,ID,ROLE,PLAYED,INJUR,SPENT,ERASE};
	public 	static enum Roles {ATTAC,DEFENS,GOAL};
	private String name;
	private String surname;
	private Calendar whenB;
	private String whereB;
	private Calendar inBy;
	private String id;	
	private int pldMatch;
	private int injuries;
	private double spent;
	private Roles role;
	
	
}
