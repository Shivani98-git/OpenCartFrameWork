package com.qa.opencart.tests;
import static com.qa.opencart.constants.AppConstants.*;
import java.util.HashMap;
import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.CSVUtil;

public class ProductInfoPageTest extends BaseTest {

	@BeforeClass
	public void ProductInfoPageSetup() {
		AccPage=loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	}
	@DataProvider
	public Object[][] getProductHeaderTestData(){
		return new Object[][] {
			{"macbook","MacBook Pro"},
			{"macbook","MacBook Air"},
			{"imac","iMac"},
			{"samsung","Samsung SyncMaster 941BW"},
			{"samsung","Samsung Galaxy Tab 10.1"}
		};
	}
	
	@Test(dataProvider="getProductHeaderTestData")	
	public void ProductHeaderTest(String SearchKey,String ProductName) {
		searchPage=AccPage.doSearch(SearchKey);
		ProductPage=searchPage.selectProduct(ProductName);
		String ActProductHeader=ProductPage.getProductHeader();
		Assert.assertEquals(ActProductHeader,ProductName);
		
	}
	@DataProvider
	public Object[][] getProductImageCountTestData(){
		return new Object[][] {
			{"macbook","MacBook Pro",4},
			{"macbook","MacBook Air",4},
			{"imac","iMac",3},
			{"samsung","Samsung SyncMaster 941BW",1},
			{"samsung","Samsung Galaxy Tab 10.1",7}
		};
	}
	
	@DataProvider
	public Object[][] getproductCsvData(){
		return CSVUtil.csvData("product");
	}
	
	
	

	@Test(dataProvider="getproductCsvData")
	public void ProductImageCountTest(String SearchKey,String ProductName,String ImageCount) {
		searchPage=AccPage.doSearch(SearchKey);
		ProductPage=searchPage.selectProduct(ProductName);
		int ActProductCount=ProductPage.getProductImagesCount();
		Assert.assertEquals(String.valueOf(ActProductCount), ImageCount);
		
	}
	
	@Test	
     public void ProductInfoTest() {
        searchPage =AccPage.doSearch("Macbook");
       	ProductPage=searchPage.selectProduct("MacBook Pro");
	   Map<String,String> ActProductDetailsMap= ProductPage.getProductDetailsMap();
	   SoftAssert softAssert=new SoftAssert();
	   softAssert.assertEquals(ActProductDetailsMap.get("Brand"),"Apple");
	   softAssert.assertEquals(ActProductDetailsMap.get("Product Code"),"Product 18");
	   softAssert.assertEquals(ActProductDetailsMap.get("Reward Points"),"800"); 
	   softAssert.assertEquals(ActProductDetailsMap.get("Availability"),"Out Of Stock");
	   softAssert.assertEquals(ActProductDetailsMap.get("productPrice"),"$2,000.00");
	   softAssert.assertEquals(ActProductDetailsMap.get("extaxPrice"),"$2,000.00");
	   softAssert.assertAll();
	   
	   
	}
		
	}
	
	
	

