package com.alten.bdd.pages;

import com.alten.bdd.exceptions.NoDayAvailableException;
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
	private static final By HOTEL_SANTS = By.xpath("(//li[@role='button'][normalize-space()='Barceló Sants'])[2]");
	private static final By CALENDAR_1 = By.id("month-1-1");
	private static final By CALENDAR_2 = By.id("month-2-1");
	private static final By PERSONS_NUMBER = By.id("rooms-fb");
	private static final By ADULTS_NUMBER = By.name("adults");
	private static final By CHILDS_NUMBER = By.name("childs");
	private static final By CHILDS_AGE = By.name("child-age");
	private static final By SEARCH_BUTTON = By.xpath("(//button[@id='fastbooking_cta_booking_home'])[1]");

	// este método comprueba si el día es un día válido, es decir, que no sea un día que no se puede seleccionar
	public boolean isAValidDay (WebElement day) {
		return !day.getAttribute("class").contains("invalid");
	}

	// este método comprueba si el día que se está comprobando es el último día de todos, si es así se acaba el bucle
	// y no se comprueban más días
	public boolean isTheLastDay (WebElement day, List<WebElement> days) {
		return days.indexOf(day) == days.size() - 1 ;
	}

	// este método establece cuáles son los días inicial y final
	// lanza una excepción NoDayAvailableException en el caso de que el día 5 días después al día actual
	// no sea un día disponible
	public WebElement[] days () throws NoDayAvailableException {
		// creo una lista única que contiene todos los días de ambos calendarios que se nos muestran
		List<WebElement> days = new ArrayList<>();
		days.addAll(monthWithoutEmptyDays(CALENDAR_1));
		days.addAll(monthWithoutEmptyDays(CALENDAR_2));
		// establezco cuál es el día actual
		int today = LocalDate.now().getDayOfMonth();
		// aquí compruebo si el día 5 días después de la fecha actual es un día válido con operador ternario
		// avanzo sólo 4 posiciones en la lista porque son el número de noches que quiero estar.
		WebElement initialDay = isAValidDay(days.get(today+4)) ? days.get(today+4) : null;
		// si el día resulta ser null porque no es válido, lanzo la excepción
		if (initialDay==null)
			throw new NoDayAvailableException("No se puede hacer una reserva para dentro de 5 días");
		// si no he lanzado la excepción porque el día no era válido, tomo su posición en la lista para calcular las noches
		int positionOfInitialDay = days.indexOf(initialDay);
		// inicializo el día de salida a null
		WebElement finalDay = null;
		for (int i = positionOfInitialDay + 1 ; i < positionOfInitialDay + 5 ; i++) {
			// empiezo a recorrer en el día de la posición siguiente a la del día actual
			WebElement currentDay = days.get(i);
			// el día de salida se irá actualizando de esta manera siempre al último día válido encontrado en los 4 días siguientes
			// al día de entrada
			if (isAValidDay(currentDay)) {
				finalDay = currentDay;
				if (isTheLastDay(currentDay,days)) break;
			} else if (!isAValidDay(currentDay)) {
				break;
			}
		}
		// si el anterior bucle no encontró un día de salida mínimo más aparte del día de entrada, lanzo la excepción
		if (finalDay==null)
			throw new NoDayAvailableException("No se ha podido realizar una reserva con las condiciones requeridas.");
		// si no se lanza ninguna excepción se devuelve un array con dos posiciones, cada una es un día, el de entrada y el de salida
        return new WebElement[]{initialDay, finalDay};
	}

	// este método vacía las listas de los meses de celdas vacías, que son aquellas que no tienen ningún día, sea válido o no.
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

	// este método clickea los días que devuelve el método que establece qué días entramos y salimos
	public void selectDays () throws NoDayAvailableException {
		LOGGER.info("Clickando los días...");
		waitToElementBeClickable(CALENDAR_1);
		WebElement initialDay = days()[0];
		WebElement finalDay = days()[1];
		LOGGER.info(("Día de entrada: " + initialDay.getText()));
		wait.until(ExpectedConditions.elementToBeClickable(initialDay));
		initialDay.click();
		LOGGER.info(("Día de salida: " + finalDay.getText()));
		wait.until(ExpectedConditions.elementToBeClickable(finalDay));
		finalDay.click();
	}

	// se añaden los adultos y el niño y se selecciona la edad de este ultimo
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

	// cuando se introducen los datos, se pulsa el botón buscar
	public void search () {
		LOGGER.info("Datos introducidos a la espera de ser enviados.");
		WebElement searchButton = getDriver().findElement(SEARCH_BUTTON);
		searchButton.click();
	}

}