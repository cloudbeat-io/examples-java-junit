package com.saucedemo.tests;

import com.saucedemo.pages.LoginPage;
import com.saucedemo.utils.DriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

public class LoginTest {
    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeEach
    public void setUp() {
        driver = DriverManager.getDriver();
        loginPage = new LoginPage(driver);
    }

    @Test
    public void standardUserLoginBehaviour() {
        loginPage.open();
        loginPage.assertPageOpen();
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.pressLoginButton();
        loginPage.assertLoginSuccess();
    }

    @Test
    public void lockedOutUserLoginBehaviour() {
        loginPage.open();
        loginPage.assertPageOpen();
        loginPage.enterUsername("locked_out_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.pressLoginButton();
        loginPage.assertLoginErrorMessage("Epic sadface: Sorry, this user has been locked out.");
    }

    @Test
    public void invalidUserLoginBehaviour() {
        loginPage.open();
        loginPage.assertPageOpen();
        loginPage.enterUsername("invalid_user");
        loginPage.enterPassword("invalid_password");
        loginPage.pressLoginButton();
        loginPage.assertLoginErrorMessage("Epic sadface: Username and password do not match any user in this service");
    }

    @AfterEach
    public void tearDown() {
        DriverManager.quitDriver();
    }
}