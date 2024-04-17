package com.alten.training.pages.heroku;

import com.alten.training.pages.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class DynamicContentPage extends BasePage {

    private static final Logger LOGGER = LogManager.getLogger(HomePage.class);

    public boolean verifyThereAre3Logos () {
        return getDriver().findElements(By.cssSelector("div.large-2.columns")).size()==3;
    }

    public boolean verifyCorrectLogoIsHere () {
        if (verifyThereAre3Logos()) {
            String logoWeAreLookingFor = "/img/avatars/Original-Facebook-Geek-Profile-Avatar-6.jpg";
            boolean logoIsHere = false;
            for (WebElement image : getDriver().findElements(By.tagName("img"))) {
                LOGGER.info(image.getAttribute("src"));
                if (image.getAttribute("src").contains(logoWeAreLookingFor))
                    logoIsHere = true;
            }
            return logoIsHere;
        } else {
            return false;
        }
    }

}

