package io.cloudbeat.utils;

import org.openqa.selenium.WebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.cloudbeat.junit.CbJunitExtension;

import java.util.Map;

public class DriverManager {
    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            WebDriverManager.chromedriver().clearDriverCache().clearResolutionCache().setup();
            // must be set before the session is created - CbJunitExtension.getCapabilities()
            // enables Chrome's "performance" log type (goog:loggingPrefs) when running under
            // CloudBeat, which getHarLog() depends on for HAR capture; wrapping the driver
            // afterward (as this used to do) is too late for that capability to take effect
            ChromeOptions options = new ChromeOptions();
            Map<String, Object> cbCapabilities = CbJunitExtension.getCapabilities();
            if (cbCapabilities != null)
                cbCapabilities.forEach(options::setCapability);
            var chromeDriver = new ChromeDriver(options);
            chromeDriver.manage().window().maximize();
            driver = CbJunitExtension.wrapWebDriver(chromeDriver);
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}