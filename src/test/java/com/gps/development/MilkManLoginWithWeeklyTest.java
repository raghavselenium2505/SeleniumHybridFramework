package com.gps.development;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.gps.base.TestBase;
import com.gps.pages.MilkMan_ChooseYourPlanPage;
import com.gps.pages.MilkMan_DeliveryDetailPage;
import com.gps.pages.MilkMan_HomePage;
import com.gps.pages.MilkMan_LoginPage;
import com.gps.pages.MilkMan_PaymentPage;
import com.gps.pages.MilkMan_SignUpPage;
import com.gps.pages.MilkMan_YourBasketPage;
import com.gps.utilities.DynamicDataProvider;
import com.gps.utilities.WaitUtils;

public class MilkManLoginWithWeeklyTest extends TestBase {

	 @Test(groups = "MilkManSignupTests",
	          dataProvider = "dynamicData",
	          dataProviderClass = com.gps.utilities.DynamicDataProvider.class)
	    public void MilkManSignupWeekly(Map<String, String> data) {

		try {
			   String runMode = data.get("runMode");

	            if (runMode.equalsIgnoreCase("no")) {
	                throw new SkipException("RunMode NO");
	            }

	            String fName = data.get("FirstName");
	            String lastName = data.get("LastName");
	            String email = data.get("Email");
	            String pass = data.get("Apppassword");
	            String address = data.get("Address");

	            String element1 = data.get("tagName");
	            String element2 = data.get("tagName2");
	            String element3 = data.get("element3");

	            String dropdownSelect = data.get("dropdownSelect");
	            String dropDownDate = data.get("dropDownDate");
	            String frameName = data.get("frameName");

	            String mainItem_1 = data.get("mainItem_1");
	            String subItem_1_1 = data.get("subItem_1_1");
	            String mainItem_2 = data.get("mainItem_2");
	            String subItem_2_1 = data.get("subItem_2_1");
	            String mainItem_3 = data.get("mainItem_3");
	            String subItem_3_1 = data.get("subItem_3_1");
	            String mainItem_4 = data.get("mainItem_4");
	            String subItem_4_1 = data.get("subItem_4_1");

	            String frameName_1 = data.get("frameName_1");
	            String cardNumber = data.get("cardNumber");

	            logger.info("Starting JSON Login Test");
			

			

			logger.info("Starting JSON Login Test");

			test.get().log(Status.INFO, "Click on Signin Button");

			MilkMan_HomePage homepage = new MilkMan_HomePage();
			homepage.hiddenElementDisplay(getDriver().findElement(homepage.buttonLogin),
					"Able to click on signin button", "Unable to click on signin button");
			MilkMan_LoginPage loginpage = new MilkMan_LoginPage();
			test.get().log(Status.INFO, "Click on SignUp Button");

			waitForElementVisible(loginpage.linkSignUp, 10, "Able to click on signup button",
					"Unable to click on signup button");

			click(loginpage.linkSignUp, "Able to click on signup button", "Unable to click on signup button");

			test.get().log(Status.INFO,
					"Check for the title in the signup page and enter the mandatory fields to navigate to the next page ");

			MilkMan_SignUpPage signup = new MilkMan_SignUpPage();
			waitForElementVisible(signup.titleModernMilk, 500, "Able to display title modern milk",
					"Unable to display title modern milk");
			verifyElementDisplayed(signup.titleModernMilk, "Able to display the ModernMilkMan title",
					"Unable to display the ModernMilkMan title");

			sendkeys(signup.textFirstName, fName, "ABle to enter the text in firstName", "Unabel to ");

			verifyElementDisplayed(signup.textLastName, "Able to display the LastName ",
					"Unable to display the LastName");

			sendkeys(signup.textLastName, lastName, "ABle to enter the text in LastName", "Unabel to ");

			verifyElementDisplayed(signup.textEmail, "Able to display the textEmail ",
					"Unable to display the textEmail");

			sendkeys(signup.textEmail, email, "ABle to enter the text in textEmail", "Unabel to ");

			verifyElementDisplayed(signup.textpassword, "Able to display the textpassword ",
					"Unable to display the textpassword");

			sendkeys(signup.textpassword, pass, "ABle to enter the text in pass", "Unabel to ");

			String mobile = "07" + String.valueOf(System.currentTimeMillis()).substring(4, 13);
			sendkeys(signup.textMobileNumber, mobile, "Able to enter the textMobileNumber",
					"Unable to enter the text Mobile Number");
			/*
			 * waitForElementVisible(signup.checkBoxoptout,300,
			 * "Able to display the CreateAccount ", "Unable to display the CreateAccount");
			 * click(signup.checkBoxoptout, "Able to click on checkbox",
			 * "Unable to click on Checkbox");
			 */
			verifyElementDisplayed(signup.buttonCreateAccount, "Able to display the CreateAccount ",
					"Unable to display the CreateAccount");

			click(signup.buttonCreateAccount, "Able to click on CreateAccount", "Unable to click on CreateAccount");
			// click(loginpage.linkSignUp, "Able to click on signup button", "Unable to
			// click on signup button");

			MilkMan_DeliveryDetailPage deliveryPage = new MilkMan_DeliveryDetailPage();

			WaitUtils.waitInvisibleSpinner(signup.buttonCreateAccount);
			test.get().log(Status.INFO,
					"Navigate to the delivery Detail page and enter the address which need to be delivered");
			waitForElementVisible(deliveryPage.titleDeliveryDetail, 3000, "Able to display title modern milk",
					"Unable to display title modern milk");
			verifyElementDisplayed(deliveryPage.titleDeliveryDetail, "Able to display the CreateAccount ",
					"Unable to display the CreateAccount");
			waitForElementVisible(deliveryPage.textStartWith, 30, "Able to display text Address field",
					"Unable to display text Address field");

			sendkeys(deliveryPage.textStartWith, address, "Able to enter the address", "Unable to enter the address");
			waitForElementVisible(deliveryPage.textStartWith, 30, "Able to display text Address field",
					"Unable to display text Address field");

			click(deliveryPage.textStartWith, "Able to click on textbox", "Unable to click on textbox");
			String shortAddress = address.split(",")[0];
			deliveryPage.selectFromDropdown(element3, shortAddress);

			waitForElementVisible(deliveryPage.buttonConfirmAddress, 10, "Able to display buttonConfirmAddress",
					"Unable to display buttonConfirmAddress");

			click(deliveryPage.buttonConfirmAddress, "Able to click on COnfirmAddress",
					"Unable to click on COnfirmAddress page");
			test.get().log(Status.INFO,
					"Navigate to the Confirm address and select the item required to deliver");

			waitForElementVisible(deliveryPage.buttonConfirmPin, 10, "Able to display buttonConfirmPin",
					"Unable to display buttonConfirmPin");
			click(deliveryPage.buttonConfirmPin, "Able to click on buttonConfirmPin",
					"Unable to click on buttonConfirmPin");

			waitforelement(3000);
			deliveryPage.selectFromDropdownValue(element1, dropdownSelect);
			waitforelement(1000);
			deliveryPage.selectFromDropdownValue(element2, dropDownDate);
			waitforelement(1000);
			deliveryPage.selectFromDropdownValue(element2, frameName);
			waitforelement(1000);
			deliveryPage.selectFromDropdownValue(element1, mainItem_1);// main
			waitforelement(1000);
			deliveryPage.selectFromDropdownValue(element1, subItem_1_1);// sub
			waitforelement(1000);
			deliveryPage.selectFromDropdownValue(element1, mainItem_2);// main
			waitforelement(1000);
			deliveryPage.selectFromDropdownValue(element1, subItem_2_1);// sub
			waitforelement(1000);
			deliveryPage.selectFromDropdownValue(element1, mainItem_3);// main
			waitforelement(1000);
			deliveryPage.selectFromDropdownValue(element1, subItem_3_1);// sub
			waitforelement(1000);
			deliveryPage.selectFromDropdownValue(element1, mainItem_4);// main
			waitforelement(1000);
			deliveryPage.selectFromDropdownValue(element1, subItem_4_1);// sub
			waitforelement(1000);
			click(deliveryPage.buttonContinue, "Able to click on continue", "Unable to click on continue");
			MilkMan_YourBasketPage yourbasket = new MilkMan_YourBasketPage();
			waitforelement(5000);
			yourbasket.openDayDropdown("6 Large Free Range Eggs", "Mon");

			yourbasket.selectDropdownValue("2");

			waitforelement(1000);
			yourbasket.scrollUpAndClick(getDriver().findElement(yourbasket.buttonContinueToCheckOut),
					"Able to click on Continue to CheckOut");
			waitforelement(1000);
			
			test.get().log(Status.INFO,
					"Navigate to chooseyourplan page and choose for the subcription monthly/yearly/weekly");
			
			MilkMan_ChooseYourPlanPage chooseyourplan = new MilkMan_ChooseYourPlanPage();
			verifyElementDisplayed(chooseyourplan.titleChooseYourPlan, "Able to display the chooseyourplan",
					"Unable to display the chooseyourplan");
			actionclick(getDriver().findElement(chooseyourplan.radioButtonWeekly), "Able to click on Weekly plans",
					"Unable to click on Weekly plans");
			waitforelement(1000);
			click(chooseyourplan.buttonCheckOut, "Able to click on checkout", "Unable to click on checkout page");

			waitforelement(1000);
			/*
			 * verifyElementDisplayed(chooseyourplan.textWelcomeRound,
			 * "Able to display the chooseyourplan",
			 * "Unable to display the chooseyourplan");
			 * 
			 * click(chooseyourplan.buttonOkay, "Able to click on buttonOkay",
			 * "Unable to click on buttonOkay"); waitforelement(1000);
			 */

			MilkMan_PaymentPage paymentpage = new MilkMan_PaymentPage();
			waitforelement(1000);
			/*
			 * verifyElementDisplayed(paymentpage.textCardNumber,
			 * "Able to display the chooseyourplan",
			 * "Unable to display the chooseyourplan"); sendkeys(paymentpage.textCardNumber,
			 * "4242424242424242", "Able to enter the card number",
			 * "Unable to enter the card number");
			 * 
			 * LocalDate futureDate = LocalDate.now().plusMonths(3); String formatted =
			 * futureDate.format(DateTimeFormatter.ofPattern("MM/yy"));
			 * 
			 * 
			 * sendkeys(paymentpage.textExpiryInput, formatted,
			 * "Able to enter the card number", "Unable to enter the card number");
			 * 
			 * String cvv = String.format("%03d", (int)(Math.random() * 1000));
			 * 
			 * sendkeys(paymentpage.textCVV, cvv, "Able to enter the card number",
			 * "Unable to enter the card number"); waitforelement(1000000);
			 */

			// paymentpage.enterCardDetails();
			test.get().log(Status.INFO,
					"Navigate to payment page and enter the valid card details along with cvv and expiry date");
			
			switchToFrame(paymentpage.frames, frameName_1);
//card number
			sendkeys(paymentpage.textCardNumber, cardNumber, "Able to enter the card number",
					"Unable to enter the card number");
			String formatted = LocalDate.now().plusMonths(3).format(DateTimeFormatter.ofPattern("MM / yy"));

			sendkeys(paymentpage.textExpiryInput, formatted, "Able to enter the card number",
					"Unable to enter the card number");

			String cvv = String.format("%03d", (int) (Math.random() * 1000));

			sendkeys(paymentpage.textCVV, cvv, "Able to enter the card number", "Unable to enter the card number");
			getDriver().switchTo().defaultContent();

			waitForElementVisible(paymentpage.buttonPayment, 10, "Able to display Payment",
					"Unable to display Payment");

			actionclick(getDriver().findElement(paymentpage.buttonPayment), "Able to click on payment button",
					"Unable to click on payment button");
					WaitUtils.waitInvisibleSpinner(paymentpage.spinerLoading, 100);
					
					test.get().log(Status.INFO,
							"Navigate to Thank you page ");
			waitForElementVisible(paymentpage.textThankYou, 10000, "Able to display text ThankYou",
					"Unable to display text ThankYou");

			assertElementDisplayed(paymentpage.textThankYou, "Able to display text thank you ",
					"Unable to display text thank you ");

			// click(paymentpage.copyLink, "Able to click on copyLink button", "Unable to
			// click on copyLink");

			/*
			 * waitForElementVisible(getDriver().findElement(paymentpage.textThankYou),
			 * "Able to display the text ThankYou", "Unable to display the text ThankYou");
			 */

		} catch (Exception e)
		{
		    if (e instanceof SkipException) {
		        System.out.println("⚠ Test Skipped: " + e.getMessage());
		        throw e; // ✅ Let TestNG mark it as SKIP
		    }

		    // ❌ Only for real failures
		    if (getDriver() != null) {
		        logAIFailure(e, getDriver().getCurrentUrl());
		    } else {
		        logAIFailure(e, "Driver not initialized");
		    }

		    throw e; // ✅ Fail the test
		}
	}

}
