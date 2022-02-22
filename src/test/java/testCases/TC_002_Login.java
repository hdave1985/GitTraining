package testCases;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import testBase.testBase;

public class TC_002_Login extends testBase
{

	@Test(groups= {"sanity","master"})
	public void test_login() throws Exception 
	{
		
		logger.info("Starting test login method started");	
		try
		{
			
		
		driver.get(rb.getString("appUrl"));
		HomePage hp = new HomePage(driver);
		hp.clickMyaccount();
		logger.info("Clicked My account");
		hp.clickLogin();

		logger.info("Clicked login");
		
		
		
		LoginPage lp = new LoginPage(driver);
		
		
		lp.setEmail(rb.getString("email"));
		logger.info("Passed username");
		lp.setPassword(rb.getString("password"));
		logger.info("Passed password");
		lp.clickLogin();
		logger.info("Clicked Login button");
		
		boolean targetmsg = lp.isMyAccountPageExists();
		
				if(targetmsg)
				{
					logger.info("Login scuccessful");
					Assert.assertTrue(true);
				}
				else
				{
					logger.info("Login failed");
					captureScreenshot(driver, "test_login");
					Assert .assertTrue(false);
				}
		
		}
	  catch (Exception e) 
		{
				// TODO: handle exception
				logger.fatal("Exception occurred login failed");
				Assert.fail();
				throw e;
			}
		
		logger.info(" Finished TC_002_Login   ");

	}
		
}


