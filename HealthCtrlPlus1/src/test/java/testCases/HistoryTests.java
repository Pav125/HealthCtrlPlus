package testCases;
 
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
 
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
 
import Utilities.ExcelUtils;
import Utilities.RetryAnalyzer;
import pageObjects.HistoryPage;
import pageObjects.HomePage;
import pageObjects.KnowYourBMIPage;
import testBase.BaseClass;
public class HistoryTests extends BaseClass  {
	String file = System.getProperty("user.dir") + "/src/test/resources/BMIDATA.xlsx";
 
	@DataProvider(name="Data")
	public Object[][] getData() throws Exception
	{
		String sheet = "HistoryData";
		ExcelUtils read = new ExcelUtils();
		Object[][] data = read.ReadData(file, sheet);
		return data;
	}
	@Test(priority = 1, groups= {"all","regression"}, retryAnalyzer = RetryAnalyzer.class)
	public void validate_NoHistory() {
		HomePage hp = new HomePage(driver);
		HistoryPage historyObj = new HistoryPage(driver);
		hp.clickHistoryLink();
		// validate that message when no history is available
		assertEquals(historyObj.getNoHistoryMsg(),"No history available.");
	}
	@Test(dataProvider = "Data", priority = 2,groups= {"all","regression"}, retryAnalyzer = RetryAnalyzer.class)
	public void validate_History(String heightUnit,String height,String weightUnit,String weight, String bmiValue) {
		HomePage hp = new HomePage(driver);
	    HistoryPage historyObj = new HistoryPage(driver);
	    hp.clickKnowyourbmiLink();
	    KnowYourBMIPage bmi = new KnowYourBMIPage(driver);
	    SoftAssert sa=new SoftAssert();
	    //setting the height and weight values with the units on the KnowYourBMI page
		bmi.setHeight(heightUnit, height);
		bmi.setWeight(weightUnit, weight);
		bmi.clickButton();
 
		if (height.endsWith(".0")) {
			height = height.substring(0, height.length() - 2);
		}
		if (weight.endsWith(".0")) {
			weight = weight.substring(0, weight.length() - 2);
		}
	    // Navigate to History page
	    hp.clickHistoryLink();
	    // validate the history for the result
	    sa.assertTrue(historyObj.getHistoryMsg(height, heightUnit, weight, weightUnit).isDisplayed());
	    sa.assertAll();
	}
	@Test(priority = 3, groups= {"all","regression"}, retryAnalyzer = RetryAnalyzer.class)
	public void validate_refreshPage_noHistory() {
		driver.navigate().refresh();
		HomePage hp = new HomePage(driver);
		HistoryPage historyObj = new HistoryPage(driver);
	    hp.clickHistoryLink();
	    assertNotEquals(historyObj.getNoHistoryMsg(),"No history available.");
	}
}