/*
 * Simula delle Query da utilizzare su un database.
 */
import java.util.*;
public interface Query{
	
	ArrayList<String> sortByAuthor(Object o,String name); //Restituisce tutti gli elementi dati da un determinato autore
	ArrayList<String> sortByArgument(Object o,String argument); //Restituisce tutti gli elementi con titolo contenente argument
	ArrayList<String> sortByLess(Object o,int amount, Boolean flag); ////Restituisce tutti gli elementi di numero <= amount se (flag), altrimenti == amount
	ArrayList<String> sortByMore(Object o, int amount, Boolean flag);//Restituisce tutti gli elementi di numero <= amount se (flag), altrimenti == amount
	void add(Object o,String title, int amount); //Aggiunge amount copie all'oggetto i di titolo title
}