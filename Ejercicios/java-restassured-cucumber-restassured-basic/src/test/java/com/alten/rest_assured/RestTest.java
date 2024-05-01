package com.alten.rest_assured;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.Before;
import org.junit.Test;

//https://petstore.swagger.io/#/


public class RestTest {

	@Before
	public void before(){
		RestAssured.baseURI = "https://reqres.in/api/";
		RestAssured.useRelaxedHTTPSValidation();
		//RestAssured.proxy("localhost");
		//RestAssured.proxy(8888);
	}
	
	@Test
	public void getTest(){
		given().log().all().param("page", 2)
			.get("/users")//?page=2
		.then().log().ifError()
			.statusCode(200)
			.body("total_pages", equalTo(2))
			.and()
			.body("data.id",hasItems(7,8,9,10,11,12) )
			;
	}
	
	
	
	
	@Test
	public void postTest(){
		given()
			
			.accept(ContentType.JSON)
			. body("{\"name\":\"juan\", \"job\":\"developer\"}")
			.post("/users")
		.then().log().ifValidationFails()
			.statusCode(201)
			//.and()
			//.body("name",equalTo("juan"))
			//.and()
			//.body("job",equalTo("leader"))
			//.and()
			.body("$", hasKey("id"))
			.and()
			.body("$", hasKey("createdAt"))
		;
	}
	
	
	@Test
	public void putTest(){
		
		JsonPath jsonPath  = given().log().all()
			.accept(ContentType.JSON)
			. body("{\"name\":\"juan2\", \"job\":\"leader\"}")
			.post("/users")
		.then()
			.extract().jsonPath();
		;
		
		String idCreated = ( jsonPath.get("id").toString() );
		
						
		 given()
		.log().all()
			.accept(ContentType.JSON)
			.contentType(ContentType.JSON)
			. body("{ \"job\" : \"TESTER\" }")
			.put("/users/"+idCreated)
		.then().log().all()
			.statusCode(200)
			.and()
			.body("$", hasKey("updatedAt"))
			.body("job", equalTo("TESTER"))
			;
		
		
	}
	
	@Test
	public void delete(){
		
		JsonPath jsonPath  = given().log().all()
				.accept(ContentType.JSON)
				. body("{\"name\":\"juan2\", \"job\":\"leader\"}")
				.post("/users")
			.then()
				.extract().jsonPath();
			;
			
			String idCreated = ( jsonPath.get("id").toString() );
		
		
		given().log().all()
			.accept(ContentType.JSON)
			.param("id", idCreated)
			.delete("/users")
		.then().log().ifValidationFails()
			.statusCode(204);
	}
	
	
	
	
	
}
