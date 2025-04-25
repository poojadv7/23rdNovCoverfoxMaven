package com.Coverfox_POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

public class CoverfoxHomePage {
	
	//variables
	@FindBy(xpath = "//div[text()='Female']") private WebElement gender;
	
	//constructor
	public CoverfoxHomePage(WebDriver driver)  // take driver as a local variable
	{
		PageFactory.initElements(driver, this);
	}
	
	//methods
	public void clickOnGender()
	{
		Reporter.log("Clicking on Gender", true);
		gender.click();
	}

}
