package ecom.pages;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import ecom.basepage.BasePage;
import ecom.util.Constants;
import ecom.util.ElementUtil;

public class RegisterPage extends BasePage{
	private WebDriver driver;
	private ElementUtil elementutil;
	private By login=By.linkText("login page");
	
	private By firstName=By.name("firstname");
	private By lastName=By.name("lastname");
	private By email=By.name("email");
	private By telephone=By.name("telephone");
	
	private By password=By.name("password");
	private By confirmPswd=By.name("confirm");
	
	private By newsletter_yes=By.xpath("//input[@name='newsletter'][position()='1']");
	//private By newsletter_no=By.xpath("//input[@name='newsletter'][position()='0']");
	
	private By checkbox=By.name("agree");
	private By continueBtn=By.xpath("//input[@type='submit']");
	
	private By successHeader=By.xpath("//div[@id='content']/h1");	
	private By logout=By.linkText("Logout");
	private By registerLink = By.linkText("Register");
	
	public RegisterPage(WebDriver driver) {
		this.driver=driver;
		elementutil=new ElementUtil(this.driver);
	}
	public String getRegisterPageTitle() {
		return elementutil.waitForTitle(Constants.REGISTER_PAGE_TITLE);		
	}
	public boolean loginLinkDisplay() {
		return elementutil.doIsDisplayed(login);
	}
	public LoginPage returnToLoginPage() {
		if(elementutil.doIsDisplayed(login)) {
		elementutil.doClick(login);}
		return new LoginPage(driver);
	}
	public boolean registerAccount(String firstName,String lastName,String telephone,String password,String newsletter) {
		elementutil.doSendKeys(this.firstName, firstName);
		elementutil.doSendKeys(this.lastName, lastName);
		Random random=new Random();
		int rand=random.nextInt(1000);
		String email=firstName+rand+"@gmail.com";
		
		elementutil.doSendKeys(this.email, email);
		elementutil.doSendKeys(this.telephone, telephone);
		elementutil.doSendKeys(this.password, password);
		elementutil.doSendKeys(confirmPswd, password);
		if(newsletter.equals("yes")) {
			elementutil.doClick(newsletter_yes);
			}
		elementutil.doClick(checkbox);
		elementutil.doClick(continueBtn);
		String msg=elementutil.doGetText(successHeader);
		System.out.println(msg);
		if(msg.equals(Constants.REGISTER_PAGE_SUCCESS_HEADER)) {
			elementutil.doClick(logout);
			elementutil.doClick(registerLink);
			return true;
		}else
			return false;
		
		
	}
}
