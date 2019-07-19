package com.me.driver;

import com.me.test.TestCase_1_Negative;
import com.me.test.TestCase_1_Positive;
import com.me.test.TestCase_2;
import com.me.test.TestCase_3;
import com.relevantcodes.extentreports.ExtentReports;

public class Driver {

    public static ExtentReports extent;

    public static void main(String[] args) {
        extent = new ExtentReports("/Users/megandsouza/Desktop/seleniumReports/Amazon_Selenium_Basic_Function_Test.html");
        TestCase_1_Negative test_1 = new TestCase_1_Negative();

        //Test case 1 (Negative): Create test scenarios for registering 1 user
        try{
            test_1.runTest(extent);
        }catch (InterruptedException e){
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        TestCase_1_Positive test_1_pos = new TestCase_1_Positive();

        //Test case 1 (Postive): Create test scenarios for registering 1 user
        try{
            test_1_pos.runTest(extent);
        }catch (InterruptedException e){
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        TestCase_2 testCase_2 = new TestCase_2();
        try{
            testCase_2.runTest(extent);
        }catch (InterruptedException e){
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        TestCase_3 testCase_3 = new TestCase_3();

                try{
            testCase_3.runTest(extent);
        }catch (InterruptedException e){
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
