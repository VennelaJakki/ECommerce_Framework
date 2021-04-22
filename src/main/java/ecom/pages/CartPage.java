package ecom.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import ecom.basepage.BasePage;
import ecom.util.Constants;
import ecom.util.ElementUtil;

public class CartPage extends BasePage{
	private WebDriver driver;
	private ElementUtil elementutil;
	private By checkout=By.linkText("Checkout");
	private By header=By.cssSelector("#content h1");
	private By columns=By.xpath("(//div[@class='table-responsive']//tr)[1]/td");
	
	public CartPage(WebDriver driver) {
		this.driver=driver;
		elementutil=new ElementUtil(this.driver);
	}
	public String getCartPageTitle() {
		return elementutil.waitForTitle(Constants.CART_PAGE_TITLE);
	}
	public boolean checkoutButtonDisplay() {
		return elementutil.getElement(checkout).isDisplayed();
	}
	public String verifyCartPageHeader() {
		return elementutil.doGetText(header);
	}
	public List<String> verifyColumnNames() {
		List<WebElement> columns=elementutil.getElements(this.columns);
		List<String> columnNames=new ArrayList<>();
		for(WebElement e:columns) {
			columnNames.add(e.getText());
		}
		return columnNames;
		
	}

}
