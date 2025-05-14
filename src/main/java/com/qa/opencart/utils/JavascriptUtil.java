package com.qa.opencart.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavascriptUtil {

	 private WebDriver driver;
	 private JavascriptExecutor Js;

	
	public JavascriptUtil(WebDriver driver) {
		this.driver=driver;
		Js=(JavascriptExecutor)driver;
	}
	
	public String getTitleByJS() {
		return Js.executeScript("return document.title;").toString();
		}
	
	public void refreshBrowserByJS() {
		Js.executeScript("history.go(0)");
	}
	
	public void NavigateToBackPage() {
		Js.executeScript("history.go(-1)");
	}
	
	public void NavigateToForwardPage() {
		Js.executeScript("history.go(1)");
	}
	
	public void generateAlertByJs(String Message) {
		
		Js.executeScript("alert('"+Message+"')");
		try {
			Thread.sleep(2000);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		driver.switchTo().alert().dismiss();
	}
	
	public String getPageInnerText() {
		return Js.executeScript("return document.documentElement.innerText;").toString();
	}
	
	
	 public void scrollPagedown() {
		 Js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
	 }
	 public void scrollPagedown(String Height) {
		 Js.executeScript("window.scrollTo(0,'"+Height+"')");
	 }
	
	 public void scrollPageUp() {
		 Js.executeScript("window.scrollTo(document.body.scrollHeight,0)");
	 }
	 
	 public void scrollIntoView(WebElement element) {
		Js.executeScript("arguments[0],scrollIntoView(true);",element);
		 }
	 
	 private void changeColor(String Color,WebElement Element ) {
		 Js.executeScript("arguments[0].style.backgroundColor='"+Color+"'",Element);
		 try {
			 Thread.sleep(20);
			 }catch(InterruptedException e)
		 {
		 }
		 
		 }
	 
	 public void flash(WebElement Element) {
		 String bgColor=Element.getCssValue("backgroundColor");
		 for(int i =0; i<5;i++){
			 changeColor("rgb(0,200,0)",Element);
			 changeColor(bgColor,Element);
			  }
		}
	 
	 
	 
	
	
	 
	
	
	
	
	
	
	
	
	
	
	
	
	}


