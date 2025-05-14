package com.qa.opencart.pages;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

import static com.qa.opencart.constants.AppConstants.*;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.StringUtils;

public class RegisterPage {

	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	
	//By Locators
	private By firstName= By.id("input-firstname");
	private By lastName=By.id("input-lastname");
	private By email=   By.id("input-email");
	private By telephone= By.id("input-telephone");
    private By Confirmpassword=By.id("input-confirm");    
	private By SubscribeYes=By.xpath("(//label[@class='radio-inline'])[1]/input[@type='radio']");
	private By SubscribeNo=By.xpath("(//label[@class='radio-inline'])[2]/input[@type='radio']");
	
	private By 	agreeCheckBox=By.name("agree");
	private By continueButton=By.xpath("//input[@type='submit' and @value ='Continue']");
	
	private By successMesg= By.cssSelector("div#content h1");
	private By logoutLink = By.linkText("Logout");
	private By registerLink= By.linkText("Register");
	private By Password = By.id("input-password");
	//Constructor
    public RegisterPage(WebDriver driver)
	{
			this.driver=driver;
			eleUtil=new ElementUtil(driver);
	}
	
   
    public boolean userRegistration(String firstName,String lastName,String telephone,String Password,String Subscribe) {
    	eleUtil.waitForElementVisible(this.firstName, DEFAULT_TIMEOUT).sendKeys(firstName);
    	eleUtil.doSendKeys(this.lastName, lastName);
    	eleUtil.doSendKeys(this.email, StringUtils.getRandomEmaild());
    	eleUtil.doSendKeys(this.telephone, telephone);
    	eleUtil.doSendKeys(this.Password,Password);
    	eleUtil.doSendKeys(this.Confirmpassword,Password);
    	
    	if(Subscribe.equalsIgnoreCase("yes")) {
    		eleUtil.doClick(SubscribeYes);
    	}	else {
    		eleUtil.doClick(SubscribeNo);
    	}
    	eleUtil.doClick(agreeCheckBox);
    	eleUtil.doClick(continueButton);
    	
    	if(eleUtil.waitForElementVisible(successMesg, DEFAULT_TIMEOUT).getText().contains(SUCCESS_MESG)) {
    		eleUtil.doClick(logoutLink);
    		eleUtil.doClick(registerLink);
    		return true;
    	}
    	return false;
    }
    
    }
    
		
	
	
	
	
	
	

