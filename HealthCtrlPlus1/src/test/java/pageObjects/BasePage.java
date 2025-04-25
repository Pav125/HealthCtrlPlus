package pageObjects;

// Importing necessary Selenium WebDriver and PageFactory classes
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
    // Declaring a WebDriver instance variable
    public WebDriver driver;
    
    // Constructor to initialize the WebDriver instance and PageFactory elements
    public BasePage(WebDriver driver) {
        this.driver = driver;
        // Initializing the PageFactory elements with the given WebDriver instance
        PageFactory.initElements(driver, this);
    }
}
