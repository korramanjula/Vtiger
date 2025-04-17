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
import POM.CreateNewOrganization;
import POM.HomePomPage;
import POM.LoginPomPage;
import POM.OrgDetailsPomPge;
import POM.OrganizationPomPages;

public class CreateOrganisationTest extends Baseclass {
	//@Parameters("browser")
	@Test(groups="smoke",retryAnalyzer=Listeners_Utility.RetryAnalyser_Utility.class)

	public void CreateOrganizationTest() throws IOException, InterruptedException {

		// Fetch DATA FROM PROPERTY FILE
	
//
//		Property_Utility pro = new Property_Utility ();
//        
//		//String Browser = pro.FetchDatafromPropertyfile("Browser");
//		String url = pro.FetchDatafromPropertyfile("url");
//		String username = pro.FetchDatafromPropertyfile("username");
//		String password = pro.FetchDatafromPropertyfile("password");
//		String timeouts = pro.FetchDatafromPropertyfile("timeouts").toString();
//		long time = Long.parseLong(timeouts);
		//FileInputStream efis = new FileInputStream("./src/test/resources/Oranization.xlsx");

		//Workbook wb = WorkbookFactory.create(efis);
	//	Sheet sh = wb.getSheet("organization");
	//	Row r = sh.getRow(1);
	//	Cell c = r.getCell(3);
	//	String orgname = c.getStringCellValue();
		Exel_Utility ex_util=new Exel_Utility();
		Java_Utility j_util=new  Java_Utility();
		int random=j_util.getRandomNumber();
		String orgname=ex_util.FetchDtaFromExelFile("organization", 1, 3)+random;
		Webdriver_Utility w_util=new Webdriver_Utility();
		//String lastname = c.getStringCellValue();

//		WebDriver driver = null;
//		if (Browser.equals("chrome")) {
//			driver = new ChromeDriver();
//		} else if (Browser.equals("edge")) {
//			driver = new EdgeDriver();
//		}

//		// Maximize the window
//		w_util.maximizeTheWindow(driver);
//		// Implicit wait
//	
//		w_util.WaitTillElementFound(timeouts, driver);
//
//		// Navigate to vtiger application
//		w_util.navigateToAnApplication(driver, url);
////login to vtiger
//		LoginPomPage l=new LoginPomPage(driver);
//		l.login(username, password);
//		// Identigy organization tab in home page and click on it
		//driver.findElement(By.linkText("Organizations")).click();
		HomePomPage home=new HomePomPage(driver);
		boolean exp_res=home.getHeader().contains("Home");
		SoftAssert soft=new SoftAssert();
		soft.assertEquals(exp_res, true);
		home.getOrg_tab();

		// Identify plus button and click on it
		//driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
OrganizationPomPages org=new OrganizationPomPages(driver);
org.getPlusicon();
		// Enter org name in create new org name page and save
		CreateNewOrganization neworg=new CreateNewOrganization(driver);
		neworg.getOrgname_TF(orgname);
		neworg.getSaveBtn();
		// Identify user name text field
		//driver.findElement(By.name("user_name")).sendKeys(username);

		// Identify PSWD textfield and pass the text
		//driver.findElement(By.name("user_password")).sendKeys(password);

		// Identify login button and click on it
		//driver.findElement(By.id("submitButton")).click();

		// Identigy organization tab in home page and click on it
		//driver.findElement(By.linkText("Organizations")).click();

		// Identify plus button and click on it
		//driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();

		// Enter org name in create new org name page and save
		// String orgname = "cat";
		//driver.findElement(By.name("accountname")).sendKeys(orgname);
		//driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();

		// verify actual org name with expected org name
		OrgDetailsPomPge orgdetail=new OrgDetailsPomPge(driver);

		//String header=orgdetail.getHeader();
		
		boolean Exp_res=orgdetail.getHeader().contains(orgname);
		
		//Save the  LastName
		Assert.assertEquals(Exp_res, true);
		home.getOrg_tab();
		driver.findElement(By.xpath("//a[text()='" + orgname + "']/ancestor::tr[@bgcolor='white']/descendant::a[text()='del']"))
				.click();

// Handle the popup
		Thread.sleep(2000);
		w_util.HandleAlerAndAccept(driver);
		
		soft.assertAll();
		
		 
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
			//WebElement header = driver.findElement(By.xpath("//span[contains(text(),' Organization Information')]"));
//			if (header.contains(orgname)) {
//				Reporter.log(orgname + "Test pass",true);
//			} else {
//				Reporter.log("org not created",true);
//			}


		home.getOrg_tab();

		// click on org tab and delete the created the ornanization
		//driver.findElement(By.linkText("Organizations")).click();
		

		driver.findElement(By.xpath("//a[text()='" + orgname + "']/ancestor::tr[@bgcolor='white']/descendant::a[text()='del']"))
				.click();

		// Handle the popup
		Thread.sleep(2000);
		w_util.HandleAlerAndAccept(driver);
		soft.assertAll();

		
	}
}


