package Baseclass_Utility;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.Status;

import GenericUtilities.ClassObject_Utility;
import GenericUtilities.Dtabase_Utility;
import GenericUtilities.Property_Utility;
import GenericUtilities.Webdriver_Utility;
import POM.HomePomPage;
import POM.LoginPomPage;

public class Baseclass {
	Dtabase_Utility db=new Dtabase_Utility();
	public  WebDriver driver=null;
	Property_Utility pro=new Property_Utility();
	Webdriver_Utility w_util=new Webdriver_Utility();
	public static WebDriver sdriver=null;
	@BeforeSuite(groups= {"smoke","regression"})
	public void beforeSuite() {
		Reporter.log("configure the DB:connect",true);
		ClassObject_Utility.getTest().log(Status.INFO, "connect to db");
		db.getDatabaseConnection();
		
		
	}
	@BeforeTest(groups= {"smoke","regression"})
	public void beforeTest() {
		Reporter.log("BT:parallel Exe",true);
		
	}
	//@Parameters("browser")
	@BeforeClass(groups= {"smoke","regression"})
	public void beforeClsss() throws IOException {
		Reporter.log("launch the browser",true);
		ClassObject_Utility.getTest().log(Status.INFO, "launching the browsr");
		//Fetch data from maven and prop file
		String Browser=System.getProperty("Browser",pro.FetchDatafromPropertyfile("Browser"));
		//launch the browser
		if (Browser.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (Browser.equals("edge")) {
			driver = new EdgeDriver();
		}
		sdriver=driver;
		ClassObject_Utility.setDriver(driver);
		
	}
	@BeforeMethod(groups= {"smoke","regression"})
	public void beforeMethod() throws IOException {
		Reporter.log("login to application",true);
		ClassObject_Utility.getTest().log(Status.INFO, "login to application");
		
		String url = System.getProperty("url",pro.FetchDatafromPropertyfile("url"));
		String username = System.getProperty("username",pro.FetchDatafromPropertyfile("username"));
		String password = System.getProperty("password",pro.FetchDatafromPropertyfile("password"));
		LoginPomPage l=new LoginPomPage(driver);
		w_util.navigateToAnApplication(driver, url);
		w_util.maximizeTheWindow(driver);
		l.login(username, password);
		String timeouts =pro.FetchDatafromPropertyfile("timeouts");
		w_util.WaitTillElementFound(timeouts, driver);
		
	}
	@AfterMethod(groups= {"smoke","regression"})
	public void afterMethod() {
		Reporter.log("logout to application",true);
		ClassObject_Utility.getTest().log(Status.INFO, "logout to apllication");
		HomePomPage home=new HomePomPage(driver);
		home.logout(driver);
		
		
	}
	@AfterClass(groups= {"smoke","regression"})
	public void afterClass() {
		Reporter.log("close the browser",true);
		ClassObject_Utility.getTest().log(Status.INFO, "closing the browser");
		Webdriver_Utility wb=new Webdriver_Utility();
		wb.QuiTheBrowser(driver);
		
		
	}
	@AfterTest(groups= {"smoke","regression"})
	public void afterTest() {
		Reporter.log("AT:parallel Exe",true);
		
	}
	@AfterSuite(groups= {"smoke","regression"})
	public void afterSuite() {
		Reporter.log("close the db connection",true);
		ClassObject_Utility.getTest().log(Status.INFO, "closing the db connection");
		db.closeDatabaseConnectio();
		
	}
	
}
