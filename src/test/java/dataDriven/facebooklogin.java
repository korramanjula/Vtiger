package dataDriven;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class facebooklogin {
	@Test( retryAnalyzer=Listeners_Utility.RetryAnalyser_Utility.class)
	public void fetchDataFromJsonFile() throws FileNotFoundException, IOException, ParseException

	{
		JSONParser  parser = new JSONParser();
		Object obj=parser.parse(new FileReader("./src/test/resources/Datadriven.json"));
		JSONObject js= (JSONObject)obj;
		String un = js.get("username").toString();
		System.out.println(un);
		System.out.println(js.get("url"));
	WebDriver driver=new ChromeDriver();
	driver.manage().window().maximize();
	
	 long wait=Long.parseLong((String)(js.get("time")));
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	String data = js.get("url").toString();
	String username = js.get("Email").toString();
	String pwd = js.get("password").toString();

	
	
	driver.findElement(By.id("email")).sendKeys("username");
	driver.findElement(By.id("pass")).sendKeys("pwd");
	driver.quit();
	
	
			
		
	}

}
