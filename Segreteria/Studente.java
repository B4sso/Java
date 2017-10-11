import java.time.LocalDate;
import java.lang.Boolean;
import java.time.format.DateTimeFormatter;

public class Studente {
	//Istanzia un oggetto Studente 
	/* STUDENTE
		|Nome (Stringa.len > 0)
		|Cognome (Stringe.len > 0)
		|Data di nascita (CALENDAR)
		|Comune di nascita (Stringa.len > 0)
		|Data iscrizione (CALENDAR)
		|Corso (rif != null @objCorso)
		|Matricola (ID Corso + Nome[0] + Cognome[0]  + GG iscr + AAAA )
  					EX: 442  5    4    05        03        2012
					   |ID | N | C | ISCR.GG |ISCR.MM| ISCR.AAAA
					   
	La matricola serve a distinguere due obj Studente in caso di omonimia.
	*/
	
	private class Corso
	{
	//Istanzia un oggetto Corso
			/*Corso
				|NOME (Stringa len > 0 | Contenuto in exams_db)
				|ESAMI DATI (Esami[] x ESAMI CORSO)
				|ESAMI DA DARE (N ESAMI)
				|ID CORSO
			*/
			
		private class Exam
		{
			//Istanzia un oggetto Esame
			/*ESAME
				|NOME (Stringa len > 0)
				|VOTO (int  > 0)
			*/
			private Exam (String nome, int voto) //Istanzia un nuovo esame
			{
				name = nome;
				mark = voto;
			}
			
			private int getMark() //Restituisce il voto
			{
				return mark;
			}
			private String getName()//Restituisce il nome
			{
				return name;
			}
			
			private int mark;
			private String name;
		}
		
		
		private Corso(String i_name) //Istanzia un un nuovo corso
		{
			name = i_name;
			eGiven = new Exam[20];
			switch (name)
			{
				case "Informatica":
				id = "451";
				Exam ex =	new Exam ("POO",0); 
				eGiven[0] = ex;			
				eGiven[1] =	new Exam ("AE",0);
				eGiven[2] = new Exam ("BD",0);
				eGiven[3] =	new Exam ("PROG2",0);  

				nexams = EX_INFO;
				break;
				
				case "Lettere":
				id = "452";
				eGiven[0] =	new Exam ("Studio delle lingue",0);  
				eGiven[1] =	new Exam ("Studio dei gomiti",0);
				eGiven[2] = new Exam ("Studio delle appendici",0);
				eGiven[3] =	new Exam ("C++",0);  
				nexams = EX_LETT;				
				break;
				
				case "Ingegneria":
				id = "453";
				eGiven[0] =	new Exam ("POO",0);  
				eGiven[1] =	new Exam ("AE",0);
				eGiven[2] = new Exam ("BD",0);
				eGiven[3] =	new Exam ("PROG2",0);  
				nexams = EX_ING;
				break;
				default: return;
			}		
		}
		
		private Boolean addExam(String nome,int voto) //Aggiunge un esame
		{
			int idx = this.isPresent(nome);
			if (idx < 0) return false;
			eGiven[idx] = new Exam (eGiven[idx].getName(),voto);
			return true;
		}
		
		private int isPresent(String target)//Controlla se un esame è presente nel corso
		{
			int i = 0;
			while (eGiven[i] != null)
			{
				if (eGiven[i].getName().equalsIgnoreCase(target)) return i;
				i++;
			}
			return -1;	
		}
		
		private int getPassed() //Restituisce il numero di esami con voto > MIN_PASSED
		{
			int i = 0, p = 0;
			while (eGiven[i] != null)
			{
				if ((eGiven[i].getMark()) >= MIN_PASSED) p++;
				i++;
			}
			return p;
		}
		private int nExams()
		{
			return nexams;
		}
		
		private String getID()
		{
			return id;
		}
		
		private String getName()
		{
			return name;
		}
		
		
		private String name;
		private String id;
		private Exam[] eGiven;
		private int nexams;
	}
	
	
	
	
	public Studente (String i_name, String i_surname,String whenb,String whereb,String account_signed,String corso) //Istanzia uno studente
	{

		name = i_name;
		surname = i_surname;
		whenB = LocalDate.parse(whenb,formatter);
		whereB = whereb;
		inBy = LocalDate.parse(account_signed,formatter);
		study = new Corso(corso);

		id = Integer.parseInt(study.getID()) + Character.toString(name.charAt(0)) + Character.toString(surname.charAt(0)) + Integer.toString(inBy.getDayOfMonth()) + Integer.toString(inBy.getMonthValue()) 
			+ Integer.toString(inBy.getYear());
	}
	
	public Boolean addExam(String name, int voto)
	{
		return study.addExam(name,voto);
	}
	
	public String getDetail(Det target) //Restituisce il campo specificato dal target
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
		return whenB.toString();
		
		case WHENSIGNED:
		return inBy.toString();
		
		case STUDY:
		return study.getName();
		
		case ID:
		return id;
	
		default:
		return name;
		}
	}
	
	public int remainingExams() //Restituisce il numero di esami rimanenti
	{
		return study.nExams() - study.getPassed();
	}	
/* 	
	private static Calendar stringtoCal(String target) //Prende una stringa con format GG-MM-AAAA la formatta, restituendo un calendario.
	{
		int year = Integer.parseInt(target.substring(6,9));
		int month = Integer.parseInt(target.substring(3,5));
		int day = Integer.parseInt(target.substring(0,2));
		Calendar temp = Calendar.getInstance();
		temp.set(year,month,day);
		return temp;
	} */

	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	public enum Det {NAME,SURNAME,WHEREBIRTH,WHENBIRTH,WHENSIGNED,STUDY,ID};
	public static final int MIN_PASSED = 18;
	public static final int EX_INFO = 4;
	public static final int EX_LETT = 4;
	public static final int EX_ING = 4;
	private String name;
	private String surname;
	private LocalDate whenB;
	private String whereB;
	private LocalDate inBy;
	private Corso study;
	private String id;												
	
}
