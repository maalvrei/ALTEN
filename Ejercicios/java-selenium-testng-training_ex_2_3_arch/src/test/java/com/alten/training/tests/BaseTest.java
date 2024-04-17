package com.alten.training.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.alten.training.pages.WebDriverFactory;

public class BaseTest {

    private static final Logger LOGGER = LogManager.getLogger(BaseTest.class);

   
    @BeforeClass (description = "Class Level Setup!")
    @Parameters({"browserName","url"})
    public void setup (String browserName, String url) throws Exception {
    	LOGGER.info("septup"+ browserName + ","+url);
		WebDriverFactory.setDriver(browserName, url);
		
    }

    @AfterClass(description = "Class Level Teardown!")
    public void teardown () {
    	LOGGER.info("teardown");
    	WebDriverFactory.closeSetup();
    }
}
