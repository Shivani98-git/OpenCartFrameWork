package com.qa.opencart.pages;
import static com.qa.opencart.constants.AppConstants.*;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private final By headers=By.cssSelector("div#content > h2");
	private final By search=By.name("search");
	private final By searchIcon= By.cssSelector("div#search button");
	
	
	public AccountsPage(WebDriver driver)
	    {
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
		}

	public String getAccPageTitle() {
		String Title=eleUtil.waitFortitleis(HOME_PAGE_TITLE, DEFAULT_TIMEOUT);
		System.out.println("Home Page Title:"+Title);
        return Title;
        }
	
	public String getAccPageURL() {
		String Url=eleUtil.waitForURLContains(HOME_PAGE_FRACTION_URL, DEFAULT_TIMEOUT);
		System.out.println("Home Page Url:"+Url);
        return Url;
        }
	
	public List<String> getAccountsPageHeaders() {
		List<WebElement> headerList=eleUtil.getElements(headers);
		List<String> headerValList=new ArrayList<String>();
		for(WebElement e:headerList) {
			String text=e.getText();
			headerValList.add(text);
		}
		System.out.println("Acc page headers:"+ headerValList);
		return headerValList;
	}
	
	public searchResultsPage doSearch(String SearchKey) {
		eleUtil.doSendKeys(search,SearchKey);
		eleUtil.doClick(searchIcon);
		return new searchResultsPage(driver);
	}
		
		
		
		
		
		
		
		
		
		
	}
	
	
	
	
	
		
		
	
	
	
	
	
	
	
	
	
	
	
	


