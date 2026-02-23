package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Page Object for Cart (cart.html).
 */
public class CartPage extends BasePage {
    public static final By CART_ITEMS = By.id("cart-items");
    public static final By CART_EMPTY = By.id("cart-empty");
    public static final By CART_SUMMARY = By.id("cart-summary");
    public static final By SUBTOTAL = By.id("subtotal");
    public static final By GRAND_TOTAL = By.id("grand-total");
    public static final By CHECKOUT_BTN = By.id("checkout-btn");
    public static final By CART_COUNT = By.id("cart-count");
    public static final By BROWSE_SHOES_LINK = By.cssSelector("#cart-empty .btn-primary");
    public static final By CART_ITEM_ROWS = By.cssSelector(".cart-item");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void openCartPage() {
        open("cart.html");
    }

    public boolean isCartEmptyMessageDisplayed() {
        WebElement empty = driver.findElement(CART_EMPTY);
        return empty.isDisplayed() && !empty.getAttribute("class").contains("hidden");
    }

    public boolean isCartSummaryDisplayed() {
        WebElement summary = driver.findElement(CART_SUMMARY);
        return summary.isDisplayed() && !summary.getAttribute("class").contains("hidden");
    }

    public String getSubtotal() {
        return driver.findElement(SUBTOTAL).getText();
    }

    public String getGrandTotal() {
        return driver.findElement(GRAND_TOTAL).getText();
    }

    public void clickCheckout() {
        click(CHECKOUT_BTN);
    }

    public String getCartBadgeCount() {
        return driver.findElement(CART_COUNT).getText();
    }

    public void clickBrowseShoes() {
        click(BROWSE_SHOES_LINK);
    }

    public boolean isCheckoutButtonDisplayed() {
        return isDisplayed(CHECKOUT_BTN);
    }

    public List<WebElement> getCartItemElements() {
        return driver.findElements(By.cssSelector("#cart-items .cart-item"));
    }

    public int getVisibleCartItemCount() {
        return getCartItemElements().size();
    }
}
