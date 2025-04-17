package POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateLeadsPomPage {
	//declaration
	@FindBy(linkText="Leads")
	private WebElement header;
	@FindBy(xpath="//img[@alt='Create Lead...']")
	private WebElement plusicon;
	//initialize
	public CreateLeadsPomPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		
	}
	//utilize
	public String getHeader() {
		return header.getText();
		
	}
	public void getPlusicon() {
		plusicon.click();
	}

}
