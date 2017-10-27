/*
 * 	Implementa un interfaccia che ci consente di gestire gli Shelf.
 *  A noi interessa ottenere i dettagli di ogni singolo Item contenuto in uno Shelf, poco
 *  importa che sia un libro, una rivista o un dvd.
 * 
 */

public interface Item
{
	String[] getDetails();
	Item newItem(String titolo,String autore,String publisher);
}