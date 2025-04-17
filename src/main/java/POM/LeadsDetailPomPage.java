package POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LeadsDetailPomPage {
	// Declare
	 @FindBy(xpath = "//span[contains(text(),'Lead Information')]")
	 private WebElement header;

	 @FindBy(id = "dtlview_Last Name")
	 private WebElement verifyLastname;

	 @FindBy(id = "mouseArea_Company")
	 private WebElement verifycompanyname;
	// Initialization
		 public LeadsDetailPomPage (WebDriver driver) {
		  PageFactory.initElements(driver, this);
		 }
		// Utilization

		 public String getHeader() {
		  return header.getText();
		 }

		 public String getVerifyLastname() {
		  return verifyLastname.getText();
		 }

		 public String getVerifycompanyname() {
		  return verifycompanyname.getText();
		 }


}
