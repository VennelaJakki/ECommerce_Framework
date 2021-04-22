package ecom.basetest;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import ecom.basepage.BasePage;
import ecom.pages.CartPage;
import ecom.pages.HomePage;
import ecom.pages.LoginPage;
import ecom.pages.ProductInfoPage;
import ecom.pages.RegisterPage;

public class BaseTest {
	public BasePage basepage;
	public Properties prop;
	public WebDriver driver;
	public LoginPage loginpage;
	public HomePage homepage;
	public ProductInfoPage productinfopage;
	public CartPage cartpage;
	public RegisterPage registerpage;
	
	@BeforeTest
	//@Parameters("browser")
	public void setUp() {
		basepage=new BasePage();
		prop=basepage.init_prop();		
		String browserName=prop.getProperty("browser");
		/*if(browser!=null) {
			browserName=browser;
		}else {
			browserName=prop.getProperty("browser");
		}*/
	    driver=basepage.init_driver(browserName);
		loginpage=new LoginPage(driver);
		driver.get(prop.getProperty("url"));
	}
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
