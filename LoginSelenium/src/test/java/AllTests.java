package test.java;

import org.apache.log4j.xml.DOMConfigurator;
import org.junit.BeforeClass;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import test.java.LoginWithInvalidPassword;
import test.java.LoginWithInvalidUrl;
import test.java.LoginwithInvalidId;
import test.java.LoginwithValidCridentials;

import org.junit.runner.notification.Failure;

import junit.framework.TestSuite;

@RunWith(Suite.class)
@SuiteClasses({ LoginwithInvalidId.class, LoginWithInvalidPassword.class, LoginWithInvalidUrl.class,
		LoginwithValidCridentials.class })

public class AllTests {
	@BeforeClass
	public static void Setup() {
	                // loading log4j.xml file
	               DOMConfigurator.configure("log4j.xml");
	}

	

}
