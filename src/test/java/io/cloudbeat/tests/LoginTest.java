package io.cloudbeat.tests;

import io.cloudbeat.pages.LoginPage;
import io.cloudbeat.utils.DriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import io.cloudbeat.junit.CbJunitExtension;

public class LoginTest extends BaseTest {
    private LoginPage loginPage;

    @BeforeEach
    public void setUp() {
        loginPage = new LoginPage(driver);
    }

    @Test
    @DisplayName("Standard User Login Behaviour")
    public void standardUserLoginBehaviour() {
        loginPage.open();
        loginPage.assertPageOpen();

        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.pressLoginButton();
        loginPage.assertLoginSuccess();
    }

    @Test
    @DisplayName("Locked Out User Login Behaviour")
    public void lockedOutUserLoginBehaviour() {
        CbJunitExtension.startStep("Open and Verify the Main Page");
        loginPage.open();
        loginPage.assertPageOpen();
        CbJunitExtension.endLastStep();

        CbJunitExtension.startStep("Login With Locked Out User");
        loginPage.enterUsername("locked_out_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.pressLoginButton();
        loginPage.assertLoginErrorMessage("Epic sadface: Sorry, this user has been locked out.");
        CbJunitExtension.endLastStep();
    }

    @Test
    @DisplayName("Invalid User Login Behaviour")
    public void invalidUserLoginBehaviour() {
        CbJunitExtension.step("Open Main Page", () -> {
            loginPage.open();
            loginPage.assertPageOpen();
        });

        CbJunitExtension.step("Login With Invalid User", () -> {
            loginPage.enterUsername("invalid_user");
            loginPage.enterPassword("invalid_password");
            loginPage.pressLoginButton();
            loginPage.assertLoginErrorMessage("Epic sadface: Username and password do not match any user in this service");
        });
    }
}