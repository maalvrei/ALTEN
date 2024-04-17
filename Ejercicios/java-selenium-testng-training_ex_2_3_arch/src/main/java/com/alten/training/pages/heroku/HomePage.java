package com.alten.training.pages.heroku;

import com.alten.training.pages.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage{
	
	private static final Logger LOGGER = LogManager.getLogger(HomePage.class);
	private static final By CHECKBOX = By.cssSelector("a[href='/checkboxes']");
	private static final By DYNAMIC_CONTENT = By.cssSelector("a[href='/dynamic_content']");
	private static final By HORIZONTAL_SLIDER = By.cssSelector("a[href='/horizontal_slider']");
	private static final By JAVASCRIPT_ALERTS = By.cssSelector("a[href='/javascript_alerts']");
	private static final By DYNAMIC_CONTROLS = By.cssSelector("a[href='/dynamic_controls']");
	private static final By DISAPPEARING_ELEMENTS = By.cssSelector("a[href='/disappearing_elements']");
	private static final By CHALLENGING_DOM = By.cssSelector("a[href='/challenging_dom']");
	private static final By MULTIPLE_WINDOWS = By.cssSelector("a[href='/windows']");

	public void verifyHomePageIsLoaded() {
		LOGGER.info("verifyHomePageIsLoaded");
		this.waitToElementBeClickable(CHECKBOX);
		this.waitToElementBeClickable(DYNAMIC_CONTENT);
		this.waitToElementBeClickable(HORIZONTAL_SLIDER);
		this.waitToElementBeClickable(JAVASCRIPT_ALERTS);
		this.waitToElementBeClickable(DYNAMIC_CONTROLS);
		this.waitToElementBeClickable(DISAPPEARING_ELEMENTS);
		this.waitToElementBeClickable(CHALLENGING_DOM);
		this.waitToElementBeClickable(MULTIPLE_WINDOWS);
	}
	
	public void navigateToCheckboxPage() {
		LOGGER.info("navigateToCheckboxPage");
		WebElement element = getDriver().findElement(CHECKBOX);
		element.click();
	}

	public void navigateToDynamicContentPage() {
		LOGGER.info("navigateToDynamicContentPage");
		WebElement element = getDriver().findElement(DYNAMIC_CONTENT);
		element.click();
	}

	public void navigateToHorizontalSliderPage() {
		LOGGER.info("navigateToHorizontalSliderPage");
		WebElement element = getDriver().findElement(HORIZONTAL_SLIDER);
		element.click();
	}

	public void navigateToJavaScriptAlertsPage() {
		LOGGER.info("navigateToJavaScriptAlertsPage");
		WebElement element = getDriver().findElement(JAVASCRIPT_ALERTS);
		element.click();
	}

	public void navigateToDynamicControlsPage() {
		LOGGER.info("navigateToDinamicControlsPage");
		WebElement element = getDriver().findElement(DYNAMIC_CONTROLS);
		element.click();
	}

	public void navigateToDisappearingElementsPage() {
		LOGGER.info("navigateToDisappearingElementsPage");
		WebElement element = getDriver().findElement(DISAPPEARING_ELEMENTS);
		element.click();
	}

	public void navigateToChallengingDOMPage() {
		LOGGER.info("navigateToChallengingDOMPage");
		WebElement element = getDriver().findElement(CHALLENGING_DOM);
		element.click();
	}

	public void navigateToMultipleWindowsPage() {
		LOGGER.info("navigateToMultipleWindowsPage");
		WebElement element = getDriver().findElement(MULTIPLE_WINDOWS);
		element.click();
	}
}
