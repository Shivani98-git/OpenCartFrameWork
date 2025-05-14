package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.constants.AppConstants.*;
import com.qa.opencart.utils.ExcelUtil;

public class RegisterPagetest extends BaseTest{

	
	@BeforeClass
	public void AccPageSetup() {
		registerPage=loginPage.navigateToRegisterPage();
	}	
	
	@DataProvider
	public Object[][] UserRegistrationTestData(){
		return new Object[][] {
			{"Shiv","rajesh","1234567890","shiv@987","yes"},
			{"kanishka","ramesh","9876543210","kanish@155","no"},
			{"roshni","ravi","1010101010","roshni@111","yes"}
				};
}
	
	@DataProvider
	public Object[][] getUserRegData() {
		Object regData[][]=ExcelUtil.getTestData(AppConstants.REGISTER_SHEET_NAME);
		return regData;
	}
	
		
	@Test(dataProvider="getUserRegData")
	public void Userregisterationtest(String firstName,String lastName,String telephone,String Password,String Subscribe) {
		Assert.assertTrue(registerPage.userRegistration(firstName,lastName,telephone,Password,Subscribe));
		
			}
	
	
	
	
	
	
	
	
	
	
}
