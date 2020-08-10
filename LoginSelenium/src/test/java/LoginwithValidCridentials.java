package test.java;
import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;
import org.junit.AfterClass;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import org.junit.rules.TestName;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import database.connexion.ConnexionBD;



public class LoginwithValidCridentials {
	public static WebDriver driver ;
    public String url = "https://www.lambdatest.com/";
    public static final String  username= "ines.zaier";
    public static final String auth_key = "rLaLDZMzRJiu6nTBkvscHJhVWze745q3djP9scdPXcdmvVFM1t";
    public static final String URL = "@hub.lambdatest.com/wd/hub";
    static boolean status = false;
    
    private String xpath;
    private String nom ;
    
    private Connection con;
    private Statement ste;
    private PreparedStatement pste;
    private  ResultSet rs;

    
    
    
    static Logger log = Logger.getLogger(LoginwithInvalidId.class.getName());
    @Rule public TestName name = new TestName();
    
    
    
    public LoginwithValidCridentials() {
    	 con = ConnexionBD.getInstance().getCnx();
		// TODO Auto-generated constructor stub
	}


	@Test
    public void Test_LoginwithValidCridentials () {
    
    	  driver.manage().window().maximize();
    	   driver.get("http://www.gmail.com/");	
    	   log.info("Entered a valid URL.");

           WebElement login = driver.findElement(By.id("identifierId") );
		        
		   login.sendKeys("ines.zaier@esprit.tn");
		   log.info("Entered a valid Email Address.");

          
          
          WebElement loginNext =driver.findElement(By.id("identifierNext") );
		    
		   loginNext.click();
		   log.info("Clicked on the Next Button.");   
   
             xpath ="//input[@name='password']";
    		new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath(xpath))).sendKeys("183JFT0875");
			   log.info("Entered a valid password.");

    		driver.findElement(By.id("passwordNext")).click();
 		   log.info("Clicked on the Login Button.");   

    		
    		 nom =  name.getMethodName();
            log.info("la fonction autamatique  : "+nom);
            log.info("search for xpath " +xpath);   

            String at = driver.getTitle();
    		String et = "gmail";
    		
            if (at.equalsIgnoreCase(et)) {
                System.out.println("Test passed , logged in");
                status = true; //Lambda status will be reflected as passed
                System.out.println(IsExisting(nom) + "1");
                if(IsExisting(nom))
                {Update(nom, xpath , status);}
                else
                Add(nom, xpath , status);
               

              } else {
                System.out.println("Test failed"); //Lambda status will be reflected as passed
                System.out.println(IsExisting(nom) + "2");
                if(IsExisting(nom).equals("true"))
                {Update(nom, xpath , status);}
                else
                Add(nom, xpath , status);
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
public List<String> getAllFunctionsOfXpath(String xpath){
        
        List<String> Functions = new ArrayList<String>() ;
        String req = "select * from functions where xpath = ?";
        try {
           pste = con.prepareStatement(req);
             ResultSet resultSet = pste.executeQuery();
           while (resultSet.next()) {
        	   String nom = new String(resultSet.getString(1));
              
              
               Functions.add(nom);
           
           }
        } catch (Exception ex) {
            System.out.println("Prob query !!!");
            System.out.println( ex.getMessage());
            ex.printStackTrace();
        }
        return Functions;
    }  
}
