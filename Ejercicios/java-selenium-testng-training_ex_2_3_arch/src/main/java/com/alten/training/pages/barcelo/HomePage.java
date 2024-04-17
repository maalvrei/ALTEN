package com.alten.training.pages.barcelo;

import com.alten.training.pages.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends BasePage {

    private static final Logger LOGGER = LogManager.getLogger(com.alten.training.pages.heroku.HomePage.class);
    private static final By DESTINATION_INPUT = By.cssSelector("#destination-fb");
    private static final By COOKIES_BUTTON = By.id("didomi-notice-agree-button");
    private static final By HOTEL_ACCORDION = By.cssSelector(".accordion-content.destination-hotels-JS");
    private static final By HOTEL_SANTS = By.xpath("(//li[@role='button'][normalize-space()='Barceló Hydra Beach'])[2]");
    private static final By CALENDAR_1 = By.id("month-1-1");
    private static final By CALENDAR_2 = By.id("month-2-1");

    public boolean isAValidDay(WebElement day) {
        return !day.getAttribute("class").contains("invalid");
    }

    public boolean isAnEmptyDay(WebElement day) {
        return day.getText().isEmpty();
    }

    public List<WebElement> initialAndFinalDay() {
        WebElement calendar_1 = getDriver().findElement(CALENDAR_1);
        WebElement calendar_2 = getDriver().findElement(CALENDAR_2);
        List<WebElement> initialAndFinalDay = new ArrayList<>();
        List<WebElement> days = calendar_1.findElement(By.tagName("tbody")).findElements(By.tagName("td"));
        int validDays = 0;
        for (int i = 0 ; i < days.size() ; i++) {
            if (isAValidDay(days.get(i))) {
                for (int j = 1 ; j <= 4 ; j++) {
                    if (isAnEmptyDay(days.get(i+j))) {
                        days = calendar_2.findElement(By.tagName("tbody")).findElements(By.tagName("td"));
                        i = 0;
                        break;
                    }
                    if (isAValidDay(days.get(i+j))) {
                        validDays+=1;
                    } else {
                        validDays = 0;
                    }
                }
            }
            if (validDays==4) {
                initialAndFinalDay.add(days.get(i));
                LOGGER.info("Dia inicial : " + days.get(i).getText());
                initialAndFinalDay.add(days.get(i+4));
                LOGGER.info("Dia final : " + days.get(i+4).getText());
                break;
            }
        }

        return initialAndFinalDay;
    }

    public void enterName() {
        this.waitToElementBeClickable(COOKIES_BUTTON);
        WebElement cookies = getDriver().findElement(COOKIES_BUTTON);
        cookies.click();
        this.waitToElementBeClickable(DESTINATION_INPUT);
        WebElement destination_input = getDriver().findElement(DESTINATION_INPUT);
        destination_input.click();
        destination_input.sendKeys("Grecia");
        this.waitToElementBeClickable(HOTEL_ACCORDION);
        this.waitToElementBeClickable(HOTEL_SANTS);
        WebElement hotel = getDriver().findElement(HOTEL_SANTS);
        hotel.click();
        this.waitToElementBeClickable(CALENDAR_1);
        WebElement calendar_1 = getDriver().findElement(CALENDAR_1);
        List<WebElement> days = calendar_1.findElement(By.tagName("tbody")).findElements(By.tagName("td"));
        initialAndFinalDay();

    }

}
