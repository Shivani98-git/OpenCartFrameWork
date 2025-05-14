package com.qa.opencart.tests;

import static com.qa.opencart.constants.AppConstants.*;
import static com.qa.opencart.constants.AppConstants.*;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.pages.LoginPage;

public class AccountsPageTest extends BaseTest {

	@BeforeClass
	public void AccPageSetup() {
		AccPage=loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	}
	
	
	@Test
	public void AccountsPageTitleTest() {
		String actTitle=AccPage.getAccPageTitle();
		Assert.assertEquals(actTitle,HOME_PAGE_TITLE);
		
		}
	
	@Test
	public void AccountsPageUrlTest() {
		String actUrl=AccPage.getAccPageURL();
		Assert.assertTrue(actUrl.contains(HOME_PAGE_FRACTION_URL));		
		}
	
	
	
	
	
	
	
	
	
	
}
