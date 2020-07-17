package Login.Suite;

import java.net.URL;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class LoginWithInvalidUrl {
	public static RemoteWebDriver driver = null;
    public String url = "https://www.lambdatest.com/";
    public static final String  username= "ines.zaier";
    public static final String auth_key = "rLaLDZMzRJiu6nTBkvscHJhVWze745q3djP9scdPXcdmvVFM1t";
    public static final String URL = "@hub.lambdatest.com/wd/hub";
    static boolean status = false;
 

   
    @Test
    public void login () {
        // TODO Auto-generated method stub
    	 driver.manage().window().maximize();
  	   driver.get("http://www.gmmmmail.com/");	

    	
 
  	     String expectedURL = "http://www.gmail.com/";
            String actualUrl = driver.getCurrentUrl().toString();
           // Assert.assertEquals(actualUrl, expectedURL);
            
            if (actualUrl.equalsIgnoreCase(expectedURL)) {
                System.out.println("Test passed ," + actualUrl);
                status = true; //Lambda status will be reflected as passed
              } else {
                System.out.println("Test failed"); //Lambda status will be reflected as passed
 
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
    @AfterClass
    public static void tearDown() throws Exception {
    	 if (driver != null) {
    	 ((JavascriptExecutor) driver).executeScript("lambda-status=" + status);
    	 driver.quit();
    	 }
    	 }
}
