package io.cloudbeat.tests;

import io.cloudbeat.pages.LoginPage;
import io.cloudbeat.pages.ProductsPage;
import io.cloudbeat.utils.DriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import io.cloudbeat.junit.CbJunitExtension;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.IOException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class ProductsTest extends BaseTest {
    private LoginPage loginPage;
    private ProductsPage productsPage;

    public void takeScreenshot(String stepName) {
        CbJunitExtension.step("Take Screenshot", () -> {
            byte[] screenshotData = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            CbJunitExtension.attachScreenshot(screenshotData, true);

            try {
                Path screenshotDir = Path.of("screenshots");
                if (!Files.exists(screenshotDir)) {
                    Files.createDirectories(screenshotDir);
                }

                String timestamp = new SimpleDateFormat("d.M.yy~HH_mm_ss").format(new Date());
                Path screenshotPath = screenshotDir.resolve(stepName + "_" + timestamp + ".png");
                Files.write(screenshotPath, screenshotData);

                System.out.println("Screenshot saved to file: " + screenshotPath.toAbsolutePath());
            } catch (IOException e) {
                System.err.println("Failed to save screenshot: " + e.getMessage());
            }
        });
    }

    @BeforeEach
    public void setUp() {
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
    }

    @Test
    @DisplayName("Add And Remove Products From Cart")
    @Tag("Sanity")
    @Tag("General")
    public void addAndRemoveProductsFromCart() {
        CbJunitExtension.startStep("Open Main Page");
        loginPage.open();
        loginPage.assertPageOpen();
        CbJunitExtension.endLastStep();

        CbJunitExtension.startStep("Login");
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.pressLoginButton();
        loginPage.assertLoginSuccess();
        CbJunitExtension.endLastStep();

        CbJunitExtension.startStep("Add And Remove Products From Cart");
        productsPage.assertProductsCount(6);
        productsPage.clickAddToCartButton(0);
        productsPage.assertPriceBarButtonText(0, "Remove");
        productsPage.clickRemoveButton(0);
        productsPage.assertPriceBarButtonText(0, "Add to cart");
        CbJunitExtension.endLastStep();

        takeScreenshot("Add to cart");
    }

    @Test
    @Disabled("This test is disabled")
    @DisplayName("A test that shouldn't be executed")
    public void IgnoreMe() {
        CbJunitExtension.startStep("Open Main Page");
        loginPage.open();
        loginPage.assertPageOpen();
        CbJunitExtension.endLastStep();
    }
}