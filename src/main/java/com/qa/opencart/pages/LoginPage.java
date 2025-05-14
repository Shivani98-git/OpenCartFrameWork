package com.qa.opencart.pages;

import org.openqa.selenium.By;



import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

import static com.qa.opencart.constants.AppConstants.*;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	
	//By Locators
	private final By email= By.id("input-email");
	private final By password= By.id("input-password");
	private final By loginBtn=By.xpath("//input[@value='Login']");
	private final By forgotPasswordLink=By.linkText("Forgotten Password");
	private final By registerLink=By.linkText("Register");
	
	
	//Constructor
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
	}
		
	
	//public page actions/methods
	@Step("getting login page title")
	public String getLoginPageTitle()
	{
		String Title=eleUtil.waitFortitleis(LOGIN_PAGE_TITLE, DEFAULT_TIMEOUT);
		System.out.println("Login Page Title:"+Title);
        return Title;
	}
	
	@Step("getting login page URL")
	public String getLoginPageURL()
	{
		String Url=eleUtil.waitForURLContains(LOGIN_PAGE_FRACTION_URL,DEFAULT_TIMEOUT);
		System.out.println("Login Page url:"+Url);
		return Url;
	}
	
	
	@Step("checking  forgot passsword link")
	public boolean isForgotPwdLinkExist() {
		return eleUtil.isElementDisplayed(forgotPasswordLink);
	}
	
	@Step("login with valid:{0} and password{1}")
	public AccountsPage doLogin(String username, String pwd) {
		System.out.println("User Credentials:"+username+":"+pwd);
		eleUtil.waitForElementVisible(email, DEFAULT_TIMEOUT).sendKeys(username);
	    eleUtil.doSendKeys(password,pwd);
		eleUtil.doClick(loginBtn);
		
		return new AccountsPage(driver);
		
	}
	@Step("Navigating to the registeration page")
	public RegisterPage navigateToRegisterPage() {
		eleUtil.clickWhenReady(registerLink, DEFAULT_TIMEOUT);
		return new RegisterPage(driver);
	}
	
	
    
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
