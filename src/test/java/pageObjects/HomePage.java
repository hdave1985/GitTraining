package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage 
{
	public WebDriver driver;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
//	webelements and action methods 
	
//	Webelements
	
//	elements and action methods everywhere
	
	
	@FindBy(xpath="//span[text()='My Account']")
	public WebElement lnkmyaccount;
	
	@FindBy(linkText="Register")
	WebElement lnkRegister;
	
	@FindBy(linkText="Login")
	WebElement lnkLogin;
	
	
	public void clickMyaccount() {
		lnkmyaccount.click();
	}
	
	public void clickRegister() {
		lnkRegister.click();
	}
	
	public void clickLogin()
	{
		lnkLogin.click();
	}
}
