package com.training.imdb;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

public class IMDBLoginTest {

	WebDriver browser;
	static String url; 
	static String email;
	static String pass;
	static String username;

	@BeforeAll
	static void setupClass() throws Exception {
		
		String local = System.getenv("EXEC_TYPE");
		
		if( local.equals( "local")) {
			System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver.exe");
		}else if(local.equals( "auto")) {
			WebDriverManager.chromedriver().setup();
		}else {
			throw new Exception("No se reconoce '"+local+"', debe de ser 'auto' o 'local'");
		}
		
		Properties prop = new Properties();
		FileInputStream fichero = new FileInputStream("src\\test\\resources\\datos.properties");
		prop.load(fichero);
		
		url = prop.getProperty("url");
		email = prop.getProperty("email");
		pass = System.getenv("IMDB_PASS");
		username = prop.getProperty("username");

	}

	@BeforeEach
	void setupTest() {
		browser = new ChromeDriver();
	}

	@AfterEach
	void teardown() {
		browser.quit();
	}

	@Test
	void TestLogin() {
		browser.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(browser, 30);
		browser.manage().window().maximize();
		browser.get(url);
		WebElement botonInicioSesion = browser.findElement(By.xpath("//a[@class='ipc-btn ipc-btn--single-padding ipc-btn--center-align-content ipc-btn--default-height ipc-btn--core-baseAlt ipc-btn--theme-baseAlt ipc-btn--on-textPrimary ipc-text-button imdb-header__signin-text']//span[@class='ipc-btn__text'][normalize-space()='Iniciar sesión']"));
		wait.until(ExpectedConditions.visibilityOf(botonInicioSesion));
		botonInicioSesion.click();
		WebElement botonISImdb = browser.findElement(By.xpath("//span[normalize-space()='Sign in with IMDb']"));
		wait.until(ExpectedConditions.visibilityOf(botonISImdb));
		botonISImdb.click();
		WebElement emailElement = browser.findElement(By.id("ap_email"));
		wait.until(ExpectedConditions.visibilityOf(emailElement));
		emailElement.sendKeys(email);
		WebElement passwordElement = browser.findElement(By.id("ap_password"));
		passwordElement.sendKeys(pass);
		WebElement botonSignIn = browser.findElement(By.id("signInSubmit"));
		botonSignIn.click();
		WebElement sesionIniciada = browser.findElement(By.xpath("//span[@class='imdb-header__account-toggle--logged-in imdb-header__accountmenu-toggle navbar__user-name navbar__user-menu-toggle__name navbar__user-menu-toggle--desktop']"));
		wait.until(ExpectedConditions.visibilityOf(sesionIniciada));
		assertTrue(sesionIniciada.getText().contains(username));

	}

}
