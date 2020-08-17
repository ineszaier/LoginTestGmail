package test.java.Login.Suite;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.junit.After;
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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import test.java.database.connexion.ConnexionBD;





public class LoginwithInvalidIdTest {
	private static WebDriver driver;
    public String url = "https://www.lambdatest.com/";
    public static final String  username= "ines.zaier";
    public static final String auth_key = "rLaLDZMzRJiu6nTBkvscHJhVWze745q3djP9scdPXcdmvVFM1t";
    public static final String URL = "@hub.lambdatest.com/wd/hub";
    private static boolean status = false;
    private String xpath;
    private String nom ;
    
    private Connection con;
    private Statement ste;
    private PreparedStatement pste;
    private  ResultSet rs;
    
    
    
    
    public LoginwithInvalidIdTest() {
        con = ConnexionBD.getInstance().getCnx();
	}

	static Logger log = Logger.getLogger(LoginwithInvalidIdTest.class.getName());
    @Rule public TestName name = new TestName();
    @Test
   public void Test_LoginwithInvalidId () throws InterruptedException, SQLException {
	      nom =  name.getMethodName();

        log.info("Started the automated function  : "+nom);

        // TODO Auto-generated method stub
		driver.manage().window().maximize();

		driver.get("http://www.gmail.com/");	

	    	   log.info("Entered a valid URL.");

 
           WebElement login = driver.findElement(By.id("identifierId") );
       
     
		   login.sendKeys("ines.zaier@esprrrit.tn");
		   log.info("Entered an invalid Email Address.");
		

          
          
          WebElement loginNext =driver.findElement(By.id("identifierNext") );
		    
		   loginNext.click();
		   log.info("Clicked on the Next Button.");   
	     
		   

		    xpath ="//div[@class='o6cuMc']";
		    AfficherParXp(xpath);
		    Thread.sleep(5000);
            String expectedErrorMsg = "Couldn't find your Google Account";
            if(IsExisting(nom))
            {Update(nom, xpath , status);}
            else
            Add(nom, xpath , status); 
            WebElement exp = driver.findElement(By.xpath(xpath));
            log.info("search for xpath " +xpath);   

            String actualErrorMsg = exp.getText();
 		//  Assert.assertEquals(actualErrorMsg, expectedErrorMsg);
    
            if (actualErrorMsg.equalsIgnoreCase(expectedErrorMsg)) {
                System.out.println("Test passed ," + actualErrorMsg);
                status = true; //Lambda status will be reflected as passed
                if(IsExisting(nom))
                {Update(nom, xpath , status);}
                else
                Add(nom, xpath , status); 
               AfficherParXp(xpath);
              } else {
                
              AfficherParXp(xpath);
                System.out.println("Test failed");
                if(IsExisting(nom))
                {Update(nom, xpath , status);}
                else
                { Add(nom, xpath , status);}
                
                
               //Lambda status will be reflected as passed
              
 
            }
 
 
    }
   
    @BeforeClass
    public static void setUp() {
    	 try {
    	log.info("Entered setup process.");

     /*   DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", "chrome");
        capabilities.setCapability("version", "72.0");
        capabilities.setCapability("platform", "win8"); // If this cap isn't specified, it will just get the any available one
        capabilities.setCapability("build", "TestNG_login_1");
        capabilities.setCapability("name", "TestNG_login_1");
        capabilities.setCapability("network", true); // To enable network logs
        capabilities.setCapability("visual", true); // To enable step by step screenshot
        capabilities.setCapability("video", true); // To enable video recording
        capabilities.setCapability("console", true); // To capture console logs
      */ 
        DOMConfigurator.configure("log4j.xml");

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\lenovoo\\Desktop\\slenim\\chromedriver_win32\\chromedriver.exe");
		driver=new ChromeDriver();
         //   driver = new RemoteWebDriver(new URL("https://" + username + ":" + auth_key + URL), capabilities);
	    	   log.info("Setup process ended.");

        } catch (Exception e) {
 
            System.out.println("Invalid grid URL" + e.getMessage());
        }
 
    }
  
    @AfterClass
    public static void tearDown() throws Exception {
    	
    	 driver.quit();
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

public List<String> AfficherParXp(String xpath) {
    List<String> lp = new ArrayList<>();
   try {
       System.out.println("d5alt!");
    
       String requete="select function_name from functions where Xpath like \""+xpath+"\"";
      
       pste = con.prepareStatement(requete);
      // pste.setString(1,xpath);
      rs = pste.executeQuery();

     while(rs.next()) {
    	
     lp.add(rs.getString("function_name"));
     } 
     
   } catch (SQLException ex) {
       Logger.getLogger(LoginwithInvalidIdTest.class.getName()).log(null, ex);
   }
    return lp; //To change body of generated methods, choose Tools | Templates.
}
}
