package testCases;

// Importing necessary classes
import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import Utilities.ExcelUtils;
import Utilities.RetryAnalyzer;
import pageObjects.HomePage;
import pageObjects.SignInPage;
import testBase.BaseClass;

public class SignInTests extends BaseClass {
    // Declaring variables for SignInPage, HomePage, and file path
    SignInPage signInPopUpPage;
    HomePage hp;
    String file = "C:\\Users\\2389139\\OneDrive - Cognizant\\Desktop\\HealthCTRL+\\Final\\HealthCtrlPlusProject\\HealthCtrlPlus1\\src\\test\\resources\\SigninData.xlsx";
    
    // Initialization method to set up SignInPage and HomePage instances
    @BeforeClass(groups = {"all", "regression"})
    public void init() {
        signInPopUpPage = new SignInPage(driver);
        hp = new HomePage(driver);
    }
    
    // Data provider method to get positive test data from Excel
    @DataProvider(name = "userPosData")
    public Object[][] getUserPosData() throws Exception {
        String sheet = "Sheet1";
        ExcelUtils read = new ExcelUtils();
        Object[][] data = read.ReadData(file, sheet);
        return data;
    }
    
    // Data provider method to get negative test data from Excel
    @DataProvider(name = "userNegData")
    public Object[][] getUserNegData() throws Exception {
        String sheet = "Sheet2";
        ExcelUtils read = new ExcelUtils();
        Object[][] data = read.ReadData(file, sheet);
        return data;
    }

    // Test method to validate sign-in with positive data
    @Test(dataProvider = "userPosData", priority = 3, groups = {"all", "regression"}, retryAnalyzer = RetryAnalyzer.class)
    public void validate_PosData_signin(String email, String password, String expectedValidationMessage) {
        SoftAssert softAssert = new SoftAssert();
        hp.clickSignUporInButton();
        signInPopUpPage.enterEmail(email);
        signInPopUpPage.enterPassword(password);
        signInPopUpPage.submit();
        
        String actualValidationMessage = getAlertText();
        softAssert.assertEquals(actualValidationMessage, expectedValidationMessage, "Validation message mismatch");
        System.out.println(actualValidationMessage);
        System.out.println(expectedValidationMessage);
        softAssert.assertAll();
    }
    
    // Test method to validate sign-in with negative data
    @Test(dataProvider = "userNegData", priority = 4, groups = {"all", "regression"}, retryAnalyzer = RetryAnalyzer.class)
    public void validate_NegData_Signin(String email, String password, String expectedValidationMessage) {
        HomePage hp = new HomePage(driver);
        SoftAssert softAssert = new SoftAssert();
        try {
        	hp.clickSignUporInButton();
        }catch(Exception e) {
        	System.out.print(e.getMessage());
        }
        signInPopUpPage.enterEmail(email);
        signInPopUpPage.enterPassword(password);
        signInPopUpPage.submit();
        
        String actualValidationMessage = getAlertText();
        softAssert.assertEquals(actualValidationMessage, expectedValidationMessage, "Validation message mismatch");
        System.out.println(actualValidationMessage);
        System.out.println(expectedValidationMessage);
        signInPopUpPage.clickCrossButton();
        softAssert.assertAll();
    }
    
    // Test method to validate the functionality of the sign-up button
    @Test(groups = {"all", "regression"}, priority = 1, retryAnalyzer = RetryAnalyzer.class)
    public void validateSignUpButtonFunctionality() throws InterruptedException {
        hp.clickSignUporInButton();
        Assert.assertTrue(signInPopUpPage.isSignUpPopUpDisplayed(), "Sign-up pop-up is not displayed");
        signInPopUpPage.clickSignUpButton();
    }
    
    // Test method to validate the functionality of the cross button
    @Test(groups = {"all", "regression"}, priority = 2, retryAnalyzer = RetryAnalyzer.class)
    public void validateCrossButtonFunctionality() throws InterruptedException {
        signInPopUpPage.clickCrossButton();
        Assert.assertTrue(signInPopUpPage.isSignInPopUpClosed(), "Sign-in pop-up is not closed");
    }
    
    // Method to get the text of an alert
    public String getAlertText() {
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        alert.accept();
        return alertText;
    }
    
    // Method to validate email format
    public boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        if (!email.matches(emailRegex)) {
            System.out.println("Ensure the input is a valid email format (e.g., user@example.com)");
            return false;
        }
        return true;
    }
    
    // Method to validate password format
    public boolean isValidPassword(String password) {
        StringBuilder message = new StringBuilder();
        boolean isValid = true;
        
        if (password.length() < 8) {
            message.append("Password must be at least 8 characters long. ");
            isValid = false;
        }
        boolean hasNumber = password.matches(".*\\d.*");
        boolean hasSpecialChar = password.matches(".*[!@#$%^&*(),.?\":{}|<>].*");
        if (!hasNumber) {
            message.append("Password must include at least one number. ");
            isValid = false;
        }
        if (!hasSpecialChar) {
            message.append("Password must include at least one special character. ");
            isValid = false;
        }
        if (!isValid) {
            System.out.println(message.toString().trim());
        }
        return isValid;
    }
}
