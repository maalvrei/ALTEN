package com.alten.bdd.steps;

import com.alten.bdd.pages.ConfirmationPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

import com.alten.bdd.pages.HomePage;

public class BarceloReservationSteps {

	
	private static final Logger LOGGER 					= LogManager.getLogger(BarceloReservationSteps.class);
	private static final Object MESSAGE_USER_NOT_FOUND 	= "there was a problem";
	private HomePage homePage = new HomePage();
	private ConfirmationPage confirmationPage = new ConfirmationPage();

	@Given("^Barcelo page is showed$")
	public void barcelo_page_is_showed() throws Throwable {
		homePage.acceptCookies();
	}

	@When("^I select the hotel$")
	public void i_select_the_hotel() throws Throwable {
		homePage.selectHotel();
	}

	@And("^I select the days$")
	public void imdb_SignIN_page_is_showed() throws Throwable {
		homePage.selectDays();
	}

	@And("^I select the number of persons$")
	public void i_click_on_Sign_In_with_IMDB() throws Throwable {
		homePage.selectPersons();
		homePage.search();
	}

	@Then("^I go to the confirmation page$")
	public void imdb_SignIn_with_IMDB_Page_is_showed() throws Throwable {
		confirmationPage.confirmReservation();
	}

}
