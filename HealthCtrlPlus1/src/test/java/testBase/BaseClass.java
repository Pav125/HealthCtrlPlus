package testBase;

// Importing necessary classes
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

    // Declaring a static WebDriver instance variable
    public static WebDriver driver;
    
    // Setup method to initialize the WebDriver based on the browser parameter
    @Parameters({"operatingSystem", "browser"})
    @BeforeClass(groups = {"all", "regression", "smoke"})
    public void setup(String operatingSystem, String browser) {
        switch (browser.toLowerCase()) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            default:
                System.out.println("invalid browser");
                return;
        }
        // Maximizing the browser window and setting implicit wait
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        
        // Navigating to the specified URL
        driver.get("https://bmi-2-s4m4.vercel.app/");
    }
    
    // Tear down method to quit the WebDriver instance
    @AfterClass(groups = {"all", "regression", "smoke"})
    public void tearDown() {
        driver.quit();
    }
    
    // Method to capture a screenshot in case of an error
    public String captureError(String tname) throws IOException {
        // Generating a timestamp for the screenshot file name
        String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        
        // Taking a screenshot and saving it to a file
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        
        // Defining the target file path and renaming the source file
        String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";
        File targetFile = new File(targetFilePath);
        
        sourceFile.renameTo(targetFile);
        
        // Returning the path of the saved screenshot
        return targetFilePath;
    }
}
