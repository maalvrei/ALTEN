package com.alten.bdd.steps;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

import com.alten.bdd.utils.Pet;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.http.ContentType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import io.restassured.response.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class PetStoreSteps {

	
	private static final Logger LOGGER = LogManager.getLogger(PetStoreSteps.class);
	
	private static String PET_GET = "/pet/";
	private static String PET_POST = "/pet";
	
	
	private int currentIDPet = 0;
	private Response currentResponse = null;
	private Pet currentPet = null;

	//GET
	@Given("^An Pet with ID equals to (\\d+)$")
	public void an_Pet_with_ID_equals_to(int id) throws Throwable {
	   currentIDPet = id;
	}

	@When("^I send a Get Request$")
	public void i_send_a_Get_Request() throws Throwable {
	    LOGGER.info("send a GET Request");
		Response response = given().get(PET_GET+this.getCurrentIDPet());
		LOGGER.info("statusCode="+response.getStatusCode());
		setCurrentResponse(response);

		
	}

	@Then("^the response return the status code (\\d+)$")
	public void the_response_return_the_status_code(int status) throws Throwable {
		LOGGER.info("the_response_return_the_status_code="+status);
		assertEquals(status, this.getCurrentResponse().getStatusCode());
	}

	//POST
	@Given("^Add a pet with name (.*) and (.*)$")
	public void add_a_pet_whit_name(String name, String tags) throws Throwable {
		HashMap<String, String> tags_map = new HashMap<>();
		tags_map.put("name",tags);
		List<HashMap<String,String>> tags_list = new ArrayList<>();
		tags_list.add(tags_map);
		Pet pet = new Pet(name, tags_list);
		setCurrentPet(pet);
	}

	@When("^I send a POST Request$")
	public void add_a_pet_with_name_and_tags() throws Throwable {
		LOGGER.info("send a POST Request");
		Response response = given()
				.contentType(ContentType.JSON)
				.body(getCurrentPet())
				.post(PET_POST);
		setCurrentResponse(response);
	}

	@And("^Verify with a Get Request to data is correct$")
	public void verify_data_is_correct() {
		LOGGER.info("Verifing data is correct");
		Response response = given().get(PET_GET+getCurrentResponse().as(Pet.class).getId());
		Pet petFromGet = response.as(Pet.class);
        assertEquals(getCurrentPet().getName(), petFromGet.getName());
		assertEquals(getCurrentPet().getTags().get(0).get("name"),petFromGet.getTags().get(0).get("name"));
	}


	//currentResponse

	public int getCurrentIDPet() {
		return currentIDPet;
	}

	public void setCurrentIDPet(int currentIDPet) {
		this.currentIDPet = currentIDPet;
	}

	public Response getCurrentResponse() {
		return currentResponse;
	}

	public void setCurrentResponse(Response currentResponse) {
		this.currentResponse = currentResponse;
		Hooks.setLastResponse(currentResponse);
	}

	public Pet getCurrentPet() {
		return currentPet;
	}

	public void setCurrentPet(Pet currentPet) {
		this.currentPet = currentPet;
	}
}
