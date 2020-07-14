package selenium.demo;
import java.util.concurrent.TimeUnit;
import java.net.URL;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Lambdatest {
	public static RemoteWebDriver driver = null;
    public String url = "https://www.lambdatest.com/";
    public static final String  username= "ines.zaier";
    public static final String auth_key = "rLaLDZMzRJiu6nTBkvscHJhVWze745q3djP9scdPXcdmvVFM1t";
    public static final String URL = "@hub.lambdatest.com/wd/hub";
    boolean status = false;
 
 
    @Test
    public void login () {
        // TODO Auto-generated method stub
        try {
 
            driver.manage().window().maximize();
            driver.get("http://www.gmail.com/");
 
            driver.findElement(By.id("identifierId") ).sendKeys("ines.zaier@esprit.tn");
    		driver.findElement(By.id("identifierNext") ).click();
            
    		new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='password']"))).sendKeys("183JFT0875");
            driver.findElement(By.id("passwordNext")).click();
 
            String at = driver.getTitle();
    		String et = "gmail";
 
            if (at.equalsIgnoreCase(et)) {
                System.out.println("Test passed");
                status = true; //Lambda status will be reflected as passed
              } else {
                System.out.println("Test failed"); //Lambda status will be reflected as passed
 
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        finally {
            tearDown();
        }
 
 
    }
 
 
 
    @BeforeClass
    public static void setUp() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", "chrome");
        capabilities.setCapability("version", "72.0");
        capabilities.setCapability("platform", "win8"); // If this cap isn't specified, it will just get the any available one
        capabilities.setCapability("build", "TestNG_login_1");
        capabilities.setCapability("name", "TestNG_login_1");
        capabilities.setCapability("network", true); // To enable network logs
        capabilities.setCapability("visual", true); // To enable step by step screenshot
        capabilities.setCapability("video", true); // To enable video recording
        capabilities.setCapability("console", true); // To capture console logs
        try {
 
            driver = new RemoteWebDriver(new URL("https://" + username + ":" + auth_key + URL), capabilities);
 
        } catch (Exception e) {
 
            System.out.println("Invalid grid URL" + e.getMessage());
        }
 
    }
    private void tearDown () {
        if (driver != null) {
            ((JavascriptExecutor) driver).executeScript("lambda-status=" + status); //Lambda status will be reflected as either passed/ failed
 
            driver.quit();
 
            System.out.println("The setup process is completed");
 
        }
    }
}
