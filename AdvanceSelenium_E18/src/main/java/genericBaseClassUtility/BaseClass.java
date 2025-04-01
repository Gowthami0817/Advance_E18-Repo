package genericBaseClassUtility;

import java.io.IOException;
import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import GenericUtility.DatabaseUtility;
import GenericUtility.PropertiesFileUtility;
import ObjectRepository.DashboardPage;
import ObjectRepository.LoginPage;

public class BaseClass {
	
	public WebDriver driver=null;
	public static WebDriver sdriver=null;
	DatabaseUtility dbUtil=new DatabaseUtility();
	PropertiesFileUtility pro=new PropertiesFileUtility();
	@BeforeSuite
	public void beforeSuite() throws SQLException
	{
			System.out.println("Established database connection");
			dbUtil.getDBConnection("jdbc:mysql://localhost:3306/Ninja_E18", "root", "081717");
	}
	@BeforeTest
	public void beforeTest()
	{
			System.out.println("Pre configuration set up");
	}
	@BeforeClass
	public void beforeClass() throws IOException
	{
			System.out.println("Launch the browser");
			String BROWSER = pro.readDataFromPropFile("browser");
			ChromeOptions Coption=new ChromeOptions();
			FirefoxOptions Foption=new FirefoxOptions();
			EdgeOptions Eoption=new EdgeOptions();
			Coption.addArguments("--headless");
			Foption.addArguments("--headless");
			Eoption.addArguments("--headless");
			if(BROWSER.equalsIgnoreCase("chrome"))
			{
				driver=new ChromeDriver(Coption);
			}
			else if(BROWSER.equalsIgnoreCase("Firefox"))
			{
				driver=new FirefoxDriver(Foption);
			}
			else if(BROWSER.equalsIgnoreCase("Edge")) {
				driver=new EdgeDriver(Eoption);
			}
			else
			{
				driver=new ChromeDriver(Coption);
			}
			sdriver=driver;
	}
	@BeforeMethod
	public void beforeMethod() throws IOException
	{
			System.out.println("Login");
			String URL = pro.readDataFromPropFile("url");
			String UN = pro.readDataFromPropFile("uname");
			String PWD = pro.readDataFromPropFile("pwd");
			driver.get(URL);
			LoginPage lp=new LoginPage(driver);
			lp.login(UN, PWD);
	}
	@AfterMethod
	public void afterMethod()
	{
			System.out.println("Logout");
			DashboardPage dp=new DashboardPage(driver);
			dp.logout();
	}
	@AfterClass
	public void afterClass()
	{
			System.out.println("Close the browser");
			driver.quit();
	}
	@AfterTest
	public void afterTest()
	{
			System.out.println("Post configuration set up");
	}
	@AfterSuite
	public void afterSuite()
	{
			System.out.println("close the db connection");
			dbUtil.closeDBConnection();
	}
	
	

}
