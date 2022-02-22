package testCases;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.testBase;

public class TC_001_AccountRegistration extends testBase
{
	
	@Test(groups= {"regression","master"})
	public void test_account_register() throws Exception {
		
		logger.info(" Starting TC_Login ");
		
		try
		{
		HomePage hp = new HomePage(driver);
		
		
		hp.clickMyaccount();
		hp.clickRegister();
		logger.info("Clicked on registration link");
		
		AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
		regpage.setFirstName("John");
		logger.info("Added username");
		regpage.setLastName("Canedy");
		regpage.setEmail(randomString()+"@gmail.com");
		regpage.setTelephone("65656565");
		regpage.setPassword("abcxyz");
		regpage.setConfirmPassword("abcxyz");
		regpage.setPrivacyPolicy();
		
		regpage.clickContinue();
		logger.info("Submitted form");
		String confmsg=regpage.getConfirmationMsg();
		
		if(confmsg.equals("Your Account Has Been Created!"))
		{
			Assert.assertTrue(true);
			logger.info("TC passed");
		}
		else
		{
			captureScreenshot(driver, "test_account_register"); //else part to get screenshot of failed test case
			Assert.assertTrue(false);
			logger.info("failed TC");
		}
		
		logger.info(" Finished TC_Login ");
	}
		catch(Exception ex) {
			throw ex;
		}
	}

	
}
