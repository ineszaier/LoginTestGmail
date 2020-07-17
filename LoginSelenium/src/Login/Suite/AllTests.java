package Login.Suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ LoginwithInvalidId.class, LoginWithInvalidPassword.class, LoginWithInvalidUrl.class,
		LoginwithValidCridentials.class })
public class AllTests {

}
