package ecom.util;

import java.util.List;


import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ecom.basepage.BasePage;

public class ElementUtil {
	private WebDriver driver;
	private Alert alert;
	private JavaScriptUtil jsUtil;
public ElementUtil(WebDriver driver) {
	this.driver=driver;
	jsUtil = new JavaScriptUtil(driver);
}
public WebElement getElement(By locator) {
	WebElement element=driver.findElement(locator);
	if(BasePage.highlight.equals("true")) {
		jsUtil.flash(element);
	}
	return element;
}
public List<WebElement> getElements(By locator) {
	List<WebElement> elements=driver.findElements(locator);
	return elements;
}
public void doSendKeys(By locator, String text) {
	WebElement element=getElement(locator);
	element.clear();
	element.sendKeys(text);
}
public void doClick(By locator) {
	getElement(locator).click();
}
public String doGetText(By locator) {
	return getElement(locator).getText();
}
public boolean doIsDisplayed(By locator) {
	return getElement(locator).isDisplayed();
}
public void doLinkClick(By locator, String value) {
	List<WebElement> elements=getElements(locator);
	System.out.println("Total links="+elements.size());
	for(WebElement e:elements) {
		if(e.getText().equals(value)) {
			e.click();
			break;
		}
	}
}
public String waitForTitle(String title) {
	WebDriverWait wait=new WebDriverWait(driver, 10);
	wait.until(ExpectedConditions.titleIs(title));
	return driver.getTitle();
}
public String getAlertText() {
	alert=driver.switchTo().alert();
	return alert.getText();
}
}
