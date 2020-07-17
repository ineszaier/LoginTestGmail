package selenium.demo;

import java.net.URL;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Login {
	public     static final String  username= "ines.zaier";
	public     static final String auth_key = "rLaLDZMzRJiu6nTBkvscHJhVWze745q3djP9scdPXcdmvVFM1t";
	 public    static final String URL = "@hub.lambdatest.com/wd/hub";
	  public   static RemoteWebDriver driver = null;

    static boolean status = false;
	 @Test
	    public void loggin (String url , String loggin , String password) {
		 
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
	 
		 
		  if(driver!=null) {
	    	  driver.manage().window().maximize();
	    	  if(url.equals("http://www.gmail.com"))
	    	  {
	    	   driver.get(url);	
	 
	           WebElement login = driver.findElement(By.id("identifierId") );
			        
			   login.sendKeys(loggin);
			  
	          
	          
	          WebElement loginNext =driver.findElement(By.id("identifierNext") );
			    
			   loginNext.click();
			   
			   String expectedErrorMsg = "Couldn't find your Google Account";
	           
			   //test message d'erreur pour login
			    if(driver.findElement(By.xpath("//div[@class='o6cuMc']")).isDisplayed()) 
	           {
	        	   WebElement exp = driver.findElement(By.xpath("//div[@class='o6cuMc']"));
	        	   String actualErrorMsg = exp.getText();
	        	   Assert.assertEquals(actualErrorMsg, expectedErrorMsg);
	           
				           if (actualErrorMsg.equalsIgnoreCase(expectedErrorMsg)) {
				               System.out.println("Test passed ," + actualErrorMsg);
				               status = true; //Lambda status will be reflected as passed
				             } else {
				               System.out.println("Test failed"); //Lambda status will be reflected as passed
			
				           }
	           }
	           else
	           { System.out.println("hani lena");
			       
	            
				    		new WebDriverWait(driver, 10).until(ExpectedConditions.
				    				elementToBeClickable(By.xpath("//input[@name='password']"))).sendKeys(password);
				            driver.findElement(By.id("passwordNext")).click();
				            String expectedErorMsg = "Wrong password. Try again or click Forgot password to reset it.";
				          //test message d'erreur pour password
			            if(driver.findElement(By.xpath("//span[contains(text(),'Wrong password. Try again or click Forgot password to reset it.')]")).isDisplayed()) {
			            	WebElement expp = driver.
			            	findElement(By.xpath("//span[contains(text(),'Wrong password. Try again or click Forgot password to reset it.')]"));
			            	String actualErorMsg = expp.getText();
			            	Assert.assertEquals(actualErorMsg, expectedErrorMsg);
			            
				            if (actualErorMsg.equalsIgnoreCase(expectedErorMsg)) {
				                System.out.println("Test passed ," + actualErorMsg);
				                status = true; //Lambda status will be reflected as passed
				              } else {
				                System.out.println("Test failed"); //Lambda status will be reflected as passed
			
				            }
			            }
			            else
			            {
			            	String at = driver.getTitle();
				    		String et = "gmail";
				 
				            if (at.equalsIgnoreCase(et)) {
				                System.out.println("Test passed , logged in");
				                status = true; //Lambda status will be reflected as passed
				              } else {
				                System.out.println("Test failed"); //Lambda status will be reflected as passed
				 
				            }
			            }
	           
	           }
	          
	           ((JavascriptExecutor) driver).executeScript("lambda-status=" + status);
		    	 driver.quit(); 
	    	  }
	    	  else 
	    		  
	    		  System.out.println("invalid url");
	    	   
		  }
		  else 
	    		  
			  System.out.println("driver gives null");
	    }
	

}
