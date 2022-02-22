package testBase;
//base class created here

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ResourceBundle;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class testBase 
{
	
	public WebDriver driver;
	public Logger logger; //For logging
	public ResourceBundle rb; ////to read config.properties
//	from java.util package ResourceBundle
	
	
	@BeforeClass(groups= {"master", "sanity","regression"})
	@Parameters({"browser"})
	public void setup(String br) {
		//Load config.properties
		logger = LogManager.getLogger(this.getClass());
		rb = ResourceBundle.getBundle("config");
		logger.info("config file loaded");
		
		if(br.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			logger.info("chrome launched");
		}
		else if(br.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			logger.info("edge launched");
		}
		else if(br.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			logger.info("firefox launched");
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
//		driver.get("https://demo.opencart.com/");
		driver.get(rb.getString("appUrl"));
//		logger = Logger.getLogger("OrderTree");
//		PropertyConfigurator.configure(null);
		//Log4j Logging
	
	}
	
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	
	public String randomString() {
	
		String generatedRandomstr = RandomStringUtils.randomAlphabetic(5);
		return generatedRandomstr;
	}
	
	public int randomNumber() {
		String generateRandomno = RandomStringUtils.randomNumeric(5);
		return Integer.parseInt(generateRandomno);
	}
	
//	as method called from test case class so it will have argument 
//	of webdriver driver and testmethod which is failing
	
	public void captureScreenshot(WebDriver driver, String testname) 
	{
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir")+"\\screenshots\\" + testname + ".png");
		try {
			FileUtils.copyFile(source, target);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
