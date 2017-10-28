package classe2.consegna.server;
/*
 * 	Implementa un interfaccia che ci consente di gestire gli Shelf.
 *  A noi interessa ottenere i dettagli di ogni singolo Item contenuto in uno Shelf, poco
 *  importa che sia un libro, una rivista o un dvd.
 * 
 */
import java.util.*;
public interface Item
{
	ArrayList<String> getDetails(); //Restituisce i dettagli dell'Item
	String getTitle(); //Restituisce l'etichetta dell'Item
	String getAuthor();//Restituisce l'autore di Item
}