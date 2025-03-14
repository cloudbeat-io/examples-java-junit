package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ProductsPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final By addToCartButtons = By.xpath("//button[text()='Add to cart']");
    private final By removeButtons = By.xpath("//button[text()='Remove']");
    private final By priceBars = By.className("pricebar");

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public List<WebElement> getAddToCartButtons() {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(addToCartButtons));
    }

    public List<WebElement> getRemoveButtons() {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(removeButtons));
    }

    public List<WebElement> getPriceBars() {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(priceBars));
    }

    public void assertProductsCount(int expectedCount) {
        int actualCount = getAddToCartButtons().size();
        assert actualCount == expectedCount : "Expected " + expectedCount + " products, but found " + actualCount;
    }

    public void assertPriceBarButtonText(int priceBarIndex, String expectedText) {
        WebElement priceBar = getPriceBars().get(priceBarIndex);
        WebElement button = priceBar.findElement(By.tagName("button"));
        wait.until(ExpectedConditions.textToBePresentInElement(button, expectedText));
        assert button.getText().equals(expectedText) : "Expected button text: " + expectedText + ", but got: " + button.getText();
    }

    public void clickAddToCartButton(int buttonIndex) {
        getAddToCartButtons().get(buttonIndex).click();
    }

    public void clickRemoveButton(int buttonIndex) {
        getRemoveButtons().get(buttonIndex).click();
    }
}