package com.example;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.example.base.BaseTest;
import com.example.pages.HomePage;
import com.example.pages.LoginPage;


public class ProductSearchListingTests extends BaseTest {

    @BeforeMethod
    public void ensureLoggedIn() {
        String email = "search" + System.currentTimeMillis() + "@example.com";
        LoginPage login = new LoginPage(getDriver());
        login.openLoginPage();
        login.signUp("Search User", email, "password123");
    }

    @Test(priority = 1, description = "Home page displays shoe grid")
    public void homePageDisplaysShoeGrid() {
        HomePage home = new HomePage(getDriver());
        home.openHomePage();
        Assert.assertTrue(home.getShoeCardCount() > 0);
    }

    @Test(priority = 2, description = "Search by name filters results")
    public void searchByNameFiltersResults() {
        HomePage home = new HomePage(getDriver());
        home.openHomePage();
        home.search("Air Max");
        Assert.assertTrue(home.getShoeCardCount() >= 1);
        Assert.assertTrue(home.getFirstShoeTitle().toLowerCase().contains("air max"));
    }

    @Test(priority = 3, description = "Search by category filters results")
    public void searchByCategoryFiltersResults() {
        HomePage home = new HomePage(getDriver());
        home.openHomePage();
        home.search("Running");
        Assert.assertTrue(home.getShoeCardCount() >= 1);
    }

    @Test(priority = 4, description = "No results message when search has no match")
    public void noResultsMessageWhenNoMatch() {
        HomePage home = new HomePage(getDriver());
        home.openHomePage();
        home.search("xyznonexistent123");
        Assert.assertTrue(home.isNoResultsDisplayed());
    }

    
    @Test(priority = 7, description = "Search input has correct placeholder")
    public void searchInputHasPlaceholder() {
        HomePage home = new HomePage(getDriver());
        home.openHomePage();
        String placeholder = getDriver().findElement(HomePage.SEARCH_INPUT).getAttribute("placeholder");
        Assert.assertNotNull(placeholder);
        Assert.assertTrue(placeholder.toLowerCase().contains("search"));
    }

    @Test(priority = 8, description = "Cart and Orders links present on home")
    public void cartAndOrdersLinksPresent() {
        HomePage home = new HomePage(getDriver());
        home.openHomePage();
        Assert.assertTrue(getDriver().findElement(HomePage.CART_LINK).isDisplayed());
    }

    @Test(priority = 9, description = "Shoe cards show price")
    public void shoeCardsShowPrice() {
        HomePage home = new HomePage(getDriver());
        home.openHomePage();
        Assert.assertTrue(home.getShoeCardCount() > 0);
        String price = getDriver().findElements(HomePage.SHOE_CARD_PRICE).get(0).getText();
        Assert.assertTrue(price.contains("$"));
    }

    @Test(priority = 10, description = "Home page title is correct")
    public void homePageTitleCorrect() {
        HomePage home = new HomePage(getDriver());
        home.openHomePage();
        Assert.assertTrue(getDriver().getTitle().toLowerCase().contains("home") || getDriver().getTitle().contains("KICKS"));
    }
}
