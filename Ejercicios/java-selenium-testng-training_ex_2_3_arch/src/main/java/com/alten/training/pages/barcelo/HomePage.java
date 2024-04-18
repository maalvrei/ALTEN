package com.alten.training.pages.barcelo;

import com.alten.training.pages.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.Keys;

public class HomePage extends BasePage {

    private static final Logger LOGGER = LogManager.getLogger(com.alten.training.pages.heroku.HomePage.class);
    private static final By DESTINATION_INPUT = By.cssSelector("#destination-fb");
    private static final By COOKIES_BUTTON = By.id("didomi-notice-agree-button");
    private static final By HOTEL_ACCORDION = By.cssSelector(".accordion-content.destination-hotels-JS");
    private static final By HOTEL_SANTS = By.xpath("//ul[@class='c-fastbooking__submenu-search-destination-barcelo-hotel-list-JS']//li[@role='button'][normalize-space()='Barceló Sants']");
    private static final By CALENDAR_1 = By.id("month-1-1");
    private static final By CALENDAR_2 = By.id("month-2-1");
    private static final By PERSONS_NUMBER = By.id("rooms-fb");
    private static final By ADULTS_NUMBER = By.xpath("//input[@name='adults']");
    private static final By CHILDS_NUMBER = By.xpath("//input[@name='childs']");

    public boolean isAValidDay (WebElement day) {
        return !day.getAttribute("class").contains("invalid");
    }

    public boolean isTheLastDay (WebElement day, List<WebElement> month) {
        return month.indexOf(day) == month.size()-1;
    }

    public WebElement[] thereAreFiveDaysAvailable(WebElement day, List<WebElement> month1, List<WebElement> month2) {
        List<WebElement> monthSimplified = month1.subList(month1.indexOf(day),month1.size());
        int validDaysFound = 0;
        int j = 0;
        for (int i = 1 ; i<5 ; i++) {
            WebElement currentDay = monthSimplified.get(j);
            if (isTheLastDay(currentDay,monthSimplified)) {
                monthSimplified = month2;
                j=0;
            }
            if (isAValidDay(currentDay)) {
                j++;
                validDaysFound++;
            }
        }
        return validDaysFound == 4 ? new WebElement[]{day, monthSimplified.get(j)} : null;
    }

    public boolean monthWithValidDays (List<WebElement> days) {
        for (WebElement day : days)
            if (isAValidDay(day))
                return true;
        return false;
    }

    public WebElement[] initialAndFinalDay() {
        WebElement calendar1 = getDriver().findElement(CALENDAR_1);
        WebElement calendar2 = getDriver().findElement(CALENDAR_2);
        By tableBody = By.tagName("tbody");
        By tableCell = By.tagName("td");
        List<WebElement> month1 = monthWithoutEmptyDays(calendar1.findElement(tableBody).findElements(tableCell));
        List<WebElement> month2 = monthWithoutEmptyDays(calendar2.findElement(tableBody).findElements(tableCell));
        WebElement[] initialAndFinalDay = new WebElement[2];
        List<WebElement> currentMonth = monthWithValidDays(month1) ? month1 : month2;
        for (WebElement day : currentMonth) {
            if (isAValidDay(day)) {
                initialAndFinalDay = thereAreFiveDaysAvailable(day,currentMonth,month2);
                if (initialAndFinalDay!=null) break;
            }
        }
        return initialAndFinalDay;
    }

    public List<WebElement> monthWithoutEmptyDays(List<WebElement> month) {
        return month.stream().filter(d-> !d.getText().isEmpty()).toList();
    }

    public void enterName() {
        this.waitToElementBeClickable(COOKIES_BUTTON);
        WebElement cookies = getDriver().findElement(COOKIES_BUTTON);
        cookies.click();
        this.waitToElementBeClickable(DESTINATION_INPUT);
        WebElement destinationInput = getDriver().findElement(DESTINATION_INPUT);
        destinationInput.click();
        destinationInput.sendKeys("Barcelona");
        this.waitToElementBeClickable(HOTEL_ACCORDION);
        this.waitToElementBeClickable(HOTEL_SANTS);
        WebElement hotel = getDriver().findElement(HOTEL_SANTS);
        hotel.click();
        waitPresenceOfElement(CALENDAR_1);
        waitToElementBeClickable(CALENDAR_1);
        wait.until(ExpectedConditions.elementToBeClickable(initialAndFinalDay()[0]));
        initialAndFinalDay()[0].click();
        wait.until(ExpectedConditions.elementToBeClickable(initialAndFinalDay()[1]));
        initialAndFinalDay()[1].click();
        WebElement num = getDriver().findElement(PERSONS_NUMBER);
        num.click();
        WebElement adultsInput = getDriver().findElement(ADULTS_NUMBER);
        adultsInput.clear();
        adultsInput.sendKeys("2");
        WebElement childsInput = getDriver().findElement(CHILDS_NUMBER);
        childsInput.clear();
        childsInput.sendKeys("1");
        childsInput.sendKeys(Keys.ENTER);
    }

}
