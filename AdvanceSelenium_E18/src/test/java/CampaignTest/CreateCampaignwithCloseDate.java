package CampaignTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import GenericUtility.ExcelFileUtility;
import GenericUtility.JavaUtility;
import GenericUtility.PropertiesFileUtility;
import ObjectRepository.CampaignsPage;
import ObjectRepository.CreateCampaignsPage;
import ObjectRepository.DashboardPage;
import ObjectRepository.LoginPage;

public class CreateCampaignwithCloseDate {

	public static void main(String[] args) throws InterruptedException, IOException {
		
		PropertiesFileUtility propUtil=new PropertiesFileUtility();
		String BROWSER = propUtil.readDataFromPropFile("browser");
		String URL = propUtil.readDataFromPropFile("url");
		String UN = propUtil.readDataFromPropFile("uname");
		String PWD = propUtil.readDataFromPropFile("pwd");
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
		
		//Random ran=new Random();
		//int randomNum = ran.nextInt(1000);
		
		ExcelFileUtility exUtil=new ExcelFileUtility();
		String Campaign = exUtil.readingDataFromExcel("DDTTestcase_ID", 1, 2)+randomNum;
		String targetSize = exUtil.readingDataFromExcel("DDTTestcase_ID", 1, 3);
		
		/*FileInputStream fis1=new FileInputStream("C:\\Users\\91830\\eclipse-workspace\\AdvanceSelenium_E18\\src\\test\\resources\\TestScriptData_E18.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
		String Campaign = wb.getSheet("DDTTestcase_ID").getRow(1).getCell(2).getStringCellValue()+randomNum;
		String targetSize= wb.getSheet("DDTTestcase_ID").getRow(1).getCell(3).getStringCellValue();
		System.out.println(Campaign);
		System.out.println(targetSize);*/
		
		String closeDate = jUtil.generateReqDate(30);
		
		/*Date dateObj=new Date();
		SimpleDateFormat sim=new SimpleDateFormat("dd-MM-yyyy");
		String todaysDate = sim.format(dateObj);
		System.out.println(todaysDate);
		
		Calendar cal = sim.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, 30);
		String closeDate = sim.format(cal.getTime());
		System.out.println(closeDate);*/
		
		String expectedURL="http://49.249.28.218:8098/dashboard";
		//Launching the browser
		WebDriver driver=null;
		if(BROWSER.equalsIgnoreCase("chrome"))
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
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//Navigating to Ninja CRM
		driver.get("http://49.249.28.218:8098/");
		
		//Enter the username and password
		//driver.findElement(By.id("username")).sendKeys("rmgyantra");
		//driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999");
		
		//click on sign in button
		//driver.findElement(By.xpath("//button[text()='Sign In']")).click();
		//Thread.sleep(2000);
		
		/*driver.findElement(By.linkText("Campaigns")).click();
		driver.findElement(By.xpath("//span[text()='Create Campaign']")).click();
		driver.findElement(By.name("campaignName")).sendKeys(Campaign);
		driver.findElement(By.name("targetSize")).clear();
		driver.findElement(By.name("targetSize")).sendKeys(targetSize);
		driver.findElement(By.name("expectedCloseDate")).sendKeys(closeDate);
		driver.findElement(By.xpath("//button[text()='Create Campaign']")).click();*/
		LoginPage lp=new LoginPage(driver);
		lp.login(UN, PWD);
		DashboardPage dp=new DashboardPage(driver);
		Thread.sleep(2000);
		dp.getCampaignsLink().click();
		CampaignsPage cp=new CampaignsPage(driver);
		cp.getCreateCampaignBtn().click();
		CreateCampaignsPage ccp=new CreateCampaignsPage(driver);
		ccp.createCampaignWithClosedDate(Campaign, targetSize, closeDate);
		Thread.sleep(2000);
		String ConfMsg = cp.getConfMsg().getText();
		if(ConfMsg.contains(Campaign))
		{
			System.out.println("Campaign"+ Campaign+"added successfully");
		}
		else
		{
			System.out.println("Campaign not added successfully");
		}
		Thread.sleep(4000);
		dp.logout();
		/*driver.findElement(By.xpath("//div[@class='user-icon']")).click();
		WebElement logoutBtn = driver.findElement(By.xpath("//div[text()='Logout ']"));
		Actions action=new Actions(driver);
		action.moveToElement(logoutBtn).click().perform();*/
		//close the browser
		driver.quit();

	}

}
