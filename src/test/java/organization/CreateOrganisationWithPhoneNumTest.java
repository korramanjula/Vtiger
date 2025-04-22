package organization;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Baseclass_Utility.Baseclass;
import GenericUtilities.Exel_Utility;
import GenericUtilities.Java_Utility;
import GenericUtilities.Property_Utility;
import GenericUtilities.Webdriver_Utility;
import POM.CreateNewOrganization;
import POM.HomePomPage;
import POM.LoginPomPage;
import POM.OrgDetailsPomPge;
import POM.OrganizationPomPages;

public class CreateOrganisationWithPhoneNumTest extends Baseclass {
	//@Parameters("browser")
	@Test(groups="regression",retryAnalyzer=Listeners_Utility.RetryAnalyser_Utility.class)
	public void createorganizationTest() throws EncryptedDocumentException, IOException, InterruptedException {
//
//		Property_Utility pro = new Property_Utility();
//
//		//String Browser = pro.FetchDatafromPropertyfile("Browser");
//		String url = pro.FetchDatafromPropertyfile("url");
//		String username = pro.FetchDatafromPropertyfile("username");
//		String password = pro.FetchDatafromPropertyfile("password");
//		String timeouts = pro.FetchDatafromPropertyfile("timeouts").toString();
//		long time = Long.parseLong(timeouts);
		// FileInputStream efis = new
		// FileInputStream("./src/test/resources/Oranization.xlsx");

		// Workbook wb = WorkbookFactory.create(efis);
		// Sheet sh = wb.getSheet("organization");
		// Row r = sh.getRow(1);
		// Cell c = r.getCell(3);
		// String orgname = c.getStringCellValue();
		Exel_Utility ex_util = new Exel_Utility();
		Java_Utility j_util = new Java_Utility();
		int random = j_util.getRandomNumber();
		String orgname = ex_util.FetchDtaFromExelFile("organization", 5, 3) + random;
		String phone = ex_util.FetchDtaFromExelFile("organization", 5, 4);
		Webdriver_Utility w_util = new Webdriver_Utility();
		// String lastname = c.getStringCellValue();

//		WebDriver driver = null;
//		if (Browser.equals("chrome")) {
//			driver = new ChromeDriver();
//		} else if (Browser.equals("edge")) {
//			driver = new EdgeDriver();
//		}
//
//		// Maximize the window
//		w_util.maximizeTheWindow(driver);
//
//		// Implicit wait
//		w_util.WaitTillElementFound(timeouts, driver);
//
//		// Navigate to vtiger application
//		w_util.navigateToAnApplication(driver, url);
//
//		// Identify user name text field
//		// driver.findElement(By.name("user_name")).sendKeys(username);
//
//		// Identify PSWD textfield and pass the text
//		// driver.findElement(By.name("user_password")).sendKeys(password);
//
//		// Identify login button and click on it
//		// driver.findElement(By.id("submitButton")).click();
//		// login to vtiger app
//		LoginPomPage l = new LoginPomPage(driver);
//		l.login(username, password);
		// Identigy organization tab in home page and click on it
		// driver.findElement(By.linkText("Organizations")).click();
		HomePomPage home = new HomePomPage(driver);
		boolean exp_res=home.getHeader().contains("Home");
		SoftAssert soft=new SoftAssert();
		soft.assertEquals(exp_res, true);
		home.getOrg_tab();

		// Identify plus button and click on it
		// driver.findElement(By.xpath("//img[@title='Create
		// Organization...']")).click();
		OrganizationPomPages org = new OrganizationPomPages(driver);
		org.getPlusicon();
		// Enter org name in create new org name page and save

		// Enter org name in create new org name page and save
		CreateNewOrganization neworg = new CreateNewOrganization(driver);
		neworg.getOrgname_TF(orgname);
		neworg.getOrgphone_TF(phone);
		neworg.getSaveBtn();

		// driver.findElement(By.name("accountname")).sendKeys(orgname);
		// driver.findElement(By.id("phone")).sendKeys(phone);
		// driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();

		// verify actual org name with expected org name

		OrgDetailsPomPge orgdetail = new OrgDetailsPomPge(driver);
		//String header = orgdetail.getHeader();
 boolean Exp_res=orgdetail.getHeader().contains(orgname);
 Assert.assertEquals(Exp_res, true);
		// WebElement header = driver.findElement(By.xpath("//span[contains(text(),'
		// Organization Information')]"));
		
		//Save the  LastName
		
//		if (header.contains(orgname)) {
//			Reporter.log(orgname + "Test pass",true);
//		} else {
//			Reporter.log("org not created",true);
//		}

		// verify actual phn number with expected number
		// WebElement phtf= driver.findElement(By.id("mouseArea_Phone"));
		boolean exp_res1=orgdetail.getVerifyOrgphno().contains(phone);
		Assert.assertEquals(exp_res1, true);
		//String phtf = orgdetail.getVerifyOrgphno();
//		if (phtf.contains(phone)) {
//			Reporter.log(phone + "Test pass",true);
//		} else {
//			Reporter.log(" phone number not created",true);
//		}

		// click on org tab and delete the created the ornanization
		// driver.findElement(By.linkText("Organizations")).click();
		home.getOrg_tab();
		driver.findElement(By.xpath("//a[text()='" + orgname + "']/ancestor::tr[@bgcolor='white']/descendant::a[text()='del']"))
				.click();

// Handle the popup
		Thread.sleep(2000);
		w_util.HandleAlerAndAccept(driver);

// Logout of the appln
//		WebElement admin = home.getAdmin_icon();// driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
//
//		w_util.ActionMouseHovering(driver, admin);
//		home.getSignout();
////driver.findElement(By.linkText("Sign Out")).click();
//
//// close the browser
//		w_util.CloseTheBrowser(driver);
		soft.assertAll();

	}
}
