package com.alten.bdd.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
		  features = "classpath:cucumber/pet_store.feature" ,
		  glue = "com.alten.bdd.steps",
		monochrome = true,
format = "json:target/cucumber-json-report.json",
plugin={"com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/report.html"}
)
public class RunPetStoreTest {
}
