package ecom.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ecom.basetest.BaseTest;
import ecom.util.Constants;
import ecom.util.ExcelUtil;

public class RegisterPageTest extends BaseTest{
@BeforeClass
public void registerPageSetUp() {
	registerpage=loginpage.register();
}
@DataProvider
public Object[][] getRegisterData() {
	Object data[][]=ExcelUtil.getExcelData(Constants.REGISTER_DATA_SHEET_NAME);
	return data;
	
}
@Test(priority=-1)
public void RegisterPageTitleTest() {
	String title=registerpage.getRegisterPageTitle();
	System.out.println("Register page title is "+title);
	Assert.assertEquals(title, Constants.REGISTER_PAGE_TITLE);
}
@Test(priority=0)
public void loginLinkDisplayTest() {
	Assert.assertTrue(registerpage.loginLinkDisplay());
}
@Test(priority=1,dataProvider="getRegisterData")
public void registerAccountTest(String firstname,String lastname,String telephone,String password,String newsletter) {
	
	Assert.assertTrue(registerpage.registerAccount(firstname,lastname,telephone,password,newsletter));
}

}
