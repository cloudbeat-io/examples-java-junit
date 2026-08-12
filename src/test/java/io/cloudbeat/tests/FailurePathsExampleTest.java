package io.cloudbeat.tests;

import io.cloudbeat.junit.CbJunitExtension;
import io.cloudbeat.pages.LoginPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Deliberately-failing examples, each failing in a different way, to exercise
 * CloudBeat's failure reporting (type/location/stacktrace) across distinct paths:
 * an assertion directly in the test method, an assertion inside a page object
 * method, a Selenium wait timeout inside a page object method, and a plain
 * (non-assertion) exception thrown directly in the test method.
 */
public class FailurePathsExampleTest extends BaseTest {
    private LoginPage loginPage;

    @BeforeEach
    public void setUp() {
        loginPage = new LoginPage(driver);
    }

    @Test
    @DisplayName("Failure: assertion directly in the test method")
    public void failureDirectlyInTestMethod() {
        loginPage.open();
        loginPage.assertPageOpen();

        assertEquals("expected", "actual", "intentional failure directly in the test method");
    }

    @Test
    @DisplayName("Failure: assertion inside a page object method")
    public void failureInsidePageObjectAssertion() {
        CbJunitExtension.startStep("Open Main Page");
        loginPage.open();
        loginPage.assertPageOpen();
        CbJunitExtension.endLastStep();

        CbJunitExtension.startStep("Login With Locked Out User");
        loginPage.enterUsername("locked_out_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.pressLoginButton();
        // the real error banner does appear, but we intentionally assert the wrong text,
        // so the failure occurs inside LoginPage.assertLoginErrorMessage(), not here
        loginPage.assertLoginErrorMessage("This is not the message that will actually appear");
        CbJunitExtension.endLastStep();
    }

    @Test
    @DisplayName("Failure: Selenium wait timeout inside a page object method")
    public void seleniumTimeoutInsidePageObject() {
        CbJunitExtension.startStep("Open Main Page");
        loginPage.open();
        loginPage.assertPageOpen();
        CbJunitExtension.endLastStep();

        CbJunitExtension.startStep("Login With Valid User");
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.pressLoginButton();
        // login actually succeeds, so no error banner will ever appear - this times out
        // waiting inside LoginPage.assertLoginErrorMessage() rather than failing an assertion
        loginPage.assertLoginErrorMessage("This element will never appear");
        CbJunitExtension.endLastStep();
    }

    @Test
    @DisplayName("Failure: plain exception thrown directly in the test method")
    public void runtimeExceptionThrownDirectly() {
        loginPage.open();
        loginPage.assertPageOpen();

        throw new RuntimeException("intentional runtime exception, not an assertion failure");
    }
}
