package Login.Suite;

import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.w3c.dom.Node;
import org.w3c.tidy.Tidy;

import database.connexion.ConnexionBD;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.text.Document;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
public class LoginWithInvalidPassword {
	
	public static WebDriver driver ;
    public String url = "https://www.lambdatest.com/";
    public static final String  username= "ines.zaier";
    public static final String auth_key = "rLaLDZMzRJiu6nTBkvscHJhVWze745q3djP9scdPXcdmvVFM1t";
    public static final String URL = "@hub.lambdatest.com/wd/hub";
    static boolean status = false;
 
    private Connection con;
    private Statement ste;
    private PreparedStatement pste;
    private  ResultSet rs;
    
    
    
    
    public LoginWithInvalidPassword() {
    	  con = ConnexionBD.getInstance().getCnx();
		// TODO Auto-generated constructor stub
	}

	static Logger log = Logger.getLogger(LoginwithInvalidId.class.getName());
    @Rule public TestName name = new TestName();

	
	 @Test
	    public void Test_LoginWithInvalidPassword () throws InterruptedException {
	    if(driver!=null) {
	        String nom =  name.getMethodName();
	        
	        log.info("Started the automated function  : "+nom);

	    	  driver.manage().window().maximize();
	    	   driver.get("http://www.gmail.com/");	
	    	   log.info("Entered a valid URL.");
	           WebElement login = driver.findElement(By.id("identifierId") );
			        
			   login.sendKeys("ines.zaier@esprit.tn");
			   log.info("Entered a valid Email Address.");
	          
	          
	          WebElement loginNext =driver.findElement(By.id("identifierNext") );
			    
			   loginNext.click();
			   log.info("Clicked on the Next Button.");   
			   //Thread.sleep(5000);

			   new WebDriverWait(driver, 20).until(ExpectedConditions.
					   elementToBeClickable(By.xpath("//input[@name='password']"))).sendKeys("password");
			   log.info("Entered an invalid password.");
		
			   driver.findElement(By.id("passwordNext")).click();
			   log.info("Clicked on the log in Button.");   
			   String xpath = "//span[contains(text(),'Wrong password. Try again or click Forgot password to reset it.')]";
			   if(IsExisting(nom))
               {Update(nom, xpath , status);}
               else
               Add(nom, xpath , status); 
		  new WebDriverWait(driver, 10);
		  String cureent = driver.getCurrentUrl().toString() ;
		  System.out.println("i'm here ," + cureent);
		
		 

		  String expectedErrorMsg = "Wrong password. Try again or click Forgot password to reset it.";
		  if (driver.findElement(By.xpath(xpath)).isDisplayed())
		  {
        WebElement exp = driver.findElement(By.xpath(xpath));
        log.info("search for xpath " +xpath);   
        String actualErrorMsg = exp.getText();
     
        
        Assert.assertEquals(actualErrorMsg, expectedErrorMsg);
        
        if (actualErrorMsg.equalsIgnoreCase(expectedErrorMsg)&& exp.isDisplayed()){
            System.out.println("Test passed ," + actualErrorMsg);
            status = true; //Lambda status will be reflected as passed
            
            if(IsExisting(nom))
            {Update(nom, xpath , status);}
  
           
          } else {
        	 // VerifyXpath(cureent, "//span[contains(text(),'Wrong password. Try again or click Forgot password to reset it.')]");
            System.out.println("Test failed"); //Lambda status will be reflected as passed
            if(IsExisting(nom))
            {Update(nom, xpath , status);}
            
         
        }
        
		  }
		  else 
		  {
			  System.out.println("Test failed"); //Lambda status will be reflected as passed
	            if(IsExisting(nom))
	            {Update(nom, xpath , status);}
	            else
	            Add(nom, xpath , status);
		  }
        
	    }else
	    	  System.out.println("driver gives null");
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
	    
	    public Boolean IsExisting(String nom_func ) {
	    	String sql1 ="SELECT * from functions WHERE function_name =?";
	    	Boolean exist = false ; 
	        	try {
					pste=con.prepareStatement(sql1);
					   pste.setString(1,nom_func);
					rs=pste.executeQuery();
					if(rs.next()) {
						// ArrayList<String> r=new ArrayList<String>();
						//rs.getString("function_name");
						//r.add(rs.getString("function_name"));
				    	   log.info("already insered , it should be updated");
				    	exist = true; 
				    	}
			    	else
			        	exist = false ;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return exist;
			
			
	    	

	    }
	public void Add(String nom_func , String xpath , boolean status )   {
	    
	    
	    
	            String requete ="INSERT INTO functions (function_name,xpath,xpath_status) values (?,?,?)";
	            
	            try {
					pste= con.prepareStatement(requete);
				     pste.setString(1,nom_func);
			            pste.setString(2,xpath);
			            pste.setBoolean(3,status);
			    

			            
			            pste.executeUpdate();
			            log.info("fonction automatique "+ nom_func+" et xpath enregistrés dans la bd " +xpath);   
			            //To change body of generated methods, choose Tools | Templates.
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            
	    	
	    	
	    }
	    
	    public void Update(String nom_func , String xpath , boolean status) {
	    String sql = "UPDATE functions SET xpath_status="+status+" WHERE function_name='"+nom_func+"'";
	    
		try {
			pste = con.prepareStatement(sql);
		
		//	pste.setBoolean(3, status);
		   
		     
		    int rowsUpdated = pste.executeUpdate();
		    if (rowsUpdated > 0) {
		        System.out.println("An existing user was updated successfully!");
		    }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    }
	    
	 /*   public void VerifyXpath(String url , String xpath) {
	    	BufferedInputStream bufferPage;
			try {
				bufferPage = new BufferedInputStream(new URL(url).openStream());
				Tidy tidy = new Tidy();
		    	tidy.setQuiet(true);
		    	tidy.setShowWarnings(false);
		    	tidy.setInputEncoding("UTF-8");
		    	org.w3c.dom.Document document =  tidy.parseDOM(bufferPage, null);
		    	document.normalize();
		    	XPathFactory factory = XPathFactory.newInstance();
		    	XPath path = factory.newXPath();
		    	Node node = (Node) path.evaluate(xpath, document, XPathConstants.NODE);
		    	if (node == null)
		    		 System.out.println("path n'e  xiste pas");
		    	else
		    		 System.out.println("existe");
		    	
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (XPathExpressionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	
	    }
	    */
	
}
