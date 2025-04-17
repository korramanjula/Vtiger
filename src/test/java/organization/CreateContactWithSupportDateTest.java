package organization;



import java.io.IOException;

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
import POM.ContactDetailPomPge;
import POM.ContactPomPage;
import POM.CreateNewContactPomPage;
import POM.HomePomPage;
import POM.LoginPomPage;

public class CreateContactWithSupportDateTest extends Baseclass {
	//@Parameters("browser")

		@Test(groups="regression",retryAnalyzer=Listeners_Utility.RetryAnalyser_Utility.class)
		public void CreateContactTest() throws IOException, InterruptedException {
			/*
			 * // convert the file into object FileInputStream fis = new
			 * FileInputStream("./src/test/resources/CommonData.properties");
			 * 
			 * // Create an object of properties Properties p = new Properties(); // Load
			 * the property object p.load(fis);
			 * 
			 * String browser = (p.getProperty("Browser")); String url =
			 * (p.getProperty("url")); String username = (p.getProperty("username")); String
			 * pwd = (p.getProperty("password")); String timeouts =
			 * (p.getProperty("timeouts")); long time = Long.parseLong(timeouts);
			 */

			// Fetch data from property file
			//Property_Utility pro = new Property_Utility();
			//String browser = (pro.FetchDatafromPropertyfile("Browser"));
//			String url = (pro.FetchDatafromPropertyfile("url"));
//			String username = (pro.FetchDatafromPropertyfile("username"));
//			String pwd = (pro.FetchDatafromPropertyfile("password"));
//			String timeouts = (pro.FetchDatafromPropertyfile("timeouts"));
//			long time = Long.parseLong(timeouts);

			// FETCH data from Excel
			// FileInputStream efis = new FileInputStream(".");

			// Workbook wb = WorkbookFactory.create(efis);
			// Sheet sh = wb.getSheet("Contact");
			// Row r = sh.getRow(9);
			// Cell c = r.getCell(3);
			Exel_Utility ex_util = new Exel_Utility();
			Java_Utility j_util = new Java_Utility();
			int random = j_util.getRandomNumber();
			String contactname = ex_util.FetchDtaFromExelFile("contacts", 3, 3) + random;
			Webdriver_Utility w_util = new Webdriver_Utility();
			// String lastname = c.getStringCellValue();

//			WebDriver driver = null;
//			if (Browser.equals("chrome")) {
//				driver = new ChromeDriver();
//			} else if (Browser.equals("edge")) {
//				driver = new EdgeDriver();
//			}

			// maximize the browser
//			w_util.maximizeTheWindow(driver);
//
//			//
//			w_util.WaitTillElementFound(timeouts, driver);
//			//
//			driver.get(url);

			// Identify user name text field
			// driver.findElement(By.name("user_name")).sendKeys(username);

			// Identify PSWD textfield and pass the text
			// driver.findElement(By.name("user_password")).sendKeys(pwd);

			// Identify login button and click on it
			// driver.findElement(By.id("submitButton")).click();
			// Login to vtiger appl
//			LoginPomPage l = new LoginPomPage(driver);
//			l.login(username, pwd);

			// Identigy contacts tab in home page and click on it
			// driver.findElement(By.linkText("Contacts")).click();
			HomePomPage home = new HomePomPage(driver);
			boolean exp_res=home.getHeader().contains("Home");
			SoftAssert soft=new SoftAssert();
			soft.assertEquals(exp_res, true);
			
			home.getCont_tab();
			// Identify plus button and click on it
			// driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
			ContactPomPage con = new ContactPomPage(driver);
			con.getPlusicon();
            //enter orgname in create new organization page and save
			// driver.findElement(By.name("lastname")).sendKeys(lastname);
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
			ContactDetailPomPge condetail = new ContactDetailPomPge(driver);
			//String header = condetail.getHeader();
			boolean Exp_res=condetail.getHeader().contains(contactname);
			Assert.assertEquals(Exp_res, true);
			//Save the  LastName
			
//			if (header.contains(contactname)) {
//				Reporter.log(contactname + "Test pass",true);
//			} else {
//				Reporter.log("org not created",true);
//			}

			// Verify start sup date and end support date
			String actstrtdate = condetail.getVerifyStartdate();
			boolean exp_resul2=condetail.getHeader().contains(actstrtdate);
			Assert.assertEquals(exp_resul2, true);
			// WebElement actstartdate = driver.findElement(By.id("dtlview_Support Start
			// Date"));
//			if (actstrtdate.contains(startdate)) {
//				Reporter.log("contact created with start date",true);
//			} else {
//				Reporter.log("contact not created with start date",true);
//			}
			String actenddate = condetail.getVerifyEnddate();
			// WebElement actenddate = driver.findElement(By.id("dtlview_Support End
			// Date"));
			boolean exp_resul3=condetail.getHeader().contains(enddate);
			Assert.assertEquals(exp_resul3, true);
//			if (actenddate.contains(enddate)) {
//				Reporter.log("conatct created with end date",true);
//			} else {
//				Reporter.log("conatct not created with end date",true);
//			}

			// click on org tab and delete the created org
			home.getCont_tab();
			// driver.findElement(By.linkText("Contacts")).click();
			driver.findElement(By.xpath(
					"//a[text()='" + contactname + "']/ancestor::tr[@bgcolor='white']/descendant::a[text()='del']"))
					.click();

			// Handle the popup
			Thread.sleep(2000);
			w_util.HandleAlerAndAccept(driver);
			soft.assertAll();
			// Logout of the appln
		

		}
	}


