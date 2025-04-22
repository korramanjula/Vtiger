package POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import GenericUtilities.Webdriver_Utility;

public class HomePomPage {
	//Declare
			@FindBy(partialLinkText="Home")
			private WebElement header;
			
			@FindBy(linkText="Organizations")
			private WebElement org_tab;
			
			@FindBy(xpath="(//a[text()='Contacts'])[1]")
			private WebElement cont_tab;
			
			@FindBy(linkText="Leads")
			private WebElement leads_tab;
			
			@FindBy(xpath="//span[text()=' Administrator']/../../descendant::img")
			private WebElement admin_icon;
			
			
			@FindBy(linkText="Sign Out")
			private WebElement signout;
			
			//Initialize
			public HomePomPage(WebDriver driver) {
				PageFactory.initElements(driver,this);
			}
		//Utilize
			public String getHeader() {
				 return header.getText();
			}

			public void getOrg_tab() {
				 org_tab.click();
			}

			public void getCont_tab() {
			     cont_tab.click();
			}
			public void getleads_tab() {
			     leads_tab.click();
			}
			

			public WebElement getAdmin_icon() {
			 return admin_icon;
			}

			public void getSignout() {
				 signout.click();
			}
			public void logout(WebDriver driver) {
				Webdriver_Utility w_util=new Webdriver_Utility();
				w_util.ActionMouseHovering(driver, admin_icon);
				signout.click();
			}

	}


