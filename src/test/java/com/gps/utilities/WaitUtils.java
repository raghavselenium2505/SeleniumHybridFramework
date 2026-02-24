package com.gps.utilities;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;
import com.gps.base.TestBase;
import com.web.utilities.AITestAnalyzer;

public class WaitUtils extends TestBase {

    public static By spinner = By.xpath("//div[contains(@class,'spinner')]");

    /* ================= WAITS ================= */

    public static void waitInvisibleSpinner(By ref, int timeInSec) {
        WebDriverWait wait =
                new WebDriverWait(getDriver(), Duration.ofSeconds(timeInSec));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(ref));
    }

    public static List<WebElement> waitVisibilityOfDropdownElements(By ref, int timeInSec) {
        WebDriverWait wait =
                new WebDriverWait(getDriver(), Duration.ofSeconds(timeInSec));
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ref));
    }

    public static void waitInVisibleElement(By ref, int timeInSec) {
        WebDriverWait wait =
                new WebDriverWait(getDriver(), Duration.ofSeconds(timeInSec));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(ref));
    }

    public static WebElement waitClickByRef(By ref, int timeInSec) {
        WebDriverWait wait =
                new WebDriverWait(getDriver(), Duration.ofSeconds(timeInSec));
        return wait.until(ExpectedConditions.elementToBeClickable(ref));
    }

    public static WebElement waitClickWebElement(WebElement ele, int timeInSec) {
        WebDriverWait wait =
                new WebDriverWait(getDriver(), Duration.ofSeconds(timeInSec));
        return wait.until(ExpectedConditions.elementToBeClickable(ele));
    }

    public static WebElement waitVisibilityByRef(By ref, int timeInSec) {
        WebDriverWait wait =
                new WebDriverWait(getDriver(), Duration.ofSeconds(timeInSec));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(ref));
    }

    public static WebElement waitClickByRef(By ref) {
        WebDriverWait wait =
                new WebDriverWait(getDriver(), Duration.ofSeconds(40));
        return wait.until(ExpectedConditions.elementToBeClickable(ref));
    }

    public static void waitInvisibleSpinner(By ref) {
        WebDriverWait wait =
                new WebDriverWait(getDriver(), Duration.ofSeconds(60));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(ref));
    }

    public static WebElement waitVisibilityByRef(By ref) {
        WebDriverWait wait =
                new WebDriverWait(getDriver(), Duration.ofSeconds(40));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(ref));
    }

    public static void waitTextIsPresent(By ref, String textInput) {
        WebDriverWait wait =
                new WebDriverWait(getDriver(), Duration.ofSeconds(20));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(ref, textInput));
    }

    /* ================= SAFE CLICK WITH AI + SCREENSHOT ================= */

    public static WebElement click(By ref1, By ref2) {

        WebElement ele = null;
        WebDriverWait wait =
                new WebDriverWait(getDriver(), Duration.ofSeconds(20));

        try {

            ele = wait.until(ExpectedConditions.elementToBeClickable(ref1));
            ele.click();
            logger.info("Click worked");

        }
        catch (ElementClickInterceptedException | StaleElementReferenceException e) {

            try {
                ele = wait.until(ExpectedConditions.elementToBeClickable(ref1));
                Actions act = new Actions(getDriver());
                act.moveToElement(ele).click().perform();

                wait.until(ExpectedConditions.elementToBeClickable(ref2));
                logger.info("Action click worked");

            }
            catch (Exception e1) {

                try {
                    ele = wait.until(ExpectedConditions.elementToBeClickable(ref1));
                    JavascriptExecutor js =
                            (JavascriptExecutor) getDriver();
                    js.executeScript("arguments[0].click();", ele);

                    logger.info("JS click worked");

                }
                catch (Exception e2) {

                    String aiSuggestion = AITestAnalyzer.analyze(e2);
                    String screenshotPath = screenshotutil.takeScreenshot(getDriver());

                    test.get().log(
                            Status.FAIL,
                            aiSuggestion + "<br>"
                            + "<a href='" + screenshotPath + "' target='_blank'>"
                            + "Click here for screenshot</a>"
                    );

                    logger.error(aiSuggestion);
                }
            }
        }

        return ele;
    }
}