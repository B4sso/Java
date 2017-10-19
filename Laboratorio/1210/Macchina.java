/*
	CLIENTE -> GETTONE_I ->MACCHINA (GETTONI_N + GETTONI_I)
	  LATTINA<---------------------{LATTINA}

	CLIENTE = Classe di testing
	MACCHINA = Classe Recipient 

	
	
	//COSTRUTTORI MACCHINA
		| Macchina(nBibite >=0) -> [nBibite bibite, 0 gettoni, contenitore vuoto]
		| Macchina(void) -> Macchina [0 bibite, 0 gettoni, contenitore vuoto]

	//METODI MACCHINA
		| getBibite(void) -> nBibite
		| getCoin(void) -> Portaf (gettoni)
		| addBibite(amount) -> nBibite = nBibite + amount
								RITORNA TRUE

		| dropBibita() -> nBibite-- && Contenitore = PIENO && Portaf ++
		| takeBibita() -> Contenitore = VUOTO;

	//

*/


public class Macchina {

	public Macchina (Boolean empty)
	{
		if(empty) nBibite = 0;
		else nBibite = MAX;
		maxAmount = MAX;
		contenitore = false;
		gettoni = 0;
	}

/////METODI	

	public int getBibite()
	{
		return nBibite;
	}
	public int getCoin()
	{
		return gettoni;
	}
	public void emptyBibite()
	{
		nBibite = 0;
	}
	public void addBibite(int amount)
	{
		nBibite = nBibite + amount;
	}

	public void insertCoin()
	{
		gettoni++;
		nBibite--;
		contenitore = true;	
	}

	public Boolean takeBibita()
	{
		return contenitore = false;	
	}
	public Boolean getContenitore()
	{
		return contenitore;
	}
	public int getMax()
	{
		return maxAmount;
	}
	

	private static final int MAX = 20;
	private	Boolean contenitore;
	private int nBibite;
	private int gettoni;
	private int maxAmount;
}
