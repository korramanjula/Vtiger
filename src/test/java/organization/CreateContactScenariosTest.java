package organization;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;

import Baseclass_Utility.Baseclass;
import GenericUtilities.ClassObject_Utility;
import GenericUtilities.Exel_Utility;
import GenericUtilities.Java_Utility;
import GenericUtilities.Webdriver_Utility;
import Listeners_Utility.Listeners;
import POM.ContactDetailPomPge;
import POM.ContactPomPage;
import POM.CreateNewContactPomPage;
import POM.CreateNewOrganization;
import POM.HomePomPage;
import POM.OrgDetailsPomPge;
import POM.OrganizationPomPages;

public class CreateContactScenariosTest extends Baseclass{
	//public class Create_ContactTest extends Baseclass{
		//@Parameters("browser")
		@Test(groups="smoke")

		public void Create_Contact_Test() throws IOException, InterruptedException {
			
			ClassObject_Utility.getTest().log(Status.INFO, "fetch data ftom excel file");
			Exel_Utility ex_util=new Exel_Utility();
			
			Java_Utility j_util=new  Java_Utility();
			int random=j_util.getRandomNumber();
			String contactname=ex_util.FetchDtaFromExelFile("contacts", 1, 3)+random;

			
	        Webdriver_Utility w_util=new Webdriver_Utility();
	        
	    	ClassObject_Utility.getTest().log(Status.INFO, "home page verigication");
			HomePomPage home=new HomePomPage(driver);
			
			boolean exp_res=home.getHeader().contains("Home");
			SoftAssert soft=new SoftAssert();
			soft.assertEquals(exp_res, true);
			ClassObject_Utility.getTest().log(Status.INFO, "navigate to contact tab");
			home.getCont_tab();
			
			//Identify plus symbol and click it
			ClassObject_Utility.getTest().log(Status.INFO, "navigate to create new contact page");
			//driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
			ContactPomPage con=new ContactPomPage(driver);
			con.getPlusicon();
			ClassObject_Utility.getTest().log(Status.INFO, "create new contact ");
			CreateNewContactPomPage newcon=new CreateNewContactPomPage(driver);
			newcon.getLastname_TF(contactname);
			newcon.getSaveBtn();
			
			//Enter the lastname in LN textfield
			ClassObject_Utility.getTest().log(Status.INFO, "verifying the  contact");
			ContactDetailPomPge condetail=new ContactDetailPomPge(driver);

			boolean Exp_res= condetail.getHeader().contains(contactname);
			//Save the  LastName
			Assert.assertEquals(Exp_res, true);
		

			ClassObject_Utility.getTest().log(Status.INFO, "navigate to  contact tab and delete contact");
			
		   //Identify the Contacts button and click on it
				
			home.getCont_tab();
			//delete the contact
			driver.findElement(By.xpath("//a[text()='"+contactname+"']/ancestor::tr[@bgcolor='white']/descendant::a[text()='del']")).click();
	       //handle the popup
			Thread.sleep(2000);
			ClassObject_Utility.getTest().log(Status.INFO, "handling delete popup");
			w_util.HandleAlerAndAccept(driver);
			soft.assertAll();
			
		}	
			
	//public class CreateContactsWithOrgTest extends Baseclass{
		//@Parameters("browser")
		@Test(groups="regression")
		public void CreateContact_OrgnameTest() throws IOException, InterruptedException {

			ClassObject_Utility.getTest().log(Status.INFO, "fetching the data from excel file");
			Exel_Utility ex_util = new Exel_Utility();
			Java_Utility j_util=new Java_Utility();
			int random=j_util.getRandomNumber();
			String contname = ex_util.FetchDtaFromExelFile("Contacts", 7, 3)+ random;

			String orgname = ex_util.FetchDtaFromExelFile("Contacts", 7, 4)+random;
			
			ClassObject_Utility.getTest().log(Status.INFO, "verifying home page");
			

			Webdriver_Utility w_util = new Webdriver_Utility();
	

			HomePomPage home = new HomePomPage(driver);
			

			boolean exp_res=home.getHeader().contains("Home");
			SoftAssert soft=new SoftAssert();
			soft.assertEquals(exp_res, true);
			ClassObject_Utility.getTest().log(Status.INFO, "navigate to org tab");
			home.getOrg_tab();

			// identify plus button and click on it
			
			ClassObject_Utility.getTest().log(Status.INFO, "navigate to create new org");
			OrganizationPomPages org = new OrganizationPomPages(driver);
			org.getPlusicon();
			// Enter org name in create new org name page and save
			ClassObject_Utility.getTest().log(Status.INFO, " create new org");
			CreateNewOrganization neworg = new CreateNewOrganization(driver);
			neworg.getOrgname_TF(orgname);
			neworg.getSaveBtn();

			// enter orgname in create new org page save

			ClassObject_Utility.getTest().log(Status.INFO, "verifying org name");
			OrgDetailsPomPge orgdetail = new OrgDetailsPomPge(driver);
			//String header = orgdetail.getHeader();
			boolean Exp_res= orgdetail.getHeader().contains(orgname);
			//Save the  LastName
			Assert.assertEquals(Exp_res, true);
//			if (header.contains(orgname)) {
//				Reporter.log(orgname + "Test Pass",true);
//			} else {
	//
//				Reporter.log("org not created",true);
//			}
			// Identify the Contacts tab in home page and click on it
			ClassObject_Utility.getTest().log(Status.INFO, "navigate to create contact tab");
			home.getCont_tab();
			ClassObject_Utility.getTest().log(Status.INFO, "navigate to create new contact page");
			// driver.findElement(By.linkText("Contacts")).click();
			// identify plus button and click
			ContactPomPage con = new ContactPomPage(driver);
			con.getPlusicon();

			

			// Enter lastname and create new lastname
			
			ClassObject_Utility.getTest().log(Status.INFO, " create new contact with org name");
			CreateNewContactPomPage newcon = new CreateNewContactPomPage(driver);
			newcon.getLastname_TF(contname);
			// click on save
			

			// Select org name
			ClassObject_Utility.getTest().log(Status.INFO, "select org name from child window");
			String pwid = driver.getWindowHandle();
			newcon.getOrgplusicon();
			// driver.findElement(By.xpath("//img[@alt='Select']")).click();
			w_util.SwitchToTabUsingUrl(driver, "module=Accounts&actions)");
			newcon.getOrgsearchBtn();
			
			driver.findElement(By.xpath("//a[text()='" + orgname + "']")).click();

			w_util.SwitchBackToParentWindow(driver, pwid);
			newcon.getSaveBtn();

			

			// Verify actual org name with expected org name
			ClassObject_Utility.getTest().log(Status.INFO, "verifying contact  name with org");
			ContactDetailPomPge condetail = new ContactDetailPomPge(driver);
			//String conheader = condetail.getHeader();
			boolean exp_res1 =condetail.getHeader().contains(contname);
			Assert.assertEquals(exp_res1, true);
			
	
			// click on org tab and delete the created org
	
			ClassObject_Utility.getTest().log(Status.INFO, "navigate to  contact tab and delete");
			home.getCont_tab();

			driver.findElement(
					By.xpath("//a[text()='" + contname + "']/ancestor::tr[@bgcolor='white']/descendant::a[text()='del']"))
					.click();

			// handle the popup
			Thread.sleep(2000);
			ClassObject_Utility.getTest().log(Status.INFO, "handling delete popup");
			w_util.HandleAlerAndAccept(driver);


//			// close the browser
//			w_util.CloseTheBrowser(driver);
			soft.assertAll();
		}
		
	//public class CreateContactWithSupportDateTest extends Baseclass {
		//@Parameters("browser")

			@Test(groups="regression")
			public void CreateContactTest() throws IOException, InterruptedException {
				

				// FETCH data from Excel
				
				ClassObject_Utility.getTest().log(Status.INFO, "feth the data from excel file");
				Exel_Utility ex_util = new Exel_Utility();
				Java_Utility j_util = new Java_Utility();
				int random = j_util.getRandomNumber();
				String contactname = ex_util.FetchDtaFromExelFile("contacts", 3, 3) + random;
				Webdriver_Utility w_util = new Webdriver_Utility();
	

				// Identigy contacts tab in home page and click on it
				
				ClassObject_Utility.getTest().log(Status.INFO, "home page verification");
				HomePomPage home = new HomePomPage(driver);
				boolean exp_res=home.getHeader().contains("Home");
				SoftAssert soft=new SoftAssert();
				soft.assertEquals(exp_res, true);
				ClassObject_Utility.getTest().log(Status.INFO, "navigate to create contact tab");
				home.getCont_tab();
				// Identify plus button and click on it
				
			// driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
				ClassObject_Utility.getTest().log(Status.INFO, "navigate to create new contact page");
				ContactPomPage con = new ContactPomPage(driver);
				con.getPlusicon();
	            //enter orgname in create new organization page and save
				// driver.findElement(By.name("lastname")).sendKeys(lastname);
				ClassObject_Utility.getTest().log(Status.INFO, " create new contact ");
				CreateNewContactPomPage newcon = new CreateNewContactPomPage(driver);
				newcon.getLastname_TF(contactname);
				// specify start and end support date
				WebElement start_dateTF = newcon.getConStartDate_TF();// driver.findElement(By.id("jscal_field_support_start_date"));
				start_dateTF.clear();

				String startdate = j_util.getStartdate();
				start_dateTF.sendKeys(startdate);

				WebElement end_dateTF = newcon.getConEndDate_TF();// driver.findElement(By.name("jscal_field_support_end_date"));
				end_dateTF.clear();
				// Calendar cal = sim.getCalendar();
				// cal.add(Calendar.DAY_OF_MONTH, 30);

				String enddate = j_util.getDateAftergivendays(30);
				end_dateTF.sendKeys(enddate);
				newcon.getSaveBtn();
				// driver.findElement(By.xpath("(//input[@class='crmButton small
				// save']")).click();
				// verify actual org name with expected org name

				// WebElement header = driver.findElement(By.xpath("//span[contains(text(),'
				// Contact Information')]"));
				ClassObject_Utility.getTest().log(Status.INFO, "verify the contact name");
				ContactDetailPomPge condetail = new ContactDetailPomPge(driver);
				//String header = condetail.getHeader();
				boolean Exp_res=condetail.getHeader().contains(contactname);
				Assert.assertEquals(Exp_res, true);
				//Save the  LastName
				
//				if (header.contains(contactname)) {
//					Reporter.log(contactname + "Test pass",true);
//				} else {
//					Reporter.log("org not created",true);
//				}

				// Verify start sup date and end support date
				ClassObject_Utility.getTest().log(Status.INFO, "verify start sup date ");
				String actstrtdate = condetail.getVerifyStartdate();
				boolean exp_resul2=condetail.getVerifyStartdate().contains(actstrtdate);
				Assert.assertEquals(exp_resul2, true);
				// WebElement actstartdate = driver.findElement(By.id("dtlview_Support Start
				// Date"));
//				if (actstrtdate.contains(startdate)) {
//					Reporter.log("contact created with start date",true);
//				} else {
//					Reporter.log("contact not created with start date",true);
//				}
				ClassObject_Utility.getTest().log(Status.INFO, "verify act end sup date");
				String actenddate = condetail.getVerifyEnddate();
				// WebElement actenddate = driver.findElement(By.id("dtlview_Support End
				// Date"));
				boolean exp_resul3=condetail.getVerifyEnddate().contains(enddate);
				Assert.assertEquals(exp_resul3, true);
//				if (actenddate.contains(enddate)) {
//					Reporter.log("conatct created with end date",true);
//				} else {
//					Reporter.log("conatct not created with end date",true);
//				}

				// click on org tab and delete the created org
				ClassObject_Utility.getTest().log(Status.INFO, "navigate to contacts tab");
				home.getCont_tab();
				// driver.findElement(By.linkText("Contacts")).click();
				ClassObject_Utility.getTest().log(Status.INFO, "delete the contact");
				driver.findElement(By.xpath(
						"//a[text()='" + contactname + "']/ancestor::tr[@bgcolor='white']/descendant::a[text()='del']"))
						.click();

				// Handle the popup
				Thread.sleep(2000);
				ClassObject_Utility.getTest().log(Status.INFO, "handling delete poup");
				w_util.HandleAlerAndAccept(driver);
				soft.assertAll();
				// Logout of the appln
			

			}
		}




