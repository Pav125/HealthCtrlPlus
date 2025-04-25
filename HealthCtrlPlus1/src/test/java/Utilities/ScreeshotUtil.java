package Utilities;

// Importing necessary classes
import java.io.File;
import java.io.IOException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreeshotUtil {
    // Declaring a WebDriver instance variable
    WebDriver driver;
    
    // Constructor to initialize the WebDriver instance
    public ScreeshotUtil(WebDriver driver) {
        this.driver = driver;
    }
    
    // Method to capture a screenshot of the page
    public void capturePage(String page, String name) throws IOException {
        // Taking a screenshot and saving it to a file
        TakesScreenshot ts = (TakesScreenshot) driver;
        File src = ts.getScreenshotAs(OutputType.FILE);
        // Defining the target file path and renaming the source file
        File tr = new File("C:\\Users\\2388993\\eclipse-workspace\\HealthCtrlPlus1\\Screenshots\\" + page + "\\" + name + ".png");
        src.renameTo(tr);
    }
}
