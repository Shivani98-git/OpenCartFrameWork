package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static com.qa.opencart.constants.AppConstants.*;
import com.qa.opencart.utils.ElementUtil;

public class searchResultsPage {
	
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private final By resultsProduct=By.cssSelector("div.product-thumb");
	
	public searchResultsPage(WebDriver driver)
    {
	this.driver=driver;
	eleUtil=new ElementUtil(driver);
	}
	
	public int getResultsProductCount() {
			int searchCount=
					eleUtil.waitForAllElementVisible(resultsProduct,MEDIUM_DEFAULT_TIMEOUT).size();
			System.out.println("total number of search products"+searchCount);
			return searchCount;
	}
	
	public ProductInfoPage selectProduct(String ProductName)
	{
		 System.out.println("Product Name:"+ProductName);
		 eleUtil.doClick(By.linkText(ProductName));
		 return new ProductInfoPage(driver);
	}
	
	
	
	
	
	
	
	

}
