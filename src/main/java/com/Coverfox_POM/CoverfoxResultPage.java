package com.Coverfox_POM;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

public class CoverfoxResultPage {
	
	//variables
	@FindBy(xpath = "//div[contains(text(),'matching')]") private WebElement youAndHusbandPlanResult;
	@FindBy(className = "plan-card-container") private List<WebElement> youAndHusbandPlancards; //identify 1st plan to get size
	@FindBy(xpath = "(//div[(text()=' mother ')])[1]") private WebElement motherOption; //to click
	@FindBy(xpath = "//div[(contains(text(),'matching'))]") private WebElement motherPlanResult;
	@FindBy(id = "plans-list") private List<WebElement> motherPlancards; //identify 1st plan to get size

	//constructor
	public CoverfoxResultPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//methods
	public void validateYouAndHusbandPlans() throws InterruptedException
	{
		String youAndHusbandrResult = youAndHusbandPlanResult.getText();
		String[] result1 = youAndHusbandrResult.split(" ");
		//44 matching Health Insurance Plans
		//convert into integer bcz we want plans count
		int result1InNumber = Integer.parseInt(result1[0]);
		Thread.sleep(2000);
		int totalPlans1 = youAndHusbandPlancards.size(); //get size
		
		if(totalPlans1==result1InNumber)
		{
			System.out.println("TC is passed");
		}
		else {
			System.out.println("TCis failed");
		}
		
	}
	
	public void validatemotherPlans() throws InterruptedException
	{
		motherOption.click();
		Thread.sleep(8000);
		String motherResult = motherPlanResult.getText();
		String[] result2 = motherResult.split(" ");
		//31 matching Health Insurance Plans
		//convert into integer bcz we want plans count
		int result2InNumber = Integer.parseInt(result2[0]);
		Thread.sleep(2000);
		int totalPlans2 = motherPlancards.size(); //get size
		
		if(totalPlans2==result2InNumber)
		{
			System.out.println("TC is passed");
		}
		else {
			System.out.println("TC is failed");
		}
		
	}
	
	//Create methods for TestNG 
	//for YOU AND HUSBAND PLANS
	public int getYouAndHusbandPlanNumberFromText()
	{
		Reporter.log("getting YouAndHusband PlanNumber From Text", true);
		//copy above code(Refer validateYouAndHusbandPlans())
		String youAndHusbandrResult = youAndHusbandPlanResult.getText();
		String[] result1 = youAndHusbandrResult.split(" ");
		//44 matching Health Insurance Plans
		//convert into integer bcz we want plans count
		int youAndHusbandplanNumber = Integer.parseInt(result1[0]);
		return youAndHusbandplanNumber;
	}
	
	public int getYouAndHusbandPlanNumberFromPLANCARDS()
	{
		Reporter.log("getting YouAndHusband PlanNumber From PLANCARDS", true);
		int youAndHusbandPlanNumFromCards = youAndHusbandPlancards.size(); // check above variable declaration
		return youAndHusbandPlanNumFromCards;
	
	}
	
	
	//for MOTHER PLANS
	public int getMotherPlanNumberFromText() throws InterruptedException
	{
		Thread.sleep(2000);
		Reporter.log("getting Mother PlanNumber From Text", true);
		motherOption.click();
		Thread.sleep(8000);
		String motherResult = motherPlanResult.getText();
		String[] result2 = motherResult.split(" ");
		//31 matching Health Insurance Plans
		//convert into integer bcz we want plans count
		int motherPlanNumber = Integer.parseInt(result2[0]);
		return motherPlanNumber;
	}
	
	public int getMotherPlanNumberFromPLANCARDS()
	{
		Reporter.log("getting Mother PlanNumber From PLANCARDS", true);
		int motherPlanNumFromCards=motherPlancards.size();
		return motherPlanNumFromCards;
	}

	
}
