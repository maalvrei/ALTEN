package com.alten.training.pages.heroku;

import com.alten.training.pages.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.Alert;

public class JavaScriptAlertsPage extends BasePage {

    private static final Logger LOGGER = LogManager.getLogger(HomePage.class);
    private static final By JSPROMPT_BUTTON = By.cssSelector("button[onclick='jsPrompt()']");
    private static final By RESULT = By.cssSelector("#result");

    public boolean textOnAlert() {
        LOGGER.info("Añadiendo texto al prompt de JS");
        String textToIntroduce = "Multiplicate por cero";
        this.waitToElementBeClickable(JSPROMPT_BUTTON);
        WebElement jsButton = getDriver().findElement(JSPROMPT_BUTTON);
        jsButton.click();
        Alert alert = getDriver().switchTo().alert();
        alert.sendKeys(textToIntroduce);
        alert.accept();
        LOGGER.info("Texto introducido: " + textToIntroduce);
        WebElement result = getDriver().findElement(RESULT);
        LOGGER.info("Texto final: " + result.getText());
        return result.getText().contains(textToIntroduce);
    }

}
