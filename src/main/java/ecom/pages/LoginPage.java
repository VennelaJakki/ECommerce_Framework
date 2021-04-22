package ecom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import ecom.basepage.BasePage;
import ecom.util.Constants;
import ecom.util.ElementUtil;
import io.qameta.allure.Step;

public class LoginPage extends BasePage{
	
	private WebDriver driver;
	private ElementUtil elementutil;
	//1.By locators or Object Repository
	// we are making the locators private -- encapsulation
	private By email=By.name("email");
	private By pass=By.name("password");
	private By login=By.xpath("//input[@type='submit']");
	private By forgot=By.linkText("Forgotten Password");
	private By continueBtn=By.linkText("Continue");
	private By header=By.linkText("Your Store");
	
	//2.constructor of the login page class
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		elementutil=new ElementUtil(this.driver);
	}
	
	//3.page actions: features of the page
	@Step("getting Login Page Title...")
	public String getLoginPageTitle() {
		return elementutil.waitForTitle(Constants.LOGIN_PAGE_TITLE);
		
	}
	@Step("getting Login Page Title...")
	public boolean verifyContinueBtn() {
		return elementutil.doIsDisplayed(continueBtn);
	}
	@Step("getting Login Page header...")
	public boolean verifyHeader() {
		return elementutil.doIsDisplayed(header);
	}
	@Step("verifying forgot Password link ...")
	public boolean verifyForgotPasswordLink() {
		return elementutil.doIsDisplayed(forgot);
	}
	@Step("Logging into the application with username : {0} and password : {1}...")
	public HomePage doLogin(String uname,String pswd) {
		System.out.println("Login with "+uname+" "+pswd);
		elementutil.doSendKeys(email, uname);
		elementutil.doSendKeys(pass, pswd);
		elementutil.doClick(login);
		return new HomePage(driver);
	}
	@Step("Registering new account...")
	public RegisterPage register() {
		elementutil.doClick(continueBtn);
		return new RegisterPage(driver);
		
	}

}
