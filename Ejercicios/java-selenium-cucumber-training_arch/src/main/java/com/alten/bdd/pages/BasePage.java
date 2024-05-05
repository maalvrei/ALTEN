package com.alten.bdd.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

	private static final long DEFAULT_TIME = 10;
	private WebDriver driver;
	protected static WebDriverWait wait;

	public BasePage() {
		driver = WebDriverFactory.getDriver();
		wait = new WebDriverWait(driver, DEFAULT_TIME);
	}

	protected final WebDriver getDriver() {
		return driver;
	}

	protected void waitToElementBeClickable(By by) {
		wait.until(ExpectedConditions.elementToBeClickable(by));
	}

	protected void waitPresenceOfElement(By by) {
		wait.until(ExpectedConditions.presenceOfElementLocated(by));
	}

}
