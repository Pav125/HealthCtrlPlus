package testCases;

// Importing necessary classes
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import Utilities.RetryAnalyzer;
import Utilities.ScreeshotUtil;
import pageObjects.HomePage;
import testBase.BaseClass;

public class InformationTests extends BaseClass {
    
    // Declaring variables for page name, SoftAssert, HomePage, and ScreenshotUtil
    String page = "InformationPage";
    SoftAssert sa = new SoftAssert();
    HomePage hp;
    ScreeshotUtil ts;
    
    // Initialization method to set up HomePage and ScreenshotUtil instances
    @BeforeClass(groups = {"all"})
    public void init() {
        hp = new HomePage(driver);
        ts = new ScreeshotUtil(driver);
    }
    
    // Test method to verify the information page URL
    @Test(groups = {"all"}, retryAnalyzer = RetryAnalyzer.class)
    public void verify_information_page_URL() throws Exception {
        // Creating a new instance of HomePage
        HomePage hp = new HomePage(driver);
        // Clicking on the Information link
        hp.clickInformationLink();
        // Expected and actual URLs
        String expectedURL = "https://bmi-2-s4m4.vercel.app/#information";
        String actualURL = driver.getCurrentUrl();
        // Asserting that the actual URL matches the expected URL
        sa.assertEquals(actualURL, expectedURL);
        
        // Capturing screenshot of the page
        ts.capturePage(page, page);
        // Asserting all soft assertions
        sa.assertAll();
    }
}
