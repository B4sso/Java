public class OS {
	
	public OS ()
	{
		ver = getVer();
	}
	
	private static String getVer ()
	{
		String ver = System.getProperty("os.name").toLowerCase();
		if (ver.contains("windows")) {return "win";} else {return "ubuntu";}
	}
	
	public void printVer()
	{
		System.out.format("\nIl sistema operativo è: %s\n",ver);
	}
	
	private String ver;
}