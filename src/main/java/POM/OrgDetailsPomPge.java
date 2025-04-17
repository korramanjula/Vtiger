package POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrgDetailsPomPge {
	//declare 
	@FindBy(xpath="//span[contains(text(),'Organization Information')]")
	private WebElement header;
	@FindBy(id="dtlview_Organization Name")
	private WebElement verifyOrgname;
	@FindBy(id="mouseArea_Phone" )
	private WebElement verifyOrgphno;
	@FindBy(id="dtlview_Industry")
	private WebElement verifyIndustry;
	@FindBy(id="dtlview_Type")
	private WebElement verifyType;
	//initialization
	public OrgDetailsPomPge (WebDriver driver) {
		PageFactory.initElements(driver, this);
		
		
	}
	//utilization
	public String getHeader() {
		return header.getText();
		
	}
	public String getVerifyOrgname() {
		return verifyOrgname.getText();
	}
	public String getVerifyOrgphno() {
		return verifyOrgphno.getText();
	}
	public String getVerifyIndustry() {
		return verifyIndustry.getText();
}
	public String getVerifyType() {
		return verifyType.getText();
}
}
