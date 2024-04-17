package com.alten.training.pages.heroku;

import com.alten.training.pages.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;


public class HorizontalSliderPage extends BasePage {

    private static final Logger LOGGER = LogManager.getLogger(HomePage.class);
    private static final By SLIDER = By.tagName("input");
    private static final By SLIDER_VALUE = By.cssSelector("#range");

    public String changeRangeValue () {
        LOGGER.info("Changing range value");
        this.waitToElementBeClickable(SLIDER);
        WebElement slider = getDriver().findElement(SLIDER);
        slider.click();
        WebElement sliderValue = getDriver().findElement(SLIDER_VALUE);
        while (!sliderValue.getText().equals("4.5"))
            slider.sendKeys(Keys.ARROW_RIGHT);
        return sliderValue.getText();
    }
}
