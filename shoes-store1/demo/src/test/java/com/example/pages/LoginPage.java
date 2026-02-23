package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Page Object for Login / Sign Up (index.html).
 */
public class LoginPage extends BasePage {
    public static final By AUTH_FORM = By.id("auth-form");
    public static final By TOGGLE_LOGIN = By.cssSelector(".toggle-btn[data-mode='login']");
    public static final By TOGGLE_SIGNUP = By.cssSelector(".toggle-btn[data-mode='signup']");
    public static final By EMAIL = By.id("email");
    public static final By PASSWORD = By.id("password");
    public static final By NAME_FIELD = By.id("name");
    public static final By NAME_INPUT = By.id("name");
    public static final By AUTH_SUBMIT = By.id("auth-submit");
    public static final By AUTH_ERROR = By.id("auth-error");
    public static final By NAME_FIELD_CONTAINER = By.id("name-field");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void openLoginPage() {
        open("index.html");
    }

    public void switchToSignUp() {
        driver.findElement(TOGGLE_SIGNUP).click();
    }

    public void switchToLogin() {
        driver.findElement(TOGGLE_LOGIN).click();
    }

    public void enterEmail(String email) {
        driver.findElement(EMAIL).clear();
        driver.findElement(EMAIL).sendKeys(email);
    }

    public void enterPassword(String password) {
        driver.findElement(PASSWORD).clear();
        driver.findElement(PASSWORD).sendKeys(password);
    }

    public void enterName(String name) {
        WebElement nameEl = driver.findElement(NAME_INPUT);
        nameEl.clear();
        nameEl.sendKeys(name);
    }

    public void clickSubmit() {
        driver.findElement(AUTH_SUBMIT).click();
    }

    public void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickSubmit();
    }

    public void signUp(String name, String email, String password) {
        switchToSignUp();
        enterName(name);
        enterEmail(email);
        enterPassword(password);
        clickSubmit();
    }

    public boolean isErrorDisplayed() {
        WebElement err = driver.findElement(AUTH_ERROR);
        return err.isDisplayed() && !err.getAttribute("class").contains("hidden");
    }

    public String getErrorText() {
        return driver.findElement(AUTH_ERROR).getText();
    }

    public boolean isOnLoginPage() {
        return driver.getCurrentUrl().contains("index.html") || driver.getTitle().toLowerCase().contains("login");
    }

    public boolean isNameFieldVisible() {
        WebElement nameField = driver.findElement(NAME_FIELD_CONTAINER);
        return nameField.isDisplayed() && !nameField.getAttribute("class").contains("hidden");
    }
}
