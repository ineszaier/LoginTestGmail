package selenium.demo;

import java.net.URL;
import java.util.Arrays;
import java.util.Collection;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@RunWith(Parameterized.class)
public class LoginTest {
	
    private String url ;
    private String loggin ;
    private String password ;
   
    private Login log = new Login();
    
    
  
	public LoginTest(String url, String loggin, String password) {
		super();
		this.url = url;
		this.loggin = loggin;
		this.password = password;
	}
	
	@Parameters
  	public static Collection data(){
         	return Arrays.asList(new Object[][]{{"http://wwww.gmail.com","ines.zaier@esprit.tn","183JFT0875"},{"http://www.gmail.com","ines.zaier@esprit.tn","183JFT0875"},{"http://www.gmail.com","QTP","183JFT0875"},{"http://www.gmail.com","ines.zaier@esprit.tn","Chennai"}});
  	}	
    @Test
    public void test () {
    
    	log.loggin(url, loggin, password);
    		  
    		  
    }
    
   
    
   

}
