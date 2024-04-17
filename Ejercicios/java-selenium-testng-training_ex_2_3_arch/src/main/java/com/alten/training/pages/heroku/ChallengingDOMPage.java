package com.alten.training.pages.heroku;

import com.alten.training.pages.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ChallengingDOMPage extends BasePage {

    private static final Logger LOGGER = LogManager.getLogger(HomePage.class);
    private final List<WebElement> buttons = getDriver().findElements(By.cssSelector("[id$=\"4e3f84f3dd0b\"]"));
    private static final By TABLE = By.tagName("table");

    public WebElement checkIfFooButtonExists () {
        for (WebElement button : buttons) if (button.getText().equals("foo")) return button;
        return null;
    }

    public WebElement checkIfBazOrBarButtonExists () {
        for (WebElement button : buttons) if (button.getText().equals("bar") || button.getText().equals("baz")) return button;
        return null;
    }

    public boolean clickButtonAndReadTable () {
        if (checkIfFooButtonExists()!=null) {
            LOGGER.info("Click en el boton foo");
            checkIfFooButtonExists().click();
            WebElement table = getDriver().findElement(TABLE);
            table.findElements(By.tagName("th")).forEach(h -> LOGGER.info(h.getText()));
            table.findElements(By.tagName("td")).forEach(d -> LOGGER.info(d.getText()));
            return true;
        } else if (checkIfBazOrBarButtonExists()!=null) {
            LOGGER.info("Click en el boton baz / bar");
            checkIfBazOrBarButtonExists().click();
            WebElement table = getDriver().findElement(TABLE);
            table.findElements(By.tagName("th")).forEach(h -> LOGGER.info(h.getText()));
            table.findElements(By.tagName("td")).forEach(d -> LOGGER.info(d.getText()));
            return true;
        }
        return false;
    }

}
