package com.qa.opencart.pages;

import java.util.HashMap;

import java.util.LinkedHashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.constants.AppConstants.*;
import com.qa.opencart.utils.ElementUtil;
import java.util.ArrayList;
import java.util.List;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	private Map<String,String> productMap;
	
	private final By productHeader=By.tagName("h1");
	private final By productImages=By.cssSelector("ul.thumbnails img");
	private final By productMetaData=By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private final By productPriceData=By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");
	
	public ProductInfoPage(WebDriver driver)
    {
	this.driver=driver;
	eleUtil=new ElementUtil(driver);
	}

	public String getProductHeader() {
		String Header=eleUtil.waitForElementVisible(productHeader, AppConstants.DEFAULT_TIMEOUT).getText();
		System.out.println("Product Header:"+Header);
		return Header;
	}
	
	public int getProductImagesCount() {
		int imageCount= eleUtil.waitForAllElementVisible(productImages, AppConstants.MEDIUM_DEFAULT_TIMEOUT).size();
		System.out.println("Total number of images:"+imageCount);
		return imageCount;
		
	}
	
	public Map<String,String> getProductDetailsMap(){
		productMap=new LinkedHashMap<String,String>();
		productMap.put("productheader",getProductHeader());
		productMap.put("productImages",String.valueOf(getProductImagesCount()));
		getProductMetaData();
		getProductPriceData();
		System.out.println("Full product details:"+productMap);
		return productMap;
	}
	
	public void getProductMetaData() {
		
	
		List<WebElement> MetaList=eleUtil.waitForAllElementVisible(productMetaData, AppConstants.DEFAULT_TIMEOUT);
		for(WebElement e: MetaList) {
				String metaData=e.getText();
				String meta[]=metaData.split(":");
				String metaKey = meta[0].trim();
				String metaValue=meta[1].trim();
				productMap.put(metaKey,metaValue);
				
		}}
	
	public void getProductPriceData() {
		List<WebElement> priceList=eleUtil.waitForAllElementVisible(productPriceData, AppConstants.DEFAULT_TIMEOUT);
		String productPrice=priceList.get(0).getText();
		String extaxPrice=priceList.get(1).getText().split(":")[1].trim();
		productMap.put("productPrice",productPrice);
		productMap.put("extaxPrice", extaxPrice);
		
		
	} 
	
	
	
		
		
		
	}
	
	
	
	
	
	
	
	
	

