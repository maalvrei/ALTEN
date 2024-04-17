package com.alten.training.pages.heroku;

import com.alten.training.pages.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class DynamicControlsPage extends BasePage {

    private static final Logger LOGGER = LogManager.getLogger(HomePage.class);
    private static final By REMOVE_BUTTON = By.xpath("//button[normalize-space()='Remove']");
    private static final By ADD_BUTTON = By.xpath("//button[normalize-space()='Add']");
    private static final By CHECKBOX = By.cssSelector("#checkbox");

    public boolean checkAndPress() {
        LOGGER.info("Cheking if remove button is shown");
        WebElement removeButton = getDriver().findElement(REMOVE_BUTTON);
        if (removeButton.isDisplayed()) {
            removeButton.click();
            waitToElementBeClickable(ADD_BUTTON);
            WebElement addButton = getDriver().findElement(ADD_BUTTON);
            addButton.click();
            waitToElementBeClickable(CHECKBOX);
            WebElement checkbox = getDriver().findElement(CHECKBOX);
            checkbox.click();
            return checkbox.getAttribute("checked")==null;
        } else {
            waitToElementBeClickable(ADD_BUTTON);
            WebElement addButton = getDriver().findElement(ADD_BUTTON);
            addButton.click();
            waitToElementBeClickable(CHECKBOX);
            WebElement checkbox = getDriver().findElement(CHECKBOX);
            checkbox.click();
            return checkbox.getAttribute("checked")==null;
        }
    }

}
