package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class searchResultsPageTest extends BaseTest {

	
	@BeforeClass
	public void searchResultsPageSetup() {
		AccPage=loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	}
	
	@Test
	public void searchTest() {
		searchPage=AccPage.doSearch("Macbook");
		int actResultsCount=searchPage.getResultsProductCount();
		Assert.assertEquals(actResultsCount, 3);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
