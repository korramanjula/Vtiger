package Organisation;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class CreateOrganisation {
	@Test
	public void CreateOrganisation() {
		//launching the browser
		WebDriver driver =new ChromeDriver();
		//maximize the window
		driver.manage().window().maximize();
		//implecitly wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//navigating to vtiger app
		driver.get("http://localhost:8888/");
		//identify username text field
		driver.findElement(By.name("user_name")).sendKeys("admin");
		//identify pswd text field
		driver.findElement(By.name("user_password")).sendKeys("root");
		//identify the login  button
		driver.findElement(By.id("submitButton")).click();
		//identifying organisation in home page 
		
		driver.findElement(By.linkText("Organizations")).click();
		//identify plus button and click on it
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		//enter org name and save
		String orgname="cat";
		driver.findElement(By.name("accountname")).sendKeys("cat");
		
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
		//verify act org name with expected orn name
		WebElement header=driver.findElement(By.xpath("//span[contains(text(),'Organization Information')]"));
		if(header.getText().contains(orgname)) {
			System.out.println(orgname+"Test pass");
			}else {
				System.out.println("org not created");
		}
		//click on org  tab and delete ctreated org
		
		driver.findElement(By.linkText("Organizations")).click();
		
		driver.findElement(By.xpath("//a[text()='"+orgname+"']/ancestor::tr[@bgcolor='white']/descendant::a[text()='del']")).click();
		//handling popup
		driver.switchTo().alert().accept();
		//log out the application
		
		 WebElement admin=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions act=new Actions(driver);
		act.moveToElement(admin).perform();
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		driver.close();
		
	}

}
