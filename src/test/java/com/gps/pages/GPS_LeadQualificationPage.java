package com.gps.pages;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gps.base.TestBase;
import com.gps.utilities.WaitUtils;
import com.relevantcodes.extentreports.LogStatus;

interface leadQualification {
	
}
public class GPS_LeadQualificationPage extends TestBase implements leadQualification {
	
	//Leftpane
	
	public By leftPane = By.xpath("//div[contains(@class,'lead-qualification-left')]");
	public By centrePane = By.xpath("//div[@class='lead-prospecting-input']");
	public By rightPane = By.xpath("//div[contains(@class,'lead-rightrail')]");
	public By inputSearch = By.xpath("//input[@placeholder='Search']");
	public By listofContacts = By.xpath("//div[contains(@class,'conver-contact-list')]");
	public By optionLoadMore = By.xpath("//span[contains(@class,'load-more')]");
	public By messageNoMatchFound = By.xpath("//h3[text()='No Match Found.']");
	public By contactType = By.xpath("(//p[contains(@class,'proposal-left-profile')])[2]");
	
	// contacts page related webelements
	public By linkLeadQualificationinContactsDetailsPage = By.xpath("//i[@ptooltip='Lead Qualification']");
	public By firstContact = By.xpath("//tbody[@class='ui-table-tbody']//tr[1]");
	
	//Pipeline related webelements
	public By buttonNewOpportunity = By.xpath("//span[text()='New Opportunity']");
	public By linkLeadQualificationinPipelinePage = By.xpath("(//i[contains(@class,'pipeline-icons')])[3]");
	public By dropdownIconPipeline = By.xpath("(//span[contains(@class,'ui-dropdown-trigger-icon')])[3]");
	public By inputSearchInPipelineDropdown = By.xpath("(//input[contains(@class,'ui-inputtext')])[2]");
	public By stage = By.xpath("//h4[contains(@class,'stage-name')]");
	
	//Rightpane
	
	public By inputFullName = By.xpath("//input[@aria-label='full name']");
	public By inputPhoneNumber = By.id("phoneNum");
	public By inputExtension = By.id("extensionNum");
	public By inputEmail = By.xpath("//input[@aria-label='email']");
	public By inputAddTags = By.id("tagFields");
	public By buttonUpdate = By.xpath("//div[contains(@class,'lead-rightrail')]//button//span");
	public By iconCrossTag = By.xpath("//i[contains(@class,'icon-edo-close')]");
	

	//Centrepane Contact Information
	
	public By textCenterPane = By.xpath("//h4[contains(.,'PROSPECTING INPUT FORM')]");
	public By textContactInformation = By.xpath("//h3[text()='Contact Information']");
	public By inputCompanyName = By.id("companyName");
	public By inputCompanyPhoneNumber = By.xpath("//input[@formcontrolname='companyPhone']");
	public By inputCompanyAddress = By.id("companyAddress");
	public By inputInitialContactName = By.xpath("//input[@formcontrolname='initialContactName']");
	public By inputInitialContactTitle = By.xpath("//input[@formcontrolname='initialContactTitle']");
	public By inputInitialContactExtension = By.xpath("//input[@formcontrolname='initialContactExtention']");
	public By inputInitialContactEmailAddress = By.xpath("//input[@formcontrolname='initialContactEmailAddress']");
	public By inputKeyDecisionMaker = By.xpath("//input[@formcontrolname='keyDecisionMaker']");
	public By inputKeyDecisionMakerTitle = By.xpath("//input[@formcontrolname='keyDecisionTitle']");
	public By inputKeyDecisionMakerExtension = By.id("keyDecisionExt");
	public By inputKeyDecisionMakerEmailAddress = By.id("keyEmailAddress");
	public By inputKeyDecisionMakerMobileNumber = By.xpath("//input[@formcontrolname='keyDecisionMakerMobileNumber']");
	public By validation_CompanyPhoneNumber = By.xpath("//label[text()='Company Phone Number']/following-sibling::input/following-sibling::div//span[contains(text(),' Enter valid phone number. ')]");
	public By validation_InitialContactName = By.xpath("//label[text()='Initial Contact Name']/following-sibling::input/following-sibling::div//span[contains(text(),'Enter Alpha numeric with only _ and -')]");
	public By validation_InitialContactTitle = By.xpath("//label[text()='Initial Contact Title']/following-sibling::input/following-sibling::div//span[contains(text(),'Enter Alpha numeric with only _ and -')]");
	public By validation_InitialContactExtension = By.xpath("//label[text()='Initial Contact Extension']/following-sibling::input/following-sibling::div//span[contains(text(),'Enter Alpha numeric with only _ and -')]");
	public By validation_InitialContactEmailAddress = By.xpath("//label[text()='Initial Contact Email Address']/following-sibling::input/following-sibling::div//span[contains(text(),' Enter valid Email. ')]");
	public By validation_KeyDecisionMaker = By.xpath("//label[text()='Key Decision Maker']/following-sibling::input/following-sibling::div//span[contains(text(),'Enter Alpha numeric with only _ and -')]");
	public By validation_KeyDecisionMakerTitle = By.xpath("//label[text()='Key Decision Maker Title']/following-sibling::input/following-sibling::div//span[contains(text(),'Enter Alpha numeric with only _ and -')]");
	public By validation_KeyDecisionMakerExtension = By.xpath("//label[text()='Key Decision Maker Extension']/following-sibling::input/following-sibling::div//span[contains(text(),'Enter Alpha numeric with only _ and -')]");
	public By validation_KeyDecisionMakerEmailAddress = By.xpath("//label[text()='Key Decision Maker Email Address']/following-sibling::input/following-sibling::div//span[contains(text(),' Enter Valid Email. ')]");
	public By validation_KeyDecisionMakerMobileNumber = By.xpath("//label[text()='Key Decision Maker Mobile Number']/following-sibling::input/following-sibling::div//span[contains(text(),' Enter valid phone number. ')]");
	
	
	//Centrepane Company Information
	public By textCompanyInformation = By.xpath("//h3[text()='Company Information']");
	public By inputCompanyInformation = By.id("companyInfo");
	public By inputCompanyDescription = By.id("companyDescription");
	public By inputWebsiteAddress = By.id("websiteAddress");
	public By inputNumberofUSLocations = By.id("usLocations");
	public By inputListUSLocationsAddress = By.id("usAddress");
	public By inputListForeignLocationsAddress = By.id("foreginList");
	public By inputNumberofNonUSLocations = By.id("usNumber");
	public By validation_NumberofUSLocations = By.xpath("//label[text()='Number of US Locations']/following-sibling::input/following-sibling::div//span[contains(text(),'Enter Alpha numeric with only _ and -')]");
	public By validation_NumberofNonUSLocations = By.xpath("//label[text()='Number of Non-US Locations']/following-sibling::input/following-sibling::div//span[contains(text(),'Enter Alpha numeric with only _ and -')]");
	
	//Centrepane Expansion Needs
	
	public By textExpansionNeeds =  By.xpath("//h3[text()='Expansion Needs']");
	public By inputBuildingSize = By.id("buildingSize");
	public By inputRoofEaveHeight = By.id("roofHeight");
	public By inputNumberofOverheadDoors = By.id("overheadDoors");
	public By inputTruckWellsOrDockHeight = By.id("truckWalls");
	public By checkboxYesCrane = By.xpath("//p-checkbox[@inputid='la']//div[@role='checkbox']");
	public By checkboxNoCrane = By.xpath("(//p-checkbox[@name='crane'])[2]");
	public By inputCraneSize = By.id("craneSize");
	public By inputTotalAcresNeeded = By.id("acresNeed");
	public By checkboxYesLayDownYard = By.xpath("//p-checkbox[@inputid='layCheckbox']//div[@role='checkbox']");
	public By checkboxNoLayDownYard = By.xpath("//p-checkbox[@inputid='layCheckbox1']//div[@role='checkbox']");
	public By inputSpecialGasRequirements = By.id("gasRequirement");
	public By inputSpecialElectricRequirements = By.id("electricRequirement");
	public By inputSpecialBroadbandRequirements = By.id("broadbandRequirement");
	public By inputSpecialWaterorSewerRequirements = By.id("waterRequirement");
	public By inputInterstateAccessImportant = By.id("accessImportant");
	public By checkboxYesBuildtoSuit = By.xpath("//p-checkbox[@inputid='buildCheck']//div[@role='checkbox']");
	public By checkboxNoBuildSuit = By.xpath("//p-checkbox[@inputid='buildCheck1']//div[@role='checkbox']");
	public By inputPrefertoLeaseorOwn = By.id("preferLease");
	public By inputSpecialSupplierNeeds = By.id("supplierNeeds");
	public By inputTimelineofExpansionProject = By.id("projectTimeline");
	public By validation_BuildingSize = By.xpath("//label[text()='Building Size(Square Feet)']/following-sibling::input/following-sibling::div//span[contains(text(),'Enter Alpha numeric with only _ and -')]");
	public By validation_RoofEaveHeight = By.xpath("//label[text()='Roof/Eave Height']/following-sibling::input/following-sibling::div//span[contains(text(),'Enter Alpha numeric with only _ and -')]");
	public By validation_NumberofOverheadDoors = By.xpath("//label[text()='Number of Overhead Doors']/following-sibling::input/following-sibling::div//span[contains(text(),'Enter Alpha numeric with only _ and -')]");
	public By validation_TruckWellsOrDockHeight = By.xpath("//label[text()='Truck Wells Or Dock Height']/following-sibling::input/following-sibling::div//span[contains(text(),'Enter Alpha numeric with only _ and -')]");
	public By validation_CraneSize = By.xpath("//label[text()='Crane Size']/following-sibling::input/following-sibling::div//span[contains(text(),'Enter Alpha numeric with only _ and -')]");
	public By validation_TotalAcresNeeded = By.xpath("//label[text()='Total Acres Needed']/following-sibling::input/following-sibling::div//span[contains(text(),'Enter Alpha numeric with only _ and -')]");
	public By validation_SpecialGasRequirements = By.xpath("//label[text()='Special Gas Requirements']/following-sibling::input/following-sibling::div//span[contains(text(),'Enter Alpha numeric with only _ and -')]");
	public By validation_SpecialElectricRequirements = By.xpath("//label[text()='Special Electric Requirements']/following-sibling::input/following-sibling::div//span[contains(text(),'Enter Alpha numeric with only _ and -')]");
	public By validation_SpecialBroadbandRequirements = By.xpath("//label[text()='Special Broadband Requirements']/following-sibling::input/following-sibling::div//span[contains(text(),'Enter Alpha numeric with only _ and -')]");
	public By validation_SpecialWaterorSewerRequirements = By.xpath("//label[text()='Special Water or Sewer Requirements']/following-sibling::input/following-sibling::div//span[contains(text(),'Enter Alpha numeric with only _ and -')]");
	public By validation_InterstateAccessImportant = By.xpath("//label[text()='Interstate Access Important']/following-sibling::input/following-sibling::div//span[contains(text(),'Enter Alpha numeric with only _ and -')]");
	public By validation_PrefertoLeaseorOwn = By.xpath("//label[text()='Prefer to Lease or Own']/following-sibling::input/following-sibling::div//span[contains(text(),'Enter Alpha numeric with only _ and -')]");
	public By validation_SpecialSupplierNeeds = By.xpath("//label[text()='Special Supplier Needs']/following-sibling::input/following-sibling::div//span[contains(text(),'Enter Alpha numeric with only _ and -')]");
	public By validation_TimelineofExpansionProject = By.xpath("//label[text()='Timeline of Expansion Project']/following-sibling::input/following-sibling::div//span[contains(text(),'Enter Alpha numeric with only _ and -')]");
	
	
	
	//Centrepane Employee Information
	
	public By textEmployeeInformation = By.xpath("//h3[text()='Employee Information']");
	public By inputCurrentNumberofEmployeesatHeadquarters = By.id("headquarterEmployees");
	public By inputCurrentNumberofEmployeesinUS = By.id("usEmployees");
	public By inputCurrentNumberofEmployeesinWorldwide = By.id("worldwideEmployees");
	public By validation_EmployeesatHeadquarters = By.xpath("//label[text()='Current Number of Employees at Headquarters']/following-sibling::input/following-sibling::div//span[contains(text(),'Enter Alpha numeric with only _ and -')]");
	public By validation_EmployeesinUS = By.xpath("//label[text()='Current Number of Employees in US']/following-sibling::input/following-sibling::div//span[contains(text(),'Enter Alpha numeric with only _ and -')]");
	public By validation_EmployeesinWorldwide = By.xpath("//label[text()='Current Number of Employees in Worldwide']/following-sibling::input/following-sibling::div//span[contains(text(),'Enter Alpha numeric with only _ and -')]");
	
	
	//Centrepane New Employees needed for Expansion Site
	
	public By textNewEmployees = By.xpath("//h3[text()='New Employees needed for Expansion Site']");
	public By inputYear1 = By.id("year1");
	public By inputYear2 = By.id("Year2");
	public By inputYear3 = By.id("Year3");
	public By inputPositionTypesNeeded = By.id("positionTypes");
	public By inputSalaryofEachPosition = By.id("salaryPosition");
	public By inputOtherBenefitsProvided = By.id("otherBenifits");
	public By inputSpecialSkillsNeeded = By.id("specialSkills");
	public By checkboxYesClientsNeedsIdentified = By.xpath("//p-checkbox[@inputid='clientCheck']");
	public By checkboxNoClientsNeedsIdentified = By.xpath("//p-checkbox[@inputid='clientCheck1']");
	public By checkboxYesProposalDeveloped = By.xpath("//p-checkbox[@inputid='proposalCheck']//div[@role='checkbox']");
	public By checkboxNoProposalDeveloped = By.xpath("//p-checkbox[@inputid='proposalCheck1']//div[@role='checkbox']");
	public By inputProposalSentToandDateTime = By.id("proposalSent");
	public By inputNextStepsFollowingProposal = By.id("nextSteps");
	public By checkboxYesSiteVisitSchedule = By.xpath("//p-checkbox[@inputid='visitCheck']//div[@role='checkbox']");
	public By checkboxNoSiteVisitSchedule = By.xpath("//p-checkbox[@inputid='visitCheck1']//div[@role='checkbox']");
	public By inputWhenandWho = By.xpath("//input[@aria-label='when and who']");
	public By inputWhy = By.xpath("//input[@id='whyField']");
	public By inputNextStepsFollowingSiteVisit = By.id("siteVisit");
	public By inputFollowUpDateTimeContact = By.id("followUpDate");
	public By inputNotes = By.id("notes");
	public By buttonClear = By.xpath("//span[text()='Clear']");
	public By buttonSave = By.xpath("//span[text()='Save']");
	public By validation_Year1 = By.xpath("//label[text()='Year1']/following-sibling::input/following-sibling::div//span[contains(text(),'Enter Alpha numeric with only _ and -')]");
	public By validation_Year2 = By.xpath("//label[text()='Year2']/following-sibling::input/following-sibling::div//span[contains(text(),'Enter Alpha numeric with only _ and -')]");
	public By validation_Year3 = By.xpath("//label[text()='Year3']/following-sibling::input/following-sibling::div//span[contains(text(),'Enter Alpha numeric with only _ and -')]");
	public By validation_OtherBenefitsProvided = By.xpath("//label[text()='Other Benefits Provided']/following-sibling::input/following-sibling::div//span[contains(text(),'Enter Alpha numeric with only _ and -')]");
	public By validation_SpecialSkillsNeeded = By.xpath("//label[text()='Special Skills Needed']/following-sibling::input/following-sibling::div//span[contains(text(),'Enter Alpha numeric with only _ and -')]");
	public By validation_NextStepsFollowingProposal = By.xpath("//label[text()='Next Steps Following Proposal']/following-sibling::input/following-sibling::div//span[contains(text(),'Enter Alpha numeric with only _ and -')]");
	public By validation_NextStepsFollowingSiteVisit = By.xpath("//label[text()='Next Steps Following Site Visit']/following-sibling::input/following-sibling::div//span[contains(text(),'Enter Alpha numeric with only _ and -')]");
	public By validation_Why = By.xpath("//label[text()='Why']/following-sibling::input/following-sibling::div//span[contains(text(),'Enter Alpha numeric with only _ and -')]");
	public By validation_WhenandWho = By.xpath("//label[text()='When and Who']/following-sibling::input/following-sibling::div//span[contains(text(),'Enter Alpha numeric with only _ and -')]");
	
	
	//WebDriver driver;
	//WebDriverWait wait;
	
	public void selectCheckboxNo(By ref) {
	    WebElement noCheckbox = driver.findElement(ref);
	    if (!noCheckbox.isSelected()) {
	        noCheckbox.click();
	    }
	    try {
	        Thread.sleep(1000); // wait for UI update
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
	}

	public void selectCheckboxYes(By ref) {
	    WebElement yesCheckbox = driver.findElement(ref);
	    if (!yesCheckbox.isSelected()) {
	        yesCheckbox.click();
	    }
	    try {
	        Thread.sleep(1000); // wait for UI update
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
	}

	public boolean isFieldVisible(By ref) {
	    try {
	        WebElement inputField = driver.findElement(ref);
	        return inputField.isDisplayed();
	        
	    } catch (Exception e) {
	        return false;
	    }
	}
	
	public boolean isInputFieldAcceptingdAlphaNumericSpecialCharacters(By ref, String excelValue, String passValue, String failValue) {
		
		try {
			WebElement element = driver.findElement(ref);
	        element.clear();
	        sendkeys(ref, excelValue, passValue, failValue);
			waitforelement(shortwaitvalue);
			String actualValue = element.getAttribute("value");
			//boolean result = excelValue.equals(actualValue);
			boolean result = actualValue.equals(excelValue);
			 
			 if (result) {
		            System.out.println("PASS: " + passValue);
		           // test.log(LogStatus.PASS, passValue);
		        } else {
		            System.out.println("FAIL: " + failValue);
		            //test.log(LogStatus.PASS, failValue);
		        }

		        return result;
		
		}
		
		catch(Exception e) {
			
			System.out.println("Exception while verifying input field: " + e.getMessage());
			return false;
			
		}
		
	}
	
	
	public void addTag(String tagText) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	    // Locate the tag input field and enter tag text
	    WebElement tagInput = wait.until(ExpectedConditions.visibilityOfElementLocated(inputAddTags));
	    tagInput.sendKeys(tagText);

	    waitforelement(shortwaitvalue);
	    // Wait for dropdown options to appear
	    By dropdownOptions = By.cssSelector("ul.ui-autocomplete-items > li");
	    wait.until(ExpectedConditions.visibilityOfElementLocated(dropdownOptions));

	    // Get all dropdown options
	    List<WebElement> options = driver.findElements(dropdownOptions);

	    for (WebElement option : options) {
	        String optionText = option.getText().trim();
	        
	        // Check if it's a new tag with '+' prefix
	        if (optionText.equalsIgnoreCase("+ " + tagText)) {
	            option.click(); // Click to add new tag
	            return;
	        } else if (optionText.equalsIgnoreCase(tagText)) {
	            option.click(); // Select from existing tags
	            return;
	        }
	    }

	    // Optionally: throw error if tag not added
	    throw new RuntimeException("Tag '" + tagText + "' could not be added.");
	}

	
	public void verifyUpdateButton(By Locator1, By Locator2,String newValue) {
		
		try {
        //WebElement inputField = wait.until(ExpectedConditions.visibilityOfElementLocated(Locator));
		waitforelement(shortwaitvalue);
		WebElement inputField = driver.findElement(Locator1);

        // Step 1: Get and print existing value
        String existingValue = inputField.getAttribute("value");
        System.out.println("Existing value in the input field: " + (existingValue.isEmpty() ? "[Empty]" : existingValue));

        logger.info("Existing value in the input field is (Empty/ have some value) " +existingValue);
		test.log(LogStatus.PASS,"Existing value in the input field is (Empty/ have some value) " +  (existingValue.isEmpty() ? "[Empty]" : existingValue));
                
        // Step 2: Clear the field
        inputField.clear();

        // Step 3: Enter new value
        inputField.sendKeys(newValue);
        click(Locator2);

        // Step 4: Click update button
        //WebElement updateBtn = wait.until(ExpectedConditions.elementToBeClickable(buttonUpdate));
        //waitforelement(shortwaitvalue);
        WebElement updateBtn = driver.findElement(buttonUpdate);
        WaitUtils.waitVisibilityByRef(By.xpath("//div[contains(@class,'lead-rightrail')]//button//span"));
        updateBtn.click();
        waitforelement(shortwaitvalue);
        
        // Step 5: Optional - wait for value to update in UI (can vary depending on app behavior)
        //wait.until(ExpectedConditions.attributeToBe(Locator, "value", newValue));

        // Step 6: Fetch and print the updated value
        String updatedValue = inputField.getAttribute("value");
        System.out.println("Updated value in the input field is: " + updatedValue);
        
        logger.info("Input field value after updating is " +updatedValue);
		test.log(LogStatus.PASS,"Input field value after updating is " +updatedValue);
	}
		catch (Exception e) {
			logger.info(e.getMessage());
			e.printStackTrace();
			// test.log(LogStatus.FAIL, failValue);

			test.log(LogStatus.FAIL, "<html><body><p><a href=" + screenshotutil.captureScreenshot(value)
					+ ">click here for screenshot</p></body></html>");

		}
    }
	
	public void clearButton(By Locator1, String newValue1) {
		try {
		waitforelement(shortwaitvalue);
		WebElement inputField_1 = driver.findElement(Locator1);
		inputField_1.sendKeys(newValue1);
		waitforelement(shortwaitvalue);
		
        String existingValue_1 = inputField_1.getAttribute("value");
        System.out.println("Existing value in the input field: " + (existingValue_1.isEmpty() ? "[Empty]" : existingValue_1));

       	test.log(LogStatus.PASS,"Existing value in the input field is: " +  (existingValue_1.isEmpty() ? "[Empty]" : existingValue_1));
       
        //waitforelement(shortwaitvalue);
        
        WebElement clearBtn = driver.findElement(buttonClear);
        clearBtn.click();
        
        String existingValue_2 = inputField_1.getAttribute("value");
        System.out.println("Existing value in the input field after clearning the data: " + (existingValue_2.isEmpty() ? "[Empty]" : existingValue_2));

       	test.log(LogStatus.PASS,"Existing value in the input field after clearning the data is: " +  (existingValue_2.isEmpty() ? "[Empty]" : existingValue_2));
		
		}
		
		catch (Exception e) {
			logger.info(e.getMessage());
			e.printStackTrace();
			// test.log(LogStatus.FAIL, failValue);

			test.log(LogStatus.FAIL, "<html><body><p><a href=" + screenshotutil.captureScreenshot(value)
					+ ">click here for screenshot</p></body></html>");

		}
      
	}
	
	public void saveButton(By Locator1, String newValue1) {
		
		try {
			waitforelement(shortwaitvalue);
			WebElement inputField_1 = driver.findElement(Locator1);
			
			isdisplay(Locator1, "User is able to see the input value",
					"User is unable to see the input value");
	        String existingValue_1 = inputField_1.getAttribute("value");
	        System.out.println("Existing value in the input field: " + (existingValue_1.isEmpty() ? "[Empty]" : existingValue_1));

	       	test.log(LogStatus.PASS,"Existing value in the input field is: " +  (existingValue_1.isEmpty() ? "[Empty]" : existingValue_1));
	       	inputField_1.sendKeys(newValue1);
			waitforelement(shortwaitvalue);
	       	
	        WebElement SaveBtn = driver.findElement(buttonSave);
	        SaveBtn.click();
	        WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
	        
	        String existingValue_2 = inputField_1.getAttribute("value");
	        System.out.println("Existing value in the input field after saving the data: " + (existingValue_2.isEmpty() ? "[Empty]" : existingValue_2));

	       	test.log(LogStatus.PASS,"Existing value in the input field after saving the data is: " +  (existingValue_2.isEmpty() ? "[Empty]" : existingValue_2));
			
	       		}
		catch (Exception e) {
			logger.info(e.getMessage());
			e.printStackTrace();
			// test.log(LogStatus.FAIL, failValue);

			test.log(LogStatus.FAIL, "<html><body><p><a href=" + screenshotutil.captureScreenshot(value)
					+ ">click here for screenshot</p></body></html>");

		}
	}
	
public void extensionField(By Locator1, String newValue1) {
        
        try {
            waitforelement(shortwaitvalue);
            WebElement inputField_1 = driver.findElement(Locator1);
            
            isdisplay(Locator1, "User is able to see the input value",
                    "User is unable to see the input value");
            String existingValue_1 = inputField_1.getAttribute("value");
            System.out.println("Existing value in the input field: " + (existingValue_1.isEmpty() ? "[Empty]" : existingValue_1));

               test.log(LogStatus.PASS,"Existing value in the input field is: " +  (existingValue_1.isEmpty() ? "[Empty]" : existingValue_1));
            inputField_1.clear();
            waitforelement(shortwaitvalue);
            
               inputField_1.sendKeys(newValue1);
            waitforelement(shortwaitvalue);
            
            WebElement inputField_2 = driver.findElement(inputEmail);
            inputField_2.click();
            
            WebElement updateBtn = driver.findElement(buttonUpdate);
            updateBtn.click();
            WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
           
            String existingValue_2 = inputField_1.getAttribute("value");
            int length = existingValue_2.length();
            System.out.println("Existing value in the input field after updating the data: " + (existingValue_2.isEmpty() ? "[Empty]" : existingValue_2));

            test.log(LogStatus.PASS,"Existing value in the input field after updating the data is: " +  (existingValue_2.isEmpty() ? "[Empty]" : existingValue_2));
            
            logger.info("The length is: " + length);
            test.log(LogStatus.PASS,"Length of the value in the extension field is: " + length);
                   }
        catch (Exception e) {
            logger.info(e.getMessage());
            e.printStackTrace();
            // test.log(LogStatus.FAIL, failValue);

            test.log(LogStatus.FAIL, "<html><body><p><a href=" + screenshotutil.captureScreenshot(value)
                    + ">click here for screenshot</p></body></html>");

        }
    }

public void extensionInvalid(By Locator) {
	 try {
         waitforelement(shortwaitvalue);
         WebElement inputField_1 = driver.findElement(Locator);
         
         isdisplay(Locator, "User is able to see the input field",
                 "User is unable to see the input field");
         String existingValue_1 = inputField_1.getAttribute("value");
         System.out.println("Existing value in the input field: " + (existingValue_1.isEmpty() ? "[Empty]" : existingValue_1));

            test.log(LogStatus.PASS,"Existing value in the input field is: " +  (existingValue_1.isEmpty() ? "[Empty]" : existingValue_1));
         inputField_1.clear();
        // waitforelement(shortwaitvalue);
         
         sendkeys(Locator, excelutil.getData("LeadQualification", "InvalidExtensionValue", xlsname),
                 "Try to enter value in extension field as "
                         + excelutil.getData("LeadQualification", "InvalidExtensionValue", xlsname),
                 "Unble to enter value in extension field");
         String Ext = driver.findElement(Locator).getAttribute("value");

         logger.info("After passing the invalid value in the Extension field is " + Ext);
         WebElement inputField_2 = driver.findElement(inputEmail);
         inputField_2.click();
         WebElement updateBtn = driver.findElement(buttonUpdate);
         updateBtn.click();
         test.log(LogStatus.PASS,"Existing value after passing the invalid value in the input field is: " +  (Ext.isEmpty() ? "[Empty]" : Ext));
         
         WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
           
                }
     catch (Exception e) {
         logger.info(e.getMessage());
         e.printStackTrace();
         // test.log(LogStatus.FAIL, failValue);

         test.log(LogStatus.FAIL, "<html><body><p><a href=" + screenshotutil.captureScreenshot(value)
                 + ">click here for screenshot</p></body></html>");

     }
}

public void selectDropdownValue(By locator1, By locator2, String textValue) {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    click (locator1);

    // Locate the tag input field and enter tag text
    WebElement inputField = wait.until(ExpectedConditions.visibilityOfElementLocated(locator2));
    inputField.sendKeys(textValue);

    waitforelement(shortwaitvalue);
    // Wait for dropdown options to appear
    By dropdownOptions = By.cssSelector("p-dropdownitem.ng-star-inserted > li");
    wait.until(ExpectedConditions.visibilityOfElementLocated(dropdownOptions));

    // Get all dropdown options
    List<WebElement> options = driver.findElements(dropdownOptions);

    for (WebElement option : options) {
        String optionText = option.getText().trim();
        
        
        if (optionText.equalsIgnoreCase(textValue)) {
            option.click(); // Select from existing value
            waitforelement(shortwaitvalue);
                       
            return;
        }
        
        else {
        	 System.out.println("Value is not available " + textValue);

             
        }
    }

    // Optionally: throw error if contact is not added
    throw new RuntimeException("Value is'" + textValue + "' could not be added.");
}
}
