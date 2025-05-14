package com.qa.opencart.base;

import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import org.testng.annotations.Parameters;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.searchResultsPage;
//@Listeners(ChainTestListener.class)
public class BaseTest {

       	WebDriver driver;
	
	    DriverFactory df;
	    protected Properties prop;
	    
	    protected LoginPage loginPage;
	   
	    protected AccountsPage AccPage;
	    
	    protected searchResultsPage searchPage;
	    
	    protected ProductInfoPage ProductPage;
	    
	    protected RegisterPage registerPage;
	    
	    private static final Logger log = LogManager.getLogger(BaseTest.class);
	    
	    @Parameters({"browser"})
	    @BeforeTest
	     public void setup(String browserName) {
	    	 
	    	 df=new DriverFactory();
	    	 prop=df.initProp();
	    	 
	    	 if(browserName!=null)
	    	 {
	    		prop.setProperty("browser", browserName);
	    	 }
	    	 driver=df.initDriver(prop);
	    	 loginPage= new LoginPage(driver);
	    	 
	    	}
	    @AfterTest
     	public void tearDown() {
		driver.quit();
		
	}
	    
	    @AfterMethod //will be running after each @test method
		public void attachScreenshot(ITestResult result) {
			if(!result.isSuccess()) {//only for failure test cases -- true
				log.info("---screenshot is taken---");
				ChainTestListener.embed(DriverFactory.getScreenshotFile(), "image/png");
			}
			
			//ChainTestListener.embed(DriverFactory.getScreenshotFile(), "image/png");


		}
	
	
	
	
	
	
	
	
	
	
	
}
