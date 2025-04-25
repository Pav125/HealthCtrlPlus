package Utilities;

// Importing necessary classes
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import testBase.BaseClass;

public class ExtentReportManager implements ITestListener {
    // Declaring variables for ExtentSparkReporter, ExtentReports, ExtentTest, and report name
    public ExtentSparkReporter sparkReporter;
    public ExtentReports extent;
    public ExtentTest test;
    String repName;
    
    // Method to initialize the report at the start of the test
    public void onStart(ITestContext testContext) {
        // Generating a timestamp for the report name
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        repName = "Test-Report-" + timeStamp + ".html";
        // Specifying the location of the report
        sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName);
        
        // Configuring the report properties
        sparkReporter.config().setDocumentTitle("Health Ctrl+ Report"); // Title of report
        sparkReporter.config().setReportName("Health Ctrl+ Testing"); // Name of the report
        sparkReporter.config().setTheme(Theme.STANDARD);
        
        // Initializing ExtentReports and attaching the reporter
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Application", "Health Ctrl+");
        extent.setSystemInfo("Team Name", System.getProperty("INTQEA25QE004HC&S"));
        extent.setSystemInfo("Environment", "QA");
        
        // Setting system information from test context parameters
        String os = testContext.getCurrentXmlTest().getParameter("os");
        extent.setSystemInfo("Operating System", os);
        
        String browser = testContext.getCurrentXmlTest().getParameter("browser");
        extent.setSystemInfo("Browser", browser);
        
        // Setting included groups information
        List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
        if (!includedGroups.isEmpty()) {
            extent.setSystemInfo("Groups", includedGroups.toString());
        }
    }
    
    // Method to log test success
    public void onTestSuccess(ITestResult result) {
        test = extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups()); // Display groups in report
        test.log(Status.PASS, result.getName() + " got successfully executed");
    }
    
    // Method to log test failure
    public void onTestFailure(ITestResult result) {
        test = extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups());
        
        test.log(Status.FAIL, result.getName() + " got failed");
        test.log(Status.INFO, result.getThrowable().getMessage());
        
        try {
            // Capturing screenshot on test failure
            String imgPath = new BaseClass().captureError(result.getName());
            test.addScreenCaptureFromPath(imgPath);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
    
    // Method to log test skipped
    public void onTestSkipped(ITestResult result) {
        test = extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.SKIP, result.getName() + " got skipped");
        test.log(Status.INFO, result.getThrowable().getMessage());
    }
    
    // Method to flush the report at the end of the test
    public void onFinish(ITestContext testContext) {
        extent.flush();
    }
}
