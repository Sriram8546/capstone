package com.example;

import com.example.base.BaseTest;
import com.example.pages.CartPage;
import com.example.pages.HomePage;
import com.example.pages.LoginPage;
import com.example.pages.ProductPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Cart management module – 10 test cases.
 */
public class CartManagementTests extends BaseTest {

    @BeforeMethod
    public void ensureLoggedIn() {
        String email = "cart" + System.currentTimeMillis() + "@example.com";
        LoginPage login = new LoginPage(getDriver());
        login.openLoginPage();
        login.signUp("Cart User", email, "password123");
    }

    @Test(priority = 1, description = "Empty cart shows empty message")
    public void emptyCartShowsEmptyMessage() {
        CartPage cart = new CartPage(getDriver());
        cart.openCartPage();
        Assert.assertTrue(cart.isCartEmptyMessageDisplayed());
    }

    @Test(priority = 2, description = "Add to cart from product page increases cart count")
    public void addToCartIncreasesCount() {
        ProductPage product = new ProductPage(getDriver());
        product.openProductDetail("1");
        product.clickAddToCart();
        Assert.assertTrue(Integer.parseInt(product.getCartBadgeCount()) >= 1);
    }

    @Test(priority = 3, description = "Cart page shows added item after adding from product")
    public void cartShowsAddedItem() {
        ProductPage product = new ProductPage(getDriver());
        product.openProductDetail("1");
        product.clickAddToCart();
        CartPage cart = new CartPage(getDriver());
        cart.openCartPage();
        Assert.assertFalse(cart.isCartEmptyMessageDisplayed());
        Assert.assertTrue(cart.getVisibleCartItemCount() >= 1);
    }

    @Test(priority = 4, description = "Cart summary and checkout button visible when cart has items")
    public void cartSummaryVisibleWhenHasItems() {
        ProductPage product = new ProductPage(getDriver());
        product.openProductDetail("1");
        product.clickAddToCart();
        CartPage cart = new CartPage(getDriver());
        cart.openCartPage();
        Assert.assertTrue(cart.isCartSummaryDisplayed());
        Assert.assertTrue(cart.isCheckoutButtonDisplayed());
    }

    @Test(priority = 5, description = "Subtotal and grand total displayed")
    public void subtotalAndGrandTotalDisplayed() {
        ProductPage product = new ProductPage(getDriver());
        product.openProductDetail("1");
        product.clickAddToCart();
        CartPage cart = new CartPage(getDriver());
        cart.openCartPage();
        Assert.assertTrue(cart.getSubtotal().contains("$"));
        Assert.assertTrue(cart.getGrandTotal().contains("$"));
    }

    @Test(priority = 6, description = "Proceed to Checkout navigates to payment page")
    public void checkoutNavigatesToPayment() {
        ProductPage product = new ProductPage(getDriver());
        product.openProductDetail("1");
        product.clickAddToCart();
        CartPage cart = new CartPage(getDriver());
        cart.openCartPage();
        cart.clickCheckout();
        Assert.assertTrue(getDriver().getCurrentUrl().contains("payment.html"));
    }

    @Test(priority = 7, description = "Browse Shoes from empty cart goes to home")
    public void browseShoesFromEmptyCartGoesToHome() {
        CartPage cart = new CartPage(getDriver());
        cart.openCartPage();
        cart.clickBrowseShoes();
        Assert.assertTrue(getDriver().getCurrentUrl().contains("home.html"));
    }

    @Test(priority = 8, description = "Cart badge on nav shows count")
    public void cartBadgeShowsCount() {
        CartPage cart = new CartPage(getDriver());
        cart.openCartPage();
        String badge = cart.getCartBadgeCount();
        Assert.assertNotNull(badge);
    }

    @Test(priority = 9, description = "Multiple items in cart show correct count")
    public void multipleItemsShowCorrectCount() {
        ProductPage product = new ProductPage(getDriver());
        product.openProductDetail("1");
        product.clickAddToCart();
        product.openProductDetail("2");
        product.clickAddToCart();
        CartPage cart = new CartPage(getDriver());
        cart.openCartPage();
        Assert.assertTrue(cart.getVisibleCartItemCount() >= 2);
    }

    @Test(priority = 10, description = "Cart page title is correct")
    public void cartPageTitleCorrect() {
        CartPage cart = new CartPage(getDriver());
        cart.openCartPage();
        Assert.assertTrue(getDriver().getTitle().toLowerCase().contains("cart") || getDriver().getTitle().contains("KICKS"));
    }
}
