package com.alten.bdd.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
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

	public boolean isTheLastDay (WebElement day, List<WebElement> month) {
		LOGGER.info("Calculando si es el último día del mes o no, para saltar al siguiente mes en consecuencia.");
		return month.indexOf(day) == month.size()-1;
	}

	public WebElement[] thereAreFiveDaysAvailable(WebElement day, List<WebElement> month1, List<WebElement> month2) {
		LOGGER.info("Calculando si hay 5 días disponibles despues del primer día válido del mes...");
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
		LOGGER.info("Validando si es un mes con mínimo un día seleccionable.");
		for (WebElement day : days)
			if (isAValidDay(day)) {
				LOGGER.info("El mes es válido.");
				return true;
			}
		LOGGER.info("El mes no es válido.");
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
		LOGGER.info("Eliminando días vacíos del mes. Se corresponden con las celdas vacías.");
		return month.stream()
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
		for (WebElement day : initialAndFinalDay()) {
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

	public void enterDatesAndSearch() {
		acceptCookies();
		selectHotel();
		selectDays();
		selectPersons();
		search();
	}

}