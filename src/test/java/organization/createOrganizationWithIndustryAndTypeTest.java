package organization;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

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

public class createOrganizationWithIndustryAndTypeTest  extends Baseclass{
	//@Parameters("browser")

	@Test(groups="regression",retryAnalyzer=Listeners_Utility.RetryAnalyser_Utility.class)

	public void createorgaWithIndustryTest() throws IOException, InterruptedException {

		// Fetch DATA FROM PROPERTY FILE
//		Property_Utility pro = new Property_Utility();
//
//		//String Browser = pro.FetchDatafromPropertyfile("Browser");
//		String url = pro.FetchDatafromPropertyfile("url");
//		String username = pro.FetchDatafromPropertyfile("username");
//		String password = pro.FetchDatafromPropertyfile("password");
//		String timeouts = pro.FetchDatafromPropertyfile("timeouts").toString();
//		long time = Long.parseLong(timeouts);

		// FETCH data from Excel
		Exel_Utility ex_util = new Exel_Utility();
		Java_Utility j_util = new Java_Utility();
		int random = j_util.getRandomNumber();
		String orgname = ex_util.FetchDtaFromExelFile("organization", 9, 3) + random;
	
		String Industry = ex_util.FetchDtaFromExelFile("organization", 9, 4);
		String Type= ex_util.FetchDtaFromExelFile("organization", 9, 5);
		// FileInputStream fis = new
		// FileInputStream("./src/test/resources/Oranization.xlsx");

		/*
		 * Workbook wb = WorkbookFactory.create(fis); Sheet sh =
		 * wb.getSheet("Organization"); Row r = sh.getRow(10); Cell c = r.getCell(3);
		 * String orgname = c.getStringCellValue();
		 * 
		 * Row r1 = sh.getRow(10); Cell c1 = r1.getCell(4); String Industry =
		 * c1.getStringCellValue();
		 * 
		 * Row r2 = sh.getRow(10); Cell c2 = r2.getCell(5); String Type =
		 * c2.getStringCellValue();
		 */
//		// Launch the browser
//		WebDriver driver = null;
//		if (Browser.equals("chrome")) {
//			driver = new ChromeDriver();
//		}
//		if (Browser.equals("edge")) {
//			driver = new EdgeDriver();
//		}
//
//		// Maximize the window
		Webdriver_Utility w_util = new Webdriver_Utility();
//		w_util.maximizeTheWindow(driver);
//		// Implicit wait
//
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
////login to vtiger app
//		LoginPomPage l = new LoginPomPage(driver);
//		l.login(username, password);
//		// Identigy organization tab in home page and click on it
//		// driver.findElement(By.linkText("Organizations")).click();
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
		CreateNewOrganization neworg = new CreateNewOrganization(driver);
		neworg.getOrgname_TF(orgname);
		WebElement ind_dd = neworg.getOrgIndustryDD();
		WebElement type_dd = neworg.getOrgTypeDD();
		w_util.HndleDropdownUsingValue(ind_dd, Industry);
		w_util.HndleDropdownUsingValue(type_dd, Type);
		neworg.getSaveBtn();
		// driver.findElement(By.name("accountname")).sendKeys(orgname);

		// WebElement ele = driver.findElement(By.name("industry"));
		// Select s = new Select(ele);
		// s.selectByValue(Industry);

		// WebElement Type1 = driver.findElement(By.name("accounttype"));
		// Select s1 = new Select(Type1);
		// s1.selectByValue(Type);

		// driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();

		// verify actual org name with expected org name
		OrgDetailsPomPge orgdetail = new OrgDetailsPomPge(driver);
		//String header = orgdetail.getHeader();
boolean exp_res1=orgdetail.getHeader().contains(orgname);
Assert.assertEquals(exp_res1, true);
		// WebElement header = drive;r.findElement(By.xpath("//span[contains(text(),'
		// Organization Information')]"));
//		if (header.contains(orgname)) {
//			Reporter.log(orgname + "Test pass",true);
//		} else {
//			Reporter.log("org not created",true);
//		}

		// verify actual industry name with expected industry name
//		String actind = orgdetail.getVerifyIndustry();
boolean Exp_res2=orgdetail.getVerifyIndustry().contains(Industry);
Assert.assertEquals(Exp_res2, true);

		// WebElement Industry1 = driver.findElement(By.id("mouseArea_Industry"));
//		if (actind.equals(Industry)) {
//			Reporter.log(Industry + "Test pass",true);
//		} else {
//			Reporter.log(" industry not selected",true);
//		}
		///String acttype = orgdetail.getVerifyIndustry();
boolean exp=orgdetail.getVerifyType().contains(Type);
Assert.assertEquals(exp, true);
		// WebElement Industry1 = driver.findElement(By.id("mouseArea_Industry"));
//		if (acttype.equals(Type)) {
//			Reporter.log(Industry + "Test pass",true);
//		} else {
//			Reporter.log(" industry not selected",true);
//		}
		// click on org tab and delete the created the ornanization
		driver.findElement(By.linkText("Organizations")).click();

		driver.findElement(
				By.xpath("//a[text()='" + orgname + "']/ancestor::tr[@bgcolor='white']/descendant::a[text()='del']"))
				.click();

		// Handle the popup
		Thread.sleep(2000);
		w_util.HandleAlerAndAccept(driver);
//
//		// Logout of the appln
//		WebElement admin = home.getAdmin_icon();// driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
//
//		w_util.ActionMouseHovering(driver, admin);
//		home.getSignout();
//		// driver.findElement(By.linkText("Sign Out")).click();
//
//		// close the browser
//		w_util.QuiTheBrowser(driver);
		soft.assertAll();

	}
}