package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page Object for product detail (shoe-detail.html).
 */
public class ProductPage extends BasePage {
    public static final By SHOE_DETAIL = By.id("shoe-detail");
    public static final By ADD_TO_CART_BTN = By.id("add-to-cart-btn");
    public static final By PRODUCT_NAME = By.cssSelector(".shoe-detail-info h1");
    public static final By PRODUCT_PRICE = By.cssSelector(".shoe-detail-price");
    public static final By PRODUCT_DESCRIPTION = By.cssSelector(".shoe-detail-desc");
    public static final By CART_COUNT = By.id("cart-count");
    public static final By BACK_LINK = By.cssSelector("a[href='home.html']");

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public void openProductDetail(String productId) {
        open("shoe-detail.html?id=" + productId);
    }

    public void clickAddToCart() {
        waitFor(ADD_TO_CART_BTN);
        driver.findElement(ADD_TO_CART_BTN).click();
    }

    public boolean isAddToCartDisplayed() {
        return isDisplayed(ADD_TO_CART_BTN);
    }

    public String getProductName() {
        return driver.findElement(PRODUCT_NAME).getText();
    }

    public String getProductPrice() {
        return driver.findElement(PRODUCT_PRICE).getText();
    }

    public String getCartBadgeCount() {
        return driver.findElement(CART_COUNT).getText();
    }

    public void clickBackToHome() {
        driver.findElement(BACK_LINK).click();
    }

    public boolean isProductDetailLoaded() {
        return isDisplayed(SHOE_DETAIL);
    }
}
