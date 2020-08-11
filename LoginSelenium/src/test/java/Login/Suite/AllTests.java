package test.java.Login.Suite;

import org.apache.log4j.xml.DOMConfigurator;
import org.junit.BeforeClass;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.junit.runner.notification.Failure;

import junit.framework.TestSuite;

@RunWith(Suite.class)
@SuiteClasses({ LoginwithInvalidIdTest.class, LoginWithInvalidPasswordTest.class, LoginWithInvalidUrlTest.class,
		LoginwithValidCridentialsTest.class })

public class AllTests {
	@BeforeClass
	public static void Setup() {
	                // loading log4j.xml file
	               DOMConfigurator.configure("log4j.xml");
	}

	

}
