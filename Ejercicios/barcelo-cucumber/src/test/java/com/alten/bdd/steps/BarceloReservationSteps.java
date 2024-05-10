package com.alten.bdd.steps;

import com.alten.bdd.pages.ConfirmationPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.alten.bdd.pages.HomePage;

public class BarceloReservationSteps {

	
	private static final Logger LOGGER 					= LogManager.getLogger(BarceloReservationSteps.class);
	private HomePage homePage = new HomePage();
	private ConfirmationPage confirmationPage = new ConfirmationPage();

	@Given("^Barcelo page is showed$")
	public void barcelo_page_is_showed() throws Throwable {
		LOGGER.info("Cookies aceptadas");
		homePage.acceptCookies();
	}

	@When("^I select hotel, days and persons$")
	public void i_select_the_hotel() throws Throwable {
		LOGGER.info("Introduciendo datos");
		homePage.selectHotel();
		homePage.selectDays();
		homePage.selectPersons();
	}

	@And("^I do a search with that data$")
	public void i_do_a_search() throws Throwable {
		LOGGER.info("Enviando datos");
		homePage.search();
	}

	@Then("^I go to the reservation page$")
	public void i_go_to_the_reservation_page() {
		LOGGER.info("Moviéndose a la página de reserva");
		confirmationPage.confirmReservation();
	}

}
