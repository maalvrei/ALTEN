package com.alten.training.tests;

import com.alten.training.pages.barcelo.HomePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestBarcelo extends BaseTest{

	private static final Logger LOGGER = LogManager.getLogger(TestBarcelo	.class);


	@Test
	@Parameters({"hotelName", "numAdults", "numChild"})
	public void testReservationHotel(String hotelName, int numAdults, int numChild) {
		LOGGER.info("hotelName="+hotelName+",numAdult="+numAdults+",numChild="+numChild);
		HomePage homePage = new HomePage();
		homePage.enterName();
	}

}
