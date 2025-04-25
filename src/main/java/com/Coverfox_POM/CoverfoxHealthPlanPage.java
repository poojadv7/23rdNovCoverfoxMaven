package com.Coverfox_POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

public class CoverfoxHealthPlanPage {
	
	//variables
	@FindBy(xpath = "//div[text()='Husband']") private WebElement husbandOption;
	@FindBy(xpath = "//div[text()='Mother']") private WebElement motherOption;
	@FindBy(className = "btn-container") private WebElement nextButton;
	
	//constructor
	public CoverfoxHealthPlanPage(WebDriver driver) // take driver as a local variable
	{
		PageFactory.initElements(driver, this);
		
	}
	
	//methods
	public void selectMembersToInsure()
	{
		Reporter.log("Selecting Husband and Mother options", true);
		husbandOption.click();
		motherOption.click();
	}
	
	public void clickOnNextButtonOfHealthPlanPage()
	{
		Reporter.log("clicking On NextButton Of HealthPlanPage", true);
		nextButton.click();
	}

}
