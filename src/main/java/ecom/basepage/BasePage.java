package ecom.basepage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;
/**
 * This class is used to initialize driver and env variables
 * @author VENNELA
 *
 */

public class BasePage {
 public WebDriver driver;
 public Properties prop;
 private OptionsManager optionsManager;
 public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
 public static String highlight = null;
 /**
  * used to initialize browser
  * @param browser
  * @return driver
  */
 
 public WebDriver init_driver(String browser) {
	 highlight=prop.getProperty("highlight");
	 optionsManager = new OptionsManager(prop);
	 
	 System.out.println("Entered browser name "+browser);
	 if(browser.equalsIgnoreCase("chrome")) {
		 WebDriverManager.chromedriver().setup();
		 if(prop.getProperty("remote").equals("true")) {
			 init_remoteDriver("chrome");
		 }else {
		 
		 tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));}
	 }else if(browser.equalsIgnoreCase("firefox")) {
		 WebDriverManager.firefoxdriver().setup();
		 if(prop.getProperty("remote").equals("true")) {
			 init_remoteDriver("firefox");
		 }
		 else {
		 tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));}
	 }else if(browser.equalsIgnoreCase("safari")) {
		 //driver=new SafariDriver();
		 tlDriver.set(new SafariDriver());
	 }else
		 System.out.println("Browser implementation not defined for "+browser);
	 getDriver().manage().deleteAllCookies();
	 getDriver().manage().window().maximize();
	 getDriver().manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
	 getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	return getDriver(); 
 }
 
 private void init_remoteDriver(String browser) {
	 System.out.println("Running test on remote");
	if(browser.equals("chrome")) {
		new DesiredCapabilities();
		DesiredCapabilities cap=DesiredCapabilities.chrome();
		cap.setCapability(ChromeOptions.CAPABILITY, optionsManager.getChromeOptions());
		if(prop.getProperty("remote").equals("true")) {
			try {
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), cap));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
	}else if(browser.equals("firefox")) {
		new DesiredCapabilities();
		DesiredCapabilities cap=DesiredCapabilities.firefox();
		cap.setCapability(FirefoxOptions.FIREFOX_OPTIONS, optionsManager.getFirefoxOptions());
		if(prop.getProperty("remote").equals("true")) {
			try {
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), cap));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
	}
	
}

/**
	 * 
	 * @return
	 */
	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}
 /**
  * this method is used to load properties from config file
  * @return properties class object
  */
 public Properties init_prop() {
	 prop=new Properties();
	 try {
		FileInputStream fip=new FileInputStream(".\\src\\main\\java\\ecom\\config\\config.properties");
		prop.load(fip);
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
	 return prop;
 }
 public String getScreenshot() {
	 File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		// File srcFile = new File(src);

		String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);

		try {
			FileUtils.copyFile(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return path;
	 
 }
 
}
