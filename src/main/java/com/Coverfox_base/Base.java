package com.Coverfox_base;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Reporter;

import com.Coverfox_utility.Utility;

public class Base {
	//when we are using driver at multiple places may be chances of creating multiple instances. So we need to make driver static. 
	//make driver protected to access in different packages=>> check driver in Listener class.
	protected static WebDriver driver; //make driver static to handle/avoid null pointer exception. static means single unit/instance.
	
	public void openBrowser() throws EncryptedDocumentException, IOException
	{
		ChromeOptions options=new ChromeOptions();
		options.addArguments("disable-notifications");
		driver = new ChromeDriver(options); //initialization
		driver.manage().window().maximize();
		driver.get(Utility.readDataFromPropertiesFile("URL")); //from Properties file
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
		Reporter.log("launching Browser", true);
}
	
	public void closeBrowserWindow() 
	{
		driver.quit();
		Reporter.log("Closing Browser", true);
	}
}
