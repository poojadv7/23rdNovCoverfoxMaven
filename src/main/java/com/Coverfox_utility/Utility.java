package com.Coverfox_utility;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.testng.Reporter;
import com.Coverfox_utility.Utility;

public class Utility 
{ 
	//Use static methods in Utility class.
	
	//1. Read data from Excel File present in Selenium project=> TestData folder.
 public static String readDataFromExcel(String sheetName, int row, int cell) throws EncryptedDocumentException, IOException
 {
	FileInputStream myFile = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\resources\\testData\\Book1.xlsx"); //refer Test.java for example
	Sheet mySheet = WorkbookFactory.create(myFile).getSheet(sheetName);
	String data = mySheet.getRow(row).getCell(cell).getStringCellValue();
	Reporter.log("reading Data From Excel", true);
	return data;

 }
	//2. Take Screenshots and save into screenshots folder in Selenium project
 public static void takeScreenshot(WebDriver driver, String screenshotName) throws IOException //pass driver and SS name parameter here
 {
	java.io.File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	java.io.File dest=new java.io.File(System.getProperty("user.dir")+"\\screenshot"+screenshotName+".png");
	FileHandler.copy(src, dest);
	Reporter.log("taking Screenshot and saved at "+dest, true);
 }
 
 	//3. Scroll into view.
 public static void scrollIntoView(WebDriver driver, WebElement element) //driver and element parametrization
 {
	 JavascriptExecutor js = (JavascriptExecutor)driver;
	 js.executeScript("arguments[0].scrollIntoView(true)", element);
	 Reporter.log("scrolling Into View", true);
 }
 
 	//4. Read data from properties file which is coverfox.properties => present in Selenium project.
 //we are doing this parametrization to change URL according to dev and test envirement in BASE CLASS.
 public static String readDataFromPropertiesFile(String key) throws IOException 
 {
	Properties prop = new Properties();
 //proprties file path =>  //C:\\Users\\Pooja\\eclipse-workspace\\Selenium\\coverfox.properties 
 	FileInputStream myFile = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\resources\\testData\\coverfox.properties");
 	prop.load(myFile);
 	String value = prop.getProperty(key); //key-value pair
 	Reporter.log("reading Data From Properties file", true);
 	return value; //will pass key and return value.
 }
 }
