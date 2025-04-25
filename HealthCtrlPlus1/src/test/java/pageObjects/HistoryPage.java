package pageObjects;
 
import java.util.ArrayList;
import java.util.List;
 
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
 
public class HistoryPage extends BasePage {
	public HistoryPage(WebDriver driver) {
		super(driver);
	}
	// finds the no history message
	@FindBy(id = "noHistoryMessage")
	WebElement noHistory;
	// finds the list of history results
	@FindBy(xpath = "  //*[@id = 'noHistoryMessage']/following::p")
	List<WebElement> historyElement;
	public String getNoHistoryMsg() {	
		try {
			return noHistory.getText();
		}
		catch(Exception e) {
			return e.getMessage();
		}
	}
	// method to get the list of all the available history
	public List<String> getHistoryElementMsg(){
		List<String> historyText = new ArrayList<>();
		try {
			for(WebElement element : historyElement) {
				historyText.add(element.getText());
			}
			return historyText;
		}
		catch(Exception e) {
			historyText.add(e.getMessage());
			return historyText;
		}
	}
	// method to get the last history message with the bmi input values
	 public  WebElement getHistoryMsg(String height, String heightUnit, String weight, String weightUnit) {
		 try {
	        String heightUnitVal = heightUnit.equals("Centimeters (cm)") ? "cm" : "in";
	        String weightUnitVal = weightUnit.equals("Kilograms (kg)") ? "kg" : "lb";
	        String historyText = "Height: " + height + " " + heightUnitVal + ", Weight: " + weight + " " + weightUnitVal;
 
	        return driver.findElement(By.xpath("//*[contains(text(), '" + historyText + "')][last()]"));
		 }
		 catch(Exception e) {
			 System.out.println(e.getMessage());
			 return null;
		 }

	 }
}