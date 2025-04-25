package com.Coverfox_Test;

import org.testng.annotations.Test;

import com.Coverfox_POM.CoverfoxAddressDetailsPage;
import com.Coverfox_POM.CoverfoxHealthPlanPage;
import com.Coverfox_POM.CoverfoxHomePage;
import com.Coverfox_POM.CoverfoxMemberDetailsPage;
import com.Coverfox_POM.CoverfoxResultPage;
import com.Coverfox_base.Base;
import com.Coverfox_utility.Utility;

import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class TestCoverfoxErrorMsgUsingTestNG extends Base
{
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
		logger.info("Welcome to Coverfox testing test case 2 error msgs on Address Details Page");
		
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
		
		coverfoxAddressDetailsPage.clickOnContinueButtonOfAddressDetailsPage();
		logger.info("clicking On Continue Button Of Address DetailsPage");
	}

  @Test
  public void validateErrorMsgsForPINCodesAndMobile()
  {
	//click on continue on coverfoxAddressDetailsPage to get error msg
	  coverfoxAddressDetailsPage.clickOnContinueButtonOfAddressDetailsPage();
	 String expectedErrorMsgForSelfPINCode = "Please enter a valid pincode";
	 String expectedErrorMsgForParentsPINCode = "Please enter a valid pincode";
	 String expectedErrorMsgForMobileNumber = "Please enter a valid mobile no.";
	 
	 logger.info("validating Error Msgs For PINCodes And Mobile");
	 
	 //Validations for error msgs are displaying or not.
	 Assert.assertTrue(coverfoxAddressDetailsPage.isSelfPINCodeErrorMsgDisplyed(),"TC is failed when error msg for self pincode is not displayed");
	 Reporter.log("error msg for self pincode is displayed. TC is Passed", true);
	 Assert.assertTrue(coverfoxAddressDetailsPage.isParentsPINCodeErrorMsgDisplyed(),"TC is failed when error msg for self pincode is not displayed");
	 Reporter.log("error msg for Parents pincode is displayed. TC is Passed", true);
	 Assert.assertTrue(coverfoxAddressDetailsPage.isMobileNumberErrorMsgDisplyed(),"TC is failed when error msg for self pincode is not displayed");
	 Reporter.log("error msg for Mobile number is displayed. TC is Passed", true);
	 
	 //Valiation for expected matching actual Error Msg.
	 Assert.assertEquals(coverfoxAddressDetailsPage.getSelfPINCodeErrorMessage(), expectedErrorMsgForSelfPINCode);
	 Reporter.log("error msg for self pincode is matching with expected error msg. TC is Passed", true);
	 Assert.assertEquals(coverfoxAddressDetailsPage.getParentPINCodeErrorMessage(), expectedErrorMsgForParentsPINCode);
	 Reporter.log("error msg for Parents pincode is matching with expected error msg. TC is Passed", true);
	 Assert.assertEquals(coverfoxAddressDetailsPage.getMobileNumberErrorMessage(), expectedErrorMsgForMobileNumber);
	 Reporter.log("error msg for Mobile number is matching with expected error msg. TC is Passed", true);
  }
  
  @AfterClass
  public void closeBrowser() throws InterruptedException
	{
		Reporter.log("Closing browser", true);
		Thread.sleep(2000);
		driver.quit();
	}

}
