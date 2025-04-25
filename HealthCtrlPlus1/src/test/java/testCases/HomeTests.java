package testCases;

// Importing necessary classes
import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import Utilities.RetryAnalyzer;
import Utilities.ScreeshotUtil;
import pageObjects.HomePage;
import testBase.BaseClass;

public class HomeTests extends BaseClass {
    
    // Declaring variables for page name, SoftAssert, HomePage, and ScreenshotUtil
    String page = "HomePage";
    SoftAssert sa = new SoftAssert();
    HomePage hp;
    ScreeshotUtil ts;
    
    // Initialization method to set up HomePage and ScreenshotUtil instances
    @BeforeClass(groups = {"all"})
    public void init() {
        hp = new HomePage(driver);
        ts = new ScreeshotUtil(driver);
    }
    
    // Test method to verify the home page URL
    @Test(groups = {"all"}, retryAnalyzer = RetryAnalyzer.class)
    public void verify_home_page_URL() throws IOException {
        String expectedURL = "https://bmi-2-s4m4.vercel.app/";
        String actualURL = driver.getCurrentUrl();
        System.out.println(actualURL);
        Assert.assertEquals(actualURL.trim(), expectedURL.trim());
        // Capture screenshot of the page
        ts.capturePage(page, page);
    }

    // Test method to verify that the quote changes after refreshing the page
    @Test(groups = {"all"}, retryAnalyzer = RetryAnalyzer.class)
    public void verify_quote_change() {
        String curQuote = hp.getQuote();
        driver.navigate().refresh();
        String newQuote = hp.getQuote();
        Assert.assertNotEquals(curQuote, newQuote);
    }
}
