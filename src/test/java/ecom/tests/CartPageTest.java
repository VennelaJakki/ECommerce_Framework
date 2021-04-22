package ecom.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import ecom.basetest.BaseTest;
import ecom.util.Constants;

public class CartPageTest extends BaseTest{
	@BeforeClass
	public void cartPageSetUp() {
		homepage=loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		homepage.doSearch("MacBook");
		productinfopage=homepage.selectProduct("MacBook Pro");
		productinfopage.addToCart("MacBook Pro");
		cartpage=productinfopage.viewCart();
		}
	@Test
	public void cartPageTitleTest() {
		Assert.assertEquals(cartpage.getCartPageTitle(),Constants.CART_PAGE_TITLE);
	}
	@Test
	public void checkoutButtonDisplayTest() {
		Assert.assertTrue(cartpage.checkoutButtonDisplay());
	}
	@Test
	public void cartPageHeaderTest() {
		Assert.assertEquals(cartpage.verifyCartPageHeader(),"Shopping Cart  (0.00kg)");
	}
	@Test
	public void verifyColumnNamesTest() {
		Assert.assertEquals(cartpage.verifyColumnNames(), Constants.expectedColumnList());
	}
}
