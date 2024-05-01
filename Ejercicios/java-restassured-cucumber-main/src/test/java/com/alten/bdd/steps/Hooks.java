package com.alten.bdd.steps;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.alten.bdd.utils.RestUtils;
import com.cucumber.listener.Reporter;
//import com.google.common.io.Files;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.restassured.RestAssured;
import io.restassured.response.Response;


public class Hooks {

	private static final Logger LOGGER = LogManager.getLogger(Hooks.class);
	private static Response lastResponse = null;

	@Before
    public void setup(Scenario scenario) throws Exception{
		LOGGER.info("setup");
        RestUtils.setup();
    } 
	
    
    @After
    public void tearDown(Scenario scenario) throws IOException {
    	    		
		LOGGER.info("tearDown");
		LOGGER.info( Hooks.lastResponse.asString());
		Reporter.addStepLog("headers="+Hooks.lastResponse.getHeaders());
		Reporter.addStepLog("status code="+Hooks.lastResponse.getStatusCode());
		Reporter.addStepLog("body="+Hooks.lastResponse.asPrettyString());

    }

	public static void setLastResponse(Response lastResponse) {
		Hooks.lastResponse = lastResponse;
	}


}
