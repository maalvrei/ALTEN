package com.alten.training.pages.heroku;

import com.alten.training.pages.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class DisappearingElementsPage extends BasePage {

    private static final Logger LOGGER = LogManager.getLogger(HomePage.class);
    private static final By LINKS = By.cssSelector("ul > li > a");

    public void navigateInFiveElements() {
        LOGGER.info("Buscando los enlaces");
        List<WebElement> links = getDriver().findElements(LINKS);
        for (WebElement link : links) {
            link.click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h1")));
            getDriver().navigate().back();
            wait.until(ExpectedConditions.visibilityOfElementLocated(LINKS));
        }
    }

}
