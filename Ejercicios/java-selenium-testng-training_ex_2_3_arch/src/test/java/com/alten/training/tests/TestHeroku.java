package com.alten.training.tests	;

import com.alten.training.pages.heroku.*;
import org.testng.Assert;
import org.testng.annotations.Test;


public class TestHeroku extends BaseTest{


	@Test
	public void testCheckBox() {
		HomePage homePage = new HomePage();
		homePage.verifyHomePageIsLoaded();
		homePage.navigateToCheckboxPage();
		CheckboxPage checkboxPage = new CheckboxPage();
		Assert.assertTrue(checkboxPage.verifyCheckBoxAreBothChecked());
	}

	@Test
	public void testDynamicContent() {
		HomePage homePage = new HomePage();
		homePage.verifyHomePageIsLoaded();
		homePage.navigateToDynamicContentPage();
		DynamicContentPage dynamicContentPage = new DynamicContentPage();
        Assert.assertTrue(dynamicContentPage.verifyCorrectLogoIsHere());
	}

	@Test
	public void testHorizontalSliderPage() {
		HomePage homePage = new HomePage();
		homePage.verifyHomePageIsLoaded();
		homePage.navigateToHorizontalSliderPage();
		HorizontalSliderPage horizontalSliderPage = new HorizontalSliderPage();
		Assert.assertEquals(horizontalSliderPage.changeRangeValue(),"4.5");
	}

	@Test
	public void testJavaScriptAlertsPage() {
		HomePage homePage = new HomePage();
		homePage.verifyHomePageIsLoaded();
		homePage.navigateToJavaScriptAlertsPage();
		JavaScriptAlertsPage javaScriptAlertsPage = new JavaScriptAlertsPage();
		Assert.assertTrue(javaScriptAlertsPage.textOnAlert());
	}

	@Test
	public void testDynamicControlsPage() {
		HomePage homePage = new HomePage();
		homePage.verifyHomePageIsLoaded();
		homePage.navigateToDynamicControlsPage();
		DynamicControlsPage dynamicControlsPage = new DynamicControlsPage();
		Assert.assertFalse(dynamicControlsPage.checkAndPress());
	}

	@Test
	public void testDisappearingElementsPage() {
		HomePage homePage = new HomePage();
		homePage.navigateToDisappearingElementsPage();
		DisappearingElementsPage disappearingElementsPage = new DisappearingElementsPage();
		disappearingElementsPage.navigateInFiveElements();
	}

	@Test
	public void testChallengingDOMPage() {
		HomePage homePage = new HomePage();
		homePage.navigateToChallengingDOMPage();
		ChallengingDOMPage challengingDOMPage = new ChallengingDOMPage();
		Assert.assertTrue(challengingDOMPage.clickButtonAndReadTable());
	}

	@Test
	public void testMultipleWindowsPage() {
		HomePage homePage = new HomePage();
		homePage.navigateToMultipleWindowsPage();
		MultipleWindowsPage multipleWindowsPage = new MultipleWindowsPage();
		Assert.assertTrue(multipleWindowsPage.navigateToNewWindow());
	}

}
