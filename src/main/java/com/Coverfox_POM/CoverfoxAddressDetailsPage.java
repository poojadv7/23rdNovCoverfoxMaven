package com.Coverfox_POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

public class CoverfoxAddressDetailsPage {
	//variables
	@FindBy(xpath = "(//input[@class='mp-input-text'])[1]") private WebElement youPinCode;
	@FindBy(className = "mp-check-input") private WebElement sameAsMineCheckBox;
	@FindBy(xpath = "//input[@id='want-expert']") private WebElement mobileNumberField;
	@FindBy(xpath = "//div[text()='Continue']") private WebElement continueButton;
	@FindBy(xpath = "(//div[contains(text(),'pincode ')])[1]")  WebElement errorMsgSelfPIN;
	@FindBy(xpath = "(//div[contains(text(),'pincode ')])[2]")  WebElement errorMsgParentsPIN;
	@FindBy(xpath = "//div[contains(text(),'mobile no. ')]")  WebElement errorMsgMobile;
	
	//constructor
	
	public CoverfoxAddressDetailsPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//methods
	
	public void enterPinCode(String pincode) throws InterruptedException
	{
		Reporter.log("entering PinCode", true);
		youPinCode.sendKeys(pincode);
	}
	
	public void clickOnCheckBox()
	{
		Reporter.log("clicking On CheckBox", true);
		sameAsMineCheckBox.click();
	}
	
	public void enterMobileNumber(String mobileNumber)
	{
		Reporter.log("entering MobileNumber", true);
		mobileNumberField.sendKeys(mobileNumber);
	}
	
	public void clickOnContinueButtonOfAddressDetailsPage()
	{
		Reporter.log("clicking On ContinueButton Of AddressDetailsPage", true);
		continueButton.click();
	}
	
	public boolean isSelfPINCodeErrorMsgDisplyed()
	{
		return errorMsgSelfPIN.isDisplayed();
	}
	
	public boolean isParentsPINCodeErrorMsgDisplyed()
	{
		return errorMsgParentsPIN.isDisplayed();
	}
	
	public boolean isMobileNumberErrorMsgDisplyed()
	{
		return errorMsgMobile.isDisplayed();
	}
	
	 public String getSelfPINCodeErrorMessage()
	 {
		 Reporter.log("getting Self PIN Code Error Message On AddressDetailsPage", true);
		 String error = errorMsgSelfPIN.getText();
		 return error;
		 //OR
		 // return errorMsgSelfPIN.getText();
	 }
	 
	 public String getParentPINCodeErrorMessage()
	 {
		 Reporter.log("getting Parents PIN Code Error Message On AddressDetailsPage", true);
		 return errorMsgParentsPIN.getText();
	 }
	 
	 public String getMobileNumberErrorMessage()
	 {
		 Reporter.log("getting Mobile number Error Message On AddressDetailsPage", true);
		 return errorMsgMobile.getText();
	 }
	
}
