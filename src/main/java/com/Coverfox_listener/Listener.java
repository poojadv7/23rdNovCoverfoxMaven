package com.Coverfox_listener;

import java.io.IOException;

import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.Coverfox_base.Base;
import com.Coverfox_utility.Utility;

public class Listener extends Base implements ITestListener 
//In takeScreenshot method we need driver to run this class and 
//driver is declared in Base class  so extend Base class
    {
	@Override
	public void onTestSuccess(ITestResult result) {
		Reporter.log("Test "+result.getName() +" is success", true);
	}
	
	@Override
	public void onTestFailure(ITestResult result) 
	{
		Reporter.log("Test "+result.getName() +" is failed", true);
		
		try {
			Utility.takeScreenshot(driver, result.getName()+"_FailedTCScreenshot");  //take screenshot of failed TC. 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
