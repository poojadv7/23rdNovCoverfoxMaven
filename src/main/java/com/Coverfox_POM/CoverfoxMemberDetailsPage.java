package com.Coverfox_POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

public class CoverfoxMemberDetailsPage {
	//variables
	@FindBy(id = "Age-You") private WebElement youAgeDropDown;
	@FindBy(name = "Spouse") private WebElement spouseAgeDropDown;
	@FindBy(id = "Age-Mother") private WebElement motherAgeDropDown;
	@FindBy(className = "next-btn") private WebElement nextButton;
	
	//constructor
	public CoverfoxMemberDetailsPage(WebDriver driver) // take driver as a local variable
	{
		PageFactory.initElements(driver, this);
	}
	
	//methods
	public void handleYouAgeDropDown(String youAge)
	{
		Reporter.log("handleling You Age DropDown ", true);
		Select s=new Select(youAgeDropDown);
		s.selectByValue(youAge+ "y");
	}
	
	public void handleSpouseAgeDropDown(String spouseAge)
	{
		Reporter.log("handleling Spouse Age DropDown ", true);
		Select s=new Select(spouseAgeDropDown);
		s.selectByValue(spouseAge+ "y");
	}
	
	public void handleMotherAgeDropDown(String motherAge)
	{
		Reporter.log("handleling Mother Age DropDown ", true);
		Select s=new Select(motherAgeDropDown);
		s.selectByValue(motherAge+ "y");
	}
	
	public void clickOnNextButtonOfMemberDetailsPage()
	{
		Reporter.log("clicking On NextButton Of MemberDetailsPage", true);
		nextButton.click();
		
	}
	

}
