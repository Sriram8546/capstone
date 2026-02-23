package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Page Object for Home / product listing (home.html).
 */
public class HomePage extends BasePage {
    public static final By SEARCH_INPUT = By.id("search-input");
    public static final By SHOES_GRID = By.id("shoes-grid");
    public static final By SHOE_CARDS = By.cssSelector(".shoe-card");
    public static final By SHOE_CARD_TITLE = By.cssSelector(".shoe-card-title");
    public static final By SHOE_CARD_PRICE = By.cssSelector(".shoe-card-price");
    public static final By VIEW_DETAILS_LINKS = By.cssSelector(".shoe-card .btn-primary");
    public static final By NO_RESULTS = By.id("no-results");
    public static final By NAV_LOGO = By.className("nav-logo");
    public static final By CART_LINK = By.cssSelector("a[href='cart.html']");
    public static final By CART_COUNT = By.id("cart-count");
    public static final By LOGOUT_BTN = By.id("logout-btn");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void openHomePage() {
        open("home.html");
    }

    public void search(String query) {
        WebElement search = driver.findElement(SEARCH_INPUT);
        search.clear();
        search.sendKeys(query);
    }

    public int getShoeCardCount() {
        return driver.findElements(SHOE_CARDS).size();
    }

    public List<WebElement> getShoeCards() {
        return driver.findElements(SHOE_CARDS);
    }

    public void clickViewDetails(int index) {
        click(driver.findElements(VIEW_DETAILS_LINKS).get(index));
    }

    public String getFirstShoeTitle() {
        List<WebElement> titles = driver.findElements(SHOE_CARD_TITLE);
        return titles.isEmpty() ? "" : titles.get(0).getText();
    }

    public boolean isNoResultsDisplayed() {
        WebElement noRes = driver.findElement(NO_RESULTS);
        return noRes.isDisplayed() && !noRes.getAttribute("class").contains("hidden");
    }

    public String getCartBadgeCount() {
        return driver.findElement(CART_COUNT).getText();
    }

    public void clickCart() {
        click(CART_LINK);
    }

    public void clickLogout() {
        click(LOGOUT_BTN);
    }

    public boolean isHomePage() {
        return driver.getCurrentUrl().contains("home.html");
    }
}
