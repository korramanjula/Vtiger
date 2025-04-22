package Listeners_Utility;

import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import Baseclass_Utility.Baseclass;
import GenericUtilities.ClassObject_Utility;

public class Listeners implements ITestListener, ISuiteListener {
	 public ExtentReports report =null;
	 public  static ExtentTest test=null;
	@Override
	public void onStart(ISuite suite) {
		String time = new Date().toString().replace("", "_").replace(":", "_");
		Reporter.log("report configuration" ,true);
		//configure the report
				ExtentSparkReporter spark=new ExtentSparkReporter("./AdvancedReporter/Adv_report"+time+".html");
				spark.config().setDocumentTitle("Demo test");
				spark.config().setReportName("DEMO REPORT");
				spark.config().setTheme(Theme.DARK);
				
				//set environment config
				report=new ExtentReports();
				report.attachReporter(spark);
				report.setSystemInfo("os version", "windows_11");
				report.setSystemInfo(" browser version", "chrome_135");
				test=report.createTest("create contact");
				ClassObject_Utility.setTest(test);
				
	}

	@Override
	public void onFinish(ISuite suite) {
		Reporter.log("report backup",true);
		report.flush();
		test.log(Status.INFO,"suit execution finished");

	}

	@Override
	public void onTestStart(ITestResult result) {
		Reporter.log(result.getMethod().getMethodName() + "----started--",true);
		test=report.createTest(result.getMethod().getMethodName());
test.log(Status.INFO, result.getMethod().getMethodName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String testname=result.getMethod().getMethodName();
		Reporter.log(testname+"--sucess--",true);
		test.log(Status.INFO, ""+testname+"--sucess--");

	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testname = result.getMethod().getMethodName();
		Reporter.log(testname+"--faild--",true);
	test.log(Status.FAIL, testname+"--failed");
		

		String time = new Date().toString().replace("", "_").replace(":", "_");
		TakesScreenshot Ts = (TakesScreenshot) Baseclass.sdriver;
		//File src = Ts.getScreenshotAs(OutputType.FILE);
		//File dest = new File("./Screenshot/" + testname + time + ".png");
		String filepath=Ts.getScreenshotAs(OutputType.BASE64);
		test.addScreenCaptureFromBase64String(filepath,testname+"_"+time);
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		Reporter.log(result.getMethod().getMethodName() + "--skipped--",true);
		test.log(Status.INFO, result.getMethod().getMethodName()+"--skipped--");

	}

}
