package com.qa.opencart.tests;
import static com.qa.opencart.constants.AppConstants.*;



import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.base.BaseTest;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
@Feature("F 50:open cart -login feature")
@Epic("Epic 100:design pages")
@Story("US 101:implement login page for open cart")
public class LoginPageTest extends BaseTest {
	
	@Severity(SeverityLevel.MINOR)
	@Test(description="checking login title")
	public void loginPageTitleTest() {
		String actTitle=loginPage.getLoginPageTitle();
		ChainTestListener.log("Checking login page title"+actTitle);
        Assert.assertEquals(actTitle,LOGIN_PAGE_TITLE);
		}
	
	
	@Test(description="checking login page Url")
	public void loginPageURLTest() {
		String actURL=loginPage.getLoginPageURL();
		Assert.assertTrue(actURL.contains(LOGIN_PAGE_FRACTION_URL));
		
	}
	
	@Test(description="forgotPasswordLinkExistTest")
	public void forgotPasswordLinkExistTest() {
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());
	}
	
    @Test(priority = Short.MAX_VALUE ,description="login with valid credentials")
	 public void doLoginTest() {
	     AccPage= loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
		Assert.assertEquals(AccPage.getAccPageTitle(),HOME_PAGE_TITLE);
	}
		
		
	
    
	
	
	
	
	
	
	
	
	
}
