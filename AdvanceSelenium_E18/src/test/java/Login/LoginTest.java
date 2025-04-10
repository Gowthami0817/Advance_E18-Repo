package Login;

import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.collections4.Get;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import GenericUtility.PropertiesFileUtility;
import ObjectRepository.DashboardPage;
import ObjectRepository.LoginPage;
import genericBaseClassUtility.BaseClass;
import genericListenerUtility.ListenerImp;
import junit.framework.Assert;
//@Listeners(ListenerImp.class)

public class LoginTest extends BaseClass{
	
	//@Parameters("browser")
	@Test(retryAnalyzer = genericListenerUtility.RetryListenerImplementation.class)
	public void loginTest() throws InterruptedException, IOException {
		
		/*PropertiesFileUtility propUtil=new PropertiesFileUtility();
		String BROWSER = propUtil.readDataFromPropFile("browser");
		//String BROWSER=browser;
		String URL = propUtil.readDataFromPropFile("url");
		String UN = propUtil.readDataFromPropFile("uname");
		String PWD = propUtil.readDataFromPropFile("pwd");
		System.out.println(BROWSER);
		System.out.println(URL);
		System.out.println(UN);
		System.out.println(PWD);*/
		
		/*FileInputStream fis=new FileInputStream("C:\\Users\\91830\\eclipse-workspace\\AdvanceSelenium_E18\\src\\test\\resources\\CommonData.properties");
		Properties prop=new Properties();
		prop.load(fis);
		String BROWSER = prop.getProperty("browser");
		String URL = prop.getProperty("url");
		String UN = prop.getProperty("uname");
		String PWD = prop.getProperty("pwd");
		System.out.println(BROWSER);
		System.out.println(URL);
		System.out.println(UN);
		System.out.println(PWD);*/
		
		String expectedURL="http://49.249.28.218:8098/dashboard";
		//Launching the browser
		//WebDriver driver=null;
		/*ChromeOptions Coption=new ChromeOptions();
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
		}*/
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		//Navigating to Ninja CRM
		//driver.get("http://49.249.28.218:8098/ ");
		
		//Enter the username and password
		//LoginPage lp=new LoginPage(driver);
		//lp.login(UN, PWD);
		Thread.sleep(2000);
		
		//driver.findElement(By.id("username")).sendKeys("rmgyantra");
		//driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999");
		
		//click on sign in button
		//driver.findElement(By.xpath("//button[text()='Sign In']")).click();
		//Thread.sleep(2000);
		
		//Verification of dashboard
		String actualURL=driver.getCurrentUrl();
		SoftAssert sf=new SoftAssert();
		sf.assertEquals(actualURL, expectedURL);
		sf.assertAll();
		/*if(actualURL.equals(expectedURL)) {
			Reporter.log("Validation is pass",true);
		}
		else {
			Reporter.log("Validation is failed",true);
		}*/
		
		//logout
		DashboardPage dp=new DashboardPage(driver);
		//dp.logout();
		/*driver.findElement(By.xpath("//div[@class='user-icon']")).click();
		WebElement logoutBtn = driver.findElement(By.xpath("//div[text()='Logout ']"));
		Actions action=new Actions(driver);
		action.moveToElement(logoutBtn).click().perform();*/
		//close the browser
		//driver.quit();
		

	}

}
