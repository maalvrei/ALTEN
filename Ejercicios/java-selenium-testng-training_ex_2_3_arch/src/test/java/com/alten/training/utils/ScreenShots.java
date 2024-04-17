package com.alten.training.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class ScreenShots {

    private static final String EXTENSION = ".png";

	public String screenShot(WebDriver browser, String reportPath) throws IOException {

        try{
            File scrfile = ((TakesScreenshot)browser).getScreenshotAs(OutputType.FILE);
            File DestinationFile = new File(reportPath+System.currentTimeMillis()+EXTENSION);
            String absolutePath = DestinationFile.getPath();
            FileUtils.copyFile(scrfile, DestinationFile);
            return absolutePath;
        }catch (IOException e){
            return "";
        }
    }
}
