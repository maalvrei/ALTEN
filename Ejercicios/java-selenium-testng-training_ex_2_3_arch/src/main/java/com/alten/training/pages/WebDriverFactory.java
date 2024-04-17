package com.alten.training.pages;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.google.common.io.Files;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverFactory {
    private static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

	/** Logger class initialization. */
	private static final Logger LOGGER = LogManager.getLogger(WebDriverFactory.class);

	private static final String CHROME = "chrome";
	private static final String EDGE = "edge";
	private static final String FIREFOX = "firefox";

    public static void setDriver(String browserName,String url) throws Exception {
    	 LOGGER.info("@setDriver," + browserName);
            switch (browserName.toLowerCase()) {
                case CHROME:
                    WebDriverManager.chromedriver().setup();
                    driverThreadLocal.set(new ChromeDriver());
                    break;
                case EDGE:
                	WebDriverManager.edgedriver().setup();
                    driverThreadLocal.set(new EdgeDriver());
                    break;
                case FIREFOX:
                	WebDriverManager.firefoxdriver().setup();
                    driverThreadLocal.set(new FirefoxDriver());
                    break;
                default:
                    throw new Exception("Browser no permitido: " + browserName);

            }
            driverThreadLocal.get().manage().window().maximize();
            driverThreadLocal.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driverThreadLocal.get().get(url);
    }

    public static void closeSetup() {
        driverThreadLocal.get().quit();
    }

    public static WebDriver getDriver() {
        return driverThreadLocal.get();
    }
    
   
}

