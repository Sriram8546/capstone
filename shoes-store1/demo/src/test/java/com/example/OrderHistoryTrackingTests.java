package com.example;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.example.base.BaseTest;
import com.example.pages.LoginPage;
import com.example.pages.OrdersPage;


public class OrderHistoryTrackingTests extends BaseTest {

    @BeforeMethod
    public void ensureLoggedIn() {
        String email = "history" + System.currentTimeMillis() + "@example.com";
        LoginPage login = new LoginPage(getDriver());
        login.openLoginPage();
        login.signUp("History User", email, "password123");
    }

    @Test(priority = 1, description = "Orders page loads successfully")
    public void ordersPageLoads() {
        OrdersPage orders = new OrdersPage(getDriver());
        orders.openOrdersPage();
        Assert.assertTrue(orders.isOrdersPage());
    }

    @Test(priority = 2, description = "New user sees empty orders message")
    public void newUserSeesEmptyOrders() {
        OrdersPage orders = new OrdersPage(getDriver());
        orders.openOrdersPage();
        Assert.assertTrue(orders.isOrdersEmptyDisplayed());
    }

    @Test(priority = 3, description = "Start Shopping link present when no orders")
    public void startShoppingLinkPresent() {
        OrdersPage orders = new OrdersPage(getDriver());
        orders.openOrdersPage();
        Assert.assertTrue(getDriver().findElement(OrdersPage.START_SHOPPING_LINK).isDisplayed());
    }

    @Test(priority = 4, description = "Start Shopping navigates to home")
    public void startShoppingNavigatesToHome() {
        OrdersPage orders = new OrdersPage(getDriver());
        orders.openOrdersPage();
        orders.clickStartShopping();
        Assert.assertTrue(getDriver().getCurrentUrl().contains("home.html"));
    }

    @Test(priority = 5, description = "Orders link in nav is present on orders page")
    public void ordersNavLinkPresent() {
        OrdersPage orders = new OrdersPage(getDriver());
        orders.openOrdersPage();
        Assert.assertTrue(getDriver().findElement(OrdersPage.NAV_ORDERS).isDisplayed());
    }

    @Test(priority = 6, description = "Orders page has correct title")
    public void ordersPageTitleCorrect() {
        OrdersPage orders = new OrdersPage(getDriver());
        orders.openOrdersPage();
        Assert.assertTrue(getDriver().getTitle().toLowerCase().contains("order") || getDriver().getTitle().contains("KICKS"));
    }

    @Test(priority = 7, description = "Orders list container exists")
    public void ordersListContainerExists() {
        OrdersPage orders = new OrdersPage(getDriver());
        orders.openOrdersPage();
        Assert.assertTrue(getDriver().findElement(OrdersPage.ORDERS_LIST).isDisplayed() ||
                getDriver().findElement(OrdersPage.ORDERS_EMPTY).isDisplayed());
    }

    @Test(priority = 8, description = "Order History heading present")
    public void orderHistoryHeadingPresent() {
        OrdersPage orders = new OrdersPage(getDriver());
        orders.openOrdersPage();
        String bodyText = getDriver().findElement(org.openqa.selenium.By.tagName("main")).getText();
        Assert.assertTrue(bodyText.toLowerCase().contains("order"));
    }

    @Test(priority = 9, description = "Empty state message is user-friendly")
    public void emptyStateMessageUserFriendly() {
        OrdersPage orders = new OrdersPage(getDriver());
        orders.openOrdersPage();
        String emptyText = getDriver().findElement(OrdersPage.ORDERS_EMPTY).getText();
        Assert.assertTrue(emptyText.length() > 0);
    }

    @Test(priority = 10, description = "Orders page accessible after login")
    public void ordersPageAccessibleAfterLogin() {
        OrdersPage orders = new OrdersPage(getDriver());
        orders.openOrdersPage();
        Assert.assertFalse(getDriver().getCurrentUrl().contains("index.html"));
        Assert.assertTrue(getDriver().getCurrentUrl().contains("orders.html"));
    }
}
