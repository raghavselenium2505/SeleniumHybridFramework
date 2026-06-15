package com.seleapi.pages.base;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import com.seleapi.actions.ElementActions;

public class BasePage {

    public static Logger log =
            Logger.getLogger(BasePage.class);

    public void click(By locator) {

        ElementActions.click(locator);
    }

    public void type(By locator,
                     String value) {

        ElementActions.type(locator, value);
    }

    public String getText(By locator) {

        return ElementActions.getText(locator);
    }

    public boolean isDisplayed(By locator) {

        return ElementActions.isDisplayed(locator);
    }

    public void jsClick(By locator) {

        ElementActions.jsClick(locator);
    }

    public void hover(By locator) {

        ElementActions.hover(locator);
    }

    public void scrollIntoView(By locator) {

        ElementActions.scrollIntoView(locator);
    }
}