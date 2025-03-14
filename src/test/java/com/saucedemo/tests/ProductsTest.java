package com.saucedemo.tests;

import com.saucedemo.pages.LoginPage;
import com.saucedemo.pages.ProductsPage;
import com.saucedemo.utils.DriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

public class ProductsTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private ProductsPage productsPage;

    @BeforeEach
    public void setUp() {
        driver = DriverManager.getDriver();
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
    }

    @Test
    public void addAndRemoveProductsFromCart() {
        loginPage.open();
        loginPage.assertPageOpen();
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.pressLoginButton();
        loginPage.assertLoginSuccess();
        productsPage.assertProductsCount(6);
        productsPage.clickAddToCartButton(0);
        productsPage.assertPriceBarButtonText(0, "Remove");
        productsPage.clickRemoveButton(0);
        productsPage.assertPriceBarButtonText(0, "Add to cart");
    }

    @AfterEach
    public void tearDown() {
        DriverManager.quitDriver();
    }
}