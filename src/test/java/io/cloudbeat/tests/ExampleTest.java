package io.cloudbeat.tests;

import io.cloudbeat.junit.CbJunitExtension;
import io.cloudbeat.pages.LoginPage;
import io.cloudbeat.utils.DriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

@ExtendWith({CbJunitExtension.class}) 
public class ExampleTest {
    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeEach
    public void setUp() {
        driver = DriverManager.getDriver();
        loginPage = new LoginPage(driver);
    }

    @Test
    @DisplayName("Example of adding test output data/attributes")
    public void OutputDataExample() {
        CbJunitExtension.startStep("Open Main Page");
        loginPage.open();
        loginPage.assertPageOpen();
        CbJunitExtension.endLastStep();

        CbJunitExtension.addOutputData("foo", "bar data");
        CbJunitExtension.addTestAttribute("foo", "bar attribute");
    }

    @Test
    @DisplayName("Example of getting environment variables from CB")
    public void EnvExample() {
        CbJunitExtension.startStep("Open Main Page");
        loginPage.open();
        loginPage.assertPageOpen();
        CbJunitExtension.endLastStep();

        var val = CbJunitExtension.getEnv("TestParam");
        System.out.println("Environment variable TestParam: " + (val != null  ? val : "UNDEFINED"));
    }

    @AfterEach
    public void tearDown() {
        CbJunitExtension.startStep("Close Browser");
        DriverManager.quitDriver();
        CbJunitExtension.endLastStep();
    }
}