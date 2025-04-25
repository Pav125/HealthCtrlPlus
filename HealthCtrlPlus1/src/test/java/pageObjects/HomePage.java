package pageObjects;
 
// Importing necessary Selenium WebDriver and WebElement classes
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
 
public class HomePage extends BasePage {
    
    // Constructor to initialize the WebDriver instance and call the parent class constructor
    public HomePage(WebDriver driver) {
        super(driver);
    }
    
    // NavBar elements
    @FindBy(xpath = "//nav/ul/li[1]/a")
    WebElement homeLink;
    
    @FindBy(xpath = "//nav/ul/li[2]/a")
    WebElement informationLink;
    
    @FindBy(xpath = "//nav/ul/li[3]/a")
    WebElement knowyourbmiLink;
    
    @FindBy(xpath = "//nav/ul/li[4]/a")
    WebElement tipsLink;
    
    @FindBy(xpath = "//nav/ul/li[5]/a")
    WebElement historyLink;
    
    // Home elements
    @FindBy(xpath = "//button[contains(text(),'Sign In / Sign Up')]")
    WebElement signinOrSignup;
    
    @FindBy(className = "modal")
    WebElement signupModal;
    
    @FindBy(xpath = "//div[@id='signUpForm']/h2")
    WebElement signupModalHeader;
    
    @FindBy(id = "quoteOfTheDay")
    WebElement quote;
    
    // Method to click on the Home link
    public void clickHomeLink() {
        homeLink.click();
    }
    
    // Method to click on the Information link
    public void clickInformationLink() {
        informationLink.click();
    }
    
    // Method to click on the Know Your BMI link
    public void clickKnowyourbmiLink() {
        knowyourbmiLink.click();
    }
    
    // Method to click on the Tips link
    public void clickTipsLink() {
        tipsLink.click();
    }
    
    // Method to click on the History link
    public void clickHistoryLink() {
        historyLink.click();
    }
    
    // Method to click on the Sign In / Sign Up button
    public void clickSignUporInButton() {
        signinOrSignup.click();
    }
    
    // Method to get the signup model element
    public WebElement getsignupModel() {
        return signupModal;
    }
    
    // Method to get the text of the signup model header
    public String getsignupModelHeader() {
        return signupModalHeader.getText();
    }
    
    // Method to get the text of the quote of the day
    public String getQuote() {
        return quote.getText();
    }
}
