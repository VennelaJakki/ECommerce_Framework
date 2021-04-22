package ecom.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.*;

import ecom.basetest.BaseTest;
import ecom.util.Constants;

public class HomePageTest extends BaseTest{
	@BeforeClass
	public void homePageSetUp() {
		homepage=loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	@Test(priority=1)
	public void HomePageTitleTest() {
		String title=homepage.getHomePageTitle();
		System.out.println("Home Page title is "+title);
		Assert.assertEquals(title, Constants.HOME_PAGE_TITLE);
	}
	@Test(priority=2)
	public void verifyHeaderTest() {
		String headerText=homepage.verifyHeader();
		System.out.println("Header is "+headerText);
		Assert.assertEquals(headerText, Constants.HOME_PAGE_HEADER);
	}
	@Test(priority=3)
	public void verifySubHeadersCountTest() {
		Assert.assertEquals(homepage.verifySubHeadersCount(),Constants.SUBHEADERS_COUNT);
		
	}
	@Test(priority=4)
	public void verifySubHeadersTest() {
		List<String> subHeadersText=homepage.verifySubHeaders();
		for(String s:subHeadersText) {
		System.out.println(s);}
		Assert.assertEquals(subHeadersText, Constants.expectedSubHeadList());
	}
	@Test(priority=5)
	public void verifySearchFeatureTest() {
		//verifying whether search results are getting displayed or not
		Assert.assertTrue(homepage.doSearch("MacBook"));
	}

}
