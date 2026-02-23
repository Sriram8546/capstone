package com.example;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.example.base.BaseTest;
import com.example.pages.CartPage;
import com.example.pages.CheckoutPage;
import com.example.pages.LoginPage;
import com.example.pages.ProductPage;


public class OrderPlacementTests extends BaseTest {

    @BeforeMethod
    public void ensureLoggedInAndCartReady() {

        // signup + login
        String email = "order" + System.currentTimeMillis() + "@test.com";
        LoginPage login = new LoginPage(getDriver());
        login.openLoginPage();
        login.signUp("Order User", email, "password123");

        // add product to cart (IMPORTANT)
        ProductPage product = new ProductPage(getDriver());
        product.openProductDetail("1");
        product.clickAddToCart();

        // go to cart
        CartPage cart = new CartPage(getDriver());
        cart.openCartPage();
        cart.clickCheckout();
    }

    @Test(priority = 1)
    public void checkoutPageLoadsWithForm() {
        CheckoutPage checkout = new CheckoutPage(getDriver());
        Assert.assertTrue(checkout.isCheckoutPage());
    }

    @Test(priority = 2)
    public void orderTotalDisplayedOnCheckout() {
        CheckoutPage checkout = new CheckoutPage(getDriver());
        String total = checkout.getPaymentTotal();
        Assert.assertTrue(total.length() > 0);
    }

    @Test(priority = 3)
    public void billingFieldsAcceptInput() {
        CheckoutPage checkout = new CheckoutPage(getDriver());
        checkout.enterFullName("John Doe");
        checkout.enterAddress("Bangalore India");

        String name = getDriver().findElement(CheckoutPage.FULLNAME).getAttribute("value");
        Assert.assertEquals(name, "John Doe");
    }

    @Test(priority = 4)
    public void cardTabShowsCardFields() {
        CheckoutPage checkout = new CheckoutPage(getDriver());
        checkout.selectCard();
        Assert.assertTrue(getDriver().findElement(CheckoutPage.CARD_NUMBER).isDisplayed());
    }

    @Test(priority = 5)
    public void upiTabShowsUpiField() {
        CheckoutPage checkout = new CheckoutPage(getDriver());
        checkout.selectUpi();
        Assert.assertTrue(getDriver().findElement(CheckoutPage.UPI_ID).isDisplayed());
    }

    @Test(priority = 6)
    public void payNowButtonPresent() {
        Assert.assertTrue(getDriver().findElement(CheckoutPage.PAY_BTN).isDisplayed());
    }

    @Test(priority = 7)
    public void navigateFromCartToCheckout() {
        Assert.assertTrue(getDriver().getCurrentUrl().contains("payment") 
                       || getDriver().getCurrentUrl().contains("checkout"));
    }

    @Test(priority = 8)
    public void checkoutHasBillingSection() {
        Assert.assertTrue(getDriver().findElement(CheckoutPage.FULLNAME).isDisplayed());
        Assert.assertTrue(getDriver().findElement(CheckoutPage.ADDRESS).isDisplayed());
    }

    @Test(priority = 9)
    public void paymentMethodTabsClickable() {
        CheckoutPage checkout = new CheckoutPage(getDriver());
        checkout.selectUpi();
        checkout.selectCard();
        Assert.assertTrue(getDriver().findElement(CheckoutPage.CARD_NUMBER).isDisplayed());
    }

    @Test(priority = 10)
    public void checkoutPageTitleCorrect() {
        String title = getDriver().getTitle().toLowerCase();
        Assert.assertTrue(title.contains("checkout") || title.contains("payment") || title.contains("kicks"));
    }
}

