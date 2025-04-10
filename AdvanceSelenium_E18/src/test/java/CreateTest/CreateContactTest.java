package CreateTest;

import java.io.IOException;
import java.time.Duration;
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
import ObjectRepository.CampaignsPage;
import ObjectRepository.ContactsPage;
import ObjectRepository.CreateCampaignsPage;
import ObjectRepository.CreateContactsPage;
import ObjectRepository.DashboardPage;
import ObjectRepository.LoginPage;
import genericBaseClassUtility.BaseClass;
import genericListenerUtility.ListenerImp;
//@Listeners(ListenerImp.class)

public class CreateContactTest extends BaseClass {
	
	//@Parameters("browser")
	@Test()
	public void createContactWithCampaignTest() throws IOException, InterruptedException {
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
		
		//Random ran=new Random();
		//int randomNum = ran.nextInt(1000);
		
		ExcelFileUtility exUtil=new ExcelFileUtility();
		String Campaign = exUtil.readingDataFromExcel("DDTTestcase_ID", 1, 2)+randomNum;
		String targetSize = exUtil.readingDataFromExcel("DDTTestcase_ID", 1, 3);
		String organization = exUtil.readingDataFromExcel("Contact", 1, 2)+randomNum;
		String title = exUtil.readingDataFromExcel("Contact", 1, 3);
		String contactName = exUtil.readingDataFromExcel("Contact", 1, 4)+randomNum;
		String mobile = exUtil.readingDataFromExcel("Contact", 1, 5);
		
		/*FileInputStream fis1=new FileInputStream("C:\\Users\\91830\\eclipse-workspace\\AdvanceSelenium_E18\\src\\test\\resources\\TestScriptData_E18.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
		String Campaign = wb.getSheet("DDTTestcase_ID").getRow(1).getCell(2).getStringCellValue()+randomNum;
		String targetSize= wb.getSheet("DDTTestcase_ID").getRow(1).getCell(3).getStringCellValue();
		String organization = wb.getSheet("Contact").getRow(1).getCell(2).getStringCellValue()+randomNum;
		String title = wb.getSheet("Contact").getRow(1).getCell(3).getStringCellValue();
		String contactName = wb.getSheet("Contact").getRow(1).getCell(4).getStringCellValue()+randomNum;
		String mobile = wb.getSheet("Contact").getRow(1).getCell(5).getStringCellValue();*/
		
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
		//driver.get(URL);
		//LoginPage lp=new LoginPage(driver);
		//lp.login(UN, PWD);
		
		DashboardPage dp=new DashboardPage(driver);
		Thread.sleep(7000);
		dp.getCampaignsLink().click();
		
		CampaignsPage cp=new CampaignsPage(driver);
		cp.getCreateCampaignBtn().click();
		
		CreateCampaignsPage ccp=new CreateCampaignsPage(driver);
		ccp.createCampaignWithMandatoryFields(Campaign, targetSize);
		Thread.sleep(4000);
		
		/*driver.findElement(By.id("username")).sendKeys(UN);
		driver.findElement(By.id("inputPassword")).sendKeys(PWD);
		driver.findElement(By.xpath("//button[text()='Sign In']")).click();
		Thread.sleep(2000);
		driver.findElement(By.linkText("Campaigns")).click();
		driver.findElement(By.xpath("//span[text()='Create Campaign']")).click();
		driver.findElement(By.name("campaignName")).sendKeys(Campaign);
		driver.findElement(By.name("targetSize")).sendKeys(targetSize);
		driver.findElement(By.xpath("//button[text()='Create Campaign']")).click();*/
		
		WebElement contactLink=dp.getContactsLink();
		WebDriverUtility Wutil=new WebDriverUtility();
		Wutil.waitForElementToBeClickable(driver, contactLink,20);
		contactLink.click();
		//WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
		//wait.until(ExpectedConditions.elementToBeClickable(contactLink));
		//contactLink.click();
		Thread.sleep(4000);
		ContactsPage ccp1=new ContactsPage(driver);
		WebElement createContactBtn = ccp1.getCreateContactBtn();
		Wutil.waitForElementToBeClickable(driver, createContactBtn, 20);
		//WebDriverWait wait1=new WebDriverWait(driver, Duration.ofSeconds(20));
		//wait.until(ExpectedConditions.visibilityOf(createContactBtn));
		createContactBtn.click();
		Thread.sleep(5000);
		CreateContactsPage cct=new CreateContactsPage(driver);
		cct.createContactWithCampaign(organization, title, contactName, mobile, "selectCampaign", "create-contact", Campaign);
		Thread.sleep(4000);
		/*driver.findElement(By.name("organizationName")).sendKeys(organization);
		driver.findElement(By.name("title")).sendKeys(title);
		driver.findElement(By.name("contactName")).sendKeys(contactName);
		driver.findElement(By.name("mobile")).sendKeys(mobile);
		driver.findElement(By.xpath("//button[@type='button' and contains(@style,'white-space')]")).click();*/
		
		//Wutil.switchToWindow(driver, "selectCampaign");
		/*Set<String> allWindowIds = driver.getWindowHandles();
		for(String Window:allWindowIds) 
		{
			driver.switchTo().window(Window);
			String actUrl = driver.getCurrentUrl();
			
				if(actUrl.contains("selectCampaign")) 
				{
					break;
				}
		}*/
		
		//WebElement selectTypeDD = driver.findElement(By.id("search-criteria"));
	    //Select select1=new Select(selectTypeDD);
	    //Wutil.select(selectTypeDD, "campaignName");
	    //select1.selectByValue("campaignName");
	   // driver.findElement(By.id("search-input")).sendKeys(Campaign);
	    //driver.findElement(By.xpath("//button[@class='select-btn']")).click();
	    
	    //Wutil.switchToWindow(driver, "create-contact");  
	    /*for(String Window:allWindowIds)
	    {
	  		driver.switchTo().window(Window);
	  		String actUrl = driver.getCurrentUrl();
	  		
	  		if(actUrl.contains("create-contact"))
	  			{
	  				break;
	  			}
	  	}*/
	    
		/*driver.findElement(By.xpath("//button[text()='Create Contact']")).click();
		Thread.sleep(3000);*/
		String ConfirmationMsg = ccp1.getConfMsg().getText();
		boolean status = ConfirmationMsg.contains(contactName);
		Assert.assertEquals(status,true,"Contact not added");
		Reporter.log("Contact name"+contactName+"added successfully",true);
		/*if(ConfirmationMsg.contains(contactName)) 
		{
			Reporter.log("Contact added successfully",true);
		}
		else
		{
			Reporter.log("Contact not added",true);
		}*/
		Thread.sleep(4000);
		//dp.logout();
		/*driver.findElement(By.xpath("//*[name()='svg' and @role=\"img\"]")).click();
		WebElement logout = driver.findElement(By.xpath("//div[text()='Logout ']"));
		Actions action=new Actions(driver);
		action.moveToElement(logout).click().perform();*/
		//driver.quit();
			
			
		}
		



}
