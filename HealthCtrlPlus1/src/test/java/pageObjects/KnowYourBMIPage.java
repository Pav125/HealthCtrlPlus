package pageObjects;

// Importing necessary classes
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class KnowYourBMIPage extends BasePage {
    
    // Constructor to initialize the WebDriver instance and call the parent class constructor
    public KnowYourBMIPage(WebDriver driver) {
        super(driver);
    }
    
    // Finding elements using @FindBy annotation
    @FindBy(className="heat")
    WebElement heading;
    
    @FindBy(xpath="//label[text()='Height:']")
    WebElement heightLabel;
    
    @FindBy(id="heightUnit")
    WebElement heightUnits;
    
    @FindBy(id="height")
    WebElement heightInput;
    
    @FindBy(xpath="//*[@id=\"bmi\"]/div/div[2]/label")
    WebElement weightLabel;
    
 @FindBy(id="weightUnit")
    WebElement weightUnits;
    
    @FindBy(id="weight")
    WebElement weightInput;
    
    @FindBy(id="calculateBtn")
    WebElement calculateButton;
    
    @FindBy(xpath="//*[@id=\"bmi\"]/div/div[3]/label")
    WebElement yourBMILabel;
    
    // Locating elements using By
    By result = By.id("bmiResult");
    By healthMeter = By.id("healthSpeedometer");
    
    // Method to check if heading is displayed and contains specific text
    public boolean heading() {
        boolean isPresent = heading.isDisplayed() && heading.getText().contains("BMI Calculator");
        return isPresent;
    }
    
    // Method to check if height label is displayed and contains specific text
    public boolean heightLabel() {
        boolean isPresent = heightLabel.isDisplayed() && heightLabel.getText().contains("Height:");
        return isPresent;
    }
    
    // Method to get options from height units dropdown
    public List<String> heightOptions() {
        List<String> list = new ArrayList<>();
        Select select = new Select(heightUnits);
        List<WebElement> options = select.getOptions();
        for (WebElement s : options) {
            list.add(s.getText());
        }
        return list;
    }
    
    // Method to check if height input is interactive
    public boolean heightInteractiveness() {
        boolean isInteractive = heightInput.isEnabled();
        return isInteractive;
    }
    
    // Method to set height value
    public void setHeight(String unit, String height) {
        new Select(heightUnits).selectByVisibleText(unit);
        heightInput.clear();
        heightInput.sendKeys(height);
    }
    
    // Method to check if weight label is displayed and contains specific text
    public boolean weightLabel() {
        boolean isPresent = weightLabel.isDisplayed() && weightLabel.getText().contains("Weight:");
        return isPresent;
    }
    
    // Method to get options from weight units dropdown
    public List<String> weightOptions() {
        List<String> list = new ArrayList<>();
        Select select = new Select(weightUnits);
        List<WebElement> options = select.getOptions();
        for (WebElement s : options) {
            list.add(s.getText());
        }
        return list;
    }
    
    // Method to check if weight input is interactive
    public boolean weightInteractiveness() {
        boolean isInteractive = weightInput.isEnabled();
        return isInteractive;
    }
    
    // Method to set weight value
    public void setWeight(String unit, String weight) {
        new Select(weightUnits).selectByVisibleText(unit);
        weightInput.clear();
        weightInput.sendKeys(weight);
    }
    
    // Method to click the calculate button
    public void clickButton() {
        calculateButton.click();
    }
    
    // Method to check if your BMI label is displayed
    public boolean yourBMILabel() {
        boolean isPresent = yourBMILabel.isDisplayed();
        return isPresent;
    }
    
    // Method to get the result value
    public String getResult() {
        return driver.findElement(result).getAttribute("value");
    }
}
