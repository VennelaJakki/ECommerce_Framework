package ecom.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import ecom.basetest.BaseTest;

public class ProductInfoTest extends BaseTest {

@BeforeClass
public void productInfoSetUp() {
	homepage=loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

@Test(enabled=true,priority=2)
public void verifyProductInfoTest_MacBookPro() {
	String prodName="MacBook Pro";
	homepage.doSearch("MacBook");
	productinfopage=homepage.selectProduct(prodName);
	Assert.assertEquals(productinfopage.getProductInfoPageTitle(prodName),prodName);
	Assert.assertTrue(productinfopage.getProductImagesCount()==4);
	Map<String,String>prodInfoMap=productinfopage.getProductInfo();
	System.out.println(prodInfoMap);
//{Brand=Apple, Availability=Out Of Stock, Header=MacBook Pro, price=$2,000.00, Ex Tax=$2,000.00, Product Code=Product 18, Reward Points=800}
	Assert.assertEquals(prodInfoMap.get("Brand"), "Apple");
	Assert.assertEquals(prodInfoMap.get("Header"), "MacBook Pro");
	Assert.assertEquals(prodInfoMap.get("Availability"), "Out Of Stock");
	Assert.assertEquals(prodInfoMap.get("price"), "$2,000.00");
	Assert.assertEquals(prodInfoMap.get("Ex Tax"), "$2,000.00");
	Assert.assertEquals(prodInfoMap.get("Product Code"), "Product 18");
	Assert.assertEquals(prodInfoMap.get("Reward Points"), "800");
	
	
}
@Test(enabled=true,priority=1)
public void verifyProductInfoTest_iMac() {
	String prodName="iMac";
	homepage.doSearch(prodName);
	productinfopage=homepage.selectProduct(prodName);
	Assert.assertEquals(productinfopage.getProductInfoPageTitle(prodName),prodName);
	Assert.assertTrue(productinfopage.getProductImagesCount()==3);
	Map<String,String>prodInfoMap=productinfopage.getProductInfo();
	System.out.println(prodInfoMap);
//{Brand=Apple, Availability=Out Of Stock, Header=MacBook Pro, price=$2,000.00, Ex Tax=$2,000.00, Product Code=Product 18, Reward Points=800}
	Assert.assertEquals(prodInfoMap.get("Brand"), "Apple");
	Assert.assertEquals(prodInfoMap.get("Header"), prodName);
	Assert.assertEquals(prodInfoMap.get("Availability"), "Out Of Stock");
	Assert.assertEquals(prodInfoMap.get("price"), "$100.00");
	Assert.assertEquals(prodInfoMap.get("Ex Tax"), "$100.00");
	Assert.assertEquals(prodInfoMap.get("Product Code"), "Product 14");
	
}
@Test(priority=3)
public void addToCartTest() {
	String prodName="MacBook Pro";
	homepage.doSearch("MacBook");
	productinfopage=homepage.selectProduct(prodName);
	productinfopage.selectQuantity("1");
	Assert.assertTrue(productinfopage.addToCart(prodName));
	
}
}
