package POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationPomPages {
	// Declare
		 @FindBy(linkText = "Organizations")
		 private WebElement header;

		 @FindBy(xpath = "//img[@alt='Create Organization...']")
		 private WebElement plusicon;

		 // Initialization
		 public  OrganizationPomPages(WebDriver driver) {

		  PageFactory.initElements(driver, this);
		 }

		 // Utilization
		 public String getHeader() {
		  return header.getText();
		 }

		 public void getPlusicon() {
		  plusicon.click();

		 }


}
