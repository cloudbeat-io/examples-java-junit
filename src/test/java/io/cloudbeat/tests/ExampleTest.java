package io.cloudbeat.tests;

import io.cloudbeat.common.annotation.CbLink;
import io.cloudbeat.common.annotation.CbXray;
import io.cloudbeat.junit.CbJunitExtension;
import io.cloudbeat.pages.LoginPage;
import org.junit.jupiter.api.*;

public class ExampleTest extends BaseTest {
    private LoginPage loginPage;

    @BeforeEach
    public void setUp() {
        loginPage = new LoginPage(driver);
    }

    @Test
    @DisplayName("Example of adding test output data/attributes")
    @CbLink("JIRA-PROJ-1234")
    @CbLink(value = "PROJ-555", source = CbLink.SRC_XRAY)
    public void OutputDataExample() {
        loginPage.open();
        loginPage.assertPageOpen();

        CbJunitExtension.addOutputData("foo", "bar data");
        CbJunitExtension.addTestAttribute("foo", "bar attribute");
    }

    @Test
    @Tag("hello")
    @Tag("ups")
    @CbLink("XRAY:PROJ-666")
    @CbXray("PROJ-777")
    @DisplayName("Example of getting environment variables from CB")
    public void EnvExample() {
        loginPage.open();
        loginPage.assertPageOpen();

        var val = CbJunitExtension.getEnv("TestParam");
        System.out.println("Environment variable TestParam: " + (val != null  ? val : "UNDEFINED"));
    }
}