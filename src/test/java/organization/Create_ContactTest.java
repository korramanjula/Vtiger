package organization;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;

import Baseclass_Utility.Baseclass;
import GenericUtilities.ClassObject_Utility;
import GenericUtilities.Exel_Utility;
import GenericUtilities.Java_Utility;
import GenericUtilities.Webdriver_Utility;
import POM.ContactDetailPomPge;
import POM.ContactPomPage;
import POM.CreateNewContactPomPage;
import POM.HomePomPage;
@Listeners(Listeners_Utility.Listeners.class)

public class Create_ContactTest extends Baseclass {
	// @Parameters("browser")
	@Test(groups = "smoke", retryAnalyzer = Listeners_Utility.RetryAnalyser_Utility.class)

	public void Create_Contact_Test() throws IOException, InterruptedException {

		ClassObject_Utility.getTest().log(Status.INFO, "Fetch data from excel");
		Exel_Utility ex_util = new Exel_Utility();

		Java_Utility j_util = new Java_Utility();
		int random = j_util.getRandomNumber();
		String contactname = ex_util.FetchDtaFromExelFile("contacts", 1, 3) + random;

		Webdriver_Utility w_util = new Webdriver_Utility();

		HomePomPage home = new HomePomPage(driver);

		boolean exp_res = home.getHeader().contains("Home");
		SoftAssert soft = new SoftAssert();
		soft.assertEquals(exp_res, true);
		ClassObject_Utility.getTest().log(Status.INFO, "verify home page");
		home.getCont_tab();

		// Identify plus symbol and click it
		// driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		ContactPomPage con = new ContactPomPage(driver);
		con.getPlusicon();

		CreateNewContactPomPage newcon = new CreateNewContactPomPage(driver);
		newcon.getLastname_TF(contactname);
		newcon.getSaveBtn();

		// Enter the lastname in LN textfield

		ContactDetailPomPge condetail = new ContactDetailPomPge(driver);
		ClassObject_Utility.getTest().log(Status.INFO, "verify contact");

		boolean Exp_res = condetail.getHeader().contains(contactname);
		// Save the LastName
		Assert.assertEquals(Exp_res, true);

//		if(header.contains(contactname)) {
//			Reporter.log(contactname +": TestPass",true);
//		}else {
//			Reporter.log("lastname not matching",true);
//			
//		}

		// Identify the Contacts button and click on it

		home.getCont_tab();
		// delete the contact
		driver.findElement(By
				.xpath("//a[text()='" + contactname + "']/ancestor::tr[@bgcolor='white']/descendant::a[text()='del']"))
				.click();
		// handle the popup
		Thread.sleep(2000);
		w_util.HandleAlerAndAccept(driver);
		soft.assertAll();

	}

}