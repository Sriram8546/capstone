package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Page Object for Order History (orders.html).
 */
public class OrdersPage extends BasePage {
    public static final By ORDERS_LIST = By.id("orders-list");
    public static final By ORDERS_EMPTY = By.id("orders-empty");
    public static final By START_SHOPPING_LINK = By.cssSelector("#orders-empty .btn-primary");
    public static final By ORDER_ITEMS = By.cssSelector(".order-card, .order-item, [class*='order']");
    public static final By NAV_ORDERS = By.cssSelector("a[href='orders.html']");

    public OrdersPage(WebDriver driver) {
        super(driver);
    }

    public void openOrdersPage() {
        open("orders.html");
    }

    public boolean isOrdersEmptyDisplayed() {
        WebElement empty = driver.findElement(ORDERS_EMPTY);
        return empty.isDisplayed() && !empty.getAttribute("class").contains("hidden");
    }

    public void clickStartShopping() {
        click(START_SHOPPING_LINK);
    }

    public List<WebElement> getOrderElements() {
        return driver.findElements(ORDER_ITEMS);
    }

    public int getOrderCount() {
        return getOrderElements().size();
    }

    public boolean isOrdersPage() {
        return driver.getCurrentUrl().contains("orders.html") || driver.getTitle().toLowerCase().contains("order");
    }
}
