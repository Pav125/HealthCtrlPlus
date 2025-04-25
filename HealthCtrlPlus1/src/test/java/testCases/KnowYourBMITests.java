package testCases;

// Importing necessary classes
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import Utilities.ExcelUtils;
import Utilities.RetryAnalyzer;
import pageObjects.HomePage;
import pageObjects.KnowYourBMIPage;
import testBase.BaseClass;

public class KnowYourBMITests extends BaseClass {
    // Declaring variables for KnowYourBMIPage, SoftAssert, and options lists
    KnowYourBMIPage bmi;
    SoftAssert sa;
    List<String> weightOptions = new ArrayList<>();
    List<String> heightOptions = new ArrayList<>();
    int rowIndex = 0;
    String file = "C:\\Users\\2389139\\OneDrive - Cognizant\\Desktop\\HealthCTRL+\\Final\\HealthCtrlPlusProject\\HealthCtrlPlus1\\src\\test\\resources\\BMIDATA.xlsx";
    
    // Initialization method to set up HomePage and KnowYourBMIPage instances
    @BeforeClass(groups = {"all", "regression", "smoke"})
    public void init() {
        HomePage hp = new HomePage(driver);
        hp.clickKnowyourbmiLink();
        bmi = new KnowYourBMIPage(driver);
    }
    
    // Test method to verify UI elements on the Know Your BMI page
    @Test(priority = 1, groups = {"all", "regression", "smoke"}, retryAnalyzer = RetryAnalyzer.class)
    public void testUIElements() {
        heightOptions.add("Centimeters (cm)");
        heightOptions.add("Inches (in)");
        weightOptions.add("Kilograms (kg)");
        weightOptions.add("Pounds (lb)");
        SoftAssert sa = new SoftAssert();
        sa.assertTrue(bmi.heading());
        sa.assertTrue(bmi.heightLabel());
        sa.assertTrue(bmi.heightLabel());
        sa.assertTrue(bmi.heightInteractiveness());
        sa.assertTrue(bmi.weightLabel());
        sa.assertTrue(bmi.weightInteractiveness());
        sa.assertTrue(bmi.yourBMILabel());
        sa.assertEquals(heightOptions, bmi.heightOptions());
        sa.assertEquals(weightOptions, bmi.weightOptions());
        sa.assertAll();
    }
    
    // Data provider method to get positive test data from Excel
    @DataProvider(name = "positiveData")
    public Object[][] getPositiveData() throws Exception {
        String sheet = "PositiveData";
        ExcelUtils read = new ExcelUtils();
        Object[][] data = read.ReadData(file, sheet);
        return data;
    }
    
    // Data provider method to get negative test data from Excel
    @DataProvider(name = "negativeData")
    public Object[][] getNegativeData() throws Exception {
        String sheet = "NegativeData";
        ExcelUtils read = new ExcelUtils();
        Object[][] data = read.ReadData(file, sheet);
        return data;
    }
    
    // Test method to validate BMI calculation with valid data
    @Test(priority = 2, dataProvider = "positiveData", groups = {"all", "regression", "smoke"}, retryAnalyzer = RetryAnalyzer.class)
    public void validate_BMI_ValidData(String heightUnit, String height, String weightUnit, String weight, String expectedBMI) throws Exception {
        SoftAssert sa = new SoftAssert();
        bmi.setHeight(heightUnit, height);
        bmi.setWeight(weightUnit, weight);
        bmi.clickButton();
        String actualBMI = bmi.getResult().trim();
        String result = "Expected:" + expectedBMI + "|Actual BMI:" + actualBMI;
        System.out.println(result);
        sa.assertEquals(actualBMI, expectedBMI);
        rowIndex++;
        sa.assertAll();
    }
    
    // Test method to validate BMI calculation with invalid data
    @Test(priority = 3, dataProvider = "negativeData", groups = {"all", "regression", "smoke"}, retryAnalyzer = RetryAnalyzer.class)
    public void validate_BMI_InvalidData(String heightUnit, String height, String weightUnit, String weight) throws Exception {
        bmi.setHeight(heightUnit, height);
        bmi.setWeight(weightUnit, weight);
        bmi.clickButton();
        if (isAlertPresent()) {
            Alert alert = driver.switchTo().alert();
            alert.accept();
        } else {
            String output = bmi.getResult();
            System.out.print(output);
        }
    }
    
    // Method to check if an alert is present
    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }
}
