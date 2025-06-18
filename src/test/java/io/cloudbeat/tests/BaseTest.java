package io.cloudbeat.tests;

import io.cloudbeat.junit.CbJunitExtension;
import io.cloudbeat.pages.LoginPage;
import io.cloudbeat.utils.DriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

@ExtendWith({ CbJunitExtension.class })
public abstract class BaseTest {
    protected WebDriver driver;

    @BeforeEach
    public void setUpDriver() {
        CbJunitExtension.step("Open Browser", () -> {
            driver = DriverManager.getDriver();
        });
    }

    @AfterEach
    public void tearDownDriver() {
        CbJunitExtension.step("Close Browser", () -> {
            DriverManager.quitDriver();
        });
    }
}
