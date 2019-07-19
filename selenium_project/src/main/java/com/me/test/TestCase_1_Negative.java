package com.me.test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import javafx.scene.web.WebErrorEvent;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import sun.jvm.hotspot.utilities.Assert;

import java.io.File;

// Sign - In Test Case Negative

public class TestCase_1_Negative {


    public ExtentTest test;


 @Test
 public void runTest(ExtentReports extent) throws Exception {

     System.setProperty("webdriver.chrome.driver","/Users/megandsouza/Desktop/chromedriver");
        WebDriver driver = new ChromeDriver();

     test = extent.startTest("TestCase_1_Negative");
     //Creating an instance of a Chrome Driver
     test.log(LogStatus.INFO,"Expected: [First Page: Home Page] -> [Final Page: Create Account Page]");

     driver.get("http://www.amazon.com");

     //Click on the Create Account Button
     WebElement sighInButton = driver.findElement(By.xpath("//*[@id=\"nav-link-accountList\"]"));
     sighInButton.click();
     driver.findElement(By.id("createAccountSubmit")).click();
     Thread.sleep(1500);

     //Typing the Email address
     WebElement email_address = driver.findElement(By.id("ap_email"));
     Thread.sleep(1500);
     email_address.sendKeys("abcxyz@yahoo.com");

     //Typing the password
     WebElement password = driver.findElement(By.id("ap_password"));
     password.sendKeys("abcxyz");
     Thread.sleep(1500);

     //Typing the Re-enter password
     WebElement apPasswordCheck = driver.findElement(By.id("ap_password_check"));
     apPasswordCheck.sendKeys("abcxyz");

     //---------------
        String screenshotPath = ScreenShots.takeScreenshot(driver,"T1NegDetails");
        test.log(LogStatus.INFO,"Entered the Details");
        test.log(LogStatus.INFO,test.addScreenCapture(screenshotPath));

     //---------------
     driver.findElement(By.id("continue")).click();

     // Checking for the alert - missing customer name
     WebElement alert = driver.findElement(By.id("auth-customerName-missing-alert"));
     Thread.sleep(1500);
    // System.out.println("I came here");
     // System.out.println(alert.isDisplayed());

     System.out.println(alert.findElement(By.className("a-alert-content")).getText());

     String alertText = alert.findElement(By.className("a-alert-content")).getText();

     if(alertText.equals("Enter your name")){

         screenshotPath = ScreenShots.takeScreenshot(driver, "T1NegFinal");

         test.log(LogStatus.PASS, "Test Successfully prevented a registeration with incomplete information");
         test.log(LogStatus.PASS,test.addScreenCapture(screenshotPath));
         test.log(LogStatus.INFO,"Actual: [First Page: Home Page] -> [Final Page: Create Account Page]");

         //assert.
         //test.log(LogStatus.INFO, "Expected Value = Enter your name "+
     }else{

         test.log(LogStatus.FAIL, "Test Failed to identify error");
       //  takeScreenshot(driver);
         test.log(LogStatus.INFO,"Actual: Error -> Check screenshots");

     }

     extent.endTest(test);

     extent.flush();

     driver.quit();



 }



}
