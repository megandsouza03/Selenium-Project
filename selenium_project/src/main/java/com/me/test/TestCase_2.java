package com.me.test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestCase_2 {

    public ExtentTest test;

    @Test
    public void runTest(ExtentReports extent) throws Exception {

        System.setProperty("webdriver.chrome.driver", "/Users/megandsouza/Desktop/chromedriver");
        WebDriver driver = new ChromeDriver();
        test = extent.startTest("TestCase_2");
        test.log(LogStatus.INFO,"Expected: [First Page: Home Page] -> [Final Page: HomePage with user signed in]");

        //Creating an instance of a Chrome Driver

        driver.get("http://www.amazon.com");
        String screenshotPath = ScreenShots.takeScreenshot(driver,"T2PosSignUP");
        test.log(LogStatus.INFO, "Sign-Up Page");
        test.log(LogStatus.INFO,test.addScreenCapture(screenshotPath));
        //Click on the Create Account Button
        WebElement sighInButton = driver.findElement(By.xpath("//*[@id=\"nav-link-accountList\"]"));
        sighInButton.click();

        WebElement apEmail = driver.findElement(By.id("ap_email"));
        apEmail.sendKeys("denisansah@yahoo.com");
        Thread.sleep(2000);
        WebElement apPassword = driver.findElement(By.id("ap_password"));
        apPassword.sendKeys("CaptainMegan");
        Thread.sleep(2000);
        screenshotPath = ScreenShots.takeScreenshot(driver,"T2PosDetails");
        test.log(LogStatus.INFO, "Details of the User entered");
        test.log(LogStatus.INFO,test.addScreenCapture(screenshotPath));
        driver.findElement(By.id("signInSubmit")).click();
        Thread.sleep(1000);

        CaptchCheck.checkForCaptch(driver,test,extent);

        WebElement name = driver.findElement(By.xpath("//*[@id=\"nav-link-accountList\"]")).findElement(By.className("nav-line-1"));
        System.out.println(name.getText());
        if(!name.getText().equals("Hello, Sign in")){
            screenshotPath = ScreenShots.takeScreenshot(driver, "T2PosFinal");

            test.log(LogStatus.PASS, "Test Successfully Signed-In the User");
            test.log(LogStatus.PASS,test.addScreenCapture(screenshotPath));
            test.log(LogStatus.INFO,"Actual: [First Page: Home Page] -> [Final Page: HomePage with user signed in]");

        }else{
            screenshotPath = ScreenShots.takeScreenshot(driver, "T2PosFinal");

            test.log(LogStatus.FAIL, "Test Unsuccessfully Signed-In the User");
            test.log(LogStatus.FAIL,test.addScreenCapture(screenshotPath));
            test.log(LogStatus.INFO,"Actual: Error -> Check screenshots");

        }

        extent.endTest(test);

        extent.flush();

        driver.quit();

    }
}
