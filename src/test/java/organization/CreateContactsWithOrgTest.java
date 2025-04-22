package organization;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
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
import POM.ContactDetailPomPge;
import POM.ContactPomPage;
import POM.CreateNewContactPomPage;
import POM.CreateNewOrganization;
import POM.HomePomPage;
import POM.LoginPomPage;
import POM.OrgDetailsPomPge;
import POM.OrganizationPomPages;

public class CreateContactsWithOrgTest extends Baseclass{
	//@Parameters("browser")
	@Test(groups="regression",retryAnalyzer=Listeners_Utility.RetryAnalyser_Utility.class)
	public void CreateContact_OrgnameTest() throws IOException, InterruptedException {

		// FileInputStream fis = new
		// FileInputStream("./src/test/resources/CommonData.Properties");

		// Properties pro = new Properties();

		// p.load(fis);
		//Property_Utility pro = new Property_Utility();
	//	String Browser = pro.FetchDatafromPropertyfile("Browser");
//		String url = pro.FetchDatafromPropertyfile("url");
//		String username = pro.FetchDatafromPropertyfile("username");
//		String password = pro.FetchDatafromPropertyfile("password");
//		String timeouts = pro.FetchDatafromPropertyfile("timeouts").toString();
//		long time = Long.parseLong(timeouts);
		// fetch data from excel
//		FileInputStream efis = new FileInputStream("C:\\Users\\dell\\Desktop\\Organization1.xlsx");
//		Workbook wb = WorkbookFactory.create(efis);
//        Sheet sh = wb.getSheet("Contacts");
//        Row r = sh.getRow(7);

		Exel_Utility ex_util = new Exel_Utility();
		Java_Utility j_util=new Java_Utility();
		int random=j_util.getRandomNumber();
		String contname = ex_util.FetchDtaFromExelFile("Contacts", 7, 3)+ random;
//		String contname = r.getCell(3).getStringCellValue();
//		String orgname = r.getCell(4).getStringCellValue();
		String orgname = ex_util.FetchDtaFromExelFile("Contacts", 7, 4)+random;
		

		

		Webdriver_Utility w_util = new Webdriver_Utility();
//maximize browser
		//w_util.maximizeTheWindow(driver);
//implecitly wait
		//w_util.WaitTillElementFound(timeouts, driver);
//navigate to url
		//w_util.navigateToAnApplication(driver, url);
		// login to vtiger app
	//	LoginPomPage l = new LoginPomPage(driver);
	//	l.login(username, password);

		// identify un textfield and pass text
		// driver.findElement(By.name("user_name")).sendKeys(username);

		// identify pwd textfield and pass text
		// driver.findElement(By.name("user_password")).sendKeys(password);

		// identify login btn and click on it
		// driver.findElement(By.id("submitButton")).click();

		// identify Organisation tab in homepage
		// driver.findElement(By.linkText("Organizations")).click();
		// Identigy organization tab in home page and click on it
		// driver.findElement(By.linkText("Organizations")).click();
		HomePomPage home = new HomePomPage(driver);
		

		boolean exp_res=home.getHeader().contains("Home");
		SoftAssert soft=new SoftAssert();
		soft.assertEquals(exp_res, true);
		home.getOrg_tab();

		// identify plus button and click on it
		// driver.findElement(By.xpath("//img[@title='Create
		// Organization...']")).click();
		// Identify plus button and click on it
		// driver.findElement(By.xpath("//img[@title='Create
		// Organization...']")).click();
		OrganizationPomPages org = new OrganizationPomPages(driver);
		org.getPlusicon();
		// Enter org name in create new org name page and save
		CreateNewOrganization neworg = new CreateNewOrganization(driver);
		neworg.getOrgname_TF(orgname);
		neworg.getSaveBtn();

		// enter orgname in create new org page save

		// driver.findElement(By.name("accountname")).sendKeys(orgname);

		// click on save
		// driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		// Verify actual org name with expected orgname
		// WebElement header =
		// driver.findElement(By.xpath("//span[contains(text(),'Organization
		// Information')]"));
		OrgDetailsPomPge orgdetail = new OrgDetailsPomPge(driver);
		//String header = orgdetail.getHeader();
		boolean Exp_res= orgdetail.getHeader().contains(orgname);
		//Save the  LastName
		Assert.assertEquals(Exp_res, true);
//		if (header.contains(orgname)) {
//			Reporter.log(orgname + "Test Pass",true);
//		} else {
//
//			Reporter.log("org not created",true);
//		}
		// Identify the Contacts tab in home page and click on it
		home.getCont_tab();
		// driver.findElement(By.linkText("Contacts")).click();
		// identify plus button and click
		ContactPomPage con = new ContactPomPage(driver);
		con.getPlusicon();

		// driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();

		// Enter lastname and create new lastname
		// driver.findElement(By.name("lastname")).sendKeys(contname);
		CreateNewContactPomPage newcon = new CreateNewContactPomPage(driver);
		newcon.getLastname_TF(contname);
		// click on save
		// driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		// Select org name
		String pwid = driver.getWindowHandle();
		newcon.getOrgplusicon();
		// driver.findElement(By.xpath("//img[@alt='Select']")).click();
		w_util.SwitchToTabUsingUrl(driver, "module=Accounts&actions)");
		newcon.getOrgsearchBtn();
		// driver.findElement(By.id("search_txt")).sendKeys(orgname);
		// driver.findElement(By.name("search")).click();
		driver.findElement(By.xpath("//a[text()='" + orgname + "']")).click();

		w_util.SwitchBackToParentWindow(driver, pwid);
		newcon.getSaveBtn();

		// driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		// Verify actual org name with expected org name
		ContactDetailPomPge condetail = new ContactDetailPomPge(driver);
		//String conheader = condetail.getHeader();
		boolean exp_res1 =condetail.getHeader().contains(contname);
		Assert.assertEquals(exp_res1, true);
		
		
		
		
//WebElement conheader = driver.findElement(By.xpath("//span[contains(text(),'Contact Information')]"));
//		if (conheader.contains(contname)) {
//			Reporter.log(contname + "Test Pass",true);
//
//		} else {
//			Reporter.log("org not created",true);
//		}
		// click on org tab and delete the created org
		// driver.findElement(By.linkText("Contacts")).click();
		home.getCont_tab();

		driver.findElement(
				By.xpath("//a[text()='" + contname + "']/ancestor::tr[@bgcolor='white']/descendant::a[text()='del']"))
				.click();

		// handle the popup
		Thread.sleep(2000);
		w_util.HandleAlerAndAccept(driver);

		// logout of the appln
//		WebElement admin = home.getAdmin_icon(); // driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
//
//		w_util.ActionMouseHovering(driver, admin);
//		home.getSignout();
//		// driver.findElement(By.linkText("Sign Out")).click();
//
//		// close the browser
//		w_util.CloseTheBrowser(driver);
		soft.assertAll();
	}
}
