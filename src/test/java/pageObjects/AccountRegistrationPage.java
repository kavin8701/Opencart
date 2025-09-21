package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {

public AccountRegistrationPage(WebDriver driver) {
	super(driver);
}

@FindBy(id = "input-firstname")
WebElement txtFirstName;

@FindBy(id = "input-lastname")
WebElement txtLastName;

@FindBy(id = "input-email")
WebElement txtEmail;

@FindBy(id = "input-telephone")
WebElement txtTelephone;

@FindBy(id = "input-password")
WebElement txtPassword;

@FindBy(id = "input-confirm")
WebElement txtConfirmPassword;

@FindBy(xpath = "(//input[@name='newsletter'])[1]")
WebElement btnClickRadio;

@FindBy(name = "agree")
WebElement chkPolicy;

@FindBy(xpath = "//input[@class='btn btn-primary']")
WebElement btnContinue;

@FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
WebElement msgConfirmation;


public void setFirstName(String fname) {
	txtFirstName.sendKeys(fname);
}



public void setLastName(String lname) {
	txtLastName.sendKeys(lname);
}

public void setEmail(String email) {
	txtEmail.sendKeys(email);
}

public void setPhoneNumber(String phone) {
	txtTelephone.sendKeys(phone);
}

public void setPassword(String pass) {
	txtPassword.sendKeys(pass);
}

public void setconfirmPass(String pass) {
	txtConfirmPassword.sendKeys(pass);
}

public void subscribe() {
	btnClickRadio.click();
}

public void policy() {
	chkPolicy.click();
}

public void clickContinue() {
	btnContinue.click();
}

public String getConfirmationMsg() {
	try {
		return (msgConfirmation.getText());
		} catch(Exception e) {
			return (e.getMessage());
			}
	}

}