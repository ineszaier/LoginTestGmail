package test.java;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class LoginWithInvalidUrl {
	public static WebDriver driver;
    public String url = "https://www.lambdatest.com/";
    public static final String  username= "ines.zaier";
    public static final String auth_key = "rLaLDZMzRJiu6nTBkvscHJhVWze745q3djP9scdPXcdmvVFM1t";
    public static final String URL = "@hub.lambdatest.com/wd/hub";
    static boolean status = false;
 
    
    private Connection con;
    private Statement ste;
    private PreparedStatement pste;
    private  ResultSet rs;
    
    static Logger log = Logger.getLogger(LoginwithInvalidId.class.getName());
    @Rule public TestName name = new TestName();
   
    @Test
    public void Test_LoginWithInvalidUrl () {
        // TODO Auto-generated method stub

 	   String nom =  name.getMethodName();
        
       log.info("Started the automated function  : "+nom);
    	 driver.manage().window().maximize();
  	   driver.get("http://www.gmmmmail.com/");	
	   log.info("Entered an invalid URL.");

 
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
    
    public void Add(String nom_func , String xpath , boolean status ) {
    	
    	try {
    	String sql1 ="SELECT * from functions WHERE nom_func = ? and xpath = ?";
    	pste=con.prepareStatement(sql1);
    	rs=pste.executeQuery();
    	if(rs.next()) {
    	   log.info("already insered , it will be updated");
    	   Update( nom_func ,  xpath ,  status );
           log.info("fonction automatique "+ nom_func+" et xpath " +xpath+" sont mis à jour");   

    	} else {
    	try {
            String requete ="INSERT INTO functions (function_name,xpath,xpath_status) values (?,?,?)";
            
            pste= con.prepareStatement(requete);
            
            
            pste.setString(1,nom_func);
            pste.setString(2,xpath);
            pste.setBoolean(3,status);
    

            
            pste.executeUpdate();
            log.info("fonction automatique "+ nom_func+" et xpath enregistrés dans la bd " +xpath);   
            //To change body of generated methods, choose Tools | Templates.
        } catch (SQLException ex) {
            Logger.getLogger(LoginwithValidCridentials.class.getName()).log(null, ex);
        }
      }
    	}catch (SQLException ex) {
            Logger.getLogger(LoginwithValidCridentials.class.getName()).log(null, ex);
        }
    }
    
    public void Update(String nom_func , String xpath , boolean status) {
    String sql = "UPDATE functions SET xpath_status="+status+" WHERE function_name="+nom_func;
    
	try {
		pste = con.prepareStatement(sql);
	
		pste.setBoolean(3, status);
	   
	     
	    int rowsUpdated = pste.executeUpdate();
	    if (rowsUpdated > 0) {
	        System.out.println("An existing user was updated successfully!");
	    }
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
    }
   
    @BeforeClass
    public static void setUp() {
    
        try {
        	 System.setProperty("webdriver.chrome.driver", "C:\\Users\\lenovoo\\Desktop\\slenim\\chromedriver_win32\\chromedriver.exe");
     		driver=new ChromeDriver();
            //driver = new RemoteWebDriver(new URL("https://" + username + ":" + auth_key + URL), capabilities);
 
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
