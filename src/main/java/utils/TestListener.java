package utils;

import com.epam.reportportal.message.ReportPortalMessage;
import driver.DriverSingleton;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;


public class TestListener implements ITestListener {

    protected Logger log = LogManager.getRootLogger();

    @Override
    public void onTestFailure(ITestResult result) {
        saveScreenshot();
    }

    //    private void saveScreenshot() {
//        File screenCapture = ((TakesScreenshot) DriverSingleton
//                .getDriver())
//                .getScreenshotAs(OutputType.FILE);
//        try {
//            FileUtils.copyFile(screenCapture, new File(
//                    ".//target/screenshots/"
//                            + getCurrentTimeAsString() +
//                            ".png"));
//        } catch (IOException e) {
//            log.error("Failed to save screenshot: " + e.getLocalizedMessage());
//        }
//    }

    private void saveScreenshot() {
        ReportPortalMessage message = null;
        try {
            TakesScreenshot ts = (TakesScreenshot) DriverSingleton.getDriver();
            File srcFile = ts.getScreenshotAs(OutputType.FILE);
            java.util.Date d = new java.util.Date();
            org.apache.commons.io.FileUtils.copyFile(srcFile, new File("./ScreenShots/" + d.toString().replace(":", "_") + ".png"));
            String rp_message = "test message for ReportPortal" + getCurrentTimeAsString();
            message = new ReportPortalMessage(srcFile, rp_message);
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info(message);
    }

    private String getCurrentTimeAsString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd_HH-mm-ss");
        return ZonedDateTime.now().format(formatter);
    }
}