package com.Coverfox_Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.logging.FileHandler;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.Coverfox_POM.CoverfoxAddressDetailsPage;
import com.Coverfox_POM.CoverfoxHealthPlanPage;
import com.Coverfox_POM.CoverfoxHomePage;
import com.Coverfox_POM.CoverfoxMemberDetailsPage;
import com.Coverfox_POM.CoverfoxResultPage;
import com.Coverfox_base.Base;
import com.Coverfox_utility.Utility;

//import coverfoxUsingPOMBaseTestandUtilityClasses.Utility;
import net.bytebuddy.utility.RandomString;

 //Removed Listener Annotation. will add in XML file
public class TestCoverfoxUsingTestNG extends Base {  // USE INHERITANCE 
	//declare All Globally.
	public static Logger logger; //declare logger variable

	//All POM classes object's Declaration
	CoverfoxHomePage coverfoxHomePage;
	CoverfoxHealthPlanPage coverfoxHealthPlanPage;
	CoverfoxMemberDetailsPage coverfoxMemberDetailsPage;
	CoverfoxAddressDetailsPage coverfoxAddressDetailsPage;
	CoverfoxResultPage coverfoxResultPage;
	
	@BeforeClass
	public void launchBrowser() throws EncryptedDocumentException, IOException
	{

		  logger= Logger.getLogger("23rdNovMaven");
		  PropertyConfigurator.configure("log4j.properties");
		  logger.info("Welcome to Coverfox testing");
		  
		openBrowser(); //from Base class
		logger.info("Launching Browser");
		//All POM classes object's initialization.
		coverfoxHomePage = new CoverfoxHomePage(driver);
		coverfoxHealthPlanPage = new CoverfoxHealthPlanPage(driver);
		coverfoxAddressDetailsPage = new CoverfoxAddressDetailsPage(driver);
		coverfoxMemberDetailsPage = new CoverfoxMemberDetailsPage(driver);
		coverfoxResultPage = new CoverfoxResultPage(driver);
	}
	
	@BeforeMethod
	public void coverfoxPreconditions() throws InterruptedException // Call All Coverfox POM classes methods. And Add Reporter.log() in those method to print it in console.
, EncryptedDocumentException, IOException
	{
		coverfoxHomePage.clickOnGender(); 
		logger.info("clicking On Gender");
		coverfoxHealthPlanPage.selectMembersToInsure();
		coverfoxHealthPlanPage.clickOnNextButtonOfHealthPlanPage();
		logger.info("clicking On Next Button Of Health Plan Page");
		//take values from excel file present in TestData Folder of Selenium Project for coverfoxMemberDetailsPage.
		coverfoxMemberDetailsPage.handleYouAgeDropDown(Utility.readDataFromExcel("Sheet1", 0, 0)); 
		logger.info("handleling You Age DropDown");
		coverfoxMemberDetailsPage.handleSpouseAgeDropDown(Utility.readDataFromExcel("Sheet1", 1, 0));
		logger.info("handleling Spouse Age DropDown");
		coverfoxMemberDetailsPage.handleMotherAgeDropDown(Utility.readDataFromExcel("Sheet1", 2, 0));
		logger.info("handleling Mother Age DropDown");
		coverfoxMemberDetailsPage.clickOnNextButtonOfMemberDetailsPage();
		logger.info("clicking On Next Button Of Member DetailsPage");
		//take values from excel file present in TestData Folder of Selenium Project for coverfoxAddressDetailsPage
		coverfoxAddressDetailsPage.enterPinCode(Utility.readDataFromExcel("Sheet1", 0, 1));
		logger.info("entering PinCode");
		coverfoxAddressDetailsPage.clickOnCheckBox();
		logger.info("clicking On CheckBox");
		coverfoxAddressDetailsPage.enterMobileNumber(Utility.readDataFromExcel("Sheet1", 0, 2));
		logger.info("entering Mobile Number");
		coverfoxAddressDetailsPage.clickOnContinueButtonOfAddressDetailsPage();
		logger.info("clicking On Continue Button Of Address DetailsPage");
		Thread.sleep(4000);
		
		//For CoverfoxResultPage create methods first to get plan number from text and cards for YOUandHUSBAND as well for MOTHER
		//To compare plan number from text and from cards. And call those all methods in @Test i.e TC or Test Method.
	}
	
	//this is our TC
	//create methods in Coverfox result page and call those methods in below Test method.
	@Test
  public void validateCoverfoxPlans() throws InterruptedException //TC-> No. of records from text is equal to no. records from 
, IOException
  {

		//Assert.fail(); // made TC fail intentionally.. to take Screenshot of failed TC. =>check Listener class OnTestFailure().
		//YouAndHusband plan number
		int YouAndHusbandPlanNumberFromText = coverfoxResultPage.getYouAndHusbandPlanNumberFromText();
		int YouAndHusbandPlanNumberFromPLANCARDS = coverfoxResultPage.getYouAndHusbandPlanNumberFromPLANCARDS();
		
		logger.info("validating YouAndHusband's Coverfox Plans");

		//validate YouAndHusband plan numbers 
		Assert.assertEquals(YouAndHusbandPlanNumberFromText, YouAndHusbandPlanNumberFromPLANCARDS, "TC is failed, When YouAndHusband Plan numbers are not matching");
		Reporter.log("YouAndHusband Plan numbers are matching. TC is passed", true);
		//Utility.takeScreenshot(driver, "Coverfox YouAndHusband Plans Screenshot"); (take screenshot if TC is passed)
		Reporter.log("velocity 1", true);
		Reporter.log("velocity 2", true);
		Reporter.log("velocity 3", true);
//		
//		//Mother plan number
//		int getMotherPlanNumberFromText = coverfoxResultPage.getMotherPlanNumberFromText();
//		int getMotherPlanNumberFromPLANCARDS = coverfoxResultPage.getMotherPlanNumberFromPLANCARDS();
//		//Hard Assert for comparison.
//		
//		logger.info("validating Mother's Coverfox Plans");
//		
//		//validate Mother plan numbers 
//		Assert.assertEquals(getMotherPlanNumberFromText, getMotherPlanNumberFromPLANCARDS,"TC is failed, When Mother Plan numbers are not matching");
//		Reporter.log("Mother Plan numbers are matching. TC is passed", true);
//		
		//Mother plans ScreenShot
		//Utility.takeScreenshot(driver, "Coverfox Mother Plans Screenshot"); //this is from Utility class  (take screenshot if TC is passed)

		
  }
	
	@AfterClass
	public void closeBrowser() throws InterruptedException
	{
		logger.info("closing Browser");
		closeBrowserWindow();
	}
	
	
 

}
