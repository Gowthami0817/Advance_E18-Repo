package DDTPractice;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class DDTByPropertiesFile {
public static void main(String[] args) throws InterruptedException, IOException {
		
		FileInputStream fis=new FileInputStream("C:\\Users\\91830\\eclipse-workspace\\AdvanceSelenium_E18\\src\\test\\resources\\CommonData.properties");
		Properties prop=new Properties();
		prop.load(fis);
		String BROWSER = prop.getProperty("browser");
		String URL = prop.getProperty("url");
		String UN = prop.getProperty("uname");
		String PWD = prop.getProperty("pwd");
		System.out.println(BROWSER);
		System.out.println(URL);
		System.out.println(UN);
		System.out.println(PWD);

		
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
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		//Navigating to Ninja CRM
		driver.get("http://49.249.28.218:8098/ ");
		
		//Enter the username and password
		driver.findElement(By.id("username")).sendKeys("rmgyantra");
		driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999");
		
		//click on sign in button
		driver.findElement(By.xpath("//button[text()='Sign In']")).click();
		Thread.sleep(2000);
		
		//Verification of dashboard
		String actualURL=driver.getCurrentUrl();
		
		if(actualURL.equals(expectedURL)) {
			System.out.println("Validation is pass");
		}
		else {
			System.out.println("Validation is failed");
		}
		
		//logout
		driver.findElement(By.xpath("//div[@class='user-icon']")).click();
		WebElement logoutBtn = driver.findElement(By.xpath("//div[text()='Logout ']"));
		Actions action=new Actions(driver);
		action.moveToElement(logoutBtn).click().perform();
		//close the browser
		driver.quit();
		

	}


}
