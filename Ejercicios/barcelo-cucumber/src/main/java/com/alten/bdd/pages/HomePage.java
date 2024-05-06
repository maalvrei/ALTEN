package com.alten.bdd.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;

public class HomePage extends BasePage {

	private static final Logger LOGGER = LogManager.getLogger(HomePage.class);
	private static final By DESTINATION_INPUT = By.id("destination-fb");
	private static final By COOKIES_BUTTON = By.id("didomi-notice-agree-button");
	private static final By HOTEL_SANTS = By.cssSelector(".px-2.mod--hover-blue.hidden-facet-JS[data-percentage-match='-8.333333333333334'][data-parent-element='/content/dam/bhg/data/es-es/destinations/province/barcelona'][data-current-element='/content/dam/bhg/data/es-es/hotels/barcelo-sants/barcelo-sants']");
	private static final By CALENDAR_1 = By.id("month-1-1");
	private static final By CALENDAR_2 = By.id("month-2-1");
	private static final By PERSONS_NUMBER = By.id("rooms-fb");
	private static final By ADULTS_NUMBER = By.name("adults");
	private static final By CHILDS_NUMBER = By.name("childs");
	private static final By CHILDS_AGE = By.name("child-age");
	private static final By SEARCH_BUTTON = By.xpath("(//button[@id='fastbooking_cta_booking_home'])[1]");

	public boolean isAValidDay (WebElement day) {
		LOGGER.info("Comprobando si el día " + day.getText() + " es un día válido.");
		return !day.getAttribute("class").contains("invalid");
	}

	public WebElement[] days () {
		List<WebElement> month1 = monthWithoutEmptyDays(CALENDAR_1);
		List<WebElement> month2 = monthWithoutEmptyDays(CALENDAR_2);
		List<WebElement> days = new ArrayList<>();
		days.addAll(month1);
		days.addAll(month2);
		int today = LocalDate.now().getDayOfMonth();
		WebElement initialDay = isAValidDay(days.get(today+4)) ? days.get(today+4) : null;
		int positionOfInitialDay = days.indexOf(initialDay);
		WebElement finalDay = null;
		for (int i = positionOfInitialDay ; i < positionOfInitialDay+5 ; i++) {
			if (isAValidDay(days.get(i)))
				finalDay = days.get(i);
		}
        return new WebElement[]{initialDay, finalDay};
	}

	public List<WebElement> monthWithoutEmptyDays(By calendar) {
		WebElement month = getDriver().findElement(calendar);
		By tableBody = By.tagName("tbody");
		By tableCell = By.tagName("td");
		List<WebElement> days = month.findElement(tableBody).findElements(tableCell);
		LOGGER.info("Eliminando días vacíos del mes. Se corresponden con las celdas vacías.");
		return days.stream()
				.filter(d -> !d.getText().isEmpty())
				.collect(Collectors.toList());
	}

	public void acceptCookies() {
		LOGGER.info("Aceptado cookies");
		waitPresenceOfElement(COOKIES_BUTTON);
		waitToElementBeClickable(COOKIES_BUTTON);
		WebElement cookies = getDriver().findElement(COOKIES_BUTTON);
		cookies.click();
		LOGGER.info("Cookies aceptadas");
	}

	public void selectHotel() {
		LOGGER.info("Seleccionando hotel...");
		waitToElementBeClickable(DESTINATION_INPUT);
		WebElement destinationInput = getDriver().findElement(DESTINATION_INPUT);
		destinationInput.click();
		destinationInput.sendKeys("Barcelona");
		WebElement hotel = getDriver().findElement(HOTEL_SANTS);
		hotel.click();
		LOGGER.info("Hotel selecionado: " + hotel.getText());
	}

	public void selectDays () {
		LOGGER.info("Clickando los días...");
		waitToElementBeClickable(CALENDAR_1);
		for (WebElement day : days()) {
			wait.until(ExpectedConditions.elementToBeClickable(day));
			day.click();
			LOGGER.info("Día seleccionado: " + day.getText());
		}
	}

	public void selectPersons() {
		LOGGER.info("Seleccionando huéspedes.");
		WebElement num = getDriver().findElement(PERSONS_NUMBER);
		num.click();
		waitPresenceOfElement(ADULTS_NUMBER);
		waitToElementBeClickable(ADULTS_NUMBER);
		WebElement adultsInput = getDriver().findElement(ADULTS_NUMBER);
		adultsInput.clear();
		adultsInput.sendKeys("2");
		WebElement childsInput = getDriver().findElement(CHILDS_NUMBER);
		childsInput.clear();
		childsInput.sendKeys("1");
		childsInput.sendKeys(Keys.ENTER);
		wait.until(ExpectedConditions.visibilityOfElementLocated(CHILDS_AGE));
		WebElement childsAge = getDriver().findElement(CHILDS_AGE);
		childsAge.click();
		Select selectAge = new Select(childsAge);
		selectAge.selectByValue("11");
		LOGGER.info("Se han añadido dos adultos y un niño de once años.");
	}

	public void search () {
		LOGGER.info("Datos introducidos a la espera de ser enviados.");
		waitToElementBeClickable(SEARCH_BUTTON);
		WebElement searchButton = getDriver().findElement(SEARCH_BUTTON);
		searchButton.click();
	}

}