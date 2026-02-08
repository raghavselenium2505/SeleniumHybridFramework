package com.gps.pages;

import org.openqa.selenium.By;

import com.gps.base.TestBase;
import com.gps.utilities.WaitUtils;

interface addUser {
	public void userRole(String locator, String userType, String passValue, String failValue);

}

public class GPS_OrgAddUserPage extends TestBase implements addUser {

	public By buttonUpload = By.xpath("//label[text()='Upload ']");

	public By buttonRemove = By.xpath("//span[text()='Remove']");

	public By inputFirstName = By.id("firstName");

	public By inputLastName = By.id("lastName");

	public By inputPassword = By.xpath("//input[@id='password']");

	public By inputEmailAddress = By.xpath("//input[@id='emailAddress']");

	public By inputPhoneNumber = By.xpath("//input[@id='phoneNumber']");

	public By inputExtension = By.xpath("//input[@id='extension']");

	public By dropdownUserRole = By.xpath("//span[text()='Select User Role']");

	public By inputUserType = By.xpath("//span[text()='Elite']");

	public By buttonCancel = By.xpath("//span[text()='Cancel']");

	public By buttonSave = By.xpath("//span[text()='Save']");

	public By iconClose = By.xpath("(//span[@class='pi pi-times'])[2]");

	public By textAdduser = By.xpath("//h3[text()='Add User']");

	public By ValidationMessage_FirstName = By.xpath("//span[text()=' First Name is required. ']");

	public By ValidationMessage_LastName = By.xpath("//span[text()=' Last Name is required. ']");

	public By ValidationMessage_Password = By.xpath("//span[text()=' Password is required. ']");

	public By ValidationMessage_EmailAddress = By.xpath("//span[text()=' Email is required. ']");

	public By ValidationMessage_PhoneNumber = By.xpath("//span[text()=' Phone number is required. ']");

	public By ValidationMessage_UserRole = By.xpath("//span[text()=' User Role is required. ']");
	public By ValidationMessageforUserType = By.xpath("//span[contains(text(),' User Type is required. ')]");

	public By ValidationMessageforExistingEmail = By.xpath("//span[text()=' This email address is already in use. ']");

	public By ValidationMessageforInvalidEmail = By.xpath("//span[text()=' Enter valid email. ']");

	public By ValidationMessageforInvalidPhone = By.xpath("//span[text()=' Enter valid phone number. ']");

	public By ValidationMessageforPassword_Characters = By
			.xpath("//p[text()='Use atleast 8 characters and no more than 30 characters ']");

	public By ValidationMessageforPassword_UpperCase = By.xpath("//p[text()='Use atleast 1 upper case letter ']");

	public By ValidationMessageforPassword_LowerCase = By.xpath("//p[text()='Use atleast 1 lower case letter ']");

	public By ValidationMessageforPassword_Numeric = By.xpath("//p[text()='Use atleast 1 numeric letter ']");

	public By ValidationMessageforPassword_SpecialCharacters = By
			.xpath("//p[text()='Use atleast 1 special character ']");

	public By dropdownValueAdmin = By.xpath("//li[@aria-label='Admin']");

	public By dropdwonValueUser = By.xpath("//li[@aria-label='User']");

	@Override
	public void userRole(String locator, String userType, String passValue, String failValue) {
		WaitUtils.waitVisibilityByRef(By.xpath("//" + locator + "[text()='" + userType + "']"));

		actionclick(driver.findElement(By.xpath("//" + locator + "[text()='" + userType + "']")), passValue, failValue);

	}

}
