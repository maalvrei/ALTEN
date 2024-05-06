package com.alten.bdd.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ConfirmationPage extends BasePage {

    private static final By RESERVATION_BUTTON = By.xpath("//button[@id='hotelinfo_cta_booking']//span[@class='c-cta'][normalize-space()='Reservar']");

    public void confirmReservation () {
        waitToElementBeClickable(RESERVATION_BUTTON);
        WebElement reservationButton = getDriver().findElement(RESERVATION_BUTTON);
        reservationButton.click();
    }

}
