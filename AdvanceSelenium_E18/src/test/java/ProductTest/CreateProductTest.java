package ProductTest;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import GenericUtility.ExcelFileUtility;
import GenericUtility.JavaUtility;
import GenericUtility.PropertiesFileUtility;
import GenericUtility.WebDriverUtility;
import ObjectRepository.DashboardPage;
import ObjectRepository.LoginPage;
import genericBaseClassUtility.BaseClass;
import genericListenerUtility.ListenerImp;
//@Listeners(ListenerImp.class)

public class CreateProductTest extends BaseClass{
	
	//@Parameters("browser")
	@Test()
	public void addProductTest() throws IOException, InterruptedException {
		/*PropertiesFileUtility propUtil=new PropertiesFileUtility();
		String BROWSER = propUtil.readDataFromPropFile("browser");
		//String BROWSER=browser;
		String URL = propUtil.readDataFromPropFile("url");
		String UN = propUtil.readDataFromPropFile("uname");
		String PWD = propUtil.readDataFromPropFile("pwd");*/
		
		JavaUtility jutil=new JavaUtility();
		int randNum = jutil.getRandomNum(2000);
		ExcelFileUtility excelUtil=new ExcelFileUtility();
		String prodName = excelUtil.readingDataFromExcel("Product", 1, 2)+randNum;
		String quantity = excelUtil.readingDataFromExcel("Product", 1, 3);
		String price = excelUtil.readingDataFromExcel("Product", 1, 4);
		
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
		}
		/*if(BROWSER.equalsIgnoreCase("chrome"))
		{
			driver=new ChromeDriver();
		}
		else if(BROWSER.equalsIgnoreCase("firefox"))
		{
			driver=new FirefoxDriver();
		}
		else if(BROWSER.equalsIgnoreCase("edge"))
		{
			driver=new EdgeDriver();
		}
		else
		{
			driver=new ChromeDriver();
		}*/
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		//navigating to ninza CRM
		//driver.get(URL);
		
		//enter the username and password
		//LoginPage lp=new LoginPage(driver);
		//lp.login(UN, PWD);
		Thread.sleep(2000);
		
		//verification of dashboard
		driver.findElement(By.linkText("Products")).click();
		driver.findElement(By.xpath("//span[text()='Add Product']")).click();
		driver.findElement(By.name("productName")).sendKeys(prodName);
		WebElement categatoryDD = driver.findElement(By.name("productCategory"));
		WebDriverUtility Wutil=new WebDriverUtility();
		Wutil.select(categatoryDD, "Others");
		driver.findElement(By.name("quantity")).sendKeys(quantity);
		driver.findElement(By.name("price")).sendKeys(price);
		WebElement vendorDD = driver.findElement(By.name("vendorId"));
		Wutil.select(vendorDD, "VID_015");
		driver.findElement(By.xpath("//button[text()='Add']")).click();
		Thread.sleep(5000);
		String confMsg = driver.findElement(By.xpath("//div[text()='Product "+prodName+" Successfully Added']")).getText();
		boolean status = confMsg.contains(prodName);
		Assert.assertEquals(status, true,"Product not added");
		Reporter.log("Product name"+prodName+"added successfully",true);
		/*if(confMsg.contains(confMsg))
		{
			Reporter.log("Product "+prodName+"added successfully",true);
		}
		else
		{
			Reporter.log("Product not added",true);
		}*/
		Thread.sleep(6000);
		//logout
		//DashboardPage dp=new DashboardPage(driver);
		//dp.logout();
        //close the browser
        //driver.quit();
	}

}
