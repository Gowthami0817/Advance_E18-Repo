package GenericUtility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFileUtility {
	public String readDataFromPropFile(String Key) throws IOException
	{
		FileInputStream fis=new FileInputStream("C:\\Users\\91830\\GIT\\E18_Batch\\AdvanceSelenium_E18\\src\\test\\resources\\CommonData.properties");
		Properties prop=new Properties();
		prop.load(fis);
		String data = prop.getProperty(Key);
		return data;
	}

}
