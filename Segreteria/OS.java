public class OS {
	public static String getVer ()
	{
		String ver = System.getProperty("os.name").toLowerCase();
		if (ver.contains("windows")) {return "win";} else {return "ubuntu";}
	}
}