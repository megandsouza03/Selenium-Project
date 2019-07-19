package com.me.test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestCase_3 {

    WebDriver driver ;
    ExtentTest test;

    @BeforeEach
    public void setUpTest(){
        System.setProperty("webdriver.chrome.driver","/Users/megandsouza/Desktop/chromedriver");
        driver = new ChromeDriver();
        test = CaptchCheck.extent.startTest("TestCase_3");
    }


    @Test
    public void runTest() throws Exception {
        test.log(LogStatus.INFO,"Expected: [First Page: Home Page] -> [Final Page: Enter Shipping Address]");
        driver.get("http://www.amazon.com/");
        Thread.sleep(1000);

        WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
        searchBox.sendKeys("headphones");
        Thread.sleep(1000);


        WebElement searchButton = driver.findElement(By.id("nav-search")).findElement(By.className("nav-input"));
        searchButton.click();
        Thread.sleep(1000);

        WebElement img = driver.findElement(By.xpath("//*[@id=\"search\"]/div[1]/div[2]/div/span[3]/div[1]/div[1]/div/div/div/div/div/div[2]/div[2]/div/div[1]/div/div/div[1]/h2/a/span"));
        img.click();
        Thread.sleep(1000);

        String screenshotPath = ScreenShots.takeScreenshot(driver,"T3AddToCart");
        test.log(LogStatus.INFO,test.addScreenCapture(screenshotPath));

        WebElement addToCart = driver.findElement(By.id("add-to-cart-button"));
        addToCart.click();

        //Thread.sleep(1000);
        WebElement c = driver.findElement(By.xpath("/html/body/div[3]"));
        c.click();

        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"nav-cart-count\"]")).click();

        Thread.sleep(1000);

        driver.findElement(By.xpath("//*[@id=\"sc-buy-box-ptc-button\"]/span/input")).click();
        Thread.sleep(1000);


        WebElement apEmail = driver.findElement(By.id("ap_email"));
        apEmail.sendKeys("denisansah@yahoo.com");
        Thread.sleep(2000);
        WebElement apPassword = driver.findElement(By.id("ap_password"));
        apPassword.sendKeys("CaptainMegan");
        Thread.sleep(2000);
         screenshotPath = ScreenShots.takeScreenshot(driver,"T3PosDetails");
        test.log(LogStatus.INFO, "Details of the User entered");
        test.log(LogStatus.INFO,test.addScreenCapture(screenshotPath));
        driver.findElement(By.id("signInSubmit")).click();
        Thread.sleep(1000);

        CaptchCheck.checkForCaptch(driver,test,CaptchCheck.extent);

        String title = driver.getTitle();

        // System.out.println("I came here");
        System.out.println(driver.getTitle());

        if(title.equals("Enter the shipping address for this order")){

            screenshotPath = ScreenShots.takeScreenshot(driver, "T3PosFinal");

            test.log(LogStatus.PASS, "Test Successfully brought a product right from adding the product in the bucket and check out the product");
            test.log(LogStatus.PASS,test.addScreenCapture(screenshotPath));
            test.log(LogStatus.INFO,"Actual: [First Page: Home Page] -> [Final Page: Enter Shipping Address]");
        }else{

            test.log(LogStatus.FAIL, "Test Failed to buy  a product right from adding the product in the bucket and check out the product");
            test.log(LogStatus.INFO,"Actual: Error -> Check screenshots");

        }
    }

    @AfterEach
    public void endTest(){
        CaptchCheck.extent.endTest(test);
        CaptchCheck.extent.flush();
        driver.quit();
    }

}
