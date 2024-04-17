package com.alten.training.pages.heroku;

import com.alten.training.pages.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public class CheckboxPage extends BasePage {

    private static final Logger LOGGER = LogManager.getLogger(HomePage.class);
    private static final By CHECKBOX_1 = By.cssSelector("input:nth-child(1)");
    private static final By CHECKBOX_2 = By.xpath("//input[2]");

    public boolean verifyCheckBox1IsChecked () {
        LOGGER.info("verifyCheckBox1IsChecked");
        this.waitToElementBeClickable(CHECKBOX_1);
        WebElement checkbox1 = getDriver().findElement(CHECKBOX_1);
        if (!checkbox1.isSelected()) checkbox1.click();
        return checkbox1.getAttribute("checked")==(null);
    }

    public boolean verifyCheckBox2IsChecked () {
        LOGGER.info("verifyCheckBox2IsChecked");
        this.waitToElementBeClickable(CHECKBOX_2);
        WebElement element = getDriver().findElement(CHECKBOX_2);
        if (!element.isSelected()) element.click();
        return element.getAttribute("checked")==(null);
    }

    public boolean verifyCheckBoxAreBothChecked () {
        LOGGER.info("verifyCheckBoxAreBothChecked");
        return !verifyCheckBox1IsChecked() && !verifyCheckBox2IsChecked();
    }

}
