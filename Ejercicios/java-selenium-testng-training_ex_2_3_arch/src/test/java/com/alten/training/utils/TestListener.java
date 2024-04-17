package com.alten.training.utils;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.alten.training.pages.WebDriverFactory;
import com.alten.training.tests.BaseTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class TestListener extends BaseTest implements ITestListener {

	private static ExtentReports extent = ExtentManager.createInstance();
	private static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
	private static final Logger LOGGER = LogManager.getLogger(TestListener.class);
	
	private void attachScreenShoot() {
		String imagePath = null;
		try {
			imagePath = new ScreenShots().screenShot(WebDriverFactory.getDriver(),  ExtentManager.getReportPath() );
			test.get().addScreenCaptureFromPath(imagePath);
		} catch (IOException e) {
			LOGGER.error( e.getLocalizedMessage());
		}
	}


	public synchronized void onStart(ITestContext context) {
		LOGGER.info("Extent Reports Version 3 Test Suite started!");
	}

	public synchronized void onFinish(ITestContext context) {
		LOGGER.info((" Test Suite is ending!"));
		extent.flush();
	}

	public synchronized void onTestStart(ITestResult result) {
		LOGGER.info((result.getMethod().getMethodName() + " started!"));
		
		ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName(),
				result.getMethod().getDescription());
		test.set(extentTest);
	}

	public synchronized void onTestSuccess(ITestResult result) {
		LOGGER.info((result.getMethod().getMethodName() + " passed!"));
		attachScreenShoot();
		test.get().pass("Test passed");
	}

	public synchronized void onTestFailure(ITestResult result) {
		LOGGER.info((result.getMethod().getMethodName() + " failed!"));
		attachScreenShoot();
		test.get().fail(result.getThrowable());
	}

	public synchronized void onTestSkipped(ITestResult result) {
		LOGGER.info((result.getMethod().getMethodName() + " skipped!"));
		attachScreenShoot();
		test.get().skip(result.getThrowable());
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		LOGGER.info(("onTestFailedButWithinSuccessPercentage for " + result.getMethod().getMethodName()));
	}
}
