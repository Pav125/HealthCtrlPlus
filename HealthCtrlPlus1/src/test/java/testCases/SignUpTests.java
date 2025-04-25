package testCases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Utilities.ExcelUtils;
import Utilities.RetryAnalyzer;
import pageObjects.SignUpPage;
import testBase.BaseClass;

public class SignUpTests extends BaseClass {

    String file = "C:\\Users\\2389139\\OneDrive - Cognizant\\Desktop\\HealthCTRL+\\Final\\HealthCtrlPlusProject\\HealthCtrlPlus1\\src\\test\\resources\\Data1 3.xlsx"; // Path to the Excel file
    SoftAssert sa = new SoftAssert(); // Initialize SoftAssert for assertions
    
    @DataProvider(name = "testng")
    public Object[][] dataprovider1() throws IOException {
        String sheet1 = "Sheet1"; // Name of the first sheet
        ExcelUtils read = new ExcelUtils(); // Create an instance of ExcelUtils
        Object[][] data = read.ReadData(file, sheet1); // Read data from the first sheet
        return data; // Return the data
    }
    
    @DataProvider(name = "testng1")
    public Object[][] dataprovider2() throws IOException {
        String sheet2 = "Sheet2"; // Name of the second sheet
        ExcelUtils read = new ExcelUtils(); // Create an instance of ExcelUtils
        Object[][] data = read.ReadData(file, sheet2); // Read data from the second sheet
        return data; // Return the data
    }
    
    @Test(dataProvider = "testng", groups= {"all","regression","smoke"}, retryAnalyzer = RetryAnalyzer.class)
    public void validate_SignUp_validData(String emailid, String password, String confirmPassword, String expectedResult) {
        SignUpPage sp = new SignUpPage(driver); // Create an instance of SignUpPage
        sp.clickSigninOrSignup(); // Click the sign-in or sign-up button
        sp.setEmail(emailid); // Set the email
        sp.setPassword(password); // Set the password
        sa.assertTrue(sp.SetvalidatePassword(password)); // Validate the password
        sp.setConfirmPassword(confirmPassword); // Set the confirm password
        sp.setSignUp(); // Click the sign-up button
        String result = driver.switchTo().alert().getText(); // Get the alert text
        driver.switchTo().alert().accept(); // Accept the alert
        sa.assertEquals(expectedResult, result); // Assert the expected result
        sp.clickClose(); // Click the close button
        sa.assertAll(); // Assert all
    }
    
    @Test(dataProvider = "testng1", groups= {"all","regression","smoke"}, retryAnalyzer = RetryAnalyzer.class)
    public void validate_SignUp_InvalidData(String emailid1, String password1, String confirmPassword1, String expectedResult1) {
        SignUpPage sp = new SignUpPage(driver); // Create an instance of SignUpPage
        sp.clickSigninOrSignup(); // Click the sign-in or sign-up button
        sp.setEmail(emailid1); // Set the email
        sp.setPassword(password1); // Set the password
        sa.assertTrue(sp.SetvalidatePassword(password1)); // Validate the password
        sp.setConfirmPassword(confirmPassword1); // Set the confirm password
        sp.setSignUp(); // Click the sign-up button
        String result = driver.switchTo().alert().getText(); // Get the alert text
        sa.assertEquals(expectedResult1, result); // Assert the expected result
        driver.switchTo().alert().accept(); // Accept the alert
        sa.assertAll(); // Assert all
    }
}
