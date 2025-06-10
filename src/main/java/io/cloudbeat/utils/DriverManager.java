package io.cloudbeat.utils;

import org.openqa.selenium.WebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import io.cloudbeat.junit.CbJunitExtension;

public class DriverManager {
    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            // CbJunitExtension.startStep("Initialize Browser");
            WebDriverManager.chromedriver().clearDriverCache().clearResolutionCache().setup();
            var chromeDriver = new ChromeDriver();
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