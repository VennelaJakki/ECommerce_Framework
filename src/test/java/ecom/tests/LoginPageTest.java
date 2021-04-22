package ecom.tests;

import org.testng.Assert;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import ecom.basetest.BaseTest;
import ecom.listeners.ExtentReportListener;
import ecom.util.Constants;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
//@Listeners(ExtentReportListener.class)
@Epic("2981:Ecom HomePage")
@Story("RAS 9870 :HomePage")
public class LoginPageTest extends BaseTest {
@Description("LoginPage Title Test")	
@Severity(SeverityLevel.MINOR)
@Test
public void LoginPageTitleTest() {
	String title=loginpage.getLoginPageTitle();
	System.out.println("Login Page title is "+title);
	Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);
}
@Severity(SeverityLevel.TRIVIAL)
@Description("verify Continue Btn Test")
@Test
public void verifyContinueBtnTest() {	
	Assert.assertTrue(loginpage.verifyContinueBtn());
}
@Severity(SeverityLevel.NORMAL)
@Description("verify Forgot Password Link Test")
@Test
public void verifyForgotPasswordLinkTest() {	
	Assert.assertTrue(loginpage.verifyForgotPasswordLink());
}
@Severity(SeverityLevel.NORMAL)
@Description("verify Header Test")
@Test
public void verifyHeaderTest() {	
	Assert.assertTrue(loginpage.verifyHeader());
}
@Severity(SeverityLevel.BLOCKER)
@Description("Login Test")
@Test(priority=1)
public void doLoginTest() {
	loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
}


}
