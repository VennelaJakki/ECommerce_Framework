package ecom.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import ecom.basepage.BasePage;
import ecom.util.ElementUtil;

public class ProductInfoPage extends BasePage{
	private WebDriver driver;
	private ElementUtil elementutil;
private By productNameHeader=By.xpath("//div[@class='col-sm-4']/h1");
private By productData=By.xpath("(//div[@class='col-sm-4']//ul[@class='list-unstyled'])[1]/li");
private By productPrice=By.xpath("(//div[@class='col-sm-4']//ul[@class='list-unstyled'])[2]/li");
private By quantity=By.id("input-quantity");
private By addToCartButton=By.id("button-cart");
private By productImages=By.xpath("//ul[@class='thumbnails']/li//img");
private By successMsg=By.xpath("//div[@id='product-product']//div[@class='alert alert-success alert-dismissible']");
private By cart=By.id("cart-total");
private By viewCart=By.linkText("View Cart");

public ProductInfoPage(WebDriver driver) {
	this.driver=driver;
	elementutil=new ElementUtil(this.driver);
}
public String getProductInfoPageTitle(String productName) {
	return elementutil.waitForTitle(productName);
}
public Map<String,String> getProductInfo() {
	Map<String,String> productInfoMap=new HashMap<>();
	productInfoMap.put("Header", elementutil.getElement(productNameHeader).getText());
	List<WebElement> prodInfoList=elementutil.getElements(productData);
	for(WebElement e:prodInfoList) {
		String text=e.getText();
		productInfoMap.put(text.split(":")[0].trim(), text.split(":")[1].trim());
	}
	List<WebElement> prodPriceList=elementutil.getElements(productPrice);
	productInfoMap.put("price",prodPriceList.get(0).getText().trim());
	productInfoMap.put(prodPriceList.get(1).getText().split(":")[0].trim(),prodPriceList.get(1).getText().split(":")[1].trim());
	return productInfoMap;
}
public void selectQuantity(String quantity) {
	elementutil.doSendKeys(this.quantity, quantity);
}
public boolean addToCart(String productName) {
	elementutil.doClick(addToCartButton);
	//Success: You have added MacBook Pro to your shopping cart!
	String msg=elementutil.doGetText(successMsg);
	String correctMsg=msg.substring(0, msg.length()-2);
	System.out.println(correctMsg);
	
	if(correctMsg.equals("Success: You have added "+productName+" to your shopping cart!"))
		return true;
	else 
		return false;
}
public int getProductImagesCount() {
	int imageCount=elementutil.getElements(productImages).size();
	System.out.println("total no.of product images="+imageCount);
	return imageCount;
}
public CartPage viewCart() {
	elementutil.getElement(cart).click();
	elementutil.getElement(viewCart).click();
	return new CartPage(driver);
}

 


}
