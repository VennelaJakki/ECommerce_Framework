package ecom.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import ecom.util.Constants;
import ecom.util.ElementUtil;

public class HomePage {
	private WebDriver driver;
	private ElementUtil elementutil;
private By header=By.linkText("Your Store");
private By subHeaders=By.xpath("(//div[@id='content']/h2)");

private By searchIcon=By.cssSelector(".btn-default");
private By searchText=By.name("search");
//private By cameras=By.linkText("Cameras");
private By searchResults=By.cssSelector(".product-layout .product-thumb");
private By searchResultsLinks=By.cssSelector(".product-layout .product-thumb a");


public HomePage(WebDriver driver) {
	this.driver=driver;
	elementutil=new ElementUtil(this.driver);
}
public String getHomePageTitle() {
	return elementutil.waitForTitle(Constants.HOME_PAGE_TITLE);
}
public String verifyHeader() {
	if(elementutil.doIsDisplayed(header)) {
		return elementutil.doGetText(header);
	}
	return null;
}
public int verifySubHeadersCount() {
	return elementutil.getElements(subHeaders).size();
}
public List<String> verifySubHeaders() {
	List<String> subHeadersText=new ArrayList<String>();
	List<WebElement> subHeadersList=elementutil.getElements(subHeaders);
	if(subHeadersList.size()>0) {
		for(WebElement e:subHeadersList) {
			subHeadersText.add(e.getText());
		}
		}
	return subHeadersText;
}
public boolean doSearch(String productName) {
	elementutil.doSendKeys(searchText, productName);
	elementutil.doClick(searchIcon);
	int count=elementutil.getElements(searchResults).size();
	System.out.println("No.of search results="+count);
	if(count>0)
		return true;
	else
		return false;
	}
public ProductInfoPage selectProduct(String productName) {
	List<WebElement> searchProductsLinksList=elementutil.getElements(searchResultsLinks);
	System.out.println("No.of links in search results="+searchProductsLinksList.size());
	for(WebElement e:searchProductsLinksList) {
		String prodName=e.getText();
		if(prodName.equalsIgnoreCase(productName)) {
			e.click();
			break;
		}
	}
	
	return new ProductInfoPage(driver);	
	
}
		
	}

