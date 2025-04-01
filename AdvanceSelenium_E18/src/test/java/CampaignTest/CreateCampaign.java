package CampaignTest;

import java.io.IOException;

import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
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
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import GenericUtility.ExcelFileUtility;
import GenericUtility.JavaUtility;
import GenericUtility.PropertiesFileUtility;
import ObjectRepository.CampaignsPage;
import ObjectRepository.CreateCampaignsPage;
import ObjectRepository.DashboardPage;
import ObjectRepository.LoginPage;
import genericBaseClassUtility.BaseClass;
import genericListenerUtility.ListenerImp;
//@Listeners(ListenerImp.class)

public class CreateCampaign extends BaseClass{
	
	//@Parameters("browser")
	@Test()
	public void createCampaignTest() throws InterruptedException, IOException {
		/*PropertiesFileUtility propUtil=new PropertiesFileUtility();
		String BROWSER = propUtil.readDataFromPropFile("browser");
		//String BROWSER=browser;
		String URL = propUtil.readDataFromPropFile("url");
		String UN = propUtil.readDataFromPropFile("uname");
		String PWD = propUtil.readDataFromPropFile("pwd");*/
		
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
		
		JavaUtility jUtil=new JavaUtility();
		int randomNum = jUtil.getRandomNum(2000);
		/*Random ran=new Random();
		int randomNum=ran.nextInt(1000);*/
		
		ExcelFileUtility exUtil=new ExcelFileUtility();
		String Campaign = exUtil.readingDataFromExcel("DDTTestcase_ID", 1, 2)+randomNum;
		String targetSize = exUtil.readingDataFromExcel("DDTTestcase_ID", 1, 3);
		
		/*FileInputStream fis1=new FileInputStream("C:\\Users\\91830\\eclipse-workspace\\AdvanceSelenium_E18\\src\\test\\resources\\TestScriptData_E18.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
		String Campaign = wb.getSheet("DDTTestcase_ID").getRow(1).getCell(2).getStringCellValue()+randomNum;
		String targetSize= wb.getSheet("DDTTestcase_ID").getRow(1).getCell(3).getStringCellValue();
		System.out.println(Campaign);
		System.out.println(targetSize);*/
		
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
		//driver.get("http://49.249.28.218:8098/");
		
		//Enter the username and password
		//driver.findElement(By.id("username")).sendKeys("rmgyantra");
		//driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999");
		
		//click on sign in button
		//driver.findElement(By.xpath("//button[text()='Sign In']")).click();
		//LoginPage lp=new LoginPage(driver);
		//lp.login(UN, PWD);
		DashboardPage dp=new DashboardPage(driver);
		Thread.sleep(5000);
		dp.getCampaignsLink().click();
		CampaignsPage cp=new CampaignsPage(driver);
		cp.getCreateCampaignBtn().click();
		CreateCampaignsPage ccp=new CreateCampaignsPage(driver);
		ccp.createCampaignWithMandatoryFields(Campaign, targetSize);
	
		//driver.findElement(By.linkText("Campaigns")).click();
		//driver.findElement(By.xpath("//span[text()='Create Campaign']")).click();
		//driver.findElement(By.name("campaignName")).sendKeys(Campaign);
		//driver.findElement(By.name("targetSize")).sendKeys(targetSize);
		//driver.findElement(By.xpath("//button[text()='Create Campaign']")).click();
		Thread.sleep(5000);
		String ConfMsg = cp.getConfMsg().getText();
		boolean status = ConfMsg.contains(Campaign);
		Assert.assertEquals(status,true,"Campaign not added");
		Reporter.log("Campaign"+Campaign+"added successfully",true);
		//String ConfMsg = driver.findElement(By.xpath("//div[@role='alert']")).getText();
		/*if(ConfMsg.contains(Campaign))
		{
			Reporter.log("Campaign"+ Campaign+ " added successfully",true);
		}
		else
		{
			Reporter.log("Campaign not added successfully",true);
		}*/
		Thread.sleep(6000);
		//dp.logout();
		/*driver.findElement(By.xpath("//div[@class='user-icon']")).click();
		WebElement logoutBtn = driver.findElement(By.xpath("//div[text()='Logout ']"));
		Actions action=new Actions(driver);
		action.moveToElement(logoutBtn).click().perform();*/
		//close the browser
		//driver.quit();
	}
	//@Parameters("browser")
	@Test(dependsOnMethods = "createCampaignTest")
	public void createCampaignDateTest() throws IOException, InterruptedException 
	{
		/*PropertiesFileUtility propUtil=new PropertiesFileUtility();
		String BROWSER = propUtil.readDataFromPropFile("browser");
		//String BROWSER=browser;
		String URL = propUtil.readDataFromPropFile("url");
		String UN = propUtil.readDataFromPropFile("uname");
		String PWD = propUtil.readDataFromPropFile("pwd");*/
		
		JavaUtility jUtil=new JavaUtility();
		int randomNum = jUtil.getRandomNum(2000);
		
		ExcelFileUtility exUtil=new ExcelFileUtility();
		String Campaign = exUtil.readingDataFromExcel("DDTTestcase_ID", 1, 2)+randomNum;
		String targetSize = exUtil.readingDataFromExcel("DDTTestcase_ID", 1, 3);
		
		String closeDate = jUtil.generateReqDate(30);
		
		String expectedURL="http://49.249.28.218:8098/dashboard";
		//Launching the browser
		//WebDriver driver=null;
		/*if(BROWSER.equalsIgnoreCase("chrome"))
		{
			driver=new ChromeDriver();
		}
		else if(BROWSER.equalsIgnoreCase("Firefox"))
		{
			driver=new FirefoxDriver();
		}
		else if(BROWSER.equalsIgnoreCase("Edge")) {
			driver=new EdgeDriver();
		}
		else
		{
			driver=new ChromeDriver();
		}*/
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//Navigating to Ninja CRM
		//driver.get("http://49.249.28.218:8098/");
		
		//LoginPage lp=new LoginPage(driver);
		//lp.login(UN, PWD);
		DashboardPage dp=new DashboardPage(driver);
		Thread.sleep(5000);
		dp.getCampaignsLink().click();
		CampaignsPage cp=new CampaignsPage(driver);
		cp.getCreateCampaignBtn().click();
		CreateCampaignsPage ccp=new CreateCampaignsPage(driver);
		ccp.createCampaignWithClosedDate(Campaign, targetSize, closeDate);
		Thread.sleep(5000);
		String ConfMsg = cp.getConfMsg().getText();
		boolean status = ConfMsg.contains(Campaign);
		Assert.assertEquals(status,true,"Campaign not added");
		Reporter.log("Campaign"+Campaign+"added successfully",true);
		/*if(ConfMsg.contains(Campaign))
		{
			Reporter.log("Campaign"+ Campaign+"added successfully",true);//to see in console add true to print string argument
		}
		else
		{
			Reporter.log("Campaign not added successfully",true);
		}*/
		Thread.sleep(6000);
		//dp.logout();
		
		//close the browser
		//driver.quit();

	}

}
