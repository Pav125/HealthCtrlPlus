package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignInPage extends BasePage
{
//    WebDriver driver;
    WebDriverWait wait;
    // Constructor
    public SignInPage(WebDriver driver) 
    {
        super(driver);
    }
    
    // Locators for the buttons
    By crossButton = By.xpath("//*[@id='closeModal']"); 
    By signInUp = By.xpath("//*[@id='loginHand']"); 
    By signInButton = By.xpath("//*[@id=\"signInForm\"]/button");
    By emailTextBox = By.id("signInEmail");
    By passTextBox = By.id("signInPassword");
    By signInPopUp = By.id("signInForm"); 
    By signUpPopUp = By.id("signUpForm");
    JavascriptExecutor js = (JavascriptExecutor) driver;

    // Method to validate the cross button
    public boolean isCrossButtonDisplayed() 
    {
        try 
        {
            WebElement crossBtn = driver.findElement(crossButton);
            return crossBtn.isDisplayed();
        } catch (NoSuchElementException e) 
        {
            return false;
        }
    }

    // Method to validate the sign-up button
    public boolean isSignUpButtonDisplayed() 
    {
        try 
        {
            WebElement signUpBtn = driver.findElement(signInUp);
            return signUpBtn.isDisplayed();
        } catch (NoSuchElementException e) 
        {
            return false;
        }
    }

    // Method to validate the sign-in button
    public boolean isSignInButtonDisplayed() 
    {
        try 
        {
            WebElement signInBtn = driver.findElement(signInButton);
            return signInBtn.isDisplayed();
        } catch (NoSuchElementException e) 
        {
            return false;
        }
    }

    // Method to validate email text box
    public boolean isEmailTextBoxDisplayed() 
    {
        try 
        {
            WebElement eTxtBox = driver.findElement(emailTextBox);
            return eTxtBox.isDisplayed();
        } catch (NoSuchElementException e) 
        {
            return false;
        }
    }

    // Method to validate password text box
    public boolean isPasswordTextBoxDisplayed() 
    {
        try 
        {
            WebElement pTxtBox = driver.findElement(passTextBox);
            return pTxtBox.isDisplayed();
        } catch (NoSuchElementException e) 
        {
            return false;
        }
    }
    

    // Method to click the cross button
    public void clickCrossButton() 
    {
        try 
        {
        	js.executeScript("arguments[0].click()", driver.findElement(crossButton));
        } catch (NoSuchElementException e) 
        {
            System.out.println("Cross button not found");
        }
    }

    // Method to click the sign-up button
    public void clickSignUpButton() 
    {
        try 
        {
            driver.findElement(signInUp).click();
        } catch (NoSuchElementException e) 
        {
            System.out.println("Sign-up button not found");
        }
    }

    // Method to check if sign-in pop-up is closed
    public boolean isSignInPopUpClosed() 
    {
        try 
        {
            return !driver.findElement(signInPopUp).isDisplayed();
        } catch (NoSuchElementException e) 
        {
            return true; // Assuming pop-up is closed if element is not found
        }
    }

    // Method to check if sign-up pop-up is displayed
    public boolean isSignUpPopUpDisplayed() 
    {
        try 
        {
            return driver.findElement(signUpPopUp).isDisplayed();
        } catch (NoSuchElementException e) 
        {
            return false;
        }
    }

    // Method to click the sign-in button
    public void clickSignInButton() 
    {
        try 
        {
            driver.findElement(signInUp).click();
        } catch (NoSuchElementException e) 
        {
            System.out.println("Sign-in button not found");
        }
    }
    
    public void submit() {
    	driver.findElement(signInButton).click();
    }

    // Method to enter email
	 public void enterEmail(String email) 
	 {
		//WebElement eTxtBox = wait.until(ExpectedConditions.visibilityOfElementLocated(emailTextBox));
			 WebElement EmTxtBox = driver.findElement(emailTextBox);
		EmTxtBox.clear();
			 EmTxtBox.sendKeys(email);
	}

 	//Method to enter password
	public void enterPassword(String password) {
		// WebElement pTxtBox = wait.until(ExpectedConditions.visibilityOfElementLocated(passTextBox));
		 WebElement paTxtBox = driver.findElement(passTextBox);
		paTxtBox.clear();
		paTxtBox.sendKeys(password);
	 }


}
