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


public class PetStoreSteps {

	
	private static final Logger LOGGER = LogManager.getLogger(PetStoreSteps.class);
	
	private static final String PET_GET = "/pet/";
	private static final String PET_POST = "/pet";
	private static final String PET_PUT = "/pet";
	private static final String PET_DELETE = "/pet/";
	
	
	private long currentIDPet = 0;
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
		// Creo el objeto pet con los datos de cada example, el constructor de la clase crea la lista de diccionarios
		// que son los tags
		Pet pet = new Pet(name, tags);
		// Para saber con qué pet estoy en cada momento creé el atributo currentPet, aquí lo seteo para poder compararlo
		// y acceder después a él para hacer el post
		setCurrentPet(pet);
	}

	@When("^I send a POST Request$")
	public void add_a_pet_with_name_and_tags() throws Throwable {
		LOGGER.info("send a POST Request");
		Response response = given()
				.contentType(ContentType.JSON)
				.body(getCurrentPet())
				.post(PET_POST);
		LOGGER.info("statusCode="+response.getStatusCode());
		setCurrentResponse(response);
		// Aquí vuelvo a setear el currentPet con el response de la petición post. El motivo es que
		// la API crea un ID para cada tag, y como yo no lo estoy manejando, lo que hago es coger el
		// objeto pet del response, que ya tiene el ID generado automáticamente, así evito líos con los ID del tag
		setCurrentPet(response.as(Pet.class));
		setCurrentIDPet(currentPet.getId());
	}

	@And("^Verify with a Get Request to data is correct$")
	public void verify_data_is_correct() {
		LOGGER.info("Verifying data is correct");
		//Hago una peticion con el ID del currentPet para comprobar que el nuevo Pet
		//se creó correctamente y a lo que recibo lo llamo petFromGet y lo mapeo como un Pet, para compararlo al Pet
		//que ya tenía y con el que hice set en el POST
		Response response = given().get(PET_GET+getCurrentIDPet());
		Pet petFromGet = response.as(Pet.class);
        assertEquals(getCurrentPet(),petFromGet);
	}

	//PUT

	@When("^I Modify the pet name with (.*) and remove the tags$")
	public void modify_name_and_remove_tags(String updateName) {
		LOGGER.info("Modifying name and remove tags");
		getCurrentPet().setName(updateName);
		getCurrentPet().removeTags();
	}

	@When("^I send a PUT Request$")
	public void send_put_request() {
		LOGGER.info("Sending put Request");
		Response response = given()
				.contentType(ContentType.JSON)
				.body(getCurrentPet())
				.put(PET_PUT);
		LOGGER.info("statusCode="+response.getStatusCode());
		setCurrentResponse(response);
	}

	//DELETE

	@When("^I send a DELETE Request$")
	public void send_delete_request() {
		LOGGER.info("Sending delete Request");
		Response response = given()
				.delete(PET_DELETE+currentIDPet);
		LOGGER.info("statusCode="+response.getStatusCode());
		setCurrentResponse(response);
	}

	//currentResponse

	public long getCurrentIDPet() {
		return currentIDPet;
	}

	public void setCurrentIDPet(long currentIDPet) {
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
