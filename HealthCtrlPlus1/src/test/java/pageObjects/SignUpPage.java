package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignUpPage extends BasePage{

//    WebDriver driver;
    public SignUpPage(WebDriver driver)
    {
    	super(driver);
    }
    
    @FindBy(xpath="//button[@onclick='openModal()']")
    WebElement signinOrSignup; // Button to open sign-in or sign-up modal
    
    @FindBy(xpath="//span[@id='closeModal']")
    WebElement close; // Close button for the modal
    
    // By closeXpath = By.xpath("//span[@id='closeModal']"); // Alternative way to locate close button
    
    @FindBy(xpath="//button[@id='loginHand']")
    WebElement SignIn; // Sign-in button
    
    @FindBy(xpath="//div[@id='signUpForm']//h2")
    WebElement text;
    
    @FindBy(xpath="//input[@id='signUpEmail']")
    WebElement email; // Email input field
    
    @FindBy(xpath="//input[@id='signUpPassword']")
    WebElement pass; // Password input field
    
    @FindBy(xpath="//input[@id='confirmPassword']")
    WebElement cpass; // Confirm password input field
    
    @FindBy(xpath="//button[@onclick='handleSignUp()']")
    WebElement signup; // Sign-up button
    
    JavascriptExecutor js = (JavascriptExecutor) driver;
    
    public boolean getCloseDisplayed()
    {
        return close.isDisplayed(); // Check if close button is displayed
    }
    
    public void clickSigninOrSignup()
    {
        signinOrSignup.click(); // Click sign-in or sign-up button
    }
    
    public void clickClose()
    {
        js.executeScript("arguments[0].click()", close); // Click close button
    }
    
    public boolean clickSignIn()
    {
        return SignIn.isEnabled(); // Check if sign-in button is enabled
    }
    
    public void setEmail(String emaill)
    {
        email.clear(); // Clear email input field
        email.sendKeys(emaill); // Enter email
    }
    
    public void setPassword(String pwd)
    {
        pass.clear(); // Clear password input field
        pass.sendKeys(pwd); // Enter password
    }
    
    public void setConfirmPassword(String cpwd)
    {
        cpass.clear(); // Clear confirm password input field
        cpass.sendKeys(cpwd); // Enter confirm password
    }
    
    public void setSignUp()
    {
        signup.click(); // Click sign-up button
    }
    
    public boolean SetvalidatePassword(String password) {
        if (password == null || password.isEmpty()) {
            return true; // Accept null or empty passwords
        }
        if (password.length() < 8) {
            return false; // Reject passwords shorter than 8 characters
        }
        boolean hasAlphabet = password.matches(".*[a-zA-Z].*"); // Check if password contains alphabet
        boolean hasNumber = password.matches(".*[0-9].*"); // Check if password contains number
        boolean hasSpecialChar = password.matches(".*[!@#$%^&*(),.?\":{}|<>].*"); // Check if password contains special character

        return hasAlphabet && hasNumber && hasSpecialChar; // Validate password
    }
    
    public boolean SetvalidateEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false; // Reject null or empty strings
        }
        email = email.trim(); // Trim whitespace from email
        // Ensure it contains '@' and ends with '.com' or '.net'
        return email.contains("@") && (email.toLowerCase().endsWith(".com") || email.toLowerCase().endsWith(".net"));
    }

}
