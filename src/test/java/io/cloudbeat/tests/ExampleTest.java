package io.cloudbeat.tests;

import io.cloudbeat.junit.CbJunitExtension;
import io.cloudbeat.pages.LoginPage;
import io.cloudbeat.utils.DriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

public class ExampleTest extends BaseTest {
    private LoginPage loginPage;

    @BeforeEach
    public void setUp() {
        loginPage = new LoginPage(driver);
    }

    @Test
    @DisplayName("Example of adding test output data/attributes")
    public void OutputDataExample() {
        loginPage.open();
        loginPage.assertPageOpen();

        CbJunitExtension.addOutputData("foo", "bar data");
        CbJunitExtension.addTestAttribute("foo", "bar attribute");
    }

    @Test
    @DisplayName("Example of getting environment variables from CB")
    public void EnvExample() {
        loginPage.open();
        loginPage.assertPageOpen();

        var val = CbJunitExtension.getEnv("TestParam");
        System.out.println("Environment variable TestParam: " + (val != null  ? val : "UNDEFINED"));
    }
}