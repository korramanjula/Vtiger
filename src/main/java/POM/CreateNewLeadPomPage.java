package POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewLeadPomPage {
	 // Declaration
	 @FindBy(xpath="(//span['Creating New Lead'])[2]")
	 private WebElement header;
	 
	 @FindBy(name="lastname")
	 private WebElement lastname_TF;
	 
	 @FindBy(name="company")
	 private WebElement company_TF;
	 
	 
	 @FindBy(xpath="(//input[@title='Save [Alt+S]'])[1]")
	 private WebElement saveBtn;
	 
	 //Initialization
	 public CreateNewLeadPomPage(WebDriver driver) {
	  PageFactory.initElements(driver, this);
	 }
	 
	 
	 //Utilization
	 public String getHeader() {
	  return header.getText();
	 }
	 
	 public void getLastname_TF(String Lastname) {
	  lastname_TF.sendKeys(Lastname);
	  
	 }
	 public void getcompany_TF(String companyname) {
		 company_TF.sendKeys(companyname);
	 }
		  
	 
	 public void getSaveBtn() {
	   saveBtn.click();
	 }
	 

}
