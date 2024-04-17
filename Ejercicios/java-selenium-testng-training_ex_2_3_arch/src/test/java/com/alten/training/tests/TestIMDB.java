package com.alten.training.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.alten.training.pages.imdb.HomePage;
import com.alten.training.pages.imdb.SignInPage;
import com.alten.training.pages.imdb.SignInWithIMDBPage;

public class TestIMDB extends BaseTest{
	
	private static final String MESSAGE_USER_NOT_FOUND 	= "there was a problem";
	private static final Integer WAIT_TO_AVOID_CAPTCHA_ML 	= 2000;
	private static final Logger LOGGER = LogManager.getLogger(TestIMDB.class);

	@Test
	@Parameters({"email","password"})
	public void checkLoginIncorrect(String email, String password) throws InterruptedException {
		LOGGER.info("checkLoginIncorrect "+email+password);
		HomePage homePage = new HomePage();
		homePage.verifyHomePageIsLoaded();
		homePage.navigateToSignInPage();
		SignInPage signInPage = new SignInPage();
		signInPage.verifySignInPageIsLoaded();
		signInPage.navigateToSignInWithIMDBPage();
		SignInWithIMDBPage signInWithIMDBPage = new SignInWithIMDBPage();
		signInWithIMDBPage.verifySignInPageIsLoaded();
		signInWithIMDBPage.typeLogin(email);
		signInWithIMDBPage.typePassword(password);
		Thread.sleep(WAIT_TO_AVOID_CAPTCHA_ML);
		signInWithIMDBPage.submit();
	    String message = signInWithIMDBPage.getErrorMessage().toLowerCase();
        Assert.assertEquals(MESSAGE_USER_NOT_FOUND, message);

	}
		

}
