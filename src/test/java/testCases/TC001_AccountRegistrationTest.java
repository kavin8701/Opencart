package testCases;



import java.time.Duration;



import org.testng.Assert;

import org.testng.annotations.Test;



import pageObjects.AccountRegistrationPage;

import pageObjects.HomePage;
import testBase.BaseClass;



public class TC001_AccountRegistrationTest extends BaseClass{





@Test(groups={"Regression","Master"})

public void verify_account_registration() {

logger.info("***** Starting TC001_AccountRegistrationTest *****");

try
{
HomePage hp = new HomePage(driver);

hp.clickMyAccount();
logger.info("Clicked on MyAccount Link");

hp.clickRegister();
logger.info("Clicked on Register Link");



AccountRegistrationPage repage = new AccountRegistrationPage(driver);


logger.info("Providing customer details.....");
repage.setFirstName(randomeString().toUpperCase());

repage.setLastName(randomeString().toUpperCase());

repage.setEmail(randomeString()+"@gmail.com");

repage.setPhoneNumber(randomeNumber());



String password = randomeAlphaNumeric();



repage.setPassword(password);

repage.setconfirmPass(password);

repage.subscribe();

repage.policy();

repage.clickContinue();

logger.info("Validating expected message.....");
String confmsg = repage.getConfirmationMsg();
if(confmsg.equals("Your Account Has Been Created!"))
{
Assert.assertTrue(true);
}
else
{
logger.error("Test failed.....");
logger.debug("Debug logs.....");
Assert.assertTrue(false);
}
}
catch(Exception e)
{

Assert.fail();
}

logger.info("***** Finished TC001_AccountRegistrationTest *****");

}



}
