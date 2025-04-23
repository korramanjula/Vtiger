package Leads;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;

import GenericUtilities.Exel_Utility;
import GenericUtilities.Java_Utility;
import GenericUtilities.Property_Utility;
import GenericUtilities.Webdriver_Utility;
import POM.CreateLeadsPomPage;
import POM.CreateNewLeadPomPage;
import POM.HomePomPage;
import POM.LeadsDetailPomPage;
import POM.LoginPomPage;
//git edit
public class CreateLeadsTest {
	@Test()

	public void Create_Leads_Test() throws IOException, InterruptedException {

		// Fetch data from property file

		Property_Utility pro = new Property_Utility();

		String Browser = pro.FetchDatafromPropertyfile("Browser");
		String url = pro.FetchDatafromPropertyfile("url");
		String username = pro.FetchDatafromPropertyfile("username");
		String password = pro.FetchDatafromPropertyfile("password");
		String timeouts = pro.FetchDatafromPropertyfile("timeouts").toString();
		long time = Long.parseLong(timeouts);
		Exel_Utility ex_util = new Exel_Utility();

		Java_Utility j_util = new Java_Utility();
		int random = j_util.getRandomNumber();
		String leadname = ex_util.FetchDtaFromExelFile("leads", 1, 2) + random;
		String company = ex_util.FetchDtaFromExelFile("leads", 1, 3) + random;
		// Launch the browser
		WebDriver driver = null;
		if (Browser.equals("chrome")) {
			driver = new ChromeDriver();
		}
		if (Browser.equals("edge")) {
			driver = new EdgeDriver();
		} else {
			driver = new ChromeDriver();
		}
		Webdriver_Utility w_util = new Webdriver_Utility();
		// maximize browser
		w_util.maximizeTheWindow(driver);
		// implecitly wait
		w_util.WaitTillElementFound(timeouts, driver);
		// navigate to url
		w_util.navigateToAnApplication(driver, url);
//LOGIN TO VTIGER APP
		LoginPomPage l = new LoginPomPage(driver);
		l.login(username, password);

		// Identigy leads tab in home page and click on it

		HomePomPage home = new HomePomPage(driver);
		home.getleads_tab();
		// Identify plus button and click on it
		CreateLeadsPomPage cl = new CreateLeadsPomPage(driver);
		cl.getPlusicon();
		// Enter lastname in create new lead name page and save
		CreateNewLeadPomPage newlead = new CreateNewLeadPomPage(driver);
		newlead.getLastname_TF(leadname);
		// enter company name
		newlead.getcompany_TF(company);
		newlead.getSaveBtn();

		// verify act and expected lastname
		LeadsDetailPomPage leaddetail = new LeadsDetailPomPage(driver);
		String header = leaddetail.getHeader();
		if (header.contains(leadname)) {
			System.out.println(leadname + "Test pass");
		} else {
			System.out.println("org not created");
		}
		// verify company
		String companyname = leaddetail.getHeader();

		// WebElement header = driver.findElement(By.xpath("//span[contains(text(),'
		// Organization Information')]"));
		if (companyname.contains(company)) {
			System.out.println(company + "Test pass");
		} else {
			System.out.println("org not created");
		}
		home.getleads_tab();
		// delete lead
		driver.findElement(
				By.xpath("//a[text()='" + leadname + "']/ancestor::tr[@bgcolor='white']/descendant::a[text()='del']"))
				.click();
		//// Handle the popup
		Thread.sleep(2000);
		w_util.HandleAlerAndAccept(driver);

		// Logout of the appln
		WebElement admin = home.getAdmin_icon();// driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));

		w_util.ActionMouseHovering(driver, admin);
		home.getSignout();
		// driver.findElement(By.linkText("Sign Out")).click();

		// close the browser
		w_util.CloseTheBrowser(driver);

	}

}
