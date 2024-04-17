package com.alten.training.pages.heroku;

import com.alten.training.pages.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MultipleWindowsPage extends BasePage {

    private static final Logger LOGGER = LogManager.getLogger(HomePage.class);
    private static final By LINK = By.cssSelector("a[href='/windows/new']");

    public boolean navigateToNewWindow() {
        LOGGER.info("Navegando a la nueva ventana");
        wait.until(ExpectedConditions.elementToBeClickable(LINK));
        WebElement link = getDriver().findElement(LINK);
        getDriver().navigate().to(link.getAttribute("href"));
        String text = getDriver().findElement(By.tagName("h3")).getText();
        return text.equals("New Window");
    }

}
