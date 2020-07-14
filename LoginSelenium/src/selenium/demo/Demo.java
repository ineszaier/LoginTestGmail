package selenium.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Demo {
	 
	public static void main(String[] args) {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\lenovoo\\Desktop\\slenim\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("http://www.gmail.com/");
		driver.manage().window().maximize();
		driver.findElement(By.id("identifierId") ).sendKeys("ines.zaier@esprit.tn");
		driver.findElement(By.id("identifierNext") ).click();

		new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='password']"))).sendKeys("183JFT0875");
        driver.findElement(By.id("passwordNext")).click();
		String at = driver.getTitle();
		String et = "gmail";
		if(at.equalsIgnoreCase(et)) 
			System.out.println("test1 succesful");		
		else
			System.out.println("test1 failure");	
	}

}
