public class Cliente{
	public static void main(String[] args)
	{
		Macchina dist = new Macchina(true);
		Macchina dist_1 = new Macchina(false);
		Boolean manoconbibita = false;
		System.out.format("Numero bibite A: %d%n",dist.getBibite());
		System.out.format("Numero bibite B: %d%n",dist_1.getBibite());
		System.out.println("Aggiungo 1 bibita ad A. EXPECTED: 1");
		dist.addBibite(1);
		System.out.format("Numero bibite A: %d%n",dist.getBibite());
		System.out.println("Ho sete, prendo bibita da A. EXPECTED: 0");
		
		dist.insertCoin();
		if (!dist.getContenitore())
		{
			System.out.println("Purtroppo il contenitore era vuoto. Se solo ci fosse un modo per gestire le eccezioni...");
			return;
		}
		dist.takeBibita();
		System.out.format("Numero bibite A: %d%n",dist.getBibite());
		System.out.format("Nel registratore ci sono %d gettoni. EXPECTED 1%n",dist.getCoin());
		System.out.println();	
	}


}
