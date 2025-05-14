package com.qa.opencart.utils;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeoutException;

import javax.lang.model.element.Element;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.factory.DriverFactory;

public class ElementUtil {

	private WebDriver driver;
	private Actions Act;
	JavascriptUtil jsUtil;
	
	public ElementUtil(WebDriver driver) {
		this.driver=driver;
		Act=new Actions(driver);
		jsUtil=new JavascriptUtil(driver);
		}
	private void nullcheck(CharSequence... value) {
		if(value == null) {
			throw new RuntimeException("VALUE CANNOT BE NULL");
		}
	}
	public void doSendKeys(By Locator,String value)
	{
		nullcheck(value);
		getElement(Locator).clear();
		getElement(Locator).sendKeys(value);
	}
	
	public void doSendKeys(By Locator,CharSequence... value)
	{
		nullcheck(value);
		getElement(Locator).sendKeys(value);
	}
	
	
	public boolean isElementDisplayed(By Locator) {
	  try {
		 return getElement(Locator).isDisplayed();
	} catch (NoSuchElementException e) {
		//e.printStackTrace();
		System.out.println("Element is not displayed");
		return false;
	}}
	
	public List<WebElement> getElements(By Locator){
		
		return  driver.findElements(Locator);	
	}
	
	public boolean checkElementDisplayed(By Locator) {
		if(getElements(Locator).size()==1) {
			System.out.println("Element is present one time");
			return true;
		}
		
		return false;
	}
	
	public boolean checkElementDisplayed(By Locator,int elementCount) {
		if(getElements(Locator).size()==elementCount) {
			System.out.println(elementCount+"Elements are present ");
			return true;
		}
		return false;
	}
	
	public WebElement getElement(By Locator) {
		ChainTestListener.log("Locator:"+ Locator.toString());
		WebElement element=driver.findElement(Locator);
		highlightElement(element);
		return element;
	}
	public void highlightElement(WebElement element) {
		if(Boolean.parseBoolean(DriverFactory.highlight)) {
			jsUtil.flash(element);
		}
	}
	
	public WebElement getElement(By Locator,int Timeout) {
		return waitForElementVisible(Locator,Timeout);
	}
	
	
	
	public void doClick(By Locator) {
		
		getElement(Locator).click();
	}
	
	public String doElementgetText(By Locator) {
		String Text=getElement(Locator).getText();
		System.out.println("Text:" +Text);
		return Text;
	}
	
	public String doElementDomAtrributeValue(By Locator, String attrName) {
		nullcheck(attrName);
		return getElement(Locator).getDomAttribute(attrName);
	}
	
	public String doElementPropertyValue(By Locator, String propName) {
		nullcheck(propName);
		return getElement(Locator).getDomProperty(propName);
	}
	public void clickElement(By Locator,String value) {
		List<WebElement> eleList=getElements(Locator);
		System.out.println("No. of Elements:"+eleList.size());
		for(WebElement e:eleList) {
			System.out.println(e.getText());
			String Text=e.getText();
			System.out.println(Text);
			if(Text.contains(value))
		     e.click();
			break;}
		
	}
	//Drop down Utils- Select based Drop Down//
	
	public boolean doSelectDropDownByIndex(By Locator,int index) {
		Select select=new Select(getElement(Locator));
		try {
			select.selectByIndex(index);
			return true;
		}
		catch(NoSuchElementException e){
			System.out.println(index + "is not present in the Drop Down");
			return false;
				}
	}
	
	public boolean doSelectDropDownByVisibleText(By Locator,String Text) {
		Select select=new Select(getElement(Locator));
		try {
			select.selectByVisibleText(Text);
			return true;
		}
		catch(NoSuchElementException e){
			System.out.println(Text + "is not present in the Drop Down");
			return false;
				}
	}
	
	public boolean doSelectDropDownByValue(By Locator,String Value) {
		Select select=new Select(getElement(Locator));
		try {
			select.selectByValue(Value);
			return true;
		}
		catch(NoSuchElementException e){
			System.out.println(Value + "is not present in the Drop Down");
			return false;
				}
	}
	
	//**************drop down util--non select based****************//
	
	public void selectChoice(By choice,By choiceList, String... choiceValue) throws InterruptedException {
		  doClick(choice);
	      Thread.sleep(2000);
	      List<WebElement> choices= getElements(choiceList);
	      System.out.println(choices.size());
	      
	      if(choiceValue[0].equalsIgnoreCase("all")) {
	    	  //logic to select all the choices:
	    	  for(WebElement e:choices) {
	    		  String Text=e.getText();
	    		  System.out.println(Text);
	    		  
	    	for(String value:choiceValue) {
	    		if(Text.trim().equals(value)) {
	    			e.click();
	    			break;
	    		}
	    		}
	    	   }
	    	  }
	         }
	    	  
	      
	//*********************ActionsUtils****************//
	public void handleParentSubMenu(By parentMenu,By subMenu) throws InterruptedException {
		
		doMoveToElement(parentMenu);
		Thread.sleep(2000);
		doClick(subMenu);
		
		
	}
	public void doMoveToElement(By Locator) throws InterruptedException
	{
		Actions Act=new Actions(driver);
		Act.moveToElement(getElement(Locator)).build().perform();
		Thread.sleep(2000);
		
	}
	
	public  void Handle4LevelMenu(By level1,By level2,By level3,By level4) throws InterruptedException{
		getElement(level1).click();
		Thread.sleep(2000);
		doMoveToElement(level2);
		Thread.sleep(2000);
		doMoveToElement(level3);
		Thread.sleep(2000);
		getElement(level4).click();
		
	}

	public void doActionsSendKeys(By Locator,String Value) {
		Act.sendKeys(getElement(Locator),Value).perform();
	}
	
	public void doActionClick(By Locator) {
		Act.click(getElement(Locator)).perform();
	}
	public void doSendKeysWithPause(By Locator,String Value,long PauseTime) {
		char Val[]=Value.toCharArray();
		for(char ch:Val) {
			Act.sendKeys(getElement(Locator),String.valueOf(ch)).pause(PauseTime).perform();
			
			
		}
		
	}
	
	public WebElement waitForElementPresence(By Locator,int Timeout) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(Timeout));
		return wait.until(ExpectedConditions.presenceOfElementLocated(Locator));
	}
	
	public WebElement waitForElementVisible(By Locator,int Timeout) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(Timeout));
		WebElement element= wait.until(ExpectedConditions.visibilityOfElementLocated(Locator));
		highlightElement(element);
		return element;
	}
	
	public List<WebElement>	waitForAllElementVisible(By Locator,int Timeout){
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(Timeout));
		try{
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(Locator));
	}
		catch(Exception e) {
			return Collections.EMPTY_LIST;
		}
		}
	
	
	
	public void clickWithWait(By Locator,int Timeout) {
		waitForElementVisible(Locator,Timeout).click();
	}
	
	public void sendKeysWithWait(By Locator,int Timeout,CharSequence... Value) {
		waitForElementVisible(Locator,Timeout).sendKeys(Value);
	}
	 
	public String waitFortitleis(String Title,int timeOut) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		
		try {
			wait.until(ExpectedConditions.titleIs(Title));
			return driver.getTitle();
		} catch (Exception e) {
			return null;
		
		}
		
}
	
     	public String waitForURLs(String Url,int Timeout)  {
    	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(Timeout));
		try{
			wait.until(ExpectedConditions.urlToBe(Url));
			return driver.getCurrentUrl();
			}
		catch(Exception e)
		{
		return null;
		}
		
	}
	
     	public String waitForURLContains(String FractionUrl,int Timeout)  {
        	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(Timeout));
    		try{
    			wait.until(ExpectedConditions.urlContains(FractionUrl));
    			return driver.getCurrentUrl();
    			}
    		catch(Exception e)
    		{
    		return null;
    		}
    	}
     	
	
	//wait for Frame
	public void waitforFrameAndSwitchtoit(By frameLocator,int Timeout) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(Timeout));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
		}
	
	public void waitforFrameAndSwitchtoit(String frameNameorId,int Timeout) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(Timeout));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameNameorId));
		}
	
	public void waitforFrameAndSwitchtoit(int frameIndex,int Timeout) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(Timeout));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));
		}
	
	public void waitforFrameAndSwitchtoit(WebElement frameElement,int Timeout) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(Timeout));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
		}
	
	//wait for Windows
	
	public boolean waitForWindow(int expectednoOfWindows,int Timeout) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(Timeout));
		try {
			return wait.until(ExpectedConditions.numberOfWindowsToBe(expectednoOfWindows));
		}
		catch(Exception e) {
		System.out.println("Expected no of windows are not correct");
		return false;
		}	
	}
	
	public void clickWhenReady(By Locator,int Timeout) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(Timeout));
		wait.until(ExpectedConditions.elementToBeClickable(Locator)).click();
	}
	
	
	
	
	
}
