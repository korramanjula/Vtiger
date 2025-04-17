package GenericUtilities;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class Webdriver_Utility {
	public void maximizeTheWindow(WebDriver driver) {
		driver.manage().window().maximize();
		
		
	}
	public void WaitTillElementFound(String Timeouts,WebDriver driver) {
		long time=Long.parseLong(Timeouts);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
	}
	public void navigateToAnApplication(WebDriver driver,String url) {
		driver.get(url);
		
	}
	public void HandleAlerAndAccept(WebDriver driver) {
		driver.switchTo().alert().accept();
	}
	public void HandleAlerAnCancel(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}
	public void HandleAlerAndFecthText(WebDriver driver) {
		driver.switchTo().alert().getText();
	}
	public void HandleAlerAndPasstext(WebDriver driver,String text) {
		driver.switchTo().alert().sendKeys(text);
	}
	public void ActionMouseHovering( WebDriver driver,WebElement element) {
		Actions act=new Actions(driver);
		act.moveToElement(element).perform();
	}
	public void  QuiTheBrowser(WebDriver driver) {
		driver.quit();
		
	}
	public void CloseTheBrowser(WebDriver driver) {
		driver.close();
		
	}
	public void HndleDropdownUsingIndex(WebElement dropdown,int index) {
		Select s=new Select(dropdown);
		s.selectByIndex(index);
	}
	public void HndleDropdownUsingValue(WebElement dropdown,String value) {
		Select s=new Select(dropdown);
		s.selectByValue(value);
	}
	public void HndleDropdownUsingVisibleText(WebElement dropdown,String text) {
		Select s=new Select(dropdown);
		s.selectByVisibleText(text);
	}
	public void SwitchToTabUsingUrl(WebDriver driver,String url) {
		Set<String> wids = driver.getWindowHandles();
		for (String s : wids) {
			driver.switchTo().window(s);
		
			if (driver.getCurrentUrl().contains(url)){
				break;
				
			}
		
		}
		
	}
	public void SwitchToTabUsingTitle(WebDriver driver,String title) {
		Set<String> wids = driver.getWindowHandles();
		for (String s : wids) {
			driver.switchTo().window(s);
		
			if (driver.getCurrentUrl().contains(title)){
				break;
				
}
		}
	}
	public void SwitchBackToParentWindow(WebDriver driver,String pwid) {
		driver.switchTo().window(pwid);
		
	}
}
