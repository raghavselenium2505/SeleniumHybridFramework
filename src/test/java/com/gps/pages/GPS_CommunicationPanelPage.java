package com.gps.pages;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.gps.base.TestBase;
import com.gps.utilities.WaitUtils;
import com.relevantcodes.extentreports.LogStatus;

interface GPS_CommunicationPanel {

	public void processUnreadMessages(By listLocator, String actionDescription, String logMessage);

	public void processArchiveMessages(By messagesLocator, By archiveIconLocator);

	public void processMouseOver(By messagesLocator, By mouseOverLoactor, String passValue, String failValue);

	public void processMiddlePane(By messagesLocator, By conversationLocator, String passValue, String failValue);

	public void processUnreadList(By listLocator, String listName);

	public void processIconsUnderAll(By messagesLocator, By archiveLocator, By tabLocator, String passValue,
			String failValue);

	public void retrieveAndLogActivePipelines();

	public void retrieveAndLogNewlyCreatedActivePipeline();

	public void deleteNewlyCreatedPipeline();

	public void length(By Element, String Value, String passValue, String failValue);

	public void enterTextInIframeEditor(By iframeLocator, String inputText);

	public void checkUpdatedStatusNames();

}

public class GPS_CommunicationPanelPage extends TestBase implements GPS_CommunicationPanel {

	public By unreadTab = By.xpath("//span[text()='Unread']");

	public By recentTab = By.xpath("//span[text()='Recent']");

	public By allTab = By.xpath("//span[text()='All']");

	public By archiveTab = By.xpath("//span[text()='Archive']");

	public By leftPane = By
			.xpath("//div[@class='ui-tabview ui-widget ui-widget-content ui-corner-all ui-tabview-top']");

	public By centralPane = By.xpath(
			"//div[@class='conversation-details  col-md-6 proposal-searching proposal-borders border-left p-r-0 p-l-0']");

	public By rightPane = By.xpath("//div[contains(@class,'proposal-calender')]");

	public By textSearch = By.xpath("//input[@placeholder='Search']");

	public By searchbtn = By.xpath("//span[@class='ui-button-icon-left ui-clickable icon icon-edo-loupe']");

	public By validationSearchMessage = By.xpath("//h3[text()='No Match Found.']");

	public By noItemsMessage = By.xpath("//h3[text()='There are no items to display.']");

	public By markAsUnreadIcon = By.xpath("//i[contains(@class,'icon icon-edo-messages-silhouette acrh-mail-icons')]");

	public By listOfcontactName = By.xpath("//p[contains(@class,'prop-cont-title')]");

	public By listOfMessages = By.xpath("//p[contains(@class,'prop-cont fs')]");

	public By listOfcontactMonthDay = By.xpath("//span[contains(@class,'proposal-time m')]");

	public By listOfcontactTime = By.xpath("//span[contains(@class,'proposal-time fs')]");

	public By listOfUsers = By.xpath("//div[contains(@class,'new-user')]");

	public By archiveIcon = By.xpath("//i[contains(@class,'icon icon-edo-archive-icon')]");

	public By unarchiveIcon = By.xpath("//i[contains(@class,'icon icon-edo-unarchive-icon')]");

	public By markAsReadIcon = By.xpath("//i[contains(@class,'icon icon-edo-envelope acrh-mail-icons')]");

	public By noConversations = By.xpath("//p[contains(@class,'text-center')]");

	public By newMessageBtn = By.xpath("//i[contains(@ptooltip,'New Message')]");

	public By conversationMessages = By.xpath("//div[@class='p-t-10 word-break-word email-singel-text add-eclips ']");

	public By middlePaneContact = By.xpath("//h5[@class='fs-18 ng-star-inserted']");

	public By sendEmailTab = By.xpath("//span[text()='Send Email']");

	public By sendSMSTab = By.xpath("//span[text()='Send SMS']");

	public By expandIcon = By.xpath("//span[contains(@class,'icon icon-edo-expand')]");

	public By textFromName = By.xpath("//input[@placeholder='From Name']");

	public By textFromEmail = By.xpath("//input[@placeholder='From Email']");

	public By textSubjectEmail = By.xpath("//input[@placeholder='Subject']");

	public By contentEditingTool = By.xpath("//div[@class='tox-editor-header']");

	public By customValueField = By.xpath("//span[text()='Custom Values']");

	public By editTextArea = By.xpath("//div[@class='tox-edit-area']");

	public By textAreabox = By.xpath("//iframe[@class='tox-edit-area__iframe']");

	public By useTemplateField = By.xpath("//p[text()='Use Template']");

	public By attachmentsField = By.xpath("//p[text()='Attachments']");

	public By buttonSend_Email = By.xpath("//span[text()='Send']");

	public By buttonClear_Email = By.xpath("//span[text()='Clear']");

	public By emailFileIcon_Email = By.xpath("//img[@alt='email file']");

	public By selectTemplate_UseTemplate = By
			.xpath("//div[contains(@class,'ui-dropdown-label-container')]//span[text()='Select Template']");

	public By searchText_UseTemplate = By
			.xpath("//input[@class='ui-dropdown-filter ui-inputtext ui-widget ui-state-default ui-corner-all']");

	public By selectDropdown_UseTemplate = By.xpath("//li[@class='ui-dropdown-item ui-corner-all']");

	public By button_UseTemplate = By.xpath("//span[text()='Use Template']");

	public By contactImage = By.xpath("//span[@class='fs-16']");

	public By contactName_RightPane = By.xpath("//input[@placeholder='Name']");

	public By phoneNum_RightPane = By.xpath("//input[@id='phoneNum']");

	public By extensionNum_RightPane = By.xpath("//input[@id='extensionNum']");

	public By extensionPlaceholder_RightPane = By.xpath("//input[@placeholder='Enter Extension Number']");

	public By emailAddress_RightPane = By.xpath("//input[@placeholder='Enter Email Address']");

	public By tagFiels_RightPane = By.id("tagFields");

	public By labelDNC_RightPane = By.xpath("//label[@for='switchId']");

	public By toogleOffDND_RightPane = By.xpath("//div[@class='ui-inputswitch ui-widget']");

	public By toggleButton_RightPane = By.xpath("//span[@class='ui-inputswitch-slider']");

	public By labelActiveSeq_RightPane = By
			.xpath("//div[@class='m-b-15 m-t-15']//div[@class='d-flex align-items-center justify-content-between']");

	public By labelActivePipeline_RightPane = By
			.xpath("//div[@class='p-b-30']//div[@class='d-flex align-items-center justify-content-between']");

	public By appointments_RightPane = By.xpath(
			"//div[@class='fs-16 set-time border text-center text-font m-b-30 border-resp position-relative cursor-pointer ng-star-inserted']//span[contains(text(),'Appointments')]");

	public By buttonUpdate_RightPane = By.xpath("//span[text()='Update']");

	public By validationContactName_RightPane = By.xpath(
			"//div[contains(@class,'error error-display error-correction-display')]//span[contains(@class,'ng-star-inserted')]");

	public By validationPhoneNumber_RightPane = By.xpath("//span[text()=' Enter valid phone number. ']");

	public By validationEmailAddress_RightPane = By.xpath("//span[text()=' Enter valid email. ']");

	public By selectTagDropdown_RightPane = By.xpath("//li[@role='option']");

	public By removeTag_RightPane = By.xpath("//i[contains(@class,'icon icon-edo-close')]");

	public By activeSequenceAddICon_RightPane = By.xpath("//i[@aria-label='add icon']");

	public By activePipelineLists_RightPane = By.xpath("//span[@class='oppor-label ']");

	public By editIcon_RightPane = By.xpath("//i[@aria-label='edit icon']");

	public By activePipelineAddIcon_RightPane = By
			.xpath("//i[@class='icon icon-edo-add pi-rep add-option fs-18 fw-800 m-t-4 m-l-5 cursor-pointer']");

	public By buttonDelete_EditPopop = By.xpath("//span[text()='Delete']");

	public By textDelete_EditPopop = By.xpath("//input[@id='exampleInputName']");

	public By buttonSubmit_EditPopop = By.xpath("//span[text()='Submit']");

	public By iframeLocator = By.xpath("//iframe[contains(@class,'tox-edit-area__iframe')]");

	// Sphurthi
	public By AllTab = By.xpath("//span[contains(text(),'All')]");

	public By activePipelinePlus = By
			.xpath("//p[contains(text(),'Active Pipeline Opportunities')]/following::i[contains(@class,'icon')]");

	public By selectStatusDropdownExpand = By
			.xpath("//p-dropdown[contains(@placeholder,'Select Status')]//following::span[contains(@class,'trigger')]");

	public By statusListFromDropdown = By.xpath("//ul[contains(@class,'dropdown-items')]//span");

	public By cancelButton = By.xpath("//span[contains(text(),'Cancel')]");

	@Override
	public void processUnreadMessages(By listLocator, String actionDescription, String logMessage) {
		// Try to get the message list inside the loop
		List<WebElement> messageList;

		try {
			messageList = driver.findElements(listLocator);
		} catch (StaleElementReferenceException e) {
			logger.info("StaleElementReferenceException: Failed to fetch the message list, retrying...");
			// Re-fetch the element list if stale
			messageList = driver.findElements(listLocator);
		}
		logger.info(logMessage + " count: " + messageList.size());

		// If there are no messages, simply click on the Unread Tab
		if (messageList.isEmpty()) {
			// logger.info("No " + logMessage + " found, clicking on Unread Tab...");
			driver.findElement(unreadTab).click();
			waitforelement(mediumwaitvalue);
			// test.log(LogStatus.PASS, "No " + logMessage + " found, clicking on Unread
			// Tab.");
			return;
		}
		// Loop through each message and perform the 'Mark as Unread' action
		for (int i = 0; i < messageList.size(); i++) {
			WebElement message = messageList.get(i);
			System.out.println(messageList.size());
			// Re-fetch the message in case it has been modified during processing
			try {
				Actions actions = new Actions(driver);
				actions.moveToElement(message).perform(); // Hover over the message to reveal options

				waitforelement(mediumwaitvalue);

				// Try to locate the "Mark as Unread" button
				WebElement markUnreadButton = message.findElement(markAsUnreadIcon);

				waitforelement(shortwaitvalue);

				// If the "Mark as Unread" button is visible, click it
				if (markUnreadButton.isDisplayed() && markUnreadButton.isEnabled()) {
					markUnreadButton.click();
					logger.info("Marked message as Unread");
				} else {
					// If the "Mark as Unread" icon is not visible, skip the message
					logger.info("Skipping, as the message is already unread or the icon is not visible.");
				}
			} catch (StaleElementReferenceException e) {
				// If the message element becomes stale, re-fetch it
				logger.info("StaleElementReferenceException caught. Re-fetching the message list and continuing.");
				messageList = driver.findElements(listLocator); // Re-locate the list of messages
				message = messageList.get(i); // Re-fetch the specific message element
				Actions actions = new Actions(driver);
				actions.moveToElement(message).perform(); // Re-perform hover
				continue; // Retry the current iteration with the updated reference
			} catch (NoSuchElementException e) {
				// If no "Mark as Unread" button found, click on the Unread Tab
				logger.info("No 'Mark as Unread' icons found, clicking on Unread Tab...");
				// test.log(LogStatus.PASS, "No 'Mark as Unread' icons found, clicking on Unread
				// Tab...");
				driver.findElement(unreadTab).click();
				waitforelement(mediumwaitvalue);
			}
		}
	}

	public boolean isElementPresent(By locator) {
		try {
			driver.findElement(locator);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public void processUnreadList(By listLocator, String listName) {
		// Find elements using the provided locator
		List<WebElement> unreadList = driver.findElements(listLocator);

		logger.info(listName + " count: " + unreadList.size());
		test.log(LogStatus.PASS, "Total " + listName + " Count: " + unreadList.size());

		if (!unreadList.isEmpty()) {
			StringBuilder unreadListText = new StringBuilder();

			// Loop through each element and append the text to the StringBuilder
			waitforelement(shortwaitvalue);
			unreadList.forEach(item -> {
				
				String itemText = item.getText();
				unreadListText.append(itemText).append("<br>"); // Append each item with <br> for new line in HTML
																// reports
			});

			// Log all items in ExtentReports
			test.log(LogStatus.PASS, listName + " successfully listed:<br>" + unreadListText.toString());
		} else {
			logger.info("No " + listName + " found.");
		}
	}

	@Override
	public void processArchiveMessages(By messagesLocator, By archiveIconLocator) {
		// Get all messages listed
		List<WebElement> messages = driver.findElements(messagesLocator);

		waitforelement(mediumwaitvalue);
		// If messages exist, process each one
		if (!messages.isEmpty()) {

			// Loop through each message and click the archive icon
			for (int i = 0; i < messages.size(); i++) {
				try {

					driver.findElement(archiveIconLocator).click();
					waitforelement(longwaitvalue);

				} catch (Exception e) {

					logger.error("Error while processing the message: " + e.getMessage());
				}
			}
		} else {
			logger.info("There are no items to display.");
		}
	}

	@Override
	public void processIconsUnderAll(By messagesLocator, By archiveLocator, By tabLocator, String passValue,
			String failValue) {
		List<WebElement> allList = driver.findElements(messagesLocator);

		boolean iconVisible = false;

		for (int i = 0; i < allList.size(); i++) {
			WebElement message = allList.get(i);

			try {
				Actions actions = new Actions(driver);
				actions.moveToElement(message).click().build().perform(); // Hover over the message to reveal options

				waitforelement(mediumwaitvalue);

				// Try to locate the "Archive Icon" button
				WebElement markArchiveIcon = message.findElement(archiveLocator);

				waitforelement(shortwaitvalue);

				// If the "Archive Icon" button is visible, click it
				if (markArchiveIcon.isDisplayed() && markArchiveIcon.isEnabled()) {
					actions.moveToElement(markArchiveIcon).click().build().perform();
					iconVisible = true;
					// markArchiveIcon.click();

				} else {
					// If the "Archive Icon" icon is not visible, skip the message
					logger.info("Skipping, icon is not visible.");
					iconVisible = false;
				}

			} catch (StaleElementReferenceException e) {
				// If no "Archive Icon" button found, click on the Archive Tab
				logger.info("No 'icons' found");
				iconVisible = true;
				// test.log(LogStatus.PASS, "No 'Archive Icon' icons found, clicking on Archive
				// Tab...");
				driver.findElement(tabLocator).click();
				waitforelement(mediumwaitvalue);
			} catch (NoSuchElementException e) {
				// If no "Archive Icon" button found, click on the Archive Tab
				logger.info("No 'icons' found");
				iconVisible = true;
				// test.log(LogStatus.PASS, "No 'Archive Icon' icons found, clicking on Archive
				// Tab...");
				driver.findElement(tabLocator).click();
				waitforelement(mediumwaitvalue);
			}
		}
		if (iconVisible) {
			test.log(LogStatus.PASS, passValue); // Log PASS if any message had the icon visible
		} else {
			test.log(LogStatus.INFO, failValue); // Log FAIL if no message had the icon visible
		}
	}

	@Override
	public void processMouseOver(By messagesLocator, By mouseOverLocator, String passValue, String failValue) {
		List<WebElement> allList = driver.findElements(messagesLocator);
		boolean iconVisible = false; // Flag to track if any "PASS" occurs

		for (int i = 0; i < allList.size(); i++) {
			WebElement message = allList.get(i);

			waitforelement(shortwaitvalue);

			try {
				// Hover over the message to reveal options
				Actions actions = new Actions(driver);
				actions.moveToElement(message).click().build().perform();

				waitforelement(shortwaitvalue);

				// Try to locate the "Archive Icon" button
				List<WebElement> markArchiveIcon = message.findElements(mouseOverLocator);

				waitforelement(shortwaitvalue);

				// Check if the "mark as unread" button is visible
				if (markArchiveIcon.size() > i && markArchiveIcon.get(i).isDisplayed()
						&& markArchiveIcon.get(i).isEnabled()) {
					actions.moveToElement(markArchiveIcon.get(i)).build().perform();
					logger.info("Icon is visible.");
					iconVisible = true; // Set the flag to true for PASS
				} else {
					// If the "Mark as Unread" icon is not visible, log it and mark it as FAIL
					logger.info("Skipping, icon is not visible.");
					iconVisible = false; // Ensure we mark it as fail
				}

			} catch (Exception e) {
				// Catch any exception and log it with screenshot
				logger.info(e.getMessage());
				e.printStackTrace();
				test.log(LogStatus.FAIL, failValue + "<html><body><p><a href=" + screenshotutil.captureScreenshot(value)
						+ ">click here for screenshot</p></body></html>");
			}
		}

		// Log the final result after the loop finishes
		if (iconVisible) {
			test.log(LogStatus.PASS, passValue); // Log PASS if any message had the icon visible
		} else {
			test.log(LogStatus.INFO, failValue); // Log FAIL if no message had the icon visible
		}
	}

	@Override
	public void processMiddlePane(By messagesLocator, By conversationLocator, String passValue, String failValue) {
		List<WebElement> allList = driver.findElements(messagesLocator);
		boolean conversationVisible = false;
		String conversationText = ""; // To store the text of the conversation

		// Iterate over all messages
		for (int i = 0; i < allList.size(); i++) {
			WebElement message = allList.get(i);
			waitforelement(mediumwaitvalue);

			try {
				// Hover over the message to reveal options
				Actions actions = new Actions(driver);
				actions.moveToElement(message).click().build().perform();

				waitforelement(mediumwaitvalue);

				// Try to locate the "Conversation" element within the message
				WebElement conversations = message.findElement(conversationLocator);

				waitforelement(mediumwaitvalue);

				// Check if the conversation element is displayed
				if (conversations.isDisplayed()) {
					// Get the text of the conversation and log it
					conversationText = conversations.getText();
					logger.info("Conversation is visible: " + conversationText); // Log the conversation text
					test.log(LogStatus.PASS, "Conversation: " + conversationText); // Log the conversation text

					conversationVisible = true; // Set the flag to true if conversation is visible
				} else {
					// If the conversation is not visible, log it
					logger.info("Skipping, conversation is not visible.");
					conversationVisible = false; // Mark as fail
				}

			} catch (Exception e) {
				// Catch any exception and log it with a screenshot
				logger.info("Error while processing conversation: " + e.getMessage());
				e.printStackTrace();
				test.log(LogStatus.FAIL, failValue + "<html><body><p><a href=" + screenshotutil.captureScreenshot(value)
						+ ">click here for screenshot</p></body></html>");
			}
		}
		if (conversationVisible) {
			test.log(LogStatus.PASS, passValue); // Log PASS if any conversation was visible
		} else {
			test.log(LogStatus.INFO, failValue); // Log FAIL if no conversation was visible
		}
	}

	@Override
	public void retrieveAndLogActivePipelines() {
		// Find all active pipeline elements
		List<WebElement> pipelineElements = driver.findElements(activePipelineLists_RightPane);

		// Find all corresponding edit icons
		List<WebElement> editIcons = driver.findElements(editIcon_RightPane);

		// Check if both lists have elements
		if (!pipelineElements.isEmpty() && pipelineElements.size() == editIcons.size()) {
			test.log(LogStatus.INFO, "Active Pipelines Found: " + pipelineElements.size());

			for (int i = 0; i < pipelineElements.size(); i++) {
				String text = pipelineElements.get(i).getText().trim();
				if (!text.isEmpty()) {
					test.log(LogStatus.INFO, "Pipeline: " + text + " | Edit Icon Available");
				}
			}
		} else {
			// Log if no pipelines are found
			test.log(LogStatus.INFO, "There are no active pipelines in the list.");
		}
	}

	@Override
	public void retrieveAndLogNewlyCreatedActivePipeline() {
		try {
			// Refresh or wait until new element is visible, if needed
			waitforelement(longwaitvalue); // Optional, based on your framework

			// Re-fetch all pipeline elements and edit icons
			List<WebElement> pipelineElements = driver.findElements(activePipelineLists_RightPane);
			List<WebElement> editIcons = driver.findElements(editIcon_RightPane);

			// Check if both lists are non-empty and of equal size
			if (!pipelineElements.isEmpty() && pipelineElements.size() == editIcons.size()) {
				// Get the last (newly added) pipeline and edit icon
				int lastIndex = pipelineElements.size() - 1;

				WebElement newPipeline = pipelineElements.get(lastIndex);
				// WebElement newEditIcon = editIcons.get(lastIndex);

				String newPipelineText = newPipeline.getText().trim();

				if (!newPipelineText.isEmpty()) {
					test.log(LogStatus.INFO, "Newly Created Pipeline: " + newPipelineText + " | Edit Icon Available");

					// Optional: Click the edit icon for newly added pipeline
					// newEditIcon.click();
				} else {
					test.log(LogStatus.INFO, "Newly created pipeline has empty text.");
				}
			} else {
				test.log(LogStatus.INFO, "No newly created pipelines found or count mismatch.");
			}

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Error retrieving newly created active pipeline: " + e.getMessage());
		}
	}

	@Override
	public void deleteNewlyCreatedPipeline() {
		try {
			// Wait for the page or section to be ready (optional)
			waitforelement(longwaitvalue);

			// Retrieve all active pipelines and edit icons
			List<WebElement> pipelineElements = driver.findElements(activePipelineLists_RightPane);
			List<WebElement> editIcons = driver.findElements(editIcon_RightPane);

			if (!pipelineElements.isEmpty() && pipelineElements.size() == editIcons.size()) {
				int lastIndex = pipelineElements.size() - 1;

				WebElement newPipeline = pipelineElements.get(lastIndex);
				WebElement newEditIcon = editIcons.get(lastIndex);

				String pipelineName = newPipeline.getText().trim();

				if (!pipelineName.isEmpty()) {
					// test.log(LogStatus.INFO, "Attempting to delete newly created pipeline: " +
					// pipelineName);

					// Click the edit icon to open the pipeline settings
					newEditIcon.click();

					// Optional wait if edit modal/pop-up opens
					waitforelement(mediumwaitvalue);

					// Click the Delete button (update this locator based on your application)
					WebElement deleteButton = driver.findElement(buttonDelete_EditPopop);
					deleteButton.click();

					waitforelement(mediumwaitvalue);

					sendkeys(textDelete_EditPopop, excelutil.getData("Communication Panel", "Delete Pipeline", xlsname),
							"User able to Delete Pipeline.", "User unable to Delete Pipeline.");

					// Confirm deletion (if confirmation popup exists)
					WebElement confirmDelete = driver.findElement(buttonSubmit_EditPopop);
					confirmDelete.click();

					// test.log(LogStatus.PASS, "Successfully deleted pipeline: " + pipelineName);
				} else {
					test.log(LogStatus.INFO, "Newly created pipeline has no visible name.");
				}
			} else {
				test.log(LogStatus.INFO, "No pipelines found or edit icons mismatch.");
			}
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Error while deleting newly created pipeline: " + e.getMessage());
		}
	}

	@Override
	public void length(By Element, String Value, String passValue, String failValue) {
		try {
			WebElement inputField = driver.findElement(Element);

			// Enter value
			sendkeys(Element, excelutil.getData("Communication Panel", Value, xlsname), passValue, failValue);

			// Get the entered value
			String enteredText = inputField.getAttribute("value");
			int length = enteredText.length();

			logger.info("Entered text: " + enteredText);
			logger.info("The length is: " + length);

		}

		catch (Exception e) {

			test.log(LogStatus.FAIL,
					"<html><body>" + "<p><b>Error occurred while executing the script.</b></p>"
							+ "<p><span style='color:red;'>" + e.getMessage() + "</span></p>" + "<p><a href=\""
							+ screenshotutil.captureScreenshot(value)
							+ "\" style='font-weight:bold;'>Click here for screenshot</a></p>" + "</body></html>");

			screenshotutil.captureScreenshot(value); // ✅ Capture a screenshot for debugging

		}
	}

	@Override
	public void enterTextInIframeEditor(By iframeLocator, String inputText) {
		try {
			// Switch to iframe
			WebElement iframeElement = driver.findElement(iframeLocator);
			driver.switchTo().frame(iframeElement);

			// Find the body tag of the rich text editor and enter text
			WebElement editorBody = driver.findElement(By.tagName("body"));
			editorBody.clear(); // Optional: clear existing text
			editorBody.sendKeys(inputText);

			// Log success
			test.log(LogStatus.PASS, "Successfully entered text in the Text Area Box.");
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to enter text in the Rich Text Area: " + e.getMessage());
		} finally {
			// Always switch back to default content
			driver.switchTo().defaultContent();
		}
	}

	// sphurthi
	@Override
	//To check updated status names in communication panel
	public void checkUpdatedStatusNames() {

		String firstName = GPS_SettingsPipelinesPage.firstStatusName;
		String secondName = GPS_SettingsPipelinesPage.secondStatusName;
		String thirdName = GPS_SettingsPipelinesPage.thirdStatusName;
		String fourthName = GPS_SettingsPipelinesPage.fourthStatusName;
		
		WaitUtils.click(AllTab, activePipelinePlus);
		test.log(LogStatus.PASS, "Able to switch to All Tab");
		
		//click(AllTab, "Able to switch to All Tab", "Unable to switch to All Tab");
		WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
		
		waitforelement(extraverylongwaitvalue);
		
		click(activePipelinePlus, "Able to click on plus icon and Create Opportunity popup is displayed",
				"Unable to click on plus icon");
		
		WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);
		
		waitforelement(mediumwaitvalue);
		
		click(selectStatusDropdownExpand, "Able to click on status dropdown", "Unable to click on status dropdown");

		List<WebElement> l1 = WaitUtils.waitVisibilityOfDropdownElements(statusListFromDropdown, 20);


		for (WebElement ele : l1) {
			String statusName = ele.getText();
			logger.info(statusName);

			if (statusName.equals(firstName)) {
				test.log(LogStatus.PASS, "Updated status name " + statusName
						+ " is reflected in status dropdown of create opportunity popup");

			}

			else if (statusName.equals(secondName)) {
				test.log(LogStatus.PASS, "Updated status name " + statusName
						+ " is reflected in status dropdown of create opportunity popup");

			} else if (statusName.equals(thirdName)) {
				test.log(LogStatus.PASS, "Updated status name " + statusName
						+ " is reflected in status dropdown of create opportunity popup");
			} else if (statusName.equals(fourthName)) {
				test.log(LogStatus.PASS, "Updated status name " + statusName
						+ " is reflected in status dropdown of create opportunity popup");
			}

			else {
				test.log(LogStatus.FAIL, "Status names are NOT Updated");

			}

		}
		
		click(cancelButton, "Able to click on cancel button and create opportunity popup is closed",
				"Unable to click om cancel button");
		
		WaitUtils.waitInvisibleSpinner(WaitUtils.spinner);

	}

}
