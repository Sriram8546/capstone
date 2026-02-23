package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Page Object for Checkout / Payment (payment.html).
 */
public class CheckoutPage extends BasePage {
    public static final By PAYMENT_FORM = By.id("payment-form");
    public static final By FULLNAME = By.id("fullname");
    public static final By ADDRESS = By.id("address");
    public static final By CARD_TAB = By.cssSelector(".payment-tab[data-method='card']");
    public static final By UPI_TAB = By.cssSelector(".payment-tab[data-method='upi']");
    public static final By CARD_NUMBER = By.id("cardnumber");
    public static final By EXPIRY = By.id("expiry");
    public static final By CVV = By.id("cvv");
    public static final By UPI_ID = By.id("upi-id");
    public static final By CARD_FIELDS = By.id("card-fields");
    public static final By UPI_FIELDS = By.id("upi-fields");
    public static final By PAY_BTN = By.id("pay-btn");
    public static final By PAYMENT_TOTAL = By.id("payment-total");
    public static final By PAYMENT_ERROR = By.id("payment-error");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void openCheckoutPage() {
        open("payment.html");
    }

    public void enterFullName(String name) {
        driver.findElement(FULLNAME).clear();
        driver.findElement(FULLNAME).sendKeys(name);
    }

    public void enterAddress(String address) {
        driver.findElement(ADDRESS).clear();
        driver.findElement(ADDRESS).sendKeys(address);
    }

    public void selectCard() {
        click(CARD_TAB);
    }

    public void selectUpi() {
        click(UPI_TAB);
    }

    public void enterCardNumber(String number) {
        driver.findElement(CARD_NUMBER).clear();
        driver.findElement(CARD_NUMBER).sendKeys(number);
    }

    public void enterExpiry(String expiry) {
        driver.findElement(EXPIRY).clear();
        driver.findElement(EXPIRY).sendKeys(expiry);
    }

    public void enterCvv(String cvv) {
        driver.findElement(CVV).clear();
        driver.findElement(CVV).sendKeys(cvv);
    }

    public void enterUpiId(String upiId) {
        driver.findElement(UPI_ID).clear();
        driver.findElement(UPI_ID).sendKeys(upiId);
    }

    public void clickPayNow() {
        click(PAY_BTN);
    }

    public String getPaymentTotal() {
        return driver.findElement(PAYMENT_TOTAL).getText();
    }

    public boolean isPaymentErrorDisplayed() {
        WebElement err = driver.findElement(PAYMENT_ERROR);
        return err.isDisplayed() && !err.getAttribute("class").contains("hidden");
    }

    public void fillBillingAndPayByCard(String name, String address, String cardNo, String expiry, String cvv) {
        enterFullName(name);
        enterAddress(address);
        selectCard();
        enterCardNumber(cardNo);
        enterExpiry(expiry);
        enterCvv(cvv);
        clickPayNow();
    }

    public void fillBillingAndPayByUpi(String name, String address, String upiId) {
        enterFullName(name);
        enterAddress(address);
        selectUpi();
        enterUpiId(upiId);
        clickPayNow();
    }

    public boolean isCheckoutPage() {
        return driver.getCurrentUrl().contains("payment.html") || driver.getTitle().toLowerCase().contains("checkout");
    }
}
