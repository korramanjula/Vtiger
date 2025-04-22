package ExtentReports;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class UsingExtentReports {
	@Test
	public void demo() {
		//configure the report
		ExtentSparkReporter spark=new ExtentSparkReporter("./AdvancedReporter/demo.html");
		spark.config().setDocumentTitle("Demo test");
		spark.config().setReportName("DEMO REPORT");
		spark.config().setTheme(Theme.DARK);
		
		//set eneveronment config
		ExtentReports report=new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("os version", "windows_11");
		report.setSystemInfo(" browser version", "chrome_135");
		ExtentTest test=report.createTest("demo test create");
		
		
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.facebook.com/");
		test.log(Status.INFO, "navigated to facebook");
		WebElement  header= driver.findElement(By.xpath("//img[@alt='Facebook']"));
		if(header.getText().contains("facebook")) {
			test.log(Status.PASS, "header verified true");
			
		
		driver.findElement(By.id("email")).sendKeys("manju");
		}
		
	else {
		test.log(Status.FAIL, "header verified false");
		System.out.println("test faild");
	}
		report.flush();
	}
}


