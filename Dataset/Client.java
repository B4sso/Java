import java.util.*;


public class Client
{
	public Client(String name, String surname, String bdate, String addr, BankAccount account)
	{
		this.name = formats(name);
		this.surname = formats(surname);
		this.addr = formats(addr);
		this.bdate = toDate(bdate);
		if (account == null)
			accounts.add(new BankAccount());
		accounts.add(account);
	}
	public void addAccount(BankAccount account)
	{
		if (account == null)
			accounts.add(new BankAccount());
		accounts.add(account);
	}
	
	static private String formats(String target)
	{
		// "  fRanCesco  " ->  "Francesco"
		String tmp = target.trim();
		if (tmp.length() < 1) return "NaName";
		return (Character.toString(tmp.charAt(0)).toUpperCase()) + tmp.substring(1,tmp.length()).toLowerCase();
	}

	static private String toDate (String target)
	{
		int day,month,year;
		String[] tgt = target.split("(\\ |-|.)");
		//25.22.2016
		//21/12/2017
		//OUTPUT : 25/22/2016
		//		   21/12/2016
		if((day = tgt[0].length()) == 0 || day > 2) return "NaDate";
		if((month = tgt[1].length()) == 0 || day > 2) return "NaDate";
		if((year = tgt[2].length()) != 4) return "NaDate";
		return  (day == 1)?("0"):("") + tgt[0] + ((month == 1)?("0"):("")) + tgt[1] + year;
	}

	String name;
	String surname;
	String bdate;
	String addr;
	ArrayList<BankAccount> accounts;
}