package com.saucedemo.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
// import io.cloudbeat.selenium.CbWebDriverListener;
// import io.cloudbeat.junit.CbJunitExtension;

public class DriverManager {
    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            // CbJunitExtension.startStep("Initialize Browser");
            WebDriverManager.chromedriver().clearDriverCache().clearResolutionCache().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            // CbJunitExtension.endLastStep();

            // CbWebDriverListener listener = CbJunitExtension.getWebDriverListener(driver);
            // WebDriver chromeDriver = new EventFiringDecorator(listener).decorate(driver);
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